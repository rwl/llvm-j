package org.llvm;

import org.bridj.IntValuedEnum;
import org.bridj.Pointer;
import org.bridj.ValuedEnum;

import static org.llvm.binding.LLVMLibrary.*;

/**
 * Represents an individual value in LLVM IR.
 */
public class Value {

    private LLVMValueRef value;

    /*package*/LLVMValueRef value() {
        return value;
    }

    /*package*/Value(LLVMValueRef value) {
        this.value = value;
    }

    public TypeRef typeOf() {
        return new TypeRef(LLVMTypeOf(value));
    }

    public String getValueName() {
        return LLVMGetValueName(value).getCString();
    }

    public void setValueName(String Name) {
        LLVMSetValueName(value, Pointer.pointerToCString(Name));
    }

    public void dumpValue() {
        LLVMDumpValue(value);
    }

    public void replaceAllUsesWith(Value NewVal) {
        LLVMReplaceAllUsesWith(value, NewVal.value());
    }

    public int hasMetadata() {
        return LLVMHasMetadata(value);
    }

    public Value getMetadata(int KindID) {
        return new Value(LLVMGetMetadata(value, KindID));
    }

    public void setMetadata(int KindID, Value Node) {
        LLVMSetMetadata(value, KindID, Node.value());
    }

    /**
     * Conversion functions. Return the input value if it is an instance of the
     * specified class, otherwise NULL. See llvm::dyn_cast_or_null<>.
     */
    public Value isArgument() {
        return new Value(LLVMIsAArgument(value));
    }

    public Value isABasicBlock() {
        return new Value(LLVMIsABasicBlock(value));
    }

    public Value isInlineAsm() {
        return new Value(LLVMIsAInlineAsm(value));
    }

    public Value isUser() {
        return new Value(LLVMIsAUser(value));
    }

    public Value isConstant() {
        return new Value(LLVMIsAConstant(value));
    }

    public Value isConstantAggregateZero() {
        return new Value(LLVMIsAConstantAggregateZero(value));
    }

    public Value isConstantArray() {
        return new Value(LLVMIsAConstantArray(value));
    }

    public Value isConstantExpr() {
        return new Value(LLVMIsAConstantExpr(value));
    }

    public Value isConstantFP() {
        return new Value(LLVMIsAConstantFP(value));
    }

    public Value isConstantInt() {
        return new Value(LLVMIsAConstantInt(value));
    }

    public Value isConstantPointerNull() {
        return new Value(LLVMIsAConstantPointerNull(value));
    }

    public Value isConstantStruct() {
        return new Value(LLVMIsAConstantStruct(value));
    }

    public Value isConstantVector() {
        return new Value(LLVMIsAConstantVector(value));
    }

    public Value isGlobalValue() {
        return new Value(LLVMIsAGlobalValue(value));
    }

    public Value isFunction() {
        return new Value(LLVMIsAFunction(value));
    }

    public Value isGlobalAlias() {
        return new Value(LLVMIsAGlobalAlias(value));
    }

    public Value isGlobalVariable() {
        return new Value(LLVMIsAGlobalVariable(value));
    }

    public Value isUndefValue() {
        return new Value(LLVMIsAUndefValue(value));
    }

    public Value isInstruction() {
        return new Value(LLVMIsAInstruction(value));
    }

    public Value isBinaryOperator() {
        return new Value(LLVMIsABinaryOperator(value));
    }

    public Value isCallInst() {
        return new Value(LLVMIsACallInst(value));
    }

    public Value isIntrinsicInst() {
        return new Value(LLVMIsAIntrinsicInst(value));
    }

    public Value isDbgInfoIntrinsic() {
        return new Value(LLVMIsADbgInfoIntrinsic(value));
    }

    public Value isDbgDeclareInst() {
        return new Value(LLVMIsADbgDeclareInst(value));
    }

    //	public Value isEHSelectorInst() { return new Value(LLVMIsAEHSelectorInst(value)); }
    public Value isMemIntrinsic() {
        return new Value(LLVMIsAMemIntrinsic(value));
    }

