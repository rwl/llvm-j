/**
 * 
 */
package clang;

import clang.binding.ClangLibrary;
import clang.binding.ClangLibrary.CXCursorSetImpl;
import clang.binding.ClangLibrary.CXString;
import clang.binding.ClangLibrary.CXTranslationUnitImpl;
import clang.binding.ClangLibrary.clang_executeOnThread_arg1_fn_callback;

import com.sun.jna.Pointer;

/**
 * functions from native binding that haven't found a home/been wrapped.
 *
 * @author Kevin
 */
public class Leftovers {
	
	//private static ClangLibrary lib = ClangLibrary.INSTANCE;

	/**
	 * Static facade class; no constructor
	 */
	private Leftovers() {}

	
	/**
	 * @see clang.binding.ClangLibrary#clang_executeOnThread(clang.ClangLibrary.clang_executeOnThread_callback,
	 *      com.sun.jna.Pointer, int)
	 */
	public void executeOnThread(clang_executeOnThread_arg1_fn_callback arg1,
			Pointer user_data, int stack_size) {
		ClangLibrary.clang_executeOnThread(arg1, user_data, stack_size);
	}
	
	///////////////////////////////////////////////////////////////////////
	// CursorSet
	
	/**
	 * @see clang.binding.ClangLibrary#clang_createCXCursorSet()
	 */
	public CXCursorSetImpl createCXCursorSet() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_disposeCXCursorSet(clang.ClangLibrary.CXCursorSet)
	 */
	public void disposeCXCursorSet(CXCursorSetImpl cset) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see clang.binding.ClangLibrary#clang_CXCursorSet_contains(clang.ClangLibrary.CXCursorSet, clang.binding.ClangLibrary.CXCursor.ByValue)
	 */
	public int CXCursorSet_contains(CXCursorSetImpl cset,
			clang.binding.ClangLibrary.CXCursor.ByValue cursor) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_CXCursorSet_insert(clang.ClangLibrary.CXCursorSet, clang.binding.ClangLibrary.CXCursor.ByValue)
	 */
	public int CXCursorSet_insert(CXCursorSetImpl cset,
			clang.binding.ClangLibrary.CXCursor.ByValue cursor) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	///////////////////////////////////////////////////////////////////////
	// ObjC

	/**
	 * @see clang.binding.ClangLibrary#clang_constructUSR_ObjCClass(com.sun.jna.Pointer)
	 */
	public CXString.ByValue constructUSR_ObjCClass(Pointer class_name) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_constructUSR_ObjCClass(java.lang.String)
	 */
	public CXString.ByValue constructUSR_ObjCClass(String class_name) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_constructUSR_ObjCCategory(com.sun.jna.Pointer, com.sun.jna.Pointer)
	 */
	public CXString.ByValue constructUSR_ObjCCategory(Pointer class_name,
			Pointer category_name) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_constructUSR_ObjCCategory(java.lang.String, java.lang.String)
	 */
	public CXString.ByValue constructUSR_ObjCCategory(String class_name,
			String category_name) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_constructUSR_ObjCProtocol(com.sun.jna.Pointer)
	 */
	public CXString.ByValue constructUSR_ObjCProtocol(Pointer protocol_name) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_constructUSR_ObjCProtocol(java.lang.String)
	 */
	public CXString.ByValue constructUSR_ObjCProtocol(String protocol_name) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_constructUSR_ObjCIvar(com.sun.jna.Pointer, clang.binding.ClangLibrary.CXString.ByValue)
	 */
	public CXString.ByValue constructUSR_ObjCIvar(Pointer name, CXString.ByValue classUSR) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_constructUSR_ObjCIvar(java.lang.String, clang.binding.ClangLibrary.CXString.ByValue)
	 */
	public CXString.ByValue constructUSR_ObjCIvar(String name, CXString.ByValue classUSR) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_constructUSR_ObjCMethod(com.sun.jna.Pointer, int, clang.binding.ClangLibrary.CXString.ByValue)
	 */
	public CXString.ByValue constructUSR_ObjCMethod(Pointer name,
			int isInstanceMethod, CXString.ByValue classUSR) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_constructUSR_ObjCMethod(java.lang.String, int, clang.binding.ClangLibrary.CXString.ByValue)
	 */
	public CXString.ByValue constructUSR_ObjCMethod(String name,
			int isInstanceMethod, CXString.ByValue classUSR) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_constructUSR_ObjCProperty(com.sun.jna.Pointer, clang.binding.ClangLibrary.CXString.ByValue)
	 */
	public CXString.ByValue constructUSR_ObjCProperty(Pointer property,
			CXString.ByValue classUSR) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_constructUSR_ObjCProperty(java.lang.String, clang.binding.ClangLibrary.CXString.ByValue)
	 */
	public CXString.ByValue constructUSR_ObjCProperty(String property,
			CXString.ByValue classUSR) {
		// TODO Auto-generated method stub
		return null;
	}

	
		
