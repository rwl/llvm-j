package org.llvm;

import org.bridj.IntValuedEnum;
import org.bridj.Pointer;
import org.bridj.ValuedEnum;

import static org.llvm.binding.LLVMLibrary.*;

public class Value {

	private LLVMValueRef value;
	/*package*/ LLVMValueRef value() { return value; }
	/*package*/ Value(LLVMValueRef value) { this.value = value; }


	public TypeRef TypeOf() { return new TypeRef(LLVMTypeOf(value)); }
	public String GetValueName() { return LLVMGetValueName(value).getCString();	}
	public void SetValueName(String Name) { LLVMSetValueName(value,  Pointer.pointerToCString(Name)); }
	public void DumpValue() { LLVMDumpValue(value); }
	public void ReplaceAllUsesWith(Value NewVal) { LLVMReplaceAllUsesWith(value, NewVal.value()); }

	public int   HasMetadata() { return LLVMHasMetadata(value); }
	public Value GetMetadata(int KindID) { return new Value(LLVMGetMetadata(value, KindID)); }
	public void  SetMetadata(int KindID, Value Node) { LLVMSetMetadata(value, KindID, Node.value()); }

	/** Conversion functions. Return the input value if it is an instance of the
	 * specified class, otherwise NULL. See llvm::dyn_cast_or_null<>.
	 */
	public Value IsAArgument() { return new Value(LLVMIsAArgument(value)); }
	public Value IsABasicBlock() { return new Value(LLVMIsABasicBlock(value)); }
	public Value IsAInlineAsm() { return new Value(LLVMIsAInlineAsm(value)); }
	public Value IsAUser() { return new Value(LLVMIsAUser(value)); }
	public Value IsAConstant() { return new Value(LLVMIsAConstant(value)); }
	public Value IsAConstantAggregateZero() { return new Value(LLVMIsAConstantAggregateZero(value)); }
	public Value IsAConstantArray() { return new Value(LLVMIsAConstantArray(value)); }
	public Value IsAConstantExpr() { return new Value(LLVMIsAConstantExpr(value)); }
	public Value IsAConstantFP() { return new Value(LLVMIsAConstantFP(value)); }
	public Value IsAConstantInt() { return new Value(LLVMIsAConstantInt(value)); }
	public Value IsAConstantPointerNull() { return new Value(LLVMIsAConstantPointerNull(value)); }
	public Value IsAConstantStruct() { return new Value(LLVMIsAConstantStruct(value)); }
	public Value IsAConstantVector() { return new Value(LLVMIsAConstantVector(value)); }
	public Value IsAGlobalValue() { return new Value(LLVMIsAGlobalValue(value)); }
	public Value IsAFunction() { return new Value(LLVMIsAFunction(value)); }
	public Value IsAGlobalAlias() { return new Value(LLVMIsAGlobalAlias(value)); }
	public Value IsAGlobalVariable() { return new Value(LLVMIsAGlobalVariable(value)); }
	public Value IsAUndefValue() { return new Value(LLVMIsAUndefValue(value)); }
	public Value IsAInstruction() { return new Value(LLVMIsAInstruction(value)); }
	public Value IsABinaryOperator() { return new Value(LLVMIsABinaryOperator(value)); }
	public Value IsACallInst() { return new Value(LLVMIsACallInst(value)); }
	public Value IsAIntrinsicInst() { return new Value(LLVMIsAIntrinsicInst(value)); }
	public Value IsADbgInfoIntrinsic() { return new Value(LLVMIsADbgInfoIntrinsic(value)); }
	public Value IsADbgDeclareInst() { return new Value(LLVMIsADbgDeclareInst(value)); }
//	public Value IsAEHSelectorInst() { return new Value(LLVMIsAEHSelectorInst(value)); }
	public Value IsAMemIntrinsic() { return new Value(LLVMIsAMemIntrinsic(value)); }
	public Value IsAMemCpyInst() { return new Value(LLVMIsAMemCpyInst(value)); }
	public Value IsAMemMoveInst() { return new Value(LLVMIsAMemMoveInst(value)); }
	public Value IsAMemSetInst() { return new Value(LLVMIsAMemSetInst(value)); }
	public Value IsACmpInst() { return new Value(LLVMIsACmpInst(value)); }
	public Value IsAFCmpInst() { return new Value(LLVMIsAFCmpInst(value)); }
	public Value IsAICmpInst() { return new Value(LLVMIsAICmpInst(value)); }
	public Value IsAExtractElementInst() { return new Value(LLVMIsAExtractElementInst(value)); }
	public Value IsAGetElementPtrInst() { return new Value(LLVMIsAGetElementPtrInst(value)); }
	public Value IsAInsertElementInst() { return new Value(LLVMIsAInsertElementInst(value)); }
	public Value IsAInsertValueInst() { return new Value(LLVMIsAInsertValueInst(value)); }
	public Value IsAPHINode() { return new Value(LLVMIsAPHINode(value)); }
	public Value IsASelectInst() { return new Value(LLVMIsASelectInst(value)); }
	public Value IsAShuffleVectorInst() { return new Value(LLVMIsAShuffleVectorInst(value)); }
	public Value IsAStoreInst() { return new Value(LLVMIsAStoreInst(value)); }
	public Value IsATerminatorInst() { return new Value(LLVMIsATerminatorInst(value)); }
	public Value IsABranchInst() { return new Value(LLVMIsABranchInst(value)); }
	public Value IsAInvokeInst() { return new Value(LLVMIsAInvokeInst(value)); }
	public Value IsAReturnInst() { return new Value(LLVMIsAReturnInst(value)); }
	public Value IsASwitchInst() { return new Value(LLVMIsASwitchInst(value)); }
	public Value IsAUnreachableInst() { return new Value(LLVMIsAUnreachableInst(value)); }
//	public Value IsAUnwindInst() { return new Value(LLVMIsAUnwindInst(value)); }
	public Value IsAUnaryInstruction() { return new Value(LLVMIsAUnaryInstruction(value)); }
	public Value IsAAllocaInst() { return new Value(LLVMIsAAllocaInst(value)); }
	public Value IsACastInst() { return new Value(LLVMIsACastInst(value)); }
	public Value IsABitCastInst() { return new Value(LLVMIsABitCastInst(value)); }
	public Value IsAFPExtInst() { return new Value(LLVMIsAFPExtInst(value)); }
	public Value IsAFPToSIInst() { return new Value(LLVMIsAFPToSIInst(value)); }
	public Value IsAFPToUIInst() { return new Value(LLVMIsAFPToUIInst(value)); }
	public Value IsAFPTruncInst() { return new Value(LLVMIsAFPTruncInst(value)); }
	public Value IsAIntToPtrInst() { return new Value(LLVMIsAIntToPtrInst(value)); }
	public Value IsAPtrToIntInst() { return new Value(LLVMIsAPtrToIntInst(value)); }
	public Value IsASExtInst() { return new Value(LLVMIsASExtInst(value)); }
	public Value IsASIToFPInst() { return new Value(LLVMIsASIToFPInst(value)); }
	public Value IsATruncInst() { return new Value(LLVMIsATruncInst(value)); }
	public Value IsAUIToFPInst() { return new Value(LLVMIsAUIToFPInst(value)); }
	public Value IsAZExtInst() { return new Value(LLVMIsAZExtInst(value)); }
	public Value IsAExtractValueInst() { return new Value(LLVMIsAExtractValueInst(value)); }
	public Value IsALoadInst() { return new Value(LLVMIsALoadInst(value)); }
	public Value IsAVAArgInst() { return new Value(LLVMIsAVAArgInst(value)); }