    public Value isMemCpyInst() {
        return new Value(LLVMIsAMemCpyInst(value));
    }

    public Value isMemMoveInst() {
        return new Value(LLVMIsAMemMoveInst(value));
    }

    public Value isMemSetInst() {
        return new Value(LLVMIsAMemSetInst(value));
    }

    public Value isCmpInst() {
        return new Value(LLVMIsACmpInst(value));
    }

    public Value isFCmpInst() {
        return new Value(LLVMIsAFCmpInst(value));
    }

    public Value isICmpInst() {
        return new Value(LLVMIsAICmpInst(value));
    }

    public Value isExtractElementInst() {
        return new Value(LLVMIsAExtractElementInst(value));
    }

    public Value isGetElementPtrInst() {
        return new Value(LLVMIsAGetElementPtrInst(value));
    }

    public Value isInsertElementInst() {
        return new Value(LLVMIsAInsertElementInst(value));
    }

    public Value isInsertValueInst() {
        return new Value(LLVMIsAInsertValueInst(value));
    }

    public Value isPHINode() {
        return new Value(LLVMIsAPHINode(value));
    }

    public Value isSelectInst() {
        return new Value(LLVMIsASelectInst(value));
    }

    public Value isShuffleVectorInst() {
        return new Value(LLVMIsAShuffleVectorInst(value));
    }

    public Value isStoreInst() {
        return new Value(LLVMIsAStoreInst(value));
    }

    public Value isTerminatorInst() {
        return new Value(LLVMIsATerminatorInst(value));
    }

    public Value isBranchInst() {
        return new Value(LLVMIsABranchInst(value));
    }

    public Value isInvokeInst() {
        return new Value(LLVMIsAInvokeInst(value));
    }

    public Value isReturnInst() {
        return new Value(LLVMIsAReturnInst(value));
    }

    public Value isSwitchInst() {
        return new Value(LLVMIsASwitchInst(value));
    }

    public Value isUnreachableInst() {
        return new Value(LLVMIsAUnreachableInst(value));
    }

    //	public Value isUnwindInst() { return new Value(LLVMIsAUnwindInst(value)); }
    public Value isUnaryInstruction() {
        return new Value(LLVMIsAUnaryInstruction(value));
    }

    public Value isAllocaInst() {
        return new Value(LLVMIsAAllocaInst(value));
    }

    public Value isCastInst() {
        return new Value(LLVMIsACastInst(value));
    }

    public Value isBitCastInst() {
        return new Value(LLVMIsABitCastInst(value));
    }

    public Value isFPExtInst() {
        return new Value(LLVMIsAFPExtInst(value));
    }

    public Value isFPToSIInst() {
        return new Value(LLVMIsAFPToSIInst(value));
    }

    public Value isFPToUIInst() {
        return new Value(LLVMIsAFPToUIInst(value));
    }

    public Value isFPTruncInst() {
        return new Value(LLVMIsAFPTruncInst(value));
    }

    public Value isIntToPtrInst() {
        return new Value(LLVMIsAIntToPtrInst(value));
    }

    public Value isPtrToIntInst() {
        return new Value(LLVMIsAPtrToIntInst(value));
    }

    public Value isSExtInst() {
        return new Value(LLVMIsASExtInst(value));
    }

    public Value isSIToFPInst() {
        return new Value(LLVMIsASIToFPInst(value));
    }

    public Value isTruncInst() {
        return new Value(LLVMIsATruncInst(value));
    }

    public Value isUIToFPInst() {
        return new Value(LLVMIsAUIToFPInst(value));
    }

    public Value isZExtInst() {
        return new Value(LLVMIsAZExtInst(value));
    }

    public Value isExtractValueInst() {
        return new Value(LLVMIsAExtractValueInst(value));
    }

    public Value isLoadInst() {
        return new Value(LLVMIsALoadInst(value));
    }

