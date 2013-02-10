package clang;

import java.nio.IntBuffer;

import clang.binding.ClangLibrary;

import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

import static clang.binding.ClangLibrary.*;

public class SourceLocation implements Comparable<SourceLocation> {
	static void echo(String s) { System.out.println(s); }
	
	private CXSourceLocation.ByValue loc;
	CXSourceLocation.ByValue loc() { return loc; }
	
	// lazy fill, using "spelling" location, only if file/line/col/offset are called.
	private PointerByReference cxfile;
	private IntByReference line;
	private IntByReference col;
	private IntByReference offset;

	Pointer cxfile() { if (cxfile == null) getSpellingLocation(); return cxfile.getValue(); }
	
	public String file() { return clang_getFileName(cxfile()); }

	public int    line() { if (line == null) getSpellingLocation(); return line.getValue(); }

	public int     col() { if (col == null) getSpellingLocation(); return col.getValue(); }

	public int  offset() { if (offset == null) getSpellingLocation(); return offset.getValue(); }
	

	SourceLocation(CXSourceLocation.ByValue loc) {
		this.loc = loc;
	}
	
	/**
	 * @see clang.binding.ClangLibrary#clang_getNullLocation()
	 */
	public static SourceLocation getNullLocation() {
		CXSourceLocation.ByValue loc = ClangLibrary.clang_getNullLocation();
		return new SourceLocation(loc);
	}
	

	public void getSpellingLocation() {
		if (cxfile == null) cxfile = new PointerByReference();
		if (line   == null) line   = new IntByReference();
		if (col    == null) col    = new IntByReference();
		if (offset == null) offset = new IntByReference();
		clang_getSpellingLocation(loc, cxfile, line, col, offset);
	}
		
	public void getInstantiationLocation() {
		if (cxfile == null) cxfile = new PointerByReference();
		if (line   == null) line   = new IntByReference();
		if (col    == null) col    = new IntByReference();
		if (offset == null) offset = new IntByReference();
		clang_getInstantiationLocation(loc, cxfile, line, col, offset);
	}
		
	
	@Override
	public String toString() {
		return file() + ":" + line() + ":" + col();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((loc == null) ? 0 : loc.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (!(obj instanceof SourceLocation)) return false;
		SourceLocation other = (SourceLocation) obj;
		if (loc == null) {
			if (other.loc != null)
				return false;
		} else if (!this.equalLocations(other))
			return false;
		return true;
	}
	
	
	/**
	 * \determine Determine whether two source locations, which must refer into<br>
	 * the same translation unit, refer to exactly the same point in the source<br>
	 * code.<br>
	 * * \returns non-zero if the source locations refer to the same location, zero<br>
	 * if they refer to different locations.<br>
	 * Original signature : <code>clang_equalLocations(CXSourceLocation, CXSourceLocation)</code><br>
	 * <i>native declaration : clang-c\Index.h:155</i>
	 *
	 * @see clang.binding.ClangLibrary#clang_equalLocations(clang.binding.ClangLibrary.CXSourceLocation.ByValue, clang.binding.ClangLibrary.CXSourceLocation.ByValue)
	 */
	boolean equalLocations(SourceLocation other) { 
		return ClangLibrary.clang_equalLocations(loc(), other.loc()) != 0;
	}

	
	@Override
	public int compareTo(SourceLocation other) {
		if (other == null) return 1;
		String myfile = file(), otherfile = other.file();
		if (myfile == null) return (otherfile == null) ? 0 : -1;
		if (otherfile == null) return 1;
		if (!myfile.equals(otherfile)) return myfile.compareTo(otherfile);
		return (offset() - other.offset());
	}
	
}
