package org.llvm;

import org.bridj.Pointer;

import static org.llvm.binding.LLVMLibrary.*;

/**
 * Implements various analyses of the LLVM IR.
 */
public class ExecutionEngine {

    private LLVMExecutionEngineRef engine;

    LLVMExecutionEngineRef engine() {
        return engine;
    }

    ExecutionEngine(LLVMExecutionEngineRef engine) {
        this.engine = engine;
    }

    public void finalise() {
        dispose();
    }

    public void dispose() {
        LLVMDisposeExecutionEngine(engine);
    }

    public static ExecutionEngine createForModule(Module m) {
        Pointer<Pointer<Byte>> outError = Pointer.allocateBytes(1, 1024);

        Pointer<LLVMExecutionEngineRef> pEE = Pointer
                .allocateTypedPointer(LLVMExecutionEngineRef.class);

        boolean err = LLVMCreateExecutionEngineForModule(pEE, m.module(),
                outError) != 0;
        if (err) {
            String msg = outError.get().getCString();
            throw new RuntimeException("can't create execution engine: " + msg);
        }

        return new ExecutionEngine(pEE.get());
    }

    /*public static native int LLVMCreateInterpreterForModule(
            Pointer<Pointer<LLVMOpaqueExecutionEngine>> outInterp,
            LLVMModuleRef m, Pointer<Pointer<Byte>> outError);

    public static native int LLVMCreateJITCompilerForModule(
            Pointer<Pointer<LLVMOpaqueExecutionEngine>> outJIT,
            LLVMModuleRef m, int optLevel, Pointer<Pointer<Byte>> outError);

    public static native int LLVMCreateExecutionEngine(
            Pointer<Pointer<LLVMOpaqueExecutionEngine>> outEE,
            LLVMModuleProviderRef mp, Pointer<Pointer<Byte>> outError);

    public static native int LLVMCreateInterpreter(
            Pointer<Pointer<LLVMOpaqueExecutionEngine>> outInterp,
            LLVMModuleProviderRef mp, Pointer<Pointer<Byte>> outError);

    public static native int LLVMCreateJITCompiler(
            Pointer<Pointer<LLVMOpaqueExecutionEngine>> outJIT,
            LLVMModuleProviderRef mp, int optLevel,
            Pointer<Pointer<Byte>> outError);*/

    public void runStaticConstructors() {
        LLVMRunStaticConstructors(engine);
    }

    public void runStaticDestructors() {
        LLVMRunStaticDestructors(engine);
    }

    public boolean runFunctionAsMain(Value f, int argC,
            Pointer<Pointer<Byte>> argV, Pointer<Pointer<Byte>> envP) {
        return LLVMRunFunctionAsMain(engine, f.value(), argC, argV, envP) != 0;
    }

    public GenericValue runFunction(Value f, GenericValue... args) {
        // Pointer<Pointer<LLVMOpaqueGenericValue>> args) {
        int n = args.length;
        LLVMGenericValueRef[] inner = new LLVMGenericValueRef[n];
        for (int i = 0; i < n; i++) {
            inner[i] = args[i].ref();
        } // .value(); }

        Pointer<LLVMGenericValueRef> array = Pointer.allocateTypedPointers(
                LLVMGenericValueRef.class, n);
        array.setArray(inner);

        return new GenericValue(LLVMRunFunction(engine, f.value(), n, array));
    }

    public void freeMachineCodeForFunction(Value f) {
        LLVMFreeMachineCodeForFunction(engine, f.value());
    }

    public void addModule(Module m) {
        LLVMAddModule(engine, m.module());
    }

    /**
     * @deprecated Use LLVMAddModule instead.
     */
    public void addModuleProvider(LLVMModuleProviderRef mp) {
        LLVMAddModuleProvider(engine, mp);
    }

    public Module removeModule(Module m) {
        Pointer<Pointer<Byte>> outError = Pointer.allocateBytes(1, 1024);
        Pointer<LLVMModuleRef> outMod = Pointer
                .allocateTypedPointer(LLVMModuleRef.class);
        boolean err = LLVMRemoveModule(engine, m.module(), outMod, outError) != 0;
        if (err) {
            String msg = outError.get().getCString();
            throw new RuntimeException("can't remove module: " + msg);
        }
        return new Module(outMod.get());
    }

    // TODO: make sure of this
    public Module removeModuleProvider(LLVMModuleProviderRef mp) {
        Pointer<Pointer<Byte>> outError = Pointer.allocateBytes(1, 1024);
        Pointer<LLVMModuleRef> outMod = Pointer
                .allocateTypedPointer(LLVMModuleRef.class);
        boolean err = LLVMRemoveModuleProvider(engine, mp, outMod, outError) != 0;
        if (err) {
            String msg = outError.get().getCString();
            throw new RuntimeException("can't remove module provider: " + msg);
        }
        return new Module(outMod.get());
    }

    public Value findFunction(String name) { // Pointer<Pointer<LLVMOpaqueValue>> outFn) {
        Pointer<Byte> cstr = Pointer.pointerToCString(name);
        Pointer<LLVMValueRef> outFn = Pointer
                .allocateTypedPointer(LLVMValueRef.class);
        boolean err = LLVMFindFunction(engine, cstr, outFn) != 0;
        if (err) {
            throw new RuntimeException("LLVMFindFunction can't find " + name);
        }
        return new Value(outFn.get());
    }

    // TODO: this probably is returning a ValueRef for the recompiled Fn
    public Pointer<?> recompileAndRelinkFunction(Value fn) {
        return LLVMRecompileAndRelinkFunction(engine, fn.value());
    }

    public LLVMTargetDataRef getExecutionEngineTargetData() {
        return LLVMGetExecutionEngineTargetData(engine);
    }

    public void addGlobalMapping(Value global, Pointer<?> addr) {
        LLVMAddGlobalMapping(engine, global.value(), addr);
    }

    public Pointer<?> getPointerToGlobal(Value global) {
        return LLVMGetPointerToGlobal(engine, global.value());
    }

}