    public Value isVAArgInst() {
        return new Value(LLVMIsAVAArgInst(value));
    }

    public boolean IsConstant() {
        return LLVMIsConstant(value) != 0;
    }

    public boolean IsNull() {
        return LLVMIsNull(value) != 0;
    }

    public boolean IsUndef() {
        return LLVMIsUndef(value) != 0;
    }

    // TODO: move
    public static native LLVMUseRef LLVMGetFirstUse(LLVMValueRef Val);

    // TODO: move
    public static native LLVMUseRef LLVMGetNextUse(LLVMUseRef U);

    // TODO: move
    public static Value getUser(LLVMUseRef U) {
        return new Value(LLVMGetUser(U));
    }

    // TODO: move
    public static Value getUsedValue(LLVMUseRef U) {
        return new Value(LLVMGetUsedValue(U));
    }

    public Value getOperand(int Index) {
        return new Value(LLVMGetOperand(value, Index));
    }

    public void setOperand(int Index, Value Val) {
        LLVMSetOperand(value, Index, Val.value());
    }

    public int getNumOperands() {
        return LLVMGetNumOperands(value);
    }

    // MetaData
    /*public ValueRef MDStringInContext(LLVMContextRef C, Pointer<Byte> Str,
            int SLen) {
        return new ValueRef(LLVMMDStringInContext(value));
    }

    public ValueRef MDString(Pointer<Byte> Str, int SLen) {
        return new ValueRef(LLVMMDString(value));
    }

    public ValueRef MDNodeInContext(LLVMContextRef C,
            Pointer<LLVMValueRef> Vals, int Count) {
        return new ValueRef(LLVMMDNodeInContext(value));
    }

    public ValueRef MDNode(Pointer<LLVMValueRef> Vals, int Count) {
        return new ValueRef(LLVMMDNode(value));
    }*/

    public long constIntGetZExtValue() {
        return LLVMConstIntGetZExtValue(value);
    }

    public long constIntGetSExtValue() {
        return LLVMConstIntGetSExtValue(value);
    }

    public static Value constStringInContext(Context C, String Str, int Length,
            boolean DontNullTerminate) {
        return new Value(LLVMConstStringInContext(C.context(),
                Pointer.pointerToCString(Str), Length, DontNullTerminate ? 1
                        : 0));
    }

    /*public ValueRef constStructInContext(Context C, ValueRef[] ConstantVals,
            int Count, boolean Packed) {
        return new ValueRef(LLVMConstStructInContext(C.context(), ConstantVals,
                Count, Packed ? 1 : 0));
    }*/

    public static Value constString(String Str, int Length,
            boolean DontNullTerminate) {
        return new Value(LLVMConstString(Pointer.pointerToCString(Str), Length,
                DontNullTerminate ? 1 : 0));
    }

    // TODO: change Pointer to array
    public static Value constArray(TypeRef ElementTy,
            Pointer<LLVMValueRef> ConstantVals, int Length) {
        return new Value(LLVMConstArray(ElementTy.type(), ConstantVals, Length));
    }

    // TODO: change Pointer to array
    public static Value constStruct(Pointer<LLVMValueRef> ConstantVals,
            int Count, boolean Packed) {
        return new Value(LLVMConstStruct(ConstantVals, Count, Packed ? 1 : 0));
    }

    // TODO: change Pointer to array
    public static Value constVector(Pointer<LLVMValueRef> ScalarConstantVals,
            int Size) {
        return new Value(LLVMConstVector(ScalarConstantVals, Size));
    }

    public static native ValuedEnum<LLVMOpcode> GetConstOpcode(
            LLVMValueRef ConstantVal);

    public Value constNeg() {
        return new Value(LLVMConstNeg(value));
    }

    public Value constNSWNeg() {
        return new Value(LLVMConstNSWNeg(value));
    }

    public Value constNUWNeg() {
        return new Value(LLVMConstNUWNeg(value));
    }

    public Value constFNeg() {
        return new Value(LLVMConstFNeg(value));
    }

