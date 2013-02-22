package org.llvm;

import org.bridj.Pointer;
import org.llvm.binding.LLVMLibrary;

import static org.llvm.binding.LLVMLibrary.*;

public class GenericValue {
    private LLVMLibrary.LLVMGenericValueRef ref;

    public LLVMLibrary.LLVMGenericValueRef ref() {
        return ref;
    }

    public GenericValue(LLVMLibrary.LLVMGenericValueRef ref) {
        this.ref = ref;
    }

    //public static native void LLVMDisposeGenericValue(LLVMLibrary.LLVMGenericValueRef genVal);
    public void dispose() {
        if (ref != null) {
            LLVMLibrary.LLVMDisposeGenericValue(ref);
            ref = null;
        }
    }

    public void finalize() {
        dispose();
    }

    //public static native LLVMLibrary.LLVMGenericValueRef LLVMCreateGenericValueOfInt(LLVMLibrary.LLVMTypeRef ty, long n, int isSigned);
    public static GenericValue createInt(TypeRef ty, long N, boolean isSigned) {
        return new GenericValue(LLVMCreateGenericValueOfInt(ty.type(), N,
                isSigned ? 1 : 0));
    }

    //public static native LLVMLibrary.LLVMGenericValueRef LLVMCreateGenericValueOfPointer(Pointer<? > p);
    public static GenericValue createPtr(Pointer<?> p) {
        return new GenericValue(LLVMCreateGenericValueOfPointer(p));
    }

    //public static native LLVMLibrary.LLVMGenericValueRef LLVMCreateGenericValueOfFloat(LLVMLibrary.LLVMTypeRef ty, double n);
    public static GenericValue createFloat(TypeRef ty, double n) {
        return new GenericValue(LLVMCreateGenericValueOfFloat(ty.type(), n));
    }

    //public static native int LLVMGenericValueIntWidth(LLVMLibrary.LLVMGenericValueRef genValRef);
    public int intWidth() {
        return LLVMGenericValueIntWidth(ref);
    }

    //public static native long LLVMGenericValueToInt(LLVMLibrary.LLVMGenericValueRef genVal, int isSigned);
    public long toInt(boolean isSigned) {
        return LLVMGenericValueToInt(ref, isSigned ? 1 : 0);
    }

    //public static native Pointer<? > LLVMGenericValueToPointer(LLVMLibrary.LLVMGenericValueRef genVal);
    public Pointer<?> toPointer() {
        return LLVMGenericValueToPointer(ref);
    }

    //public static native double LLVMGenericValueToFloat(LLVMLibrary.LLVMTypeRef tyRef, LLVMLibrary.LLVMGenericValueRef genVal);
    public double toFloat(TypeRef ty) {
        return LLVMGenericValueToFloat(ty.type(), ref);
    }

}
