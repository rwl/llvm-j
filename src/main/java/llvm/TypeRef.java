package llvm;

import org.bridj.Pointer;
import org.bridj.ValuedEnum;

import static llvm.binding.LLVMLibrary.*;


public class TypeRef {

	private LLVMTypeRef type;
	public  LLVMTypeRef type() { return type; }


	TypeRef(LLVMTypeRef type) { this.type = type; }


	public ValuedEnum<LLVMTypeKind > GetTypeKind() {
		return LLVMGetTypeKind(type);
	}

	public Context getTypeContext() { return new Context(LLVMGetTypeContext(type)); }

	public static TypeRef Int1TypeInContext (Context C) { return new TypeRef(LLVMInt1TypeInContext (C.context())); }
	public static TypeRef Int8TypeInContext (Context C) { return new TypeRef(LLVMInt8TypeInContext (C.context())); }
	public static TypeRef Int16TypeInContext(Context C) { return new TypeRef(LLVMInt16TypeInContext(C.context())); }
	public static TypeRef Int32TypeInContext(Context C) { return new TypeRef(LLVMInt32TypeInContext(C.context())); }
	public static TypeRef Int64TypeInContext(Context C) { return new TypeRef(LLVMInt64TypeInContext(C.context())); }
	public static TypeRef IntTypeInContext(Context C, int NumBits) { return new TypeRef(LLVMIntTypeInContext(C.context(), NumBits)); }

	public static TypeRef Int1Type () { return new TypeRef(LLVMInt1Type ()); }
	public static TypeRef Int8Type () { return new TypeRef(LLVMInt8Type ()); }
	public static TypeRef Int16Type() { return new TypeRef(LLVMInt16Type()); }
	public static TypeRef Int32Type() { return new TypeRef(LLVMInt32Type()); }
	public static TypeRef Int64Type() { return new TypeRef(LLVMInt64Type()); }
	public static TypeRef IntType(int NumBits) { return new TypeRef(LLVMIntType(NumBits)); }

	public int GetIntTypeWidth() { return LLVMGetIntTypeWidth(type); }

	public static TypeRef FloatTypeInContext   (Context C) { return new TypeRef(LLVMFloatTypeInContext   (C.context())); }
	public static TypeRef DoubleTypeInContext  (Context C) { return new TypeRef(LLVMDoubleTypeInContext  (C.context())); }
	public static TypeRef X86FP80TypeInContext (Context C) { return new TypeRef(LLVMX86FP80TypeInContext (C.context())); }
	public static TypeRef FP128TypeInContext   (Context C) { return new TypeRef(LLVMFP128TypeInContext   (C.context())); }
	public static TypeRef PPCFP128TypeInContext(Context C) { return new TypeRef(LLVMPPCFP128TypeInContext(C.context())); }

	public static TypeRef FloatType   () { return new TypeRef(LLVMFloatType   ()); }
	public static TypeRef DoubleType  () { return new TypeRef(LLVMDoubleType  ()); }
	public static TypeRef X86FP80Type () { return new TypeRef(LLVMX86FP80Type ()); }
	public static TypeRef FP128Type   () { return new TypeRef(LLVMFP128Type   ()); }
	public static TypeRef PPCFP128Type() { return new TypeRef(LLVMPPCFP128Type()); }

	public static TypeRef FunctionType(
			LLVMTypeRef ReturnType,
			Pointer<LLVMTypeRef> ParamTypes,
			int ParamCount, int IsVarArg) {
		return new TypeRef(LLVMFunctionType(ReturnType, ParamTypes, ParamCount, IsVarArg));
	}
	public static TypeRef FunctionType(TypeRef ReturnType, TypeRef... ParamTypes) {
		int N = ParamTypes.length;
		LLVMTypeRef[] inner = new LLVMTypeRef[N];
		for (int i=0; i<N; i++) { inner[i] = ParamTypes[i].type; }

		Pointer<LLVMTypeRef> array =
				Pointer.allocateTypedPointers(LLVMTypeRef.class, ParamTypes.length);
		array.setArray(inner);

		return new TypeRef(LLVMFunctionType(ReturnType.type, array, ParamTypes.length, 0));
	}

	public boolean IsFunctionVarArg() { return LLVMIsFunctionVarArg(type) != 0; }
	public TypeRef GetReturnType() { return new TypeRef(LLVMGetReturnType(type)); }
	public int     CountParamTypes() { return LLVMCountParamTypes(type); }
	public void    GetParamTypes(Pointer<LLVMTypeRef> Dest) { LLVMGetParamTypes(type, Dest); }