	public boolean IsConstant() { return LLVMIsConstant(value) != 0; }
	public boolean IsNull() { return LLVMIsNull(value) != 0; }
	public boolean IsUndef() { return LLVMIsUndef(value) != 0; }

	// TODO: move
	public static native LLVMUseRef LLVMGetFirstUse(LLVMValueRef Val);
	// TODO: move
	public static native LLVMUseRef LLVMGetNextUse(LLVMUseRef U);
	// TODO: move
	public static Value GetUser(LLVMUseRef U) { return new Value(LLVMGetUser(U)); }
	// TODO: move
	public static Value GetUsedValue(LLVMUseRef U) { return new Value(LLVMGetUsedValue(U)); }

	public Value GetOperand(int Index) { return new Value(LLVMGetOperand(value, Index)); }
	public void  SetOperand(int Index, Value Val) { LLVMSetOperand(value, Index, Val.value()); }
	public int   GetNumOperands() { return LLVMGetNumOperands(value); }



	// MetaData
//	public ValueRef MDStringInContext(LLVMContextRef C, Pointer<Byte > Str, int SLen) { return new ValueRef(LLVMMDStringInContext(value)); }
//	public ValueRef MDString(Pointer<Byte > Str, int SLen) { return new ValueRef(LLVMMDString(value)); }
//	public ValueRef MDNodeInContext(LLVMContextRef C, Pointer<LLVMValueRef> Vals, int Count) { return new ValueRef(LLVMMDNodeInContext(value)); }
//	public ValueRef MDNode(Pointer<LLVMValueRef> Vals, int Count) { return new ValueRef(LLVMMDNode(value)); }