    public Value constNot() {
        return new Value(LLVMConstNot(value));
    }

    public static Value constAdd(Value LHSConstant, Value RHSConstant) {
        return new Value(LLVMConstAdd(LHSConstant.value(), RHSConstant.value()));
    }

    public static Value constNSWAdd(Value LHSConstant, Value RHSConstant) {
        return new Value(LLVMConstNSWAdd(LHSConstant.value(),
                RHSConstant.value()));
    }

    public static Value constNUWAdd(Value LHSConstant, Value RHSConstant) {
        return new Value(LLVMConstNUWAdd(LHSConstant.value(),
                RHSConstant.value()));
    }

    public static Value constFAdd(Value LHSConstant, Value RHSConstant) {
        return new Value(
                LLVMConstFAdd(LHSConstant.value(), RHSConstant.value()));
    }

    public static Value constSub(Value LHSConstant, Value RHSConstant) {
        return new Value(LLVMConstSub(LHSConstant.value(), RHSConstant.value()));
    }

    public static Value constNSWSub(Value LHSConstant, Value RHSConstant) {
        return new Value(LLVMConstNSWSub(LHSConstant.value(),
                RHSConstant.value()));
    }

    public static Value constNUWSub(Value LHSConstant, Value RHSConstant) {
        return new Value(LLVMConstNUWSub(LHSConstant.value(),
                RHSConstant.value()));
    }

    public static Value constFSub(Value LHSConstant, Value RHSConstant) {
        return new Value(
                LLVMConstFSub(LHSConstant.value(), RHSConstant.value()));
    }

    public static Value constMul(Value LHSConstant, Value RHSConstant) {
        return new Value(LLVMConstMul(LHSConstant.value(), RHSConstant.value()));
    }

    public static Value constNSWMul(Value LHSConstant, Value RHSConstant) {
        return new Value(LLVMConstNSWMul(LHSConstant.value(),
                RHSConstant.value()));
    }

    public static Value constNUWMul(Value LHSConstant, Value RHSConstant) {
        return new Value(LLVMConstNUWMul(LHSConstant.value(),
                RHSConstant.value()));
    }

    public static Value constFMul(Value LHSConstant, Value RHSConstant) {
        return new Value(
                LLVMConstFMul(LHSConstant.value(), RHSConstant.value()));
    }

    public static Value constUDiv(Value LHSConstant, Value RHSConstant) {
        return new Value(
                LLVMConstUDiv(LHSConstant.value(), RHSConstant.value()));
    }

    public static Value constSDiv(Value LHSConstant, Value RHSConstant) {
        return new Value(
                LLVMConstSDiv(LHSConstant.value(), RHSConstant.value()));
    }

    public static Value constExactSDiv(Value LHSConstant, Value RHSConstant) {
        return new Value(LLVMConstExactSDiv(LHSConstant.value(),
                RHSConstant.value()));
    }

    public static Value constFDiv(Value LHSConstant, Value RHSConstant) {
        return new Value(
                LLVMConstFDiv(LHSConstant.value(), RHSConstant.value()));
    }

    public static Value constURem(Value LHSConstant, Value RHSConstant) {
        return new Value(
                LLVMConstURem(LHSConstant.value(), RHSConstant.value()));
    }

    public static Value constSRem(Value LHSConstant, Value RHSConstant) {
        return new Value(
                LLVMConstSRem(LHSConstant.value(), RHSConstant.value()));
    }

    public static Value constFRem(Value LHSConstant, Value RHSConstant) {
        return new Value(
                LLVMConstFRem(LHSConstant.value(), RHSConstant.value()));
    }

    public static Value constAnd(Value LHSConstant, Value RHSConstant) {
        return new Value(LLVMConstAnd(LHSConstant.value(), RHSConstant.value()));
    }

    public static Value constOr(Value LHSConstant, Value RHSConstant) {
        return new Value(LLVMConstOr(LHSConstant.value(), RHSConstant.value()));
    }

