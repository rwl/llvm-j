package org.llvm;

import org.bridj.Pointer;

import static org.llvm.binding.LLVMLibrary.*;


public class ExecutionEngine {

	private LLVMExecutionEngineRef engine;
	/*package*/ LLVMExecutionEngineRef engine() { return engine; }

	ExecutionEngine(LLVMExecutionEngineRef engine) { this.engine = engine; }

	public void finalise() { dispose(); }
	public void dispose() { LLVMDisposeExecutionEngine(engine); }


	public static ExecutionEngine createForModule(Module M) {
		Pointer<Pointer<Byte>> OutError = Pointer.allocateBytes(1,1024);

		Pointer<LLVMExecutionEngineRef> pEE = Pointer.allocateTypedPointer(LLVMExecutionEngineRef.class);

		boolean err = LLVMCreateExecutionEngineForModule(pEE, M.module(), OutError) != 0;
		if (err) {
			String msg = OutError.get().getCString();
			throw new RuntimeException("can't create execution engine: " + msg);
		}

		return new ExecutionEngine(pEE.get());
	}

//	public static native int LLVMCreateInterpreterForModule(Pointer<Pointer<LLVMOpaqueExecutionEngine > > OutInterp, LLVMModuleRef M, Pointer<Pointer<Byte > > OutError);
//	public static native int LLVMCreateJITCompilerForModule(Pointer<Pointer<LLVMOpaqueExecutionEngine > > OutJIT, LLVMModuleRef M, int OptLevel, Pointer<Pointer<Byte > > OutError);

//	public static native int LLVMCreateExecutionEngine(Pointer<Pointer<LLVMOpaqueExecutionEngine > > OutEE, LLVMModuleProviderRef MP, Pointer<Pointer<Byte > > OutError);
//	public static native int LLVMCreateInterpreter(Pointer<Pointer<LLVMOpaqueExecutionEngine > > OutInterp, LLVMModuleProviderRef MP, Pointer<Pointer<Byte > > OutError);
//	public static native int LLVMCreateJITCompiler(Pointer<Pointer<LLVMOpaqueExecutionEngine > > OutJIT, LLVMModuleProviderRef MP, int OptLevel, Pointer<Pointer<Byte > > OutError);


	public void RunStaticConstructors() { LLVMRunStaticConstructors(engine); }

	public void runStaticDestructors() { LLVMRunStaticDestructors(engine); }

	public boolean RunFunctionAsMain(Value F, int ArgC, Pointer<Pointer<Byte>> ArgV, Pointer<Pointer<Byte > > EnvP) {
		return LLVMRunFunctionAsMain(engine, F.value(), ArgC, ArgV, EnvP) != 0;
	}

	public GenericValue RunFunction(Value F, GenericValue... Args) {
			//Pointer<Pointer<LLVMOpaqueGenericValue > > Args) {
		int N = Args.length;
		LLVMGenericValueRef[] inner = new LLVMGenericValueRef[N];
		for (int i=0; i<N; i++) { inner[i] = Args[i].ref(); } //.value(); }

		Pointer<LLVMGenericValueRef> array =
				Pointer.allocateTypedPointers(LLVMGenericValueRef.class, N);
		array.setArray(inner);

		return new GenericValue(LLVMRunFunction(engine, F.value(), N, array));
	}

	public void FreeMachineCodeForFunction(Value F) { LLVMFreeMachineCodeForFunction(engine, F.value()); }

	public void AddModule(Module M) { LLVMAddModule(engine, M.module()); }

	public void AddModuleProvider(LLVMModuleProviderRef MP) { LLVMAddModuleProvider(engine, MP); }

	public Module RemoveModule(Module M) {
		Pointer<Pointer<Byte>> OutError = Pointer.allocateBytes(1,1024);
		Pointer<LLVMModuleRef> OutMod = Pointer.allocateTypedPointer(LLVMModuleRef.class);
		boolean err = LLVMRemoveModule(engine, M.module(), OutMod, OutError) != 0;
		if (err) {
			String msg = OutError.get().getCString();
			throw new RuntimeException("can't remove module: " + msg);
		}
		return new Module(OutMod.get());
	}

	// TODO: make sure of this
	public Module RemoveModuleProvider(LLVMModuleProviderRef MP) {
		Pointer<Pointer<Byte>> OutError = Pointer.allocateBytes(1,1024);
		Pointer<LLVMModuleRef> OutMod = Pointer.allocateTypedPointer(LLVMModuleRef.class);
		boolean err = LLVMRemoveModuleProvider(engine, MP, OutMod, OutError) != 0;
		if (err) {
			String msg = OutError.get().getCString();
			throw new RuntimeException("can't remove module provider: " + msg);
		}
		return new Module(OutMod.get());
	}

	public Value FindFunction(String Name) { // Pointer<Pointer<LLVMOpaqueValue > > OutFn) {
		Pointer<Byte> cstr = Pointer.pointerToCString(Name);
		Pointer<LLVMValueRef> OutFn = Pointer.allocateTypedPointer(LLVMValueRef.class);
		boolean err = LLVMFindFunction(engine, cstr, OutFn) != 0;
		if (err) throw new RuntimeException("LLVMFindFunction can't find " + Name);
		return new Value(OutFn.get());
	}

	// TODO: this probably is returning a ValueRef for the recompiled Fn
	public Pointer<? > RecompileAndRelinkFunction(Value Fn) { return LLVMRecompileAndRelinkFunction(engine, Fn.value()); }

	public LLVMTargetDataRef GetExecutionEngineTargetData() { return LLVMGetExecutionEngineTargetData(engine); }

	public void AddGlobalMapping(Value Global, Pointer<? > Addr) { LLVMAddGlobalMapping(engine, Global.value(), Addr); }

	public Pointer<? > GetPointerToGlobal(Value Global) { return LLVMGetPointerToGlobal(engine, Global.value()); }

}