	public long ConstIntGetZExtValue() { return LLVMConstIntGetZExtValue(value); }
	public long ConstIntGetSExtValue() { return LLVMConstIntGetSExtValue(value); }

	public static  Value ConstStringInContext(Context C, String Str, int Length, boolean DontNullTerminate) { return new Value(LLVMConstStringInContext(C.context(), Pointer.pointerToCString(Str), Length, DontNullTerminate?1:0)); }
	//public ValueRef ConstStructInContext(Context C, ValueRef[] ConstantVals, int Count, boolean Packed) { return new ValueRef(LLVMConstStructInContext(C.context(), ConstantVals, Count, Packed?1:0)); }
	public static Value ConstString(String Str, int Length, boolean DontNullTerminate) { return new Value(LLVMConstString(Pointer.pointerToCString(Str), Length, DontNullTerminate?1:0)); }
	// TODO: change Pointer to array
	public static Value ConstArray(TypeRef ElementTy, Pointer<LLVMValueRef> ConstantVals, int Length) { return new Value(LLVMConstArray(ElementTy.type(), ConstantVals, Length)); }
	// TODO: change Pointer to array
	public static Value ConstStruct(Pointer<LLVMValueRef> ConstantVals, int Count, boolean Packed) { return new Value(LLVMConstStruct(ConstantVals, Count, Packed?1:0)); }
	// TODO: change Pointer to array
	public static Value ConstVector(Pointer<LLVMValueRef> ScalarConstantVals, int Size) { return new Value(LLVMConstVector(ScalarConstantVals, Size)); }
	public static native ValuedEnum<LLVMOpcode > GetConstOpcode(LLVMValueRef ConstantVal);

	public Value ConstNeg() { return new Value(LLVMConstNeg(value)); }
	public Value ConstNSWNeg() { return new Value(LLVMConstNSWNeg(value)); }
	public Value ConstNUWNeg() { return new Value(LLVMConstNUWNeg(value)); }
	public Value ConstFNeg() { return new Value(LLVMConstFNeg(value)); }
	public Value ConstNot() { return new Value(LLVMConstNot(value)); }