    public static Value constXor(Value LHSConstant, Value RHSConstant) {
        return new Value(LLVMConstXor(LHSConstant.value(), RHSConstant.value()));
    }

    public static Value constICmp(IntValuedEnum<LLVMIntPredicate> Predicate,
            Value LHSConstant, Value RHSConstant) {
        return new Value(LLVMConstICmp(Predicate, LHSConstant.value(),
                RHSConstant.value()));
    }

    public static Value constFCmp(IntValuedEnum<LLVMRealPredicate> Predicate,
            Value LHSConstant, Value RHSConstant) {
        return new Value(LLVMConstFCmp(Predicate, LHSConstant.value(),
                RHSConstant.value()));
    }

    public static Value constShl(Value LHSConstant, Value RHSConstant) {
        return new Value(LLVMConstShl(LHSConstant.value(), RHSConstant.value()));
    }

    public static Value constLShr(Value LHSConstant, Value RHSConstant) {
        return new Value(
                LLVMConstLShr(LHSConstant.value(), RHSConstant.value()));
    }

    public static Value constAShr(Value LHSConstant, Value RHSConstant) {
        return new Value(
                LLVMConstAShr(LHSConstant.value(), RHSConstant.value()));
    }

    // TODO: fix Pointer (change to array)
    public Value ConstGEP(Value ConstantVal,
            Pointer<LLVMValueRef> ConstantIndices, int NumIndices) {
        return new Value(LLVMConstGEP(ConstantVal.value(), ConstantIndices,
                NumIndices));
    }

    // TODO: fix Pointer (change to array)
    public static Value constInBoundsGEP(Value ConstantVal,
            Pointer<LLVMValueRef> ConstantIndices, int NumIndices) {
        return new Value(LLVMConstInBoundsGEP(ConstantVal.value(),
                ConstantIndices, NumIndices));
    }

    public static Value constTrunc(Value ConstantVal, TypeRef ToType) {
        return new Value(LLVMConstTrunc(ConstantVal.value(), ToType.type()));
    }

    public static Value constSExt(Value ConstantVal, TypeRef ToType) {
        return new Value(LLVMConstSExt(ConstantVal.value(), ToType.type()));
    }

    public static Value constZExt(Value ConstantVal, TypeRef ToType) {
        return new Value(LLVMConstZExt(ConstantVal.value(), ToType.type()));
    }

    public static Value constFPTrunc(Value ConstantVal, TypeRef ToType) {
        return new Value(LLVMConstFPTrunc(ConstantVal.value(), ToType.type()));
    }

    public static Value constFPExt(Value ConstantVal, TypeRef ToType) {
        return new Value(LLVMConstFPExt(ConstantVal.value(), ToType.type()));
    }

    public static Value constUIToFP(Value ConstantVal, TypeRef ToType) {
        return new Value(LLVMConstUIToFP(ConstantVal.value(), ToType.type()));
    }

    public static Value constSIToFP(Value ConstantVal, TypeRef ToType) {
        return new Value(LLVMConstSIToFP(ConstantVal.value(), ToType.type()));
    }

    public static Value constFPToUI(Value ConstantVal, TypeRef ToType) {
        return new Value(LLVMConstFPToUI(ConstantVal.value(), ToType.type()));
    }

    public static Value constFPToSI(Value ConstantVal, TypeRef ToType) {
        return new Value(LLVMConstFPToSI(ConstantVal.value(), ToType.type()));
    }

    public static Value constPtrToInt(Value ConstantVal, TypeRef ToType) {
        return new Value(LLVMConstPtrToInt(ConstantVal.value(), ToType.type()));
    }

    public static Value constIntToPtr(Value ConstantVal, TypeRef ToType) {
        return new Value(LLVMConstIntToPtr(ConstantVal.value(), ToType.type()));
    }

    public static Value constBitCast(Value ConstantVal, TypeRef ToType) {
        return new Value(LLVMConstBitCast(ConstantVal.value(), ToType.type()));
    }

