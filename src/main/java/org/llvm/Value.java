package org.llvm;

import org.bridj.IntValuedEnum;
import org.bridj.Pointer;

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

    /**
     * Obtain the type of a value.<br>
     * * @see llvm::Value::getType()
     */
    public TypeRef typeOf() {
        return new TypeRef(LLVMTypeOf(value));
    }

    /**
     * Obtain the string name of a value.<br>
     *
     * @see llvm::Value::getName()
     */
    public String getValueName() {
        return LLVMGetValueName(value).getCString();
    }

    /**
     * Set the string name of a value.<br>
     *
     * @see llvm::Value::setName()
     */
    public void setValueName(String name) {
        LLVMSetValueName(value, Pointer.pointerToCString(name));
    }

    /**
     * Dump a representation of a value to stderr.<br>
     *
     * @see llvm::Value::dump()
     */
    public void dumpValue() {
        LLVMDumpValue(value);
    }

    /**
     * Replace all uses of a value with another one.<br>
     *
     * @see llvm::Value::replaceAllUsesWith()
     */
    public void replaceAllUsesWith(Value newVal) {
        LLVMReplaceAllUsesWith(value, newVal.value());
    }

    /**
     * Determine whether an instruction has any metadata attached.
     */
    public int hasMetadata() {
        return LLVMHasMetadata(value);
    }

    /**
     * Return metadata associated with an instruction value.
     */
    public Value getMetadata(int kindID) {
        return new Value(LLVMGetMetadata(value, kindID));
    }

    /**
     * Set metadata associated with an instruction value.
     */
    public void setMetadata(int kindID, Value node) {
        LLVMSetMetadata(value, kindID, node.value());
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

    public Value isAConstant() {
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

    /**
     * Determine whether the specified constant instance is constant.
     */
    public boolean isConstant() {
        return LLVMIsConstant(value) != 0;
    }

    /**
     * Determine whether a value instance is null.<br>
     *
     * @see llvm::Constant::isNullValue()
     */
    public boolean isNull() {
        return LLVMIsNull(value) != 0;
    }

    /**
     * Determine whether a value instance is undefined.
     */
    public boolean isUndef() {
        return LLVMIsUndef(value) != 0;
    }

    // TODO: move
    public static native LLVMUseRef LLVMGetFirstUse(LLVMValueRef val);

    // TODO: move
    public static native LLVMUseRef LLVMGetNextUse(LLVMUseRef u);

    /**
     * Obtain the user value for a user.<br>
     * The returned value corresponds to a llvm::User type.<br>
     *
     * @see llvm::Use::getUser()
     */
    // TODO: move
    public static Value getUser(LLVMUseRef u) {
        return new Value(LLVMGetUser(u));
    }

    /**
     * Obtain the value this use corresponds to.<br>
     *
     * @see llvm::Use::get()
     */
    // TODO: move
    public static Value getUsedValue(LLVMUseRef u) {
        return new Value(LLVMGetUsedValue(u));
    }

    /**
     * Obtain an operand at a specific index in a llvm::User value.<br>
     *
     * @see llvm::User::getOperand()
     */
    public Value getOperand(int index) {
        return new Value(LLVMGetOperand(value, index));
    }

    /**
     * Set an operand at a specific index in a llvm::User value.<br>
     *
     * @see llvm::User::setOperand()
     */
    public void setOperand(int index, Value val) {
        LLVMSetOperand(value, index, val.value());
    }

    /**
     * Obtain the number of operands in a llvm::User value.<br>
     *
     * @see llvm::User::getNumOperands()
     */
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

    /**
     * Obtain the zero extended value for an integer constant value.<br>
     *
     * @see llvm::ConstantInt::getZExtValue()
     */
    public long constIntGetZExtValue() {
        return LLVMConstIntGetZExtValue(value);
    }

    /**
     * Obtain the sign extended value for an integer constant value.<br>
     *
     * @see llvm::ConstantInt::getSExtValue()
     */
    public long constIntGetSExtValue() {
        return LLVMConstIntGetSExtValue(value);
    }

    /**
     * Create a ConstantDataSequential and initialize it with a string.<br>
     *
     * @see llvm::ConstantDataArray::getString()
     */
    public static Value constStringInContext(Context c, String str, int length,
            boolean dontNullTerminate) {
        return new Value(LLVMConstStringInContext(c.context(),
                Pointer.pointerToCString(str), length, dontNullTerminate ? 1
                        : 0));
    }

    /*public ValueRef constStructInContext(Context C, ValueRef[] ConstantVals,
            int Count, boolean Packed) {
        return new ValueRef(LLVMConstStructInContext(C.context(), ConstantVals,
                Count, Packed ? 1 : 0));
    }*/

    /**
     * Create a ConstantDataSequential with string content in the global
     * context.<br>
     * This is the same as LLVMConstStringInContext except it operates on the<br>
     * global context.<br>
     *
     * @see LLVMConstStringInContext()<br>
     * @see llvm::ConstantDataArray::getString()
     */
    public static Value constString(String str, int length,
            boolean dontNullTerminate) {
        return new Value(LLVMConstString(Pointer.pointerToCString(str), length,
                dontNullTerminate ? 1 : 0));
    }

    /**
     * Create a ConstantArray from values.<br>
     *
     * @see llvm::ConstantArray::get()
     */
    // TODO: change Pointer to array
    public static Value constArray(TypeRef elementTy,
            Pointer<LLVMValueRef> constantVals, int length) {
        return new Value(LLVMConstArray(elementTy.type(), constantVals, length));
    }

    /**
     * Create a ConstantStruct in the global Context.<br>
     * This is the same as LLVMConstStructInContext except it operates on the<br>
     * global Context.<br>
     *
     * @see LLVMConstStructInContext()
     */
    public static Value constStruct(Pointer<LLVMValueRef> constantVals,
            int count, boolean packed) {
        return new Value(LLVMConstStruct(constantVals, count, packed ? 1 : 0));
    }

    /**
     * Create a non-packed constant structure in the global context.
     */
    public static Value constStruct(Value... constantVals) {
        int n = constantVals.length;
        LLVMValueRef[] inner = new LLVMValueRef[n];
        for (int i = 0; i < n; i++) {
            inner[i] = constantVals[i].value;
        }

        Pointer<LLVMValueRef> array = Pointer.allocateTypedPointers(
                LLVMValueRef.class, constantVals.length);
        array.setArray(inner);

        return new Value(LLVMConstStruct(array, n, 0));
    }

    /**
     * Create a ConstantVector from values.<br>
     *
     * @see llvm::ConstantVector::get()
     */
    // TODO: change Pointer to array
    public static Value constVector(Pointer<LLVMValueRef> ScalarConstantVals,
            int Size) {
        return new Value(LLVMConstVector(ScalarConstantVals, Size));
    }

    public static native IntValuedEnum<LLVMOpcode> GetConstOpcode(
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

    public IntValuedEnum<LLVMLinkage> getLinkage() {
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

    public IntValuedEnum<LLVMVisibility> getVisibility() {
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

    /**
     * Advance a Function iterator to the next Function.<br>
     * Returns null if the iterator was already at the end and there are no more<br>
     * functions.
     */
    public Value getNextFunction() {
        return new Value(LLVMGetNextFunction(value));
    }

    /**
     * Decrement a Function iterator to the previous Function.<br>
     * Returns null if the iterator was already at the beginning and there are<br>
     * no previous functions.
     */
    public Value getPreviousFunction() {
        return new Value(LLVMGetPreviousFunction(value));
    }

    /**
     * Remove a function from its containing module and deletes it.<br>
     *
     * @see llvm::Function::eraseFromParent()
     */
    public void deleteFunction() {
        LLVMDeleteFunction(value);
    }

    /**
     * Obtain the ID number from a function instance.<br>
     *
     * @see llvm::Function::getIntrinsicID()
     */
    public void getIntrinsicID() {
        LLVMGetIntrinsicID(value);
    }

    /**
     * Obtain the calling function of a function.<br>
     * The returned value corresponds to the LLVMCallConv enumeration.<br>
     *
     * @see llvm::Function::getCallingConv()
     */
    public void getFunctionCallConv() {
        LLVMGetFunctionCallConv(value);
    }

    /**
     * Set the calling convention of a function.<br>
     *
     * @see llvm::Function::setCallingConv()<br>
     * @param CC
     *            LLVMCallConv to set calling convention to
     */
    public void setFunctionCallConv(int CC) {
        LLVMSetFunctionCallConv(value, CC);
    }

    /**
     * Obtain the name of the garbage collector to use during code<br>
     * generation.<br>
     *
     * @see llvm::Function::getGC()
     */
    public String getGC() {
        return LLVMGetGC(value).getCString();
    }

    /**
     * Define the garbage collector to use during code generation.<br>
     *
     * @see llvm::Function::setGC()
     */
    public void setGC(String Name) {
        LLVMSetGC(value, Pointer.pointerToCString(Name));
    }

    /**
     * Add an attribute to a function.<br>
     *
     * @see llvm::Function::addAttribute()
     */
    public void addFunctionAttr(IntValuedEnum<LLVMAttribute> PA) {
        LLVMAddFunctionAttr(value, PA);
    }

    /**
     * Obtain an attribute from a function.<br>
     *
     * @see llvm::Function::getAttributes()
     */
    public IntValuedEnum<LLVMAttribute> getFunctionAttr() {
        return LLVMGetFunctionAttr(value);
    }

    /**
     * Remove an attribute from a function.
     */
    public void removeFunctionAttr(IntValuedEnum<LLVMAttribute> PA) {
        LLVMRemoveFunctionAttr(value, PA);
    }

    /**
     * Obtain the number of parameters in a function.<br>
     *
     * @see llvm::Function::arg_size()
     */
    public int countParams() {
        return LLVMCountParams(value);
    }

    /**
     * Obtain the parameters in a function.<br>
     * The takes a pointer to a pre-allocated array of LLVMValueRef that is<br>
     * at least LLVMCountParams() long. This array will be filled with<br>
     * LLVMValueRef instances which correspond to the parameters the<br>
     * function receives. Each LLVMValueRef corresponds to a llvm::Argument<br>
     * instance.<br>
     *
     * @see llvm::Function::arg_begin()
     */
    public void getParams(Pointer<LLVMValueRef> Params) {
        LLVMGetParams(value, Params);
    }

    /**
     * Obtain the parameter at the specified index.<br>
     * Parameters are indexed from 0.<br>
     *
     * @see llvm::Function::arg_begin()
     */
    public Value getParam(int Index) {
        return new Value(LLVMGetParam(value, Index));
    }

    /**
     * Obtain the function to which this argument belongs.<br>
     * Unlike other functions in this group, this one takes a LLVMValueRef<br>
     * that corresponds to a llvm::Attribute.<br>
     * The returned LLVMValueRef is the llvm::Function to which this<br>
     * argument belongs.
     */
    public Value getParamParent() {
        return new Value(LLVMGetParamParent(value));
    }

    /**
     * Obtain the first parameter to a function.<br>
     *
     * @see llvm::Function::arg_begin()
     */
    public Value getFirstParam() {
        return new Value(LLVMGetFirstParam(value));
    }

    /**
     * Obtain the last parameter to a function.<br>
     *
     * @see llvm::Function::arg_end()
     */
    public Value getLastParam() {
        return new Value(LLVMGetLastParam(value));
    }

    /**
     * Obtain the next parameter to a function.<br>
     * This takes a LLVMValueRef obtained from LLVMGetFirstParam() (which is<br>
     * actually a wrapped iterator) and obtains the next parameter from the<br>
     * underlying iterator.
     */
    public Value getNextParam() {
        return new Value(LLVMGetNextParam(value));
    }

    /**
     * Obtain the previous parameter to a function.<br>
     * This is the opposite of LLVMGetNextParam().
     */
    public Value getPreviousParam() {
        return new Value(LLVMGetPreviousParam(value));
    }

    /**
     * Add an attribute to a function argument.<br>
     *
     * @see llvm::Argument::addAttr()
     */
    public void addAttribute(IntValuedEnum<LLVMAttribute> PA) {
        LLVMAddAttribute(value, PA);
    }

    /**
     * Remove an attribute from a function argument.<br>
     *
     * @see llvm::Argument::removeAttr()
     */
    public void removeAttribute(IntValuedEnum<LLVMAttribute> PA) {
        LLVMRemoveAttribute(value, PA);
    }

    /**
     * Get an attribute from a function argument.
     */
    public IntValuedEnum<LLVMAttribute> getAttribute() {
        return LLVMGetAttribute(value);
    }

    /**
     * Set the alignment for a function parameter.<br>
     *
     * @see llvm::Argument::addAttr()<br>
     * @see llvm::Attribute::constructAlignmentFromInt()
     */
    public void setParamAlignment(int align) {
        LLVMSetParamAlignment(value, align);
    }

    /**
     * Determine whether a LLVMValueRef is itself a basic block.
     */
    public boolean isBasicBlock() {
        return LLVMValueIsBasicBlock(value) != 0;
    }

    /**
     * Convert a LLVMValueRef to a LLVMBasicBlockRef instance.
     */
    public BasicBlock asBasicBlock() {
        return new BasicBlock(LLVMValueAsBasicBlock(value));
    }

    /**
     * Obtain the number of basic blocks in a function.<br>
     *
     * @param Fn
     *            Function value to operate on.
     */
    public int countBasicBlocks() {
        return LLVMCountBasicBlocks(value);
    }

    /**
     * Obtain all of the basic blocks in a function.<br>
     * This operates on a function value. The BasicBlocks parameter is a<br>
     * pointer to a pre-allocated array of LLVMBasicBlockRef of at least<br>
     * LLVMCountBasicBlocks() in length. This array is populated with<br>
     * LLVMBasicBlockRef instances.
     */
    public void getBasicBlocks(Pointer<LLVMBasicBlockRef> BasicBlocks) {
        LLVMGetBasicBlocks(value, BasicBlocks);
    }

    /**
     * Obtain the first basic block in a function.<br>
     * The returned basic block can be used as an iterator. You will likely<br>
     * eventually call into LLVMGetNextBasicBlock() with it.<br>
     *
     * @see llvm::Function::begin()
     */
    public BasicBlock getFirstBasicBlock() {
        return new BasicBlock(LLVMGetFirstBasicBlock(value));
    }

    /**
     * Obtain the last basic block in a function.<br>
     *
     * @see llvm::Function::end()
     */
    public BasicBlock getLastBasicBlock() {
        return new BasicBlock(LLVMGetLastBasicBlock(value));
    }

    /**
     * Obtain the basic block that corresponds to the entry point of a<br>
     * function.<br>
     *
     * @see llvm::Function::getEntryBlock()
     */
    public BasicBlock getEntryBasicBlock() {
        return new BasicBlock(LLVMGetEntryBasicBlock(value));
    }

    /**
     * Append a basic block to the end of a function.<br>
     *
     * @see llvm::BasicBlock::Create()
     */
    public BasicBlock appendBasicBlockInContext(Context C, String Name) {
        return new BasicBlock(LLVMAppendBasicBlockInContext(C.context(), value,
                Pointer.pointerToCString(Name)));
    }

    /**
     * Append a basic block to the end of a function using the global<br>
     * context.<br>
     *
     * @see llvm::BasicBlock::Create()
     */
    public BasicBlock appendBasicBlock(String Name) {
        return new BasicBlock(LLVMAppendBasicBlock(value,
                Pointer.pointerToCString(Name)));
    }

    // Instruction

    /**
     * Obtain the basic block to which an instruction belongs.<br>
     *
     * @see llvm::Instruction::getParent()
     */
    public BasicBlock getInstructionParent() {
        return new BasicBlock(LLVMGetInstructionParent(value));
    }

    /**
     * Obtain the instruction that occurs after the one specified.<br>
     * The next instruction will be from the same basic block.<br>
     * If this is the last instruction in a basic block, NULL will be<br>
     * returned.
     */
    public Value getNextInstruction(Value Inst) {
        return new Value(LLVMGetNextInstruction(value));
    }

    /**
     * Obtain the instruction that occured before this one.<br>
     * If the instruction is the first instruction in a basic block, NULL<br>
     * will be returned.
     */
    public Value getPreviousInstruction(Value Inst) {
        return new Value(LLVMGetPreviousInstruction(value));
    }

    /**
     * Set the calling convention for a call instruction.<br>
     * This expects an LLVMValueRef that corresponds to a llvm::CallInst or<br>
     * llvm::InvokeInst.<br>
     *
     * @see llvm::CallInst::setCallingConv()<br>
     * @see llvm::InvokeInst::setCallingConv()
     */
    public void setInstructionCallConv(int CC) {
        LLVMSetInstructionCallConv(value, CC);
    }

    /**
     * Obtain the calling convention for a call instruction.<br>
     * This is the opposite of LLVMSetInstructionCallConv(). Reads its<br>
     * usage.<br>
     *
     * @see LLVMSetInstructionCallConv()
     */
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

    /**
     * Obtain whether a call instruction is a tail call.<br>
     * This only works on llvm::CallInst instructions.<br>
     *
     * @see llvm::CallInst::isTailCall()
     */
    public boolean isTailCall() {
        return LLVMIsTailCall(value) != 0;
    }

    /**
     * Set whether a call instruction is a tail call.<br>
     * This only works on llvm::CallInst instructions.<br>
     *
     * @see llvm::CallInst::setTailCall()
     */
    public void setTailCall(boolean IsTailCall) {
        LLVMSetTailCall(value, IsTailCall ? 1 : 0);
    }

    /**
     * Add an incoming value to the end of a PHI list.
     */
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

    /**
     * Obtain the number of incoming basic blocks to a PHI node.
     */
    public int countIncoming() {
        return LLVMCountIncoming(value);
    }

    /**
     * Obtain an incoming value to a PHI node as a LLVMValueRef.
     */
    public Value getIncomingValue(int Index) {
        return new Value(LLVMGetIncomingValue(value, Index));
    }

    /**
     * Obtain an incoming value to a PHI node as a LLVMBasicBlockRef.
     */
    public BasicBlock getIncomingBlock(int Index) {
        return new BasicBlock(LLVMGetIncomingBlock(value, Index));
    }

}
