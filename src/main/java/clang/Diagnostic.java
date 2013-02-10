package clang;

import clang.binding.ClangLibrary;

import com.sun.jna.Pointer;

public class Diagnostic {
	private Pointer cxdiag;
	
	Diagnostic(Pointer diag) { 
		this.cxdiag = diag;
	}
	
	/**
	 * @see clang.binding.ClangLibrary#clang_disposeDiagnostic(com.sun.jna.Pointer)
	 */
	public void dispose() {
		if (cxdiag != null)
			ClangLibrary.clang_disposeDiagnostic(cxdiag);
		cxdiag = null;
	}
	
	protected void finalize() {
		dispose();
	}
	
	/**
	 * @see clang.binding.ClangLibrary#clang_defaultDiagnosticDisplayOptions()
	 */
	public int defaultDiagnosticDisplayOptions() {
		return ClangLibrary.clang_defaultDiagnosticDisplayOptions();
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_getDiagnosticSeverity(com.sun.jna.Pointer)
	 */
	public int getSeverity() {
		return ClangLibrary.clang_getDiagnosticSeverity(cxdiag);
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_getDiagnosticLocation(com.sun.jna.Pointer)
	 */
	public SourceLocation getLocation() {
		return new SourceLocation(ClangLibrary.clang_getDiagnosticLocation(cxdiag));
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_getDiagnosticSpelling(com.sun.jna.Pointer)
	 */
	public String getSpelling() {
		String str = ClangLibrary.clang_getDiagnosticSpelling(cxdiag);
		return str;
	}
	
	
	
	

	/**
	 * @see clang.binding.ClangLibrary#clang_getDiagnosticOption(com.sun.jna.Pointer, clang.binding.ClangLibrary.CXString)
	 */
	public String getOption(ClangLibrary.CXString Disable) {
		// TODO fix the disable
		String str = ClangLibrary.clang_getDiagnosticOption(cxdiag, Disable);
		return str;
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_getDiagnosticCategory(com.sun.jna.Pointer)
	 */
	public int getDiagnosticCategory() {
		// TODO enumerated categories instead of int
		return ClangLibrary.clang_getDiagnosticCategory(cxdiag);
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_getDiagnosticCategoryName(int)
	 */
	public String getDiagnosticCategoryName(int Category) {
		String str = ClangLibrary.clang_getDiagnosticCategoryName(Category);
		return str;
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_getDiagnosticNumRanges(com.sun.jna.Pointer)
	 */
	public int getDiagnosticNumRanges() {
		return ClangLibrary.clang_getDiagnosticNumRanges(cxdiag);
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_getDiagnosticRange(com.sun.jna.Pointer, int)
	 */
	public SourceRange getDiagnosticRange(int Range) {
		return new SourceRange(ClangLibrary.clang_getDiagnosticRange(cxdiag, Range));
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_getDiagnosticNumFixIts(com.sun.jna.Pointer)
	 */
	public int getDiagnosticNumFixIts() {
		return ClangLibrary.clang_getDiagnosticNumFixIts(cxdiag);
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_getDiagnosticFixIt(com.sun.jna.Pointer, int, clang.binding.ClangLibrary.CXSourceRange)
	 */
	public String getDiagnosticFixIt(int FixIt, SourceRange ReplacementRange) {	
		String str = 
			ClangLibrary.clang_getDiagnosticFixIt(cxdiag, FixIt, ReplacementRange.range);
		return str;
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_formatDiagnostic(com.sun.jna.Pointer, int)
	 */
	public String formatDiagnostic(int Options) {
		String str = ClangLibrary.clang_formatDiagnostic(cxdiag, Options);
		return str;
	}


}