    public static Value constZExtOrBitCast(Value ConstantVal, TypeRef ToType) {
        return new Value(LLVMConstZExtOrBitCast(ConstantVal.value(),
                ToType.type()));
    }

    public static Value constSExtOrBitCast(Value ConstantVal, TypeRef ToType) {
        return new Value(LLVMConstSExtOrBitCast(ConstantVal.value(),
                ToType.type()));
    }

    public static Value constTruncOrBitCast(Value ConstantVal, TypeRef ToType) {
        return new Value(LLVMConstTruncOrBitCast(ConstantVal.value(),
                ToType.type()));
    }

    public static Value constPointerCast(Value ConstantVal, TypeRef ToType) {
        return new Value(LLVMConstPointerCast(ConstantVal.value(),
                ToType.type()));
    }

    public static Value constIntCast(Value ConstantVal, TypeRef ToType,
            boolean isSigned) {
        return new Value(LLVMConstIntCast(ConstantVal.value(), ToType.type(),
                isSigned ? 1 : 0));
    }

    public static Value constFPCast(Value ConstantVal, TypeRef ToType) {
        return new Value(LLVMConstFPCast(ConstantVal.value(), ToType.type()));
    }

    public static Value constSelect(Value ConstantCondition,
            Value ConstantIfTrue, Value ConstantIfFalse) {
        return new Value(LLVMConstSelect(ConstantCondition.value(),
                ConstantIfTrue.value(), ConstantIfFalse.value()));
    }

    public static Value constExtractElement(Value VectorConstant,
            Value IndexConstant) {
        return new Value(LLVMConstExtractElement(VectorConstant.value(),
                IndexConstant.value()));
    }

    public static Value constInsertElement(Value VectorConstant,
            Value ElementValueConstant, Value IndexConstant) {
        return new Value(LLVMConstInsertElement(VectorConstant.value(),
                ElementValueConstant.value(), IndexConstant.value()));
    }

    public static Value constShuffleVector(Value VectorAConstant,
            Value VectorBConstant, Value MaskConstant) {
        return new Value(LLVMConstShuffleVector(VectorAConstant.value(),
                VectorBConstant.value(), MaskConstant.value()));
    }

    public static Value constExtractValue(Value AggConstant,
            Pointer<Integer> IdxList, int NumIdx) {
        return new Value(LLVMConstExtractValue(AggConstant.value(), IdxList,
                NumIdx));
    }

    public static Value constInsertValue(Value AggConstant,
            Value ElementValueConstant, Pointer<Integer> IdxList, int NumIdx) {
        return new Value(LLVMConstInsertValue(AggConstant.value(),
                ElementValueConstant.value(), IdxList, NumIdx));
    }

    public static Value constInlineAsm(TypeRef Ty, String AsmString,
            String Constraints, boolean HasSideEffects, boolean IsAlignStack) {
        return new Value(LLVMConstInlineAsm(Ty.type(),
                Pointer.pointerToCString(AsmString),
                Pointer.pointerToCString(Constraints), HasSideEffects ? 1 : 0,
                IsAlignStack ? 1 : 0));
    }

    public static Value blockAddress(Value F, BasicBlock BB) {
        return new Value(LLVMBlockAddress(F.value(), BB.bb()));
    }

    public Module getGlobalParent() {
        return new Module(LLVMGetGlobalParent(value));
    }

    public boolean isDeclaration() {
        return LLVMIsDeclaration(value) != 0;
    }

    public ValuedEnum<LLVMLinkage> getLinkage() {
        return LLVMGetLinkage(value);
    }

    public void setLinkage(IntValuedEnum<LLVMLinkage> Linkage) {
        LLVMSetLinkage(value, Linkage);
    }

    public String getSection() {
        return LLVMGetSection(value).getCString();
    }

    public void setSection(String Section) {
        LLVMSetSection(value, Pointer.pointerToCString(Section));
    }

    public ValuedEnum<LLVMVisibility> getVisibility() {
        return LLVMGetVisibility(value);
    }

