package clang;

import java.util.Date;

import clang.binding.ClangLibrary;

import com.sun.jna.Pointer;

/**
 * wraps a CXFile -- opaque handle to clang's file rep.
 * 
 * Creation is through TranslationUnit.getFile(String filename)
 * 
 * @author Kevin
 *
 */
public class File {
	
	private Pointer cxfile;
	Pointer cxfile() { return cxfile; }
	
	File(Pointer ptr) {
		this.cxfile = ptr;
	}
	
	
	public String filepath() {
		String str = ClangLibrary.clang_getFileName(cxfile);
// this stuff not needed, apparently -- JNA seems to jump right thru CXString type to its void *data
//		ByValue cxstr = ClangLibrary.clang_getFileName(cxfile);
//		String str = Leftovers.getCString(cxstr);
//		Leftovers.disposeString(cxstr);
		return str;
	}
	
	// TODO
	public Date filetime() {
		ClangLibrary.time_t cxtime = ClangLibrary.clang_getFileTime(cxfile());
		//Date time = cxtime.asDate();
		//return time;
		return null;
	}

}
