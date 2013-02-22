package org.llvm;

import static org.llvm.binding.LLVMLibrary.LLVMAlignOf;
import static org.llvm.binding.LLVMLibrary.LLVMArrayType;
import static org.llvm.binding.LLVMLibrary.LLVMConstAllOnes;
import static org.llvm.binding.LLVMLibrary.LLVMConstInt;
import static org.llvm.binding.LLVMLibrary.LLVMConstIntOfArbitraryPrecision;
import static org.llvm.binding.LLVMLibrary.LLVMConstIntOfString;
import static org.llvm.binding.LLVMLibrary.LLVMConstIntOfStringAndSize;
import static org.llvm.binding.LLVMLibrary.LLVMConstNull;
import static org.llvm.binding.LLVMLibrary.LLVMConstPointerNull;
import static org.llvm.binding.LLVMLibrary.LLVMConstReal;
import static org.llvm.binding.LLVMLibrary.LLVMConstRealOfString;
import static org.llvm.binding.LLVMLibrary.LLVMConstRealOfStringAndSize;
import static org.llvm.binding.LLVMLibrary.LLVMCountParamTypes;
import static org.llvm.binding.LLVMLibrary.LLVMCountStructElementTypes;
import static org.llvm.binding.LLVMLibrary.LLVMDoubleType;
import static org.llvm.binding.LLVMLibrary.LLVMDoubleTypeInContext;
import static org.llvm.binding.LLVMLibrary.LLVMFP128Type;
import static org.llvm.binding.LLVMLibrary.LLVMFP128TypeInContext;
import static org.llvm.binding.LLVMLibrary.LLVMFloatType;
import static org.llvm.binding.LLVMLibrary.LLVMFloatTypeInContext;
import static org.llvm.binding.LLVMLibrary.LLVMFunctionType;
import static org.llvm.binding.LLVMLibrary.LLVMGetArrayLength;
import static org.llvm.binding.LLVMLibrary.LLVMGetElementType;
import static org.llvm.binding.LLVMLibrary.LLVMGetIntTypeWidth;
import static org.llvm.binding.LLVMLibrary.LLVMGetParamTypes;
import static org.llvm.binding.LLVMLibrary.LLVMGetPointerAddressSpace;
import static org.llvm.binding.LLVMLibrary.LLVMGetReturnType;
import static org.llvm.binding.LLVMLibrary.LLVMGetStructElementTypes;
import static org.llvm.binding.LLVMLibrary.LLVMGetTypeContext;
import static org.llvm.binding.LLVMLibrary.LLVMGetTypeKind;
import static org.llvm.binding.LLVMLibrary.LLVMGetUndef;
import static org.llvm.binding.LLVMLibrary.LLVMGetVectorSize;
import static org.llvm.binding.LLVMLibrary.LLVMInt16Type;
import static org.llvm.binding.LLVMLibrary.LLVMInt16TypeInContext;
import static org.llvm.binding.LLVMLibrary.LLVMInt1Type;
import static org.llvm.binding.LLVMLibrary.LLVMInt1TypeInContext;
import static org.llvm.binding.LLVMLibrary.LLVMInt32Type;
import static org.llvm.binding.LLVMLibrary.LLVMInt32TypeInContext;
import static org.llvm.binding.LLVMLibrary.LLVMInt64Type;
import static org.llvm.binding.LLVMLibrary.LLVMInt64TypeInContext;
import static org.llvm.binding.LLVMLibrary.LLVMInt8Type;
import static org.llvm.binding.LLVMLibrary.LLVMInt8TypeInContext;
import static org.llvm.binding.LLVMLibrary.LLVMIntType;
import static org.llvm.binding.LLVMLibrary.LLVMIntTypeInContext;
import static org.llvm.binding.LLVMLibrary.LLVMIsFunctionVarArg;
import static org.llvm.binding.LLVMLibrary.LLVMIsPackedStruct;
import static org.llvm.binding.LLVMLibrary.LLVMLabelType;
import static org.llvm.binding.LLVMLibrary.LLVMLabelTypeInContext;
import static org.llvm.binding.LLVMLibrary.LLVMPPCFP128Type;
import static org.llvm.binding.LLVMLibrary.LLVMPPCFP128TypeInContext;
import static org.llvm.binding.LLVMLibrary.LLVMPointerType;
import static org.llvm.binding.LLVMLibrary.LLVMSizeOf;
import static org.llvm.binding.LLVMLibrary.LLVMStructType;
import static org.llvm.binding.LLVMLibrary.LLVMStructTypeInContext;
import static org.llvm.binding.LLVMLibrary.LLVMVectorType;
import static org.llvm.binding.LLVMLibrary.LLVMVoidType;
import static org.llvm.binding.LLVMLibrary.LLVMVoidTypeInContext;
import static org.llvm.binding.LLVMLibrary.LLVMX86FP80Type;
import static org.llvm.binding.LLVMLibrary.LLVMX86FP80TypeInContext;
import static org.llvm.binding.LLVMLibrary.LLVMX86MMXType;
import static org.llvm.binding.LLVMLibrary.LLVMX86MMXTypeInContext;

