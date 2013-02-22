package org.llvm;

import org.bridj.Pointer;

import static org.llvm.binding.LLVMLibrary.*;

/**
 * The main container class for the LLVM Intermediate Representation.
 */
public class Module {

    private LLVMModuleRef module;

    LLVMModuleRef module() {
        return module;
    }

    Module(LLVMModuleRef module) {
        this.module = module;
    }

    /**
     * Create a new, empty module in the global context.<br>
     * This is equivalent to calling LLVMModuleCreateWithNameInContext with<br>
     * LLVMGetGlobalContext() as the context parameter.<br>
     * Every invocation should be paired with LLVMDisposeModule() or memory<br>
     * will be leaked.
     */
    public static Module createWithName(String ModuleID) {
        Pointer<Byte> cstr = Pointer.pointerToCString(ModuleID);
        return new Module(LLVMModuleCreateWithName(cstr));
    }

    /**
     * Create a new, empty module in a specific context.<br>
     * Every invocation should be paired with LLVMDisposeModule() or memory<br>
     * will be leaked.
     */
    public static Module createWithNameInContext(String ModuleID, Context C) {
        Pointer<Byte> cstr = Pointer.pointerToCString(ModuleID);
        return new Module(LLVMModuleCreateWithNameInContext(cstr, C.context()));
    }

    public void finalize() {
        dispose();
    }

    /**
     * Destroy a module instance.<br>
     * * This must be called for every created module or memory will be<br>
     * leaked.
     */
    public void dispose() {
        LLVMDisposeModule(module);
        module = null;
    }

    /**
     * Obtain the data layout for a module.<br>
     * 
     * @see Module::getDataLayout()
     */
    public String getDataLayout() {
        Pointer<Byte> cstr = LLVMGetDataLayout(module);
        return cstr.getCString();
    }

    /**
     * Set the data layout for a module.<br>
     * 
     * @see Module::setDataLayout()
     */
    public void setDataLayout(String Triple) {
        Pointer<Byte> cstr = Pointer.pointerToCString(Triple);
        LLVMSetDataLayout(module, cstr);
    }

    /**
     * Obtain the target triple for a module.<br>
     * 
     * @see Module::getTargetTriple()
     */
    public String getTarget() {
        Pointer<Byte> cstr = LLVMGetTarget(module);
        return cstr.getCString();
    }

    /**
     * Set the target triple for a module.<br>
     * 
     * @see Module::setTargetTriple()
     */
    public void setTarget(String Triple) {
        Pointer<Byte> cstr = Pointer.pointerToCString(Triple);
        LLVMSetTarget(module, cstr);
    }

    /*public int addTypeName(String Name, LLVMTypeRef Ty) {
        Pointer<Byte> cstr = Pointer.pointerToCString(Name);
        return LLVMAddTypeName(module, cstr, Ty);
    }

    public void deleteTypeName(String Name) {
        Pointer<Byte> cstr = Pointer.pointerToCString(Name);
        LLVMDeleteTypeName(module, cstr);
    }*/

    /**
     * Obtain a Type from a module by its registered name.
     */
    public TypeRef getTypeByName(String Name) {
        //Pointer<Byte> cstr = Pointer.pointerToCString(Name);
        return new TypeRef(LLVMGetTypeByName(module,
                Pointer.pointerToCString(Name)));
    }

    /*public String getTypeName(LLVMTypeRef Ty) {
        Pointer<Byte> cstr = LLVMGetTypeName(module, Ty);
        return cstr.getCString();
    }*/

    /**
     * Dump a representation of a module to stderr.<br>
     * 
     * @see Module::dump()
     */
    public void dumpModule() {
        LLVMDumpModule(module);
    }

    /**
     * Set inline assembly for a module.<br>
     * 
     * @see Module::setModuleInlineAsm()
     */
    public void setModuleInlineAsm(String Asm) {
        Pointer<Byte> cstr = Pointer.pointerToCString(Asm);
        LLVMSetModuleInlineAsm(module, cstr);
    }

    public Context getModuleContext() {
        return Context.getModuleContext(this);
    }

    public Value addGlobal(TypeRef Ty, String Name) {
        return new Value(LLVMAddGlobal(module(), Ty.type(),
                Pointer.pointerToCString(Name)));
    }

    public Value addGlobalInAddressSpace(TypeRef Ty, String Name,
            int AddressSpace) {
        return new Value(LLVMAddGlobalInAddressSpace(module(), Ty.type(),
                Pointer.pointerToCString(Name), AddressSpace));
    }

    public Value getNamedGlobal(String Name) {
        return new Value(LLVMGetNamedGlobal(module(),
                Pointer.pointerToCString(Name)));
    }

    public Value getFirstGlobal() {
        return new Value(LLVMGetFirstGlobal(module()));
    }

    public Value getLastGlobal() {
        return new Value(LLVMGetLastGlobal(module()));
    }

    public Value addAlias(TypeRef Ty, Value Aliasee, String Name) {
        return new Value(LLVMAddAlias(module, Ty.type(), Aliasee.value(),
                Pointer.pointerToCString(Name)));
    }

    /**
     * Add a function to a module under a specified name.<br>
     * 
     * @see llvm::Function::Create()
     */
    public Value addFunction(String Name, LLVMTypeRef FunctionTy) {
        return new Value(LLVMAddFunction(module,
                Pointer.pointerToCString(Name), FunctionTy));
    }

    /**
     * Obtain a Function value from a Module by its name.<br>
     * The returned value corresponds to a llvm::Function value.<br>
     * 
     * @see llvm::Module::getFunction()
     */
    public Value getNamedFunction(String Name) {
        return new Value(LLVMGetNamedFunction(module,
                Pointer.pointerToCString(Name)));
    }

    /**
     * Obtain an iterator to the first Function in a Module.<br>
     * 
     * @see llvm::Module::begin()
     */
    public Value getFirstFunction() {
        return new Value(LLVMGetFirstFunction(module));
    }

    /**
     * Obtain an iterator to the last Function in a Module.<br>
     * 
     * @see llvm::Module::end()
     */
    public Value getLastFunction() {
        return new Value(LLVMGetLastFunction(module));
    }

}