	public static TypeRef StructTypeInContext(
			Context C,
			Pointer<LLVMTypeRef> ElementTypes,
			int ElementCount,
			boolean Packed) {
		return new TypeRef(LLVMStructTypeInContext(C.context(), ElementTypes, ElementCount, Packed ? 1 : 0));
	}

	public static TypeRef StructType(
			Pointer<LLVMTypeRef> ElementTypes,
			int ElementCount,
			boolean Packed) {
		return new TypeRef(LLVMStructType(ElementTypes, ElementCount, Packed ? 1 : 0));
		}

	public int CountStructElementTypes() { return LLVMCountStructElementTypes(type); }

	public void GetStructElementTypes(Pointer<LLVMTypeRef> Dest) { LLVMGetStructElementTypes(type, Dest); }

	public boolean IsPackedStruct() { return LLVMIsPackedStruct(type) != 0; }

	public TypeRef ArrayType  (int ElementCount) { return new TypeRef(LLVMArrayType  (type, ElementCount)); }
	public TypeRef PointerType(int AddressSpace) { return new TypeRef(LLVMPointerType(type, AddressSpace)); }
	public TypeRef VectorType (int ElementCount) { return new TypeRef(LLVMVectorType (type, ElementCount)); }

	public TypeRef GetElementType    () { return new TypeRef(LLVMGetElementType(type)); }
	public int GetArrayLength        () { return LLVMGetArrayLength(type); }
	public int GetPointerAddressSpace() { return LLVMGetPointerAddressSpace(type); }
	public int GetVectorSize         () { return LLVMGetVectorSize(type); }

	public static TypeRef VoidTypeInContext  (Context C) { return new TypeRef(LLVMVoidTypeInContext  (C.context())); }
	public static TypeRef LabelTypeInContext (Context C) { return new TypeRef(LLVMLabelTypeInContext (C.context())); }
//public static TypeRef OpaqueTypeInContext(Context C) { return new TypeRef(LLVMOpaqueTypeInContext(C.context())); }
	public static TypeRef X86MMXTypeInContext(Context C) { return new TypeRef(LLVMX86MMXTypeInContext(C.context())); }

	public static TypeRef VoidType  () { return new TypeRef(LLVMVoidType  ()); }
	public static TypeRef LabelType () { return new TypeRef(LLVMLabelType ()); }
//public static TypeRef OpaqueType() { return new TypeRef(LLVMOpaqueType()); }
	public static TypeRef X86MMXType() { return new TypeRef(LLVMX86MMXType()); }

//public static TypeHandleRef CreateTypeHandle(LLVMTypeRef PotentiallyAbstractTy);
//public static void    RefineType(LLVMTypeRef AbstractTy, LLVMTypeRef ConcreteTy);
//public static TypeRef ResolveTypeHandle(LLVMTypeHandleRef TypeHandle);
//public static void    DisposeTypeHandle(LLVMTypeHandleRef TypeHandle);


	public Value ConstNull() { return new Value(LLVMConstNull(type)); }
	public Value ConstPointerNull() { return new Value(LLVMConstPointerNull(type)); }
	public Value ConstAllOnes() { return new Value(LLVMConstAllOnes(type)); }
	public Value GetUndef() { return new Value(LLVMGetUndef(type)); }

	public Value ConstInt(long N, boolean SignExtend) { return new Value(LLVMConstInt(type, N, SignExtend?1:0)); }
	// TODO: change Pointer to array
	public Value ConstIntOfArbitraryPrecision(int NumWords, Pointer<Long > Words) { return new Value(LLVMConstIntOfArbitraryPrecision(type, NumWords, Words)); }
	public Value ConstIntOfString(String Text, byte Radix) { return new Value(LLVMConstIntOfString(type, Pointer.pointerToCString(Text), Radix)); }
	public Value ConstIntOfStringAndSize(String Text, int SLen, byte Radix) { return new Value(LLVMConstIntOfStringAndSize(type, Pointer.pointerToCString(Text), SLen, Radix)); }
	public Value ConstReal(double N) { return new Value(LLVMConstReal(type, N)); }
	public Value ConstRealOfString(String Text) { return new Value(LLVMConstRealOfString(type, Pointer.pointerToCString(Text))); }
	public Value ConstRealOfStringAndSize(String Text, int SLen) { return new Value(LLVMConstRealOfStringAndSize(type, Pointer.pointerToCString(Text), SLen)); }

	public Value AlignOf(TypeRef Ty) { return new Value(LLVMAlignOf(type)); }
	public Value SizeOf(TypeRef Ty) { return new Value(LLVMSizeOf(type)); }


}
