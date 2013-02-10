package clang;
import clang.binding.ClangLibrary;

import com.sun.jna.Pointer;

public class Index {
	public static void echo(String s) { System.out.println(s); }
	
	Pointer cxindex;
	Pointer cxindex() { return this.cxindex; }

	private Index(Pointer idx) { this.cxindex = idx; }
	
	
	/**
	 * @see clang.binding.ClangLibrary#clang_createIndex(int, int)
	 */
	public static Index create() {
		boolean excludeDeclarationsFromPCH = false;
		boolean displayDiagnostics = true;
		return create(excludeDeclarationsFromPCH, displayDiagnostics);
	}
	
	/**
	 * @see clang.binding.ClangLibrary#clang_createIndex(int, int)
	 */
	public static Index create(boolean excludeDeclarationsFromPCH, boolean displayDiagnostics) {
		Pointer idx = ClangLibrary.clang_createIndex(
				excludeDeclarationsFromPCH ? 1 : 0, 
				displayDiagnostics ? 1 : 0);
		return new Index(idx);
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_disposeIndex(com.sun.jna.Pointer)
	 */
	public synchronized void dispose() {
		if (cxindex() != null)
			ClangLibrary.clang_disposeIndex(cxindex());
		cxindex = null;
	}
	
	protected void finalize() {
		dispose();
	}

	
	public TranslationUnit parse(String sourceFilename) {
		TranslationUnit tu = TranslationUnit.createFromSourceFile(this, sourceFilename);
		return tu;
	}

	public TranslationUnit parse(String sourceFilename, String[] args) {
		TranslationUnit tu = TranslationUnit.createFromSourceFile(this, sourceFilename, args);
		return tu;
	}

}
