package clang;

import clang.binding.ClangLibrary;


public class SourceRange {
	ClangLibrary.CXSourceRange.ByValue range;
	
	SourceRange(ClangLibrary.CXSourceRange.ByValue range) {
		this.range = range;
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_getNullRange()
	 */
	public static SourceRange getNullRange() {
		ClangLibrary.CXSourceRange.ByValue range = ClangLibrary.clang_getNullRange();
		return new SourceRange(range);
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_getRange(clang.binding.ClangLibrary.CXSourceLocation.ByValue, clang.binding.ClangLibrary.CXSourceLocation.ByValue)
	 */
	public static SourceRange getRange(SourceLocation begin, SourceLocation end) {
		return new SourceRange(ClangLibrary.clang_getRange(begin.loc(), end.loc()));
	}

//	public  SourceLocation begin() { return begin; }
//	public  SourceLocation end() { return end; }
	
	/**
	 * @see clang.binding.ClangLibrary#clang_getRangeStart(clang.binding.ClangLibrary.CXSourceRange.ByValue)
	 */
	public SourceLocation getRangeStart() {
		return new SourceLocation(ClangLibrary.clang_getRangeStart(range));
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_getRangeEnd(clang.binding.ClangLibrary.CXSourceRange.ByValue)
	 */
	public SourceLocation getRangeEnd() {
		return new SourceLocation(ClangLibrary.clang_getRangeEnd(range));
	}


}