	public static Value ConstAdd(Value LHSConstant, Value RHSConstant) { return new Value(LLVMConstAdd(LHSConstant.value(), RHSConstant.value())); }
	public static Value ConstNSWAdd(Value LHSConstant, Value RHSConstant) { return new Value(LLVMConstNSWAdd(LHSConstant.value(), RHSConstant.value())); }
	public static Value ConstNUWAdd(Value LHSConstant, Value RHSConstant) { return new Value(LLVMConstNUWAdd(LHSConstant.value(), RHSConstant.value())); }
	public static Value ConstFAdd(Value LHSConstant, Value RHSConstant) { return new Value(LLVMConstFAdd(LHSConstant.value(), RHSConstant.value())); }
	public static Value ConstSub(Value LHSConstant, Value RHSConstant) { return new Value(LLVMConstSub(LHSConstant.value(), RHSConstant.value())); }
	public static Value ConstNSWSub(Value LHSConstant, Value RHSConstant) { return new Value(LLVMConstNSWSub(LHSConstant.value(), RHSConstant.value())); }
	public static Value ConstNUWSub(Value LHSConstant, Value RHSConstant) { return new Value(LLVMConstNUWSub(LHSConstant.value(), RHSConstant.value())); }
	public static Value ConstFSub(Value LHSConstant, Value RHSConstant) { return new Value(LLVMConstFSub(LHSConstant.value(), RHSConstant.value())); }
	public static Value ConstMul(Value LHSConstant, Value RHSConstant) { return new Value(LLVMConstMul(LHSConstant.value(), RHSConstant.value())); }
	public static Value ConstNSWMul(Value LHSConstant, Value RHSConstant) { return new Value(LLVMConstNSWMul(LHSConstant.value(), RHSConstant.value())); }
	public static Value ConstNUWMul(Value LHSConstant, Value RHSConstant) { return new Value(LLVMConstNUWMul(LHSConstant.value(), RHSConstant.value())); }
	public static Value ConstFMul(Value LHSConstant, Value RHSConstant) { return new Value(LLVMConstFMul(LHSConstant.value(), RHSConstant.value())); }
	public static Value ConstUDiv(Value LHSConstant, Value RHSConstant) { return new Value(LLVMConstUDiv(LHSConstant.value(), RHSConstant.value())); }
	public static Value ConstSDiv(Value LHSConstant, Value RHSConstant) { return new Value(LLVMConstSDiv(LHSConstant.value(), RHSConstant.value())); }
	public static Value ConstExactSDiv(Value LHSConstant, Value RHSConstant) { return new Value(LLVMConstExactSDiv(LHSConstant.value(), RHSConstant.value())); }
	public static Value ConstFDiv(Value LHSConstant, Value RHSConstant) { return new Value(LLVMConstFDiv(LHSConstant.value(), RHSConstant.value())); }
	public static Value ConstURem(Value LHSConstant, Value RHSConstant) { return new Value(LLVMConstURem(LHSConstant.value(), RHSConstant.value())); }
	public static Value ConstSRem(Value LHSConstant, Value RHSConstant) { return new Value(LLVMConstSRem(LHSConstant.value(), RHSConstant.value())); }
	public static Value ConstFRem(Value LHSConstant, Value RHSConstant) { return new Value(LLVMConstFRem(LHSConstant.value(), RHSConstant.value())); }
	public static Value ConstAnd(Value LHSConstant, Value RHSConstant) { return new Value(LLVMConstAnd(LHSConstant.value(), RHSConstant.value())); }
	public static Value ConstOr(Value LHSConstant, Value RHSConstant) { return new Value(LLVMConstOr(LHSConstant.value(), RHSConstant.value())); }
	public static Value ConstXor(Value LHSConstant, Value RHSConstant) { return new Value(LLVMConstXor(LHSConstant.value(), RHSConstant.value())); }
	public static Value ConstICmp(IntValuedEnum<LLVMIntPredicate > Predicate, Value LHSConstant, Value RHSConstant) { return new Value(LLVMConstICmp(Predicate, LHSConstant.value(), RHSConstant.value())); }
	public static Value ConstFCmp(IntValuedEnum<LLVMRealPredicate > Predicate, Value LHSConstant, Value RHSConstant) { return new Value(LLVMConstFCmp(Predicate, LHSConstant.value(), RHSConstant.value())); }
	public static Value ConstShl(Value LHSConstant, Value RHSConstant) { return new Value(LLVMConstShl(LHSConstant.value(), RHSConstant.value())); }
	public static Value ConstLShr(Value LHSConstant, Value RHSConstant) { return new Value(LLVMConstLShr(LHSConstant.value(), RHSConstant.value())); }
	public static Value ConstAShr(Value LHSConstant, Value RHSConstant) { return new Value(LLVMConstAShr(LHSConstant.value(), RHSConstant.value())); }
	// TODO: fix Pointer (change to array)
	public Value ConstGEP(Value ConstantVal, Pointer<LLVMValueRef > ConstantIndices, int NumIndices) { return new Value(LLVMConstGEP(ConstantVal.value(), ConstantIndices, NumIndices)); }
	// TODO: fix Pointer (change to array)
	public static Value ConstInBoundsGEP(Value ConstantVal, Pointer<LLVMValueRef> ConstantIndices, int NumIndices) { return new Value(LLVMConstInBoundsGEP(ConstantVal.value(), ConstantIndices, NumIndices)); }
	public static Value ConstTrunc(Value ConstantVal, TypeRef ToType) { return new Value(LLVMConstTrunc(ConstantVal.value(), ToType.type())); }
	public static Value ConstSExt(Value ConstantVal, TypeRef ToType) { return new Value(LLVMConstSExt(ConstantVal.value(), ToType.type())); }
	public static Value ConstZExt(Value ConstantVal, TypeRef ToType) { return new Value(LLVMConstZExt(ConstantVal.value(), ToType.type())); }
	public static Value ConstFPTrunc(Value ConstantVal, TypeRef ToType) { return new Value(LLVMConstFPTrunc(ConstantVal.value(), ToType.type())); }
	public static Value ConstFPExt(Value ConstantVal, TypeRef ToType) { return new Value(LLVMConstFPExt(ConstantVal.value(), ToType.type())); }
	public static Value ConstUIToFP(Value ConstantVal, TypeRef ToType) { return new Value(LLVMConstUIToFP(ConstantVal.value(), ToType.type())); }
	public static Value ConstSIToFP(Value ConstantVal, TypeRef ToType) { return new Value(LLVMConstSIToFP(ConstantVal.value(), ToType.type())); }
	public static Value ConstFPToUI(Value ConstantVal, TypeRef ToType) { return new Value(LLVMConstFPToUI(ConstantVal.value(), ToType.type())); }
	public static Value ConstFPToSI(Value ConstantVal, TypeRef ToType) { return new Value(LLVMConstFPToSI(ConstantVal.value(), ToType.type())); }
	public static Value ConstPtrToInt(Value ConstantVal, TypeRef ToType) { return new Value(LLVMConstPtrToInt(ConstantVal.value(), ToType.type())); }
	public static Value ConstIntToPtr(Value ConstantVal, TypeRef ToType) { return new Value(LLVMConstIntToPtr(ConstantVal.value(), ToType.type())); }
	public static Value ConstBitCast(Value ConstantVal, TypeRef ToType) { return new Value(LLVMConstBitCast(ConstantVal.value(), ToType.type())); }
	public static Value ConstZExtOrBitCast(Value ConstantVal, TypeRef ToType) { return new Value(LLVMConstZExtOrBitCast(ConstantVal.value(), ToType.type())); }
	public static Value ConstSExtOrBitCast(Value ConstantVal, TypeRef ToType) { return new Value(LLVMConstSExtOrBitCast(ConstantVal.value(), ToType.type())); }
	public static Value ConstTruncOrBitCast(Value ConstantVal, TypeRef ToType) { return new Value(LLVMConstTruncOrBitCast(ConstantVal.value(), ToType.type())); }
	public static Value ConstPointerCast(Value ConstantVal, TypeRef ToType) { return new Value(LLVMConstPointerCast(ConstantVal.value(), ToType.type())); }
	public static Value ConstIntCast(Value ConstantVal, TypeRef ToType, boolean isSigned) { return new Value(LLVMConstIntCast(ConstantVal.value(), ToType.type(), isSigned ? 1 : 0)); }
	public static Value ConstFPCast(Value ConstantVal, TypeRef ToType) { return new Value(LLVMConstFPCast(ConstantVal.value(), ToType.type())); }
	public static Value ConstSelect(Value ConstantCondition, Value ConstantIfTrue, Value ConstantIfFalse) { return new Value(LLVMConstSelect(ConstantCondition.value(), ConstantIfTrue.value(), ConstantIfFalse.value())); }
	public static Value ConstExtractElement(Value VectorConstant, Value IndexConstant) { return new Value(LLVMConstExtractElement(VectorConstant.value(), IndexConstant.value())); }
	public static Value ConstInsertElement(Value VectorConstant, Value ElementValueConstant, Value IndexConstant) { return new Value(LLVMConstInsertElement(VectorConstant.value(), ElementValueConstant.value(), IndexConstant.value())); }
	public static Value ConstShuffleVector(Value VectorAConstant, Value VectorBConstant, Value MaskConstant) { return new Value(LLVMConstShuffleVector(VectorAConstant.value(), VectorBConstant.value(), MaskConstant.value())); }
	public static Value ConstExtractValue(Value AggConstant, Pointer<Integer > IdxList, int NumIdx) { return new Value(LLVMConstExtractValue(AggConstant.value(), IdxList, NumIdx)); }
	public static Value ConstInsertValue(Value AggConstant, Value ElementValueConstant, Pointer<Integer > IdxList, int NumIdx) { return new Value(LLVMConstInsertValue(AggConstant.value(), ElementValueConstant.value(), IdxList, NumIdx)); }
	public static Value ConstInlineAsm(TypeRef Ty, String AsmString, String Constraints, boolean HasSideEffects, boolean IsAlignStack) { return new Value(LLVMConstInlineAsm(Ty.type(), Pointer.pointerToCString(AsmString), Pointer.pointerToCString(Constraints), HasSideEffects?1:0, IsAlignStack?1:0)); }
	public static Value BlockAddress(Value F, BasicBlock BB) { return new Value(LLVMBlockAddress(F.value(), BB.bb())); }