    public void setVisibility(IntValuedEnum<LLVMVisibility> Viz) {
        LLVMSetVisibility(value, Viz);
    }

    public void getAlignment() {
        LLVMGetAlignment(value);
    }

    public void setAlignment(int Bytes) {
        LLVMSetAlignment(value, Bytes);
    }

    // this.value is GlobalVar
    public Value getNextGlobal() {
        return new Value(LLVMGetNextGlobal(value));
    }

    public Value getPreviousGlobal() {
        return new Value(LLVMGetPreviousGlobal(value));
    }

    // this.value is GlobalVar
    public void deleteGlobal() {
        LLVMDeleteGlobal(value);
    }

    public Value getInitializer() {
        return new Value(LLVMGetInitializer(value));
    }

    public void setInitializer(Value ConstantVal) {
        LLVMSetInitializer(value, ConstantVal.value());
    }

    public boolean isThreadLocal() {
        return LLVMIsThreadLocal(value) != 0;
    }

    public void setThreadLocal(boolean IsThreadLocal) {
        LLVMSetThreadLocal(value, IsThreadLocal ? 1 : 0);
    }

    public boolean isGlobalConstant() {
        return LLVMIsGlobalConstant(value) != 0;
    }

    public void setGlobalConstant(boolean IsConstant) {
        LLVMSetGlobalConstant(value, IsConstant ? 1 : 0);
    }

    // this.value is Fn
    public Value getNextFunction() {
        return new Value(LLVMGetNextFunction(value));
    }

    public Value getPreviousFunction() {
        return new Value(LLVMGetPreviousFunction(value));
    }

    // value is Fn
    public void deleteFunction() {
        LLVMDeleteFunction(value);
    }

    public void getIntrinsicID() {
        LLVMGetIntrinsicID(value);
    }

    public void getFunctionCallConv() {
        LLVMGetFunctionCallConv(value);
    }

    public void setFunctionCallConv(int CC) {
        LLVMSetFunctionCallConv(value, CC);
    }

    public String getGC() {
        return LLVMGetGC(value).getCString();
    }

    public void setGC(String Name) {
        LLVMSetGC(value, Pointer.pointerToCString(Name));
    }

    public void addFunctionAttr(IntValuedEnum<LLVMAttribute> PA) {
        LLVMAddFunctionAttr(value, PA);
    }

    public ValuedEnum<LLVMAttribute> getFunctionAttr() {
        return LLVMGetFunctionAttr(value);
    }

    public void removeFunctionAttr(IntValuedEnum<LLVMAttribute> PA) {
        LLVMRemoveFunctionAttr(value, PA);
    }

    public int countParams() {
        return LLVMCountParams(value);
    }

    public void getParams(Pointer<LLVMValueRef> Params) {
        LLVMGetParams(value, Params);
    }

    public Value getParam(int Index) {
        return new Value(LLVMGetParam(value, Index));
    }

    public Value getParamParent() {
        return new Value(LLVMGetParamParent(value));
    }

    public Value getFirstParam() {
        return new Value(LLVMGetFirstParam(value));
    }

    public Value getLastParam() {
        return new Value(LLVMGetLastParam(value));
    }

    public Value getNextParam() {
        return new Value(LLVMGetNextParam(value));
    }

    public Value getPreviousParam() {
        return new Value(LLVMGetPreviousParam(value));
    }

    public void addAttribute(IntValuedEnum<LLVMAttribute> PA) {
        LLVMAddAttribute(value, PA);
    }

    public void removeAttribute(IntValuedEnum<LLVMAttribute> PA) {
        LLVMRemoveAttribute(value, PA);
    }

    public ValuedEnum<LLVMAttribute> getAttribute() {
        return LLVMGetAttribute(value);
    }

    public void setParamAlignment(int align) {
        LLVMSetParamAlignment(value, align);
    }

    // BasicBlock
    public boolean isBasicBlock() {
        return LLVMValueIsBasicBlock(value) != 0;
    }

