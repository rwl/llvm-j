package main;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

import llvm.Context;
import llvm.ExecutionEngine;
import llvm.Module;
import llvm.PassManager;

import org.bridj.BridJ;
import org.bridj.demangling.Demangler.Symbol;

import clang.Cursor;
import clang.Index;
import clang.TranslationUnit;
import clang.Clang;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import com.sun.jna.Platform;

public class Main {
	public static void echo(String s) {
		System.out.println(s);
	}

	static {
		System.setProperty("jna.library.path", "C:/LLVM/llvm-3.0/bin/");
	}

	// This is the standard, stable way of mapping, which supports extensive
	// customization and mapping of Java to native types.
	public interface CLibrary extends Library {
		CLibrary INSTANCE = (CLibrary) Native.loadLibrary(
				(Platform.isWindows() ? "msvcrt" : "c"), CLibrary.class);
		void printf(String format, Object... args);
	}

	public interface ClangJNA extends Library {
		ClangJNA INSTANCE = (ClangJNA) Native.loadLibrary("clang.dll", ClangJNA.class);
		// I think this is actually kind of wrong, taking advantage of the fact
		// that the CXString that's returned has void* data as first element.
		String clang_getClangVersion();
	}

	public static void main(String[] args) {
		//CLibrary.INSTANCE.printf("Hello, libC!\n");

		//NativeLibrary lib = NativeLibrary.getInstance("clang");
		String ver = ClangJNA.INSTANCE.clang_getClangVersion();
		System.out.println(ver);

		{
			Symbol[] symbols = getLLVMSymbols();
			echo(" LLVM lib has " + symbols.length + " symbols.");
			for (Symbol sym: getLLVMSymbols()) {
				if (sym.getName().startsWith("LLVM")) {
					//echo(sym.getName());
				}
			}			
		}
		{
			Symbol[] clangsym = getClangSymbols();
			echo(" clang lib has " + clangsym.length + " symbols.");
			for (Symbol sym: getClangSymbols()) {
				//echo(sym.getName());
			}			
		}

		//checkLLVM();

		checkClang();
	}

	public static Symbol[] getLLVMSymbols() {		
		try {
			org.bridj.NativeLibrary lib = BridJ.getNativeLibrary("LLVM-3.0");
			Collection<Symbol> symbols = lib.getSymbols();
			Symbol[] array = symbols.toArray(new Symbol[0]);
			Arrays.sort(array, new Comparator<Symbol>() {
				@Override
				public int compare(Symbol a, Symbol b) {
					if (a == null) {
						if (b == null) return 0;
						return 1;
					} else if (b == null) return -1;
					return a.getName().compareTo(b.getName());
				}				
			});
			return array;
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static Symbol[] getClangSymbols() {		
		try {
			org.bridj.NativeLibrary lib = BridJ.getNativeLibrary("clang");
			Collection<Symbol> symbols = lib.getSymbols();
			Symbol[] array = symbols.toArray(new Symbol[0]);
			Arrays.sort(array, new Comparator<Symbol>() {
				@Override
				public int compare(Symbol a, Symbol b) {
					if (a == null) {
						if (b == null) return 0;
						return 1;
					} else if (b == null) return -1;
					return a.getName().compareTo(b.getName());
				}				
			});
			return array;
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void checkLLVM() {
		//LLVMLibrary.LLVMInitializeNativeTarget();
		
		Context ctx = Context.getGlobalContext();
		echo("got global context "+ctx);
		
		Module module = Module.CreateWithNameInContext("test module", ctx);
		echo("created module "+module);
		
		ExecutionEngine execengine = ExecutionEngine.createForModule(module);
		echo("created execution engine "+execengine);
		
		//LLVMLibrary.LLVMPassManagerRef fpm = LLVMLibrary.LLVMCreateFunctionPassManagerForModule(module);
		PassManager fpm = PassManager.createForModule(module);
		echo("created FPM "+fpm);

		//LLVMLibrary.TargetData?
		//try out some various optzns...
		fpm.AddInstructionCombiningPass();
		fpm.AddReassociatePass();
		fpm.AddGVNPass();
		fpm.AddCFGSimplificationPass();
		
		fpm.Initialize();
		echo("added some passes to FPM " + fpm);
		
		// sposed to initializeFPM after adding passes like in tut?
		//
		// looks like should be: fpm.create, init, run, finalize, dispose (cf. approp LLVMPassManagerRef-taking fns)
		
		//LLVMLibrary.LLVMPassRegistryRef passreg = LLVMLibrary.LLVMGetGlobalPassRegistry();
		//LLVMLibrary.LLVMInitializeCore(passreg);
				
	}

	public static void checkClang() {
		try {
			System.out.println(Clang.getClangVersion());

			String sourceFilename = "C:/LLVM/llvm-3.0/include/clang-c/Index.h"; // "test.c";
			String args[] = {    
					// "-Xclang", "-include-pch=Index.pch",		 
					"-Ic:/MinGW/include",
					"-Ic:/MinGW/lib/gcc/mingw32/4.6.1/include",
					"-Ic:/LLVM/llvm-3.0/include"
			};
			Index idx = Index.create();
			TranslationUnit tu = idx.parse(sourceFilename, args);
			if (tu == null) throw new RuntimeException("fail: no TranslationUnit from 'Index.parse'");
			echo(tu.name());
			
			Cursor cursor = tu.getCursor();

			cursor.visitChildren(cursor.new Visitor() {
				@Override
				public int apply(Cursor cursor, Cursor parent,
						com.sun.jna.Pointer client_data) {
					return Recurse;
				}
			}, null);

			Cursor.Visitor visitor = cursor.new Visitor() {
				@Override
				public int apply(Cursor cursor, Cursor parent,
						com.sun.jna.Pointer client_data) 
				{					
					//int depth = cursor.lexicalDepth();
					if (cursor.isDeclaration()) {
						//echo("\n\n"+cursor + " - " + parent);
						//final String spacer = ".........................................................";
						//echo("\n"+spacer);
						//String prefix = spacer.substring(0, depth);
						echo( parent.getCursorKindSpelling()
								+ "=" + parent.toString());
						echo( cursor.getCursorKindSpelling() + "="
								+ cursor.toString());
					}
					return Continue;
					//return (depth > 10) ? Continue : Recurse; // Continue; //Recurse;
				}
			};
			cursor.visitChildren(visitor, null);

			tu.dispose();
			idx.dispose();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