	public Module GetGlobalParent() { return new Module(LLVMGetGlobalParent(value)); }
	public boolean IsDeclaration() { return LLVMIsDeclaration(value) != 0; }
	public ValuedEnum<LLVMLinkage > GetLinkage() { return LLVMGetLinkage(value); }
	public void SetLinkage(IntValuedEnum<LLVMLinkage > Linkage) { LLVMSetLinkage(value, Linkage); }
	public String GetSection() { return LLVMGetSection(value).getCString(); }
	public void SetSection(String Section) { LLVMSetSection(value, Pointer.pointerToCString(Section)); }
	public ValuedEnum<LLVMVisibility > GetVisibility() { return LLVMGetVisibility(value); }
	public void SetVisibility(IntValuedEnum<LLVMVisibility > Viz) { LLVMSetVisibility(value, Viz); }
	public void GetAlignment() { LLVMGetAlignment(value); }
	public void SetAlignment(int Bytes) { LLVMSetAlignment(value, Bytes); }


	// this.value is GlobalVar
	public Value GetNextGlobal() { return new Value(LLVMGetNextGlobal(value)); }
	public Value GetPreviousGlobal() { return new Value(LLVMGetPreviousGlobal(value)); }

	// this.value is GlobalVar
	public void DeleteGlobal() { LLVMDeleteGlobal(value); }
	public Value GetInitializer() { return new Value(LLVMGetInitializer(value)); }
	public void SetInitializer(Value ConstantVal) { LLVMSetInitializer(value, ConstantVal.value()); }
	public boolean IsThreadLocal() { return LLVMIsThreadLocal(value) != 0; }
	public void SetThreadLocal(boolean IsThreadLocal) { LLVMSetThreadLocal(value,  IsThreadLocal? 1: 0); }
	public boolean IsGlobalConstant() { return LLVMIsGlobalConstant(value) != 0; }
	public void SetGlobalConstant(boolean IsConstant) { LLVMSetGlobalConstant(value, IsConstant? 1: 0); }