    public BasicBlock asBasicBlock() {
        return new BasicBlock(LLVMValueAsBasicBlock(value));
    }

    // Fn
    public int countBasicBlocks() {
        return LLVMCountBasicBlocks(value);
    }

    public void getBasicBlocks(Pointer<LLVMBasicBlockRef> BasicBlocks) {
        LLVMGetBasicBlocks(value, BasicBlocks);
    }

    public BasicBlock getFirstBasicBlock() {
        return new BasicBlock(LLVMGetFirstBasicBlock(value));
    }

    public BasicBlock getLastBasicBlock() {
        return new BasicBlock(LLVMGetLastBasicBlock(value));
    }

    public BasicBlock getEntryBasicBlock() {
        return new BasicBlock(LLVMGetEntryBasicBlock(value));
    }

    // this.value is Fn: in Fn, append a new BB and return it
    public BasicBlock appendBasicBlockInContext(Context C, String Name) {
        return new BasicBlock(LLVMAppendBasicBlockInContext(C.context(), value,
                Pointer.pointerToCString(Name)));
    }

    // this.value is Fn: in Fn, append a new BB and return it
    public BasicBlock appendBasicBlock(String Name) {
        return new BasicBlock(LLVMAppendBasicBlock(value,
                Pointer.pointerToCString(Name)));
    }

    // Instruction
    public BasicBlock getInstructionParent() {
        return new BasicBlock(LLVMGetInstructionParent(value));
    }

    public Value getNextInstruction(Value Inst) {
        return new Value(LLVMGetNextInstruction(value));
    }

    public Value getPreviousInstruction(Value Inst) {
        return new Value(LLVMGetPreviousInstruction(value));
    }

    public void setInstructionCallConv(int CC) {
        LLVMSetInstructionCallConv(value, CC);
    }

    public int getInstructionCallConv() {
        return LLVMGetInstructionCallConv(value);
    }

    public void addInstrAttribute(int index,
            IntValuedEnum<LLVMAttribute> attribute) {
        LLVMAddInstrAttribute(value, index, attribute);
    }

    public void removeInstrAttribute(int index,
            IntValuedEnum<LLVMAttribute> attribute) {
        LLVMRemoveInstrAttribute(value, index, attribute);
    }

    public void setInstrParamAlignment(int index, int align) {
        LLVMSetInstrParamAlignment(value, index, align);
    }

    public boolean isTailCall() {
        return LLVMIsTailCall(value) != 0;
    }

    public void setTailCall(boolean IsTailCall) {
        LLVMSetTailCall(value, IsTailCall ? 1 : 0);
    }

    // this.value is phi node
    /*public void AddIncoming(Pointer<LLVMValueRef> IncomingValues,
            Pointer<LLVMBasicBlockRef > IncomingBlocks, int Count) {*/
    public void addIncoming(Value[] IncomingValues,
            BasicBlock[] IncomingBlocks, int Count) {

        LLVMValueRef[] rawVals = new LLVMValueRef[IncomingValues.length];
        for (int i = 0; i < IncomingValues.length; i++) {
            rawVals[i] = IncomingValues[i].value;
        }
        Pointer<LLVMValueRef> ptrVals = Pointer.pointerToArray(rawVals);

        LLVMBasicBlockRef[] rawBlocks = new LLVMBasicBlockRef[IncomingBlocks.length];
        for (int i = 0; i < IncomingBlocks.length; i++) {
            rawBlocks[i] = IncomingBlocks[i].bb();
        }
        Pointer<LLVMBasicBlockRef> ptrBlocks = Pointer
                .pointerToArray(rawBlocks);

        LLVMAddIncoming(value, ptrVals, ptrBlocks, Count);
    }

    public int countIncoming() {
        return LLVMCountIncoming(value);
    }

    public Value getIncomingValue(int Index) {
        return new Value(LLVMGetIncomingValue(value, Index));
    }

    public BasicBlock getIncomingBlock(int Index) {
        return new BasicBlock(LLVMGetIncomingBlock(value, Index));
    }

}
