package org.llvm;

import static org.llvm.binding.LLVMLibrary.*;


public class Context {

	private LLVMContextRef context;
	/*package*/ LLVMContextRef context() { return context; }
	
	
	/*package*/ Context(LLVMContextRef context) { this.context = context; }
	
	public static Context create() { return new Context(LLVMContextCreate()); }
	
	public static Context getGlobalContext() { return new Context(LLVMGetGlobalContext()); }
	
	public static Context getModuleContext(Module M) { return new Context(LLVMGetModuleContext(M.module())); }
	
	public void finalize() { dispose(); }
	
	public void dispose() { if (context != null) LLVMContextDispose(context); context = null; }

}
