package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import clang.Cursor;
import clang.Index;
import clang.TranslationUnit;

import com.sun.jna.Pointer;

/**
 * testing... uses JTree to throw up a browser for a translation unit
 * parsed by clang
 *
 * @author Kevin
 *
 */
public class Browser implements TreeModel {

	public static void echo(String s) { System.out.println(s); }

	static { System.setProperty("jna.library.path", "C:/LLVM/llvm-3.0/bin/"); }

	HashMap<Cursor, Cursor> c2p = new HashMap<Cursor, Cursor>();
	HashMap<Cursor, ArrayList<Cursor>> p2c = new HashMap<Cursor, ArrayList<Cursor>>();

	void record(Cursor parent, Cursor child) {
		if (parent == null || child == null) return;
		c2p.put(child, parent);
		ArrayList<Cursor> children;
		if (!p2c.containsKey(parent)) {
			children = new ArrayList<Cursor>();
			p2c.put(parent, children);
		} else {
			children = p2c.get(parent);
		}
		children.add(child);
	}

	/**
	 * record cursors in this translation unit, ignoring ones that
	 * don't originate in this file.
	 * Note this is probably a bad idea -- cursors stop being
	 * valid when the TU is disposed, so shouldn't be holding on to them.
	 * @param tu
	 * @param file
	 */
	void record(TranslationUnit tu, final String file) {
		Cursor cursor = tu.getCursor();
		cursor.visitChildren(cursor.new Visitor() {
			@Override
			public int apply(Cursor child, Cursor parent, Pointer client_data) {
				String curFile = child.getLocation().file();
				if (!file.equalsIgnoreCase(curFile)) {
					return Continue;  // skip, not in same file
				}
				record(parent, child);
				return Recurse;
			}
		}, null);
	}

	ArrayList<Cursor> children(Cursor c) { return p2c.get(c); }

	Cursor parent(Cursor c) { return c2p.get(c); }


	private JFrame frame;

    private Vector<TreeModelListener> treeModelListeners =
        new Vector<TreeModelListener>();

    private TranslationUnit tu;
    private Index idx;
    private Cursor rootCursor;
	String file;
	String[] args;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		// should pass on args
		final String filename = "/usr/include/clang-c/Index.h";
		final String[] clangargs = {
//				"-IC:/MinGW/include",
//				"-IC:/MinGW/lib/gcc/mingw32/4.6.1/include",
//				"-IC:/LLVM/llvm-3.0/include"
		};

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Browser window = new Browser(filename, clangargs);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Browser(String filename, String[] args) {
		initTranslationUnit(filename, args);
		initialize();
	}

	/**
	 * create a TranslationUnit by parsing a sourcefile;
	 * also iterate its cursors to collect parent/child maps
	 *
	 * @param filename to parse
	 * @param extraargs like extra include directories, whatever clang takes
	 */
	private void initTranslationUnit(String filename, String[] extraargs) {

		file = filename;
		args = extraargs;

		idx = Index.create();
		tu = idx.parse(file, args);
		if (tu == null) throw new RuntimeException("fail: no TranslationUnit from 'Index.parse()'");

		rootCursor = tu.getCursor();
		record(tu, file);

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 580, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);

		JMenuItem mntmOpen = new JMenuItem("Open");
		mnNewMenu.add(mntmOpen);

		JTree tree = new JTree();
		frame.getContentPane().add(new JScrollPane(tree), BorderLayout.CENTER);
		tree.setModel(this);
	}

	protected void finalize() { dispose(); }
	public void dispose() {
		if (tu != null) { tu.dispose(); tu = null; }
		if (idx != null) { idx.dispose(); idx = null; }
		rootCursor = null;
	}

	///////////////////////////////////////////////////////////////////////
	// following is a simple TreeModel impl

    /**
     * Adds a listener for the TreeModelEvent posted after the tree changes.
     */
    public void addTreeModelListener(TreeModelListener l) {
        treeModelListeners.addElement(l);
    }

    /**
     * Returns the child of parent at index index in the parent's child array.
     */
    public Object getChild(Object parent, int index) {
        Cursor p = (Cursor)parent;
        ArrayList<Cursor> children = children(p);
        if (children == null) return null;
        return children.get(index);
    }

    /**
     * Returns the number of children of parent.
     */
    public int getChildCount(Object parent) {
        Cursor p = (Cursor)parent;
        ArrayList<Cursor> children = children(p);
        if (children == null) return 0;
       return children.size();
    }

    /**
     * Returns the index of child in parent.
     */
    public int getIndexOfChild(Object parent, Object child) {
        Cursor p = (Cursor)parent;
        ArrayList<Cursor> children = children(p);
        if (children == null) throw new RuntimeException("parent "+parent+" has no children");
        return children.indexOf(child);
    }

    /**
     * Returns the root of the tree.
     */
    public Object getRoot() {
        return rootCursor;
    }

    /**
     * Returns true if node is a leaf.
     */
    public boolean isLeaf(Object node) {
        Cursor p = (Cursor)node;
        ArrayList<Cursor> children = children(p);
        return children == null || children.size() == 0;
    }

    /**
     * Removes a listener previously added with addTreeModelListener().
     */
    public void removeTreeModelListener(TreeModelListener l) {
        treeModelListeners.removeElement(l);
    }

    /**
     * Messaged when the user has altered the value for the item
     * identified by path to newValue.  Not used by this model.
     */
    public void valueForPathChanged(TreePath path, Object newValue) {
        echo("*** valueForPathChanged : " + path + " --> " + newValue);
    }


}