import org.bridj.Pointer;
import org.bridj.ValuedEnum;
import org.llvm.binding.LLVMLibrary.LLVMTypeKind;
import org.llvm.binding.LLVMLibrary.LLVMTypeRef;

/**
 * Each value in the LLVM IR has a type, an LLVMTypeRef.
 */
public class TypeRef {

    private LLVMTypeRef type;

    public LLVMTypeRef type() {
        return type;
    }

    TypeRef(LLVMTypeRef type) {
        this.type = type;
    }

    public ValuedEnum<LLVMTypeKind> getTypeKind() {
        return LLVMGetTypeKind(type);
    }

    public Context getTypeContext() {
        return new Context(LLVMGetTypeContext(type));
    }

    public static TypeRef int1TypeInContext(Context C) {
        return new TypeRef(LLVMInt1TypeInContext(C.context()));
    }

    public static TypeRef int8TypeInContext(Context C) {
        return new TypeRef(LLVMInt8TypeInContext(C.context()));
    }

    public static TypeRef int16TypeInContext(Context C) {
        return new TypeRef(LLVMInt16TypeInContext(C.context()));
    }

    public static TypeRef int32TypeInContext(Context C) {
        return new TypeRef(LLVMInt32TypeInContext(C.context()));
    }

    public static TypeRef int64TypeInContext(Context C) {
        return new TypeRef(LLVMInt64TypeInContext(C.context()));
    }

    public static TypeRef intTypeInContext(Context C, int NumBits) {
        return new TypeRef(LLVMIntTypeInContext(C.context(), NumBits));
    }

    public static TypeRef int1Type() {
        return new TypeRef(LLVMInt1Type());
    }

    public static TypeRef int8Type() {
        return new TypeRef(LLVMInt8Type());
    }

    public static TypeRef int16Type() {
        return new TypeRef(LLVMInt16Type());
    }

    public static TypeRef int32Type() {
        return new TypeRef(LLVMInt32Type());
    }

    public static TypeRef int64Type() {
        return new TypeRef(LLVMInt64Type());
    }

    public static TypeRef intType(int NumBits) {
        return new TypeRef(LLVMIntType(NumBits));
    }

    public int getIntTypeWidth() {
        return LLVMGetIntTypeWidth(type);
    }

    public static TypeRef floatTypeInContext(Context C) {
        return new TypeRef(LLVMFloatTypeInContext(C.context()));
    }

    public static TypeRef doubleTypeInContext(Context C) {
        return new TypeRef(LLVMDoubleTypeInContext(C.context()));
    }

    public static TypeRef x86FP80TypeInContext(Context C) {
        return new TypeRef(LLVMX86FP80TypeInContext(C.context()));
    }

    public static TypeRef FP128TypeInContext(Context C) {
        return new TypeRef(LLVMFP128TypeInContext(C.context()));
    }

    public static TypeRef PPCFP128TypeInContext(Context C) {
        return new TypeRef(LLVMPPCFP128TypeInContext(C.context()));
    }

    public static TypeRef floatType() {
        return new TypeRef(LLVMFloatType());
    }

    public static TypeRef doubleType() {
        return new TypeRef(LLVMDoubleType());
    }

    public static TypeRef x86FP80Type() {
        return new TypeRef(LLVMX86FP80Type());
    }

    public static TypeRef FP128Type() {
        return new TypeRef(LLVMFP128Type());
    }

    public static TypeRef PPCFP128Type() {
        return new TypeRef(LLVMPPCFP128Type());
    }

    public static TypeRef functionType(LLVMTypeRef ReturnType,
            Pointer<LLVMTypeRef> ParamTypes, int ParamCount, int IsVarArg) {
        return new TypeRef(LLVMFunctionType(ReturnType, ParamTypes, ParamCount,
                IsVarArg));
    }

    public static TypeRef functionType(TypeRef ReturnType,
            TypeRef... ParamTypes) {
        int N = ParamTypes.length;
        LLVMTypeRef[] inner = new LLVMTypeRef[N];
        for (int i = 0; i < N; i++) {
            inner[i] = ParamTypes[i].type;
        }

        Pointer<LLVMTypeRef> array = Pointer.allocateTypedPointers(
                LLVMTypeRef.class, ParamTypes.length);
        array.setArray(inner);

        return new TypeRef(LLVMFunctionType(ReturnType.type, array,
                ParamTypes.length, 0));
    }

    public boolean isFunctionVarArg() {
        return LLVMIsFunctionVarArg(type) != 0;
    }

    public TypeRef getReturnType() {
        return new TypeRef(LLVMGetReturnType(type));
    }

    public int countParamTypes() {
        return LLVMCountParamTypes(type);
    }

