package org.llvm;

import org.bridj.Pointer;

import static org.llvm.binding.LLVMLibrary.*;


public class Module {
	
	private LLVMModuleRef module;
	LLVMModuleRef module() { return module; }
	
	Module(LLVMModuleRef module) { this.module = module; }
	
	public static Module CreateWithName(String ModuleID) {
		Pointer<Byte> cstr = Pointer.pointerToCString(ModuleID);
		return new Module(LLVMModuleCreateWithName(cstr));
	}
	
	public static Module CreateWithNameInContext(String ModuleID, Context C) {
		Pointer<Byte> cstr = Pointer.pointerToCString(ModuleID);
		return new Module(LLVMModuleCreateWithNameInContext(cstr, C.context()));		
	}
	
	public void finalize() { dispose(); }
	
	public void dispose() { LLVMDisposeModule(module); module = null; }
	
	public String GetDataLayout() { 
		Pointer<Byte> cstr = LLVMGetDataLayout(module);
		return cstr.getCString();
	}
	
	public void SetDataLayout(String Triple) { 
		Pointer<Byte> cstr = Pointer.pointerToCString(Triple);
		LLVMSetDataLayout(module, cstr);
	}
	
	public String GetTarget() {
		Pointer<Byte> cstr = LLVMGetTarget(module);
		return cstr.getCString();
	}
	
	public void SetTarget(String Triple) { 
		Pointer<Byte> cstr = Pointer.pointerToCString(Triple);
		LLVMSetTarget(module, cstr);
	}
	
//	public int addTypeName(String Name, LLVMTypeRef Ty) { Pointer<Byte> cstr = Pointer.pointerToCString(Name); return LLVMAddTypeName(module, cstr, Ty); }
//	public void deleteTypeName(String Name) { Pointer<Byte> cstr = Pointer.pointerToCString(Name); LLVMDeleteTypeName(module, cstr); }
	
	public TypeRef GetTypeByName(String Name) { 
		//Pointer<Byte> cstr = Pointer.pointerToCString(Name);
		return new TypeRef(LLVMGetTypeByName(module, Pointer.pointerToCString(Name)));
	}
//	public String getTypeName(LLVMTypeRef Ty) { Pointer<Byte> cstr = LLVMGetTypeName(module, Ty); return cstr.getCString(); }
	
	public void DumpModule() { LLVMDumpModule(module); }
	
	public void SetModuleInlineAsm(String Asm) { 
		Pointer<Byte> cstr = Pointer.pointerToCString(Asm);
		LLVMSetModuleInlineAsm(module, cstr);
	}
	
	public Context GetModuleContext() { return Context.getModuleContext(this); }

	
	public Value AddGlobal(TypeRef Ty, String Name) { return new Value(LLVMAddGlobal(module(), Ty.type(), Pointer.pointerToCString(Name))); }
	public Value AddGlobalInAddressSpace(TypeRef Ty, String Name, int AddressSpace) { return new Value(LLVMAddGlobalInAddressSpace(module(), Ty.type(), Pointer.pointerToCString(Name), AddressSpace)); }
	public Value GetNamedGlobal(String Name) { return new Value(LLVMGetNamedGlobal(module(), Pointer.pointerToCString(Name))); }
	public Value GetFirstGlobal() { return new Value(LLVMGetFirstGlobal(module())); }
	public Value GetLastGlobal() { return new Value(LLVMGetLastGlobal(module())); }
	

	public Value AddAlias(TypeRef Ty, Value Aliasee, String Name) { return new Value(LLVMAddAlias(module, Ty.type(), Aliasee.value(), Pointer.pointerToCString(Name))); }
	public Value AddFunction(String Name, LLVMTypeRef FunctionTy) { return new Value(LLVMAddFunction(module, Pointer.pointerToCString(Name), FunctionTy)); }
	public Value GetNamedFunction(String Name) { return new Value(LLVMGetNamedFunction(module, Pointer.pointerToCString(Name))); }
	public Value GetFirstFunction() { return new Value(LLVMGetFirstFunction(module)); }
	public Value GetLastFunction() { return new Value(LLVMGetLastFunction(module)); }
	

}
