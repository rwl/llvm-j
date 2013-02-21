package org.llvm;


import org.bridj.Pointer;
import org.llvm.binding.LLVMLibrary;

import static org.llvm.binding.LLVMLibrary.*;

public class GenericValue
{
	private LLVMLibrary.LLVMGenericValueRef ref;
	public  LLVMLibrary.LLVMGenericValueRef ref() { return ref; }
	
	public GenericValue(LLVMLibrary.LLVMGenericValueRef ref) { this.ref = ref; }
	
	//public static native void LLVMDisposeGenericValue(LLVMLibrary.LLVMGenericValueRef GenVal);
	public void dispose() {
		if (ref != null) {
			LLVMLibrary.LLVMDisposeGenericValue(ref);
			ref = null;
		}
	}

	public void finalize() { dispose(); }
	
	
	//public static native LLVMLibrary.LLVMGenericValueRef LLVMCreateGenericValueOfInt(LLVMLibrary.LLVMTypeRef Ty, long N, int IsSigned);
	public static GenericValue Int(TypeRef ty, long N, boolean isSigned) {
		return new GenericValue(LLVMCreateGenericValueOfInt(ty.type(), N, isSigned ? 1 : 0));
	}
	
	//public static native LLVMLibrary.LLVMGenericValueRef LLVMCreateGenericValueOfPointer(Pointer<? > P);
	public static GenericValue Ptr(Pointer<?> P) {
		return new GenericValue(LLVMCreateGenericValueOfPointer(P));
	}
	
	//public static native LLVMLibrary.LLVMGenericValueRef LLVMCreateGenericValueOfFloat(LLVMLibrary.LLVMTypeRef Ty, double N);
	public static GenericValue Float(TypeRef ty, double N) {
		return new GenericValue(LLVMCreateGenericValueOfFloat(ty.type(), N));
	}
	
	
	//public static native int LLVMGenericValueIntWidth(LLVMLibrary.LLVMGenericValueRef GenValRef);
	public int intWidth() {
		return LLVMGenericValueIntWidth(ref);
	}
	
	//public static native long LLVMGenericValueToInt(LLVMLibrary.LLVMGenericValueRef GenVal, int IsSigned);
	public long toInt(boolean isSigned) {
		return LLVMGenericValueToInt(ref, isSigned ? 1 : 0);
	}
	
	//public static native Pointer<? > LLVMGenericValueToPointer(LLVMLibrary.LLVMGenericValueRef GenVal);
	public Pointer<?> toPointer() {
		return LLVMGenericValueToPointer(ref);
	}
	
	//public static native double LLVMGenericValueToFloat(LLVMLibrary.LLVMTypeRef TyRef, LLVMLibrary.LLVMGenericValueRef GenVal);
	public double toFloat(TypeRef ty) {
		return LLVMGenericValueToFloat(ty.type(), ref);
	}
	
}