    public void getParamTypes(Pointer<LLVMTypeRef> Dest) {
        LLVMGetParamTypes(type, Dest);
    }

    public static TypeRef structTypeInContext(Context C,
            Pointer<LLVMTypeRef> ElementTypes, int ElementCount, boolean Packed) {
        return new TypeRef(LLVMStructTypeInContext(C.context(), ElementTypes,
                ElementCount, Packed ? 1 : 0));
    }

    public static TypeRef structType(Pointer<LLVMTypeRef> ElementTypes,
            int ElementCount, boolean Packed) {
        return new TypeRef(LLVMStructType(ElementTypes, ElementCount,
                Packed ? 1 : 0));
    }

    public int countStructElementTypes() {
        return LLVMCountStructElementTypes(type);
    }

    public void getStructElementTypes(Pointer<LLVMTypeRef> Dest) {
        LLVMGetStructElementTypes(type, Dest);
    }

    public boolean isPackedStruct() {
        return LLVMIsPackedStruct(type) != 0;
    }

    public TypeRef arrayType(int ElementCount) {
        return new TypeRef(LLVMArrayType(type, ElementCount));
    }

    public TypeRef pointerType(int AddressSpace) {
        return new TypeRef(LLVMPointerType(type, AddressSpace));
    }

    public TypeRef vectorType(int ElementCount) {
        return new TypeRef(LLVMVectorType(type, ElementCount));
    }

    public TypeRef getElementType() {
        return new TypeRef(LLVMGetElementType(type));
    }

    public int getArrayLength() {
        return LLVMGetArrayLength(type);
    }

    public int getPointerAddressSpace() {
        return LLVMGetPointerAddressSpace(type);
    }

    public int getVectorSize() {
        return LLVMGetVectorSize(type);
    }

    public static TypeRef voidTypeInContext(Context C) {
        return new TypeRef(LLVMVoidTypeInContext(C.context()));
    }

    public static TypeRef labelTypeInContext(Context C) {
        return new TypeRef(LLVMLabelTypeInContext(C.context()));
    }

    /*public static TypeRef opaqueTypeInContext(Context C) {
        return new TypeRef(LLVMOpaqueTypeInContext(C.context()));
    }*/

    public static TypeRef x86MMXTypeInContext(Context C) {
        return new TypeRef(LLVMX86MMXTypeInContext(C.context()));
    }

    public static TypeRef voidType() {
        return new TypeRef(LLVMVoidType());
    }

    public static TypeRef labelType() {
        return new TypeRef(LLVMLabelType());
    }

    /*public static TypeRef opaqueType() {
        return new TypeRef(LLVMOpaqueType());
    }*/

    public static TypeRef x86MMXType() {
        return new TypeRef(LLVMX86MMXType());
    }

    //public static TypeHandleRef createTypeHandle(LLVMTypeRef PotentiallyAbstractTy);
    //public static void    refineType(LLVMTypeRef AbstractTy, LLVMTypeRef ConcreteTy);
    //public static TypeRef resolveTypeHandle(LLVMTypeHandleRef TypeHandle);
    //public static void    disposeTypeHandle(LLVMTypeHandleRef TypeHandle);

    public Value constNull() {
        return new Value(LLVMConstNull(type));
    }

    public Value constPointerNull() {
        return new Value(LLVMConstPointerNull(type));
    }

    public Value constAllOnes() {
        return new Value(LLVMConstAllOnes(type));
    }

    public Value GetUndef() {
        return new Value(LLVMGetUndef(type));
    }

    public Value constInt(long N, boolean SignExtend) {
        return new Value(LLVMConstInt(type, N, SignExtend ? 1 : 0));
    }

    // TODO: change Pointer to array
    public Value constIntOfArbitraryPrecision(int NumWords, Pointer<Long> Words) {
        return new Value(
                LLVMConstIntOfArbitraryPrecision(type, NumWords, Words));
    }

    public Value constIntOfString(String Text, byte Radix) {
        return new Value(LLVMConstIntOfString(type,
                Pointer.pointerToCString(Text), Radix));
    }

    public Value constIntOfStringAndSize(String Text, int SLen, byte Radix) {
        return new Value(LLVMConstIntOfStringAndSize(type,
                Pointer.pointerToCString(Text), SLen, Radix));
    }

    public Value constReal(double N) {
        return new Value(LLVMConstReal(type, N));
    }

    public Value constRealOfString(String Text) {
        return new Value(LLVMConstRealOfString(type,
                Pointer.pointerToCString(Text)));
    }

    public Value constRealOfStringAndSize(String Text, int SLen) {
        return new Value(LLVMConstRealOfStringAndSize(type,
                Pointer.pointerToCString(Text), SLen));
    }

    public Value alignOf(TypeRef Ty) {
        return new Value(LLVMAlignOf(type));
    }

    public Value sizeOf(TypeRef Ty) {
        return new Value(LLVMSizeOf(type));
    }

}