	///////////////////////////////////////////////////////////////////////
	// Completion

	/**
	 * @see clang.binding.ClangLibrary#clang_getCompletionChunkKind(com.sun.jna.Pointer, int)
	 */
	public int getCompletionChunkKind(Pointer completion_string,
			int chunk_number) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_getCompletionChunkText(com.sun.jna.Pointer, int)
	 */
	public CXString.ByValue getCompletionChunkText(Pointer completion_string,
			int chunk_number) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_getCompletionChunkCompletionString(com.sun.jna.Pointer, int)
	 */
	public Pointer getCompletionChunkCompletionString(
			Pointer completion_string, int chunk_number) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_getNumCompletionChunks(com.sun.jna.Pointer)
	 */
	public int getNumCompletionChunks(Pointer completion_string) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_getCompletionPriority(com.sun.jna.Pointer)
	 */
	public int getCompletionPriority(Pointer completion_string) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_getCompletionAvailability(com.sun.jna.Pointer)
	 */
	public int getCompletionAvailability(Pointer completion_string) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	/**
	 * @see clang.binding.ClangLibrary#clang_defaultCodeCompleteOptions()
	 */
	public int defaultCodeCompleteOptions() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_codeCompleteAt(clang.ClangLibrary.CXTranslationUnit, com.sun.jna.Pointer, int, int, clang.binding.ClangLibrary.CXUnsavedFile, int, int)
	 */
	public ClangLibrary.CXCodeCompleteResults codeCompleteAt(CXTranslationUnitImpl TU,
			Pointer complete_filename, int complete_line, int complete_column,
			ClangLibrary.CXUnsavedFile unsaved_files, int num_unsaved_files, int options) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_codeCompleteAt(clang.ClangLibrary.CXTranslationUnit, java.lang.String, int, int, clang.binding.ClangLibrary.CXUnsavedFile, int, int)
	 */
	public ClangLibrary.CXCodeCompleteResults codeCompleteAt(CXTranslationUnitImpl TU,
			String complete_filename, int complete_line, int complete_column,
			ClangLibrary.CXUnsavedFile unsaved_files, int num_unsaved_files, int options) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_sortCodeCompletionResults(clang.binding.ClangLibrary.CXCompletionResult, int)
	 */
	public void sortCodeCompletionResults(ClangLibrary.CXCompletionResult Results,
			int NumResults) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see clang.binding.ClangLibrary#clang_disposeCodeCompleteResults(clang.binding.ClangLibrary.CXCodeCompleteResults)
	 */
	public void disposeCodeCompleteResults(ClangLibrary.CXCodeCompleteResults Results) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see clang.binding.ClangLibrary#clang_codeCompleteGetNumDiagnostics(clang.binding.ClangLibrary.CXCodeCompleteResults)
	 */
	public int codeCompleteGetNumDiagnostics(ClangLibrary.CXCodeCompleteResults Results) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_codeCompleteGetDiagnostic(clang.binding.ClangLibrary.CXCodeCompleteResults, int)
	 */
	public Pointer codeCompleteGetDiagnostic(
			ClangLibrary.CXCodeCompleteResults Results, int Index) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String sourceFilename = "test.c";
		Index idx = Index.create(true, true); // excl. pch decl; show diags
		//TU = clang_createTranslationUnitFromSourceFile(Idx, "IndexTest.c", 2, args, 0, 0);
		TranslationUnit tu = TranslationUnit.createFromSourceFile(idx, sourceFilename, 0, null, 0, null);
		 
		Cursor c = tu.getCursor();
		//cursor.visitChildren(Visitor, null);
		
		//  clang_visitChildren(clang_getTranslationUnitCursor(TU), TranslationUnitVisitor, 0);
		
		//tu.dispose(); 		
	}

}
