package org.llvm.test;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;

import org.bridj.BridJ;
import org.bridj.NativeLibrary;
import org.bridj.demangling.Demangler.Symbol;
import org.llvm.Context;
import org.llvm.ExecutionEngine;
import org.llvm.Module;
import org.llvm.PassManager;

public class TestLLVM extends TestCase {

	private static final double LLVM_VERSION = 3.1;

	public void testLLVMSymbols() {
		NativeLibrary lib = null;
		try {
			lib = BridJ.getNativeLibrary("LLVM-" + LLVM_VERSION);
		} catch (IOException e) {
            fail(e.getMessage());
        }

		Collection<Symbol> symbols = lib.getSymbols();
		Set<Symbol> llvmSymbols = new HashSet<Symbol>(symbols.size());

		for (Symbol sym : symbols) {
			if (sym.getName().startsWith("LLVM")) {
				llvmSymbols.add(sym);
			}
		}

		assertTrue(llvmSymbols.size() > 10);
	}

	public static void checkLLVM() {
		Context ctx = Context.getGlobalContext();
		assertNotNull(ctx);

		Module module = Module.CreateWithNameInContext("test_module", ctx);
		assertNotNull(module);

		ExecutionEngine execEngine = ExecutionEngine.createForModule(module);
		assertNotNull(execEngine);

		PassManager fpm = PassManager.createForModule(module);
		assertNotNull(fpm);
	}

}