	// this.value is Fn
	public Value GetNextFunction() { return new Value(LLVMGetNextFunction(value)); }
	public Value GetPreviousFunction() { return new Value(LLVMGetPreviousFunction(value)); }

	// value is Fn
	public void DeleteFunction() { LLVMDeleteFunction(value); }
	public void GetIntrinsicID() { LLVMGetIntrinsicID(value); }
	public void GetFunctionCallConv() { LLVMGetFunctionCallConv(value); }
	public void SetFunctionCallConv(int CC) { LLVMSetFunctionCallConv(value, CC); }
	public String GetGC() { return LLVMGetGC(value).getCString(); }
	public void SetGC(String Name) { LLVMSetGC(value, Pointer.pointerToCString(Name)); }
	public void AddFunctionAttr(IntValuedEnum<LLVMAttribute > PA) { LLVMAddFunctionAttr(value, PA); }
	public ValuedEnum<LLVMAttribute > GetFunctionAttr() { return LLVMGetFunctionAttr(value); }
	public void RemoveFunctionAttr(IntValuedEnum<LLVMAttribute > PA) { LLVMRemoveFunctionAttr(value, PA); }
	public int  CountParams() { return LLVMCountParams(value); }
	public void GetParams(Pointer<LLVMValueRef> Params) { LLVMGetParams(value, Params); }

	public Value GetParam(int Index) { return new Value(LLVMGetParam(value, Index)); }

	public Value GetParamParent() { return new Value(LLVMGetParamParent(value)); }
	public Value GetFirstParam() { return new Value(LLVMGetFirstParam(value)); }
	public Value GetLastParam() { return new Value(LLVMGetLastParam(value)); }
	public Value GetNextParam() { return new Value(LLVMGetNextParam(value)); }
	public Value GetPreviousParam() { return new Value(LLVMGetPreviousParam(value)); }
	public void AddAttribute(IntValuedEnum<LLVMAttribute > PA) { LLVMAddAttribute(value, PA); }
	public void RemoveAttribute(IntValuedEnum<LLVMAttribute > PA) { LLVMRemoveAttribute(value, PA); }
	public ValuedEnum<LLVMAttribute > GetAttribute() { return LLVMGetAttribute(value); }
	public void SetParamAlignment(int align) { LLVMSetParamAlignment(value, align); }


