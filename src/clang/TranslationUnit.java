package clang;

import clang.binding.ClangLibrary;

import com.sun.jna.Pointer;
import com.sun.jna.StringArray;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

public class TranslationUnit {
	
	private ClangLibrary.CXTranslationUnitImpl tu;
	ClangLibrary.CXTranslationUnitImpl tu() { return tu; }
	
	TranslationUnit(ClangLibrary.CXTranslationUnitImpl tu) {
		this.tu = tu;
	}
	
	public static TranslationUnit createFromSourceFile(Index idx, String filename) {
		return createFromSourceFile(idx, filename, 0, null, 0, null);
	}

	public static TranslationUnit createFromSourceFile(Index index,
			String filename, String[] args) {
		
		
		Pointer p1 = new StringArray(new String[]{filename});
		StringArray clargs = new StringArray(args);
		Pointer ptr = clargs.share(0);

		return createFromSourceFile(index, filename, args.length, ptr, 0, null);
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_createTranslationUnitFromSourceFile(com.sun.jna.Pointer, com.sun.jna.Pointer, int, com.sun.jna.ptr.PointerByReference, int, clang.binding.ClangLibrary.CXUnsavedFile)
	 */
	@Deprecated
	static TranslationUnit createFromSourceFile(
			Index cIdx, String source_filename,
			int num_clang_command_line_args,
			Pointer command_line_args, int num_unsaved_files,
			Pointer unsaved_files) {
		ClangLibrary.CXTranslationUnitImpl tu = ClangLibrary.clang_createTranslationUnitFromSourceFile(
				cIdx.cxindex(),
				source_filename,
				num_clang_command_line_args,
				command_line_args,
				num_unsaved_files,
				unsaved_files);
		return new TranslationUnit(tu);
	}

//	/**
//	 * @see clang.ClangLibrary#clang_createTranslationUnitFromSourceFile(com.sun.jna.Pointer, java.lang.String, int, java.lang.String[], int, clang.ClangLibrary.CXUnsavedFile)
//	 */
//	public static TranslationUnit createFromSourceFile(
//			Index cIdx, 
//			String source_filename,
//			String[] command_line_args,
//			int num_unsaved_files,
//			ClangLibrary.CXUnsavedFile unsaved_files) {
//		
//		Pointer p1 = new StringArray(new String[]{source_filename});
//		StringArray clargs = new StringArray(command_line_args);
//		Pointer ptr = clargs.share(0);
//
//		ClangLibrary.CXTranslationUnitImpl tu = ClangLibrary.clang_createTranslationUnitFromSourceFile(
//				cIdx.cxindex(),
//				p1,
//				command_line_args.length,
//				clargs,
//				num_unsaved_files,
//				unsaved_files);
//		return new TranslationUnit(tu);
//	}


	/**
	 * @see clang.binding.ClangLibrary#clang_createTranslationUnit(com.sun.jna.Pointer, com.sun.jna.Pointer)
	 */
	@Deprecated
	static TranslationUnit createTranslationUnit(Index index, Pointer ast_filename) {
		ClangLibrary.CXTranslationUnitImpl tu = ClangLibrary.clang_createTranslationUnit(index.cxindex(), ast_filename);
		return new TranslationUnit(tu);
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_createTranslationUnit(com.sun.jna.Pointer, java.lang.String)
	 */
	public static TranslationUnit createTranslationUnit(Index index, String ast_filename) {
		ClangLibrary.CXTranslationUnitImpl tu = ClangLibrary.clang_createTranslationUnit(index.cxindex(), ast_filename);
		return new TranslationUnit(tu);
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_defaultEditingTranslationUnitOptions()
	 */
	int defaultEditingOptions() {
		int opts = ClangLibrary.clang_defaultEditingTranslationUnitOptions();
		// TODO enumerate options
		return opts;
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_parseTranslationUnit(com.sun.jna.Pointer, com.sun.jna.Pointer, com.sun.jna.ptr.PointerByReference, int, clang.binding.ClangLibrary.CXUnsavedFile, int, int)
	 */
	@Deprecated
	static TranslationUnit parseTranslationUnit(Index cIdx,
			Pointer source_filename, PointerByReference command_line_args,
			int num_command_line_args, ClangLibrary.CXUnsavedFile unsaved_files,
			int num_unsaved_files, int options) {
		ClangLibrary.CXTranslationUnitImpl tu = ClangLibrary.clang_parseTranslationUnit(
				cIdx.cxindex(), 
				source_filename, 
				command_line_args, 
				num_command_line_args,
				unsaved_files, 
				num_unsaved_files,
				options);
		return new TranslationUnit(tu);
	}

//	/**
//	 * @see clang.ClangLibrary#clang_parseTranslationUnit(com.sun.jna.Pointer, java.lang.String, java.lang.String[], int, clang.ClangLibrary.CXUnsavedFile, int, int)
//	 */
//	public static TranslationUnit parseTranslationUnit(
//			Index cIdx,
//			String source_filename, 
//			String[] command_line_args,
//			ClangLibrary.CXUnsavedFile unsaved_files,
//			int num_unsaved_files,
//			int options) {
//		ClangLibrary.CXTranslationUnitImpl tu = ClangLibrary.clang_parseTranslationUnit(
//				cIdx.cxindex(), 
//				source_filename, 
//				command_line_args, 
//				command_line_args.length,
//				unsaved_files, 
//				num_unsaved_files, 
//				options);
//		return new TranslationUnit(tu);
//	}


	/**
	 * @see clang.binding.ClangLibrary#clang_defaultSaveOptions(clang.binding.ClangLibrary.CXTranslationUnitImpl)
	 */
	int defaultSaveOptions() {
		int opts = ClangLibrary.clang_defaultSaveOptions(tu);
		// TODO enumerate
		return opts;
	}

	/**
	 * @return true if error
	 * 
	 * @see clang.binding.ClangLibrary#clang_saveTranslationUnit(clang.binding.ClangLibrary.CXTranslationUnitImpl, com.sun.jna.Pointer, int)
	 */
	@Deprecated
	boolean save(Pointer FileName, int options) {
		int failed = ClangLibrary.clang_saveTranslationUnit(tu, FileName, options);
		// TODO exception on failure
		return failed != 0;
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_saveTranslationUnit(clang.binding.ClangLibrary.CXTranslationUnitImpl, java.lang.String, int)
	 */
	boolean save(String FileName, int options) {
		int failed = ClangLibrary.clang_saveTranslationUnit(tu, FileName, options);
		// TODO exception on failure
		return failed != 0;
	}

	
	/**
	 * @see clang.binding.ClangLibrary#clang_disposeTranslationUnit(clang.binding.ClangLibrary.CXTranslationUnitImpl)
	 */
	public synchronized void dispose() {
		if (tu != null) {
			ClangLibrary.clang_disposeTranslationUnit(tu);
			tu = null;			
		}
	}

	protected void finalize() { 
		dispose();
	}

	
	/**
	 * @see clang.binding.ClangLibrary#clang_defaultReparseOptions(clang.binding.ClangLibrary.CXTranslationUnitImpl)
	 */
	int defaultReparseOptions() {
		int opts = ClangLibrary.clang_defaultReparseOptions(tu);
		// TODO enumerate options
		return opts;
	}

	/**
	 * @return true on failure
	 * 
	 * @see clang.binding.ClangLibrary#clang_reparseTranslationUnit(clang.binding.ClangLibrary.CXTranslationUnitImpl, int, clang.binding.ClangLibrary.CXUnsavedFile, int)
	 */
	public boolean reparseTranslationUnit(
			int num_unsaved_files, 
			ClangLibrary.CXUnsavedFile unsaved_files,
			int options) {
		int failed = ClangLibrary.clang_reparseTranslationUnit(tu, num_unsaved_files, unsaved_files, options);
		// TODO exception on failure
		return failed != 0;
	}

	
//	/**
//	 * @see clang.ClangLibrary#clang_getFile(clang.ClangLibrary.CXTranslationUnitImpl, com.sun.jna.Pointer)
//	 */
//	@Deprecated
//	File getFile(Pointer file_name) {
//		Pointer cxfile = ClangLibrary.clang_getFile(tu, file_name);
//		return new File(cxfile);
//	}

	/**
	 * @see clang.binding.ClangLibrary#clang_getFile(clang.binding.ClangLibrary.CXTranslationUnitImpl, java.lang.String)
	 */
	public File getFile(String file_name) {
		Pointer cxfile = ClangLibrary.clang_getFile(tu(), file_name);
		return new File(cxfile);
	}
	
	/**
	 * @see clang.binding.ClangLibrary#clang_getLocation(clang.binding.ClangLibrary.CXTranslationUnitImpl, com.sun.jna.Pointer, int, int)
	 */
	public SourceLocation getLocation(Pointer file, int line, int column) {
		ClangLibrary.CXSourceLocation.ByValue loc = ClangLibrary.clang_getLocation(tu(), file, line, column);
		return new SourceLocation(loc);
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_getLocationForOffset(clang.binding.ClangLibrary.CXTranslationUnitImpl, com.sun.jna.Pointer, int)
	 */
	public SourceLocation getLocationForOffset(Pointer file, int offset) {
		ClangLibrary.CXSourceLocation.ByValue loc = ClangLibrary.clang_getLocationForOffset(tu(), file, offset);
		return new SourceLocation(loc);
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_getNumDiagnostics(clang.binding.ClangLibrary.CXTranslationUnitImpl)
	 */
	public int getNumDiagnostics() {
		return ClangLibrary.clang_getNumDiagnostics(tu());
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_getDiagnostic(clang.binding.ClangLibrary.CXTranslationUnitImpl, int)
	 */
	public Diagnostic getDiagnostic(int Index) {
		//return new Diagnostic(tu, Index); // let diag know its owner?
		return new Diagnostic(ClangLibrary.clang_getDiagnostic(tu(), Index));
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_disposeDiagnostic(com.sun.jna.Pointer)
	 */
	public void disposeDiagnostic(Diagnostic diagnostic) {
		diagnostic.dispose();
	}
	
	
	/**
	 * @see clang.TranslationUnit#getTranslationUnitSpelling()
	 */
	public String name() { return getTranslationUnitSpelling(); }
	
	/**
	 * @see clang.binding.ClangLibrary#clang_getTranslationUnitSpelling(clang.binding.ClangLibrary.CXTranslationUnitImpl)
	 */
	public String getTranslationUnitSpelling() {
		String str = ClangLibrary.clang_getTranslationUnitSpelling(tu());
//		String str = Leftovers.getCString(cxstr);
//		Leftovers.disposeString(cxstr);
		return str;
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_getTranslationUnitCursor(clang.binding.ClangLibrary.CXTranslationUnitImpl)
	 */
	public Cursor getCursor() {
		return new Cursor(ClangLibrary.clang_getTranslationUnitCursor(tu));
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_getCursor(clang.binding.ClangLibrary.CXTranslationUnitImpl, clang.binding.ClangLibrary.CXSourceLocation.ByValue)
	 */
	public Cursor getCursor(SourceLocation sourceLoc) {
		return new Cursor(ClangLibrary.clang_getCursor(tu(), sourceLoc.loc()));
	}


	public interface InclusionVisitor extends ClangLibrary.CXInclusionVisitor {}
	/**
	 * @see clang.binding.ClangLibrary#clang_getInclusions(clang.binding.ClangLibrary.CXTranslationUnitImpl, clang.binding.ClangLibrary.CXInclusionVisitor, com.sun.jna.Pointer)
	 */
	public void getInclusions(InclusionVisitor visitor, Pointer client_data) {
		ClangLibrary.clang_getInclusions(tu(), visitor, client_data);
	}

	
	
	///////////////////////////////////////////////////////////////////////
	// Token

	/**
	 * @see clang.binding.ClangLibrary#clang_getTokenKind(clang.binding.ClangLibrary.CXToken.ByValue)
	 */
	int getTokenKind(clang.binding.ClangLibrary.CXToken.ByValue cxtok) {
		return ClangLibrary.clang_getTokenKind(cxtok);
	}
	/**
	 * \brief A token that contains some kind of punctuation.<br>
	 * <i>native declaration : clang-c\Index.h:1630</i>
	 */
	public boolean isTokenPunctuation(ClangLibrary.CXToken.ByValue cxtok) {
		return getTokenKind(cxtok) == ClangLibrary.CXTokenKind.CXToken_Punctuation;
	}
	/**
	 * \brief A language keyword.<br>
	 * <i>native declaration : clang-c\Index.h:1632</i>
	 */
	public boolean isTokenKeyword(ClangLibrary.CXToken.ByValue cxtok) {
		return getTokenKind(cxtok) == ClangLibrary.CXTokenKind.CXToken_Keyword;
	}
	/**
	 * \brief An identifier (that is not a keyword).<br>
	 * <i>native declaration : clang-c\Index.h:1634</i>
	 */
	public boolean isTokenIdentifier(ClangLibrary.CXToken.ByValue cxtok) {
		return getTokenKind(cxtok) == ClangLibrary.CXTokenKind.CXToken_Identifier;
	}
	/**
	 * \brief A numeric, string, or character literal.<br>
	 * <i>native declaration : clang-c\Index.h:1636</i>
	 */
	public boolean isTokenLiteral(ClangLibrary.CXToken.ByValue cxtok) {
		return getTokenKind(cxtok) == ClangLibrary.CXTokenKind.CXToken_Literal;
	}
	/**
	 * \brief A comment.<br>
	 * <i>native declaration : clang-c\Index.h:1638</i>
	 */
	public boolean isTokenComment(ClangLibrary.CXToken.ByValue cxtok) {
		return getTokenKind(cxtok) == ClangLibrary.CXTokenKind.CXToken_Comment;
	}
	
	
	/**
	 * @see clang.binding.ClangLibrary#clang_getTokenSpelling(clang.binding.ClangLibrary.CXTranslationUnitImpl, clang.binding.ClangLibrary.CXToken.ByValue)
	 */
	public String getTokenSpelling(clang.binding.ClangLibrary.CXToken.ByValue cxtok) {
		String str = ClangLibrary.clang_getTokenSpelling(tu, cxtok);
		return str;
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_getTokenLocation(clang.binding.ClangLibrary.CXTranslationUnitImpl, clang.binding.ClangLibrary.CXToken.ByValue)
	 */
	public SourceLocation getTokenLocation(clang.binding.ClangLibrary.CXToken.ByValue cxtok) {
		return new SourceLocation(ClangLibrary.clang_getTokenLocation(tu,  cxtok));
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_getTokenExtent(clang.binding.ClangLibrary.CXTranslationUnitImpl, clang.binding.ClangLibrary.CXToken.ByValue)
	 */
	public SourceRange getTokenExtent(clang.binding.ClangLibrary.CXToken.ByValue cxtok) {
		return new SourceRange(ClangLibrary.clang_getTokenExtent(tu,  cxtok));
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_tokenize(clang.binding.ClangLibrary.CXTranslationUnitImpl, clang.binding.ClangLibrary.CXSourceRange.ByValue, com.sun.jna.ptr.PointerByReference, com.sun.jna.ptr.IntByReference)
	 */
	@Deprecated
	public void tokenize(
			SourceRange Range,
			PointerByReference Tokens,
			IntByReference NumTokens) {
		ClangLibrary.clang_tokenize(tu,  Range.range, Tokens, NumTokens);
	}

//	/**
//	 * @see clang.ClangLibrary#clang_tokenize(clang.ClangLibrary.CXTranslationUnitImpl, clang.ClangLibrary.CXSourceRange.ByValue, clang.ClangLibrary.CXToken.ByReference[], java.nio.IntBuffer)
//	 */
//	public void tokenize(
//			SourceRange Range,
//			clang.ClangLibrary.CXToken.ByReference[] Tokens,
//			IntBuffer NumTokens) {
//		ClangLibrary.clang_tokenize(tu,  Range.range, Tokens, NumTokens);
//	}
//
//	/**
//	 * @see clang.ClangLibrary#clang_tokenize(clang.ClangLibrary.CXTranslationUnitImpl, clang.ClangLibrary.CXSourceRange.ByValue, clang.ClangLibrary.CXToken.ByReference[], com.sun.jna.ptr.IntByReference)
//	 */
//	public void tokenize(
//			SourceRange Range,
//			clang.ClangLibrary.CXToken.ByReference[] Tokens,
//			IntByReference NumTokens) {
//		// TODO token class?
//		ClangLibrary.clang_tokenize(tu,  Range.range, Tokens, NumTokens);
//	}

	/**
	 * @see clang.binding.ClangLibrary#clang_annotateTokens(clang.binding.ClangLibrary.CXTranslationUnitImpl, clang.binding.ClangLibrary.CXToken, int, clang.binding.ClangLibrary.CXCursor)
	 */
	public void annotateTokens(
			ClangLibrary.CXToken Tokens,
			int NumTokens,
			ClangLibrary.CXCursor Cursors) {
		ClangLibrary.clang_annotateTokens(tu, Tokens, NumTokens, Cursors);
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_disposeTokens(clang.binding.ClangLibrary.CXTranslationUnitImpl, clang.binding.ClangLibrary.CXToken, int)
	 */
	public void disposeTokens(ClangLibrary.CXToken Tokens, int NumTokens) {
		// TODO fix/don't forget
		ClangLibrary.clang_disposeTokens(tu, Tokens, NumTokens);
	}
	
}
