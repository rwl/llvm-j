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

    public static Module createWithName(String ModuleID) {
        Pointer<Byte> cstr = Pointer.pointerToCString(ModuleID);
        return new Module(LLVMModuleCreateWithName(cstr));
    }

    public static Module createWithNameInContext(String ModuleID, Context C) {
        Pointer<Byte> cstr = Pointer.pointerToCString(ModuleID);
        return new Module(LLVMModuleCreateWithNameInContext(cstr, C.context()));
    }

    public void finalize() {
        dispose();
    }

    public void dispose() {
        LLVMDisposeModule(module);
        module = null;
    }

    public String getDataLayout() {
        Pointer<Byte> cstr = LLVMGetDataLayout(module);
        return cstr.getCString();
    }

    public void setDataLayout(String Triple) {
        Pointer<Byte> cstr = Pointer.pointerToCString(Triple);
        LLVMSetDataLayout(module, cstr);
    }

    public String getTarget() {
        Pointer<Byte> cstr = LLVMGetTarget(module);
        return cstr.getCString();
    }

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

    public TypeRef getTypeByName(String Name) {
        //Pointer<Byte> cstr = Pointer.pointerToCString(Name);
        return new TypeRef(LLVMGetTypeByName(module,
                Pointer.pointerToCString(Name)));
    }

    /*public String getTypeName(LLVMTypeRef Ty) {
        Pointer<Byte> cstr = LLVMGetTypeName(module, Ty);
        return cstr.getCString();
    }*/

    public void dumpModule() {
        LLVMDumpModule(module);
    }

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

    public Value addFunction(String Name, LLVMTypeRef FunctionTy) {
        return new Value(LLVMAddFunction(module,
                Pointer.pointerToCString(Name), FunctionTy));
    }

    public Value getNamedFunction(String Name) {
        return new Value(LLVMGetNamedFunction(module,
                Pointer.pointerToCString(Name)));
    }

    public Value getFirstFunction() {
        return new Value(LLVMGetFirstFunction(module));
    }

    public Value getLastFunction() {
        return new Value(LLVMGetLastFunction(module));
    }

}