	// BasicBlock
	public boolean  IsBasicBlock() { return LLVMValueIsBasicBlock(value) != 0; }

	public BasicBlock AsBasicBlock() { return new BasicBlock(LLVMValueAsBasicBlock(value)); }

	// Fn
	public int  CountBasicBlocks(){ return LLVMCountBasicBlocks(value); }
	public void GetBasicBlocks(Pointer<LLVMBasicBlockRef > BasicBlocks){ LLVMGetBasicBlocks(value, BasicBlocks); }
	public BasicBlock GetFirstBasicBlock() { return new BasicBlock(LLVMGetFirstBasicBlock(value)); }
	public BasicBlock GetLastBasicBlock() { return new BasicBlock(LLVMGetLastBasicBlock(value)); }

	public BasicBlock GetEntryBasicBlock() { return new BasicBlock(LLVMGetEntryBasicBlock(value)); }

	// this.value is Fn: in Fn, append a new BB and return it
	public BasicBlock AppendBasicBlockInContext(Context C, String Name) { return new BasicBlock(LLVMAppendBasicBlockInContext(C.context(), value, Pointer.pointerToCString(Name))); }

	// this.value is Fn: in Fn, append a new BB and return it
	public BasicBlock AppendBasicBlock(String Name) { return new BasicBlock(LLVMAppendBasicBlock(value, Pointer.pointerToCString(Name))); }


	// Instruction
	public BasicBlock GetInstructionParent() { return new BasicBlock(LLVMGetInstructionParent(value)); }

	public Value GetNextInstruction(Value Inst) { return new Value(LLVMGetNextInstruction(value)); }
	public Value GetPreviousInstruction(Value Inst) { return new Value(LLVMGetPreviousInstruction(value)); }

	public void SetInstructionCallConv(int CC) { LLVMSetInstructionCallConv(value, CC); }
	public int GetInstructionCallConv() { return LLVMGetInstructionCallConv(value); }
	public void AddInstrAttribute(int index, IntValuedEnum<LLVMAttribute > attribute) { LLVMAddInstrAttribute(value, index, attribute); }
	public void RemoveInstrAttribute(int index, IntValuedEnum<LLVMAttribute > attribute) { LLVMRemoveInstrAttribute(value, index, attribute); }
	public void SetInstrParamAlignment(int index, int align) { LLVMSetInstrParamAlignment(value, index, align); }

	public boolean IsTailCall() { return LLVMIsTailCall(value) != 0; }
	public void SetTailCall(boolean IsTailCall) { LLVMSetTailCall(value, IsTailCall? 1: 0); }

	// this.value is phi node
	//public void AddIncoming(Pointer<LLVMValueRef> IncomingValues, Pointer<LLVMBasicBlockRef > IncomingBlocks, int Count) {
	public void AddIncoming(Value[] IncomingValues, BasicBlock[] IncomingBlocks, int Count) {

		LLVMValueRef[] rawVals = new LLVMValueRef[IncomingValues.length];
		for (int i=0; i<IncomingValues.length; i++) { rawVals[i] = IncomingValues[i].value; }
		Pointer<LLVMValueRef> ptrVals = Pointer.pointerToArray(rawVals);

		LLVMBasicBlockRef[] rawBlocks = new LLVMBasicBlockRef[IncomingBlocks.length];
		for (int i=0; i<IncomingBlocks.length; i++) { rawBlocks[i] = IncomingBlocks[i].bb(); }
		Pointer<LLVMBasicBlockRef> ptrBlocks = Pointer.pointerToArray(rawBlocks);

		LLVMAddIncoming(value, ptrVals, ptrBlocks, Count);
	}
	public int CountIncoming()  { return LLVMCountIncoming(value); }
	public Value GetIncomingValue(int Index) { return new Value(LLVMGetIncomingValue(value, Index)); }
	public BasicBlock GetIncomingBlock(int Index) { return new BasicBlock(LLVMGetIncomingBlock(value, Index)); }

}
