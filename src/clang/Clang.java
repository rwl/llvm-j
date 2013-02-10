package clang;

import com.sun.jna.Pointer;

import clang.binding.ClangLibrary;
import clang.binding.ClangLibrary.clang_executeOnThread_arg1_fn_callback;

public class Clang {

	/**
	 * @see clang.binding.ClangLibrary#clang_getClangVersion()
	 */
	public static String getClangVersion() {
		return ClangLibrary.clang_getClangVersion();
		// CXString.ByValue cxstr = ClangLibrary.clang_getClangVersion();
		// String str = getCString(cxstr);
		// disposeString(cxstr);
		// return str;
	}

	// /**
	// * Clang strings (CXString) must be disposed after use, so instead of exposing
	// * them to client code, and requiring client to track lifetime, I'm keeping
	// * the extractor and disposer functions package-private. Any APIs that use
	// * CXString will be published in terms of String instead.
	// *
	// * @see clang.ClangLibrary#clang_getCString(clang.ClangLibrary.CXString.ByValue)
	// */
	// static String getCString(CXString.ByValue string) {
	// return ClangLibrary.clang_getCString(string);
	// }
	//
	// /**
	// * Inform Clang to release the CXString object.
	// *
	// * @see clang.ClangLibrary#clang_disposeString(clang.ClangLibrary.CXString.ByValue)
	// */
	// static void disposeString(CXString.ByValue string) {
	// ClangLibrary.clang_disposeString(string);
	// }

	/**
	 * @see clang.binding.ClangLibrary#clang_enableStackTraces()
	 */
	public void enableStackTraces() {
		ClangLibrary.clang_enableStackTraces();
	}

}
