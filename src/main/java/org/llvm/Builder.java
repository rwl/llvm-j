package org.llvm;

import static org.llvm.binding.LLVMLibrary.*;

import org.bridj.IntValuedEnum;
import org.bridj.Pointer;
import org.llvm.binding.LLVMLibrary.LLVMBasicBlockRef;
import org.llvm.binding.LLVMLibrary.LLVMBuilderRef;
import org.llvm.binding.LLVMLibrary.LLVMIntPredicate;
import org.llvm.binding.LLVMLibrary.LLVMOpcode;
import org.llvm.binding.LLVMLibrary.LLVMRealPredicate;
import org.llvm.binding.LLVMLibrary.LLVMTypeRef;
import org.llvm.binding.LLVMLibrary.LLVMValueRef;

/**
 * This provides a uniform API for creating instructions and inserting them into
 * a basic block: either at the end of a BasicBlock, or at a specific iterator
 * location in a block.
 */
public class Builder {
    private LLVMBuilderRef builder;

    LLVMBuilderRef builder() {
        return builder;
    }

    Builder(LLVMBuilderRef builder) {
        this.builder = builder;
    }

    public void finalize() {
        dispose();
    }

    public void dispose() {
        LLVMDisposeBuilder(builder);
        builder = null;
    }

    /* Creation */

    public static Builder createBuilderInContext(Context c) {
        return new Builder(LLVMCreateBuilderInContext(c.context()));
    }

    public static Builder createBuilder() {
        return new Builder(LLVMCreateBuilder());
    }

    /* Navigation */

    public void positionBuilder(BasicBlock block, Value instr) {
        LLVMPositionBuilder(builder, block.bb(), instr.value());
    }

    public void positionBuilderBefore(Value instr) {
        LLVMPositionBuilderBefore(builder, instr.value());
    }

    public void positionBuilderAtEnd(BasicBlock block) {
        LLVMPositionBuilderAtEnd(builder, block.bb());
    }

    public BasicBlock getInsertBlock() {
        return new BasicBlock(LLVMGetInsertBlock(builder));
    }

    public void clearInsertionPosition() {
        LLVMClearInsertionPosition(builder);
    }

    public void insertIntoBuilder(Value instr) {
        LLVMInsertIntoBuilder(builder, instr.value());
    }

    public void insertIntoBuilderWithName(Value instr, String name) {
        LLVMInsertIntoBuilderWithName(builder, instr.value(),
                Pointer.pointerToCString(name));
    }

    public void setCurrentDebugLocation(Value l) {
        LLVMSetCurrentDebugLocation(builder, l.value());
    }

    public Value getCurrentDebugLocation() {
        return new Value(LLVMGetCurrentDebugLocation(builder));
    }

    public void setInstDebugLocation(Value inst) {
        LLVMSetInstDebugLocation(builder, inst.value());
    }

    /* Building */

    public Value buildRetVoid() {
        return new Value(LLVMBuildRetVoid(builder));
    }

    public Value buildRet(Value v) {
        return new Value(LLVMBuildRet(builder, v.value()));
    }

    public Value buildAggregateRet(Pointer<LLVMValueRef> retVals, int n) {
        return new Value(LLVMBuildAggregateRet(builder, retVals, n));
    }

    public Value buildBr(LLVMBasicBlockRef dest) {
        return new Value(LLVMBuildBr(builder, dest));
    }

    public Value buildCondBr(Value if_, LLVMBasicBlockRef then,
            LLVMBasicBlockRef else_) {
        return new Value(LLVMBuildCondBr(builder, if_.value(), then, else_));
    }

    public Value buildSwitch(Value v, LLVMBasicBlockRef else_, int numCases) {
        return new Value(LLVMBuildSwitch(builder, v.value(), else_, numCases));
    }

    public Value buildIndirectBr(Value addr, int numDests) {
        return new Value(LLVMBuildIndirectBr(builder, addr.value(), numDests));
    }

    public Value buildInvoke(Value fn, Pointer<LLVMValueRef> args, int numArgs,
            LLVMBasicBlockRef then, LLVMBasicBlockRef catch_, String name) {
        return new Value(LLVMBuildInvoke(builder, fn.value(), args, numArgs,
                then, catch_, Pointer.pointerToCString(name)));
    }

    /*public Value buildUnwind() {
    	return new Value(LLVMBuildUnwind(builder));
    }*/

    public Value buildUnreachable() {
        return new Value(LLVMBuildUnreachable(builder));
    }

    // TODO: put these where they belong; they probably don't belong here.

    /**
     * Add a case to the switch instruction
     */
    public void addCase(Value switch_, Value onVal, LLVMBasicBlockRef dest) {
        LLVMAddCase(switch_.value(), onVal.value(), dest);
    }

    /**
     * Add a destination to the indirectbr instruction
     */
    public void addDestination(Value indirectBr, LLVMBasicBlockRef dest) {
        LLVMAddDestination(indirectBr.value(), dest);
    }

    public Value buildAdd(Value lhs, Value rhs, String name) {
        return new Value(LLVMBuildAdd(builder, lhs.value(), rhs.value(),
                Pointer.pointerToCString(name)));
    }

    public Value buildNSWAdd(Value lhs, Value rhs, String name) {
        return new Value(LLVMBuildNSWAdd(builder, lhs.value(), rhs.value(),
                Pointer.pointerToCString(name)));
    }

    public Value buildNUWAdd(Value lhs, Value rhs, String name) {
        return new Value(LLVMBuildNUWAdd(builder, lhs.value(), rhs.value(),
                Pointer.pointerToCString(name)));
    }

    public Value buildFAdd(Value lhs, Value rhs, String name) {
        return new Value(LLVMBuildFAdd(builder, lhs.value(), rhs.value(),
                Pointer.pointerToCString(name)));
    }

    public Value buildSub(Value lhs, Value rhs, String name) {
        return new Value(LLVMBuildSub(builder, lhs.value(), rhs.value(),
                Pointer.pointerToCString(name)));
    }

    public Value buildNSWSub(Value lhs, Value rhs, String name) {
        return new Value(LLVMBuildNSWSub(builder, lhs.value(), rhs.value(),
                Pointer.pointerToCString(name)));
    }

    public Value buildNUWSub(Value lhs, Value rhs, String name) {
        return new Value(LLVMBuildNUWSub(builder, lhs.value(), rhs.value(),
                Pointer.pointerToCString(name)));
    }

    public Value buildFSub(Value lhs, Value rhs, String name) {
        return new Value(LLVMBuildFSub(builder, lhs.value(), rhs.value(),
                Pointer.pointerToCString(name)));
    }

    public Value buildMul(Value lhs, Value rhs, String name) {
        return new Value(LLVMBuildMul(builder, lhs.value(), rhs.value(),
                Pointer.pointerToCString(name)));
    }

    public Value buildNSWMul(Value lhs, Value rhs, String name) {
        return new Value(LLVMBuildNSWMul(builder, lhs.value(), rhs.value(),
                Pointer.pointerToCString(name)));
    }

    public Value buildNUWMul(Value lhs, Value rhs, String name) {
        return new Value(LLVMBuildNUWMul(builder, lhs.value(), rhs.value(),
                Pointer.pointerToCString(name)));
    }

    public Value buildFMul(Value lhs, Value rhs, String name) {
        return new Value(LLVMBuildFMul(builder, lhs.value(), rhs.value(),
                Pointer.pointerToCString(name)));
    }

    public Value buildUDiv(Value lhs, Value rhs, String name) {
        return new Value(LLVMBuildUDiv(builder, lhs.value(), rhs.value(),
                Pointer.pointerToCString(name)));
    }

    public Value buildSDiv(Value lhs, Value rhs, String name) {
        return new Value(LLVMBuildSDiv(builder, lhs.value(), rhs.value(),
                Pointer.pointerToCString(name)));
    }

    public Value buildExactSDiv(Value lhs, Value rhs, String name) {
        return new Value(LLVMBuildExactSDiv(builder, lhs.value(), rhs.value(),
                Pointer.pointerToCString(name)));
    }

    public Value buildFDiv(Value lhs, Value rhs, String name) {
        return new Value(LLVMBuildFDiv(builder, lhs.value(), rhs.value(),
                Pointer.pointerToCString(name)));
    }

    public Value buildURem(Value lhs, Value rhs, String name) {
        return new Value(LLVMBuildURem(builder, lhs.value(), rhs.value(),
                Pointer.pointerToCString(name)));
    }

    public Value buildSRem(Value lhs, Value rhs, String name) {
        return new Value(LLVMBuildSRem(builder, lhs.value(), rhs.value(),
                Pointer.pointerToCString(name)));
    }

    public Value buildFRem(Value lhs, Value rhs, String name) {
        return new Value(LLVMBuildFRem(builder, lhs.value(), rhs.value(),
                Pointer.pointerToCString(name)));
    }

    public Value buildShl(Value lhs, Value rhs, String name) {
        return new Value(LLVMBuildShl(builder, lhs.value(), rhs.value(),
                Pointer.pointerToCString(name)));
    }

    public Value buildLShr(Value lhs, Value rhs, String name) {
        return new Value(LLVMBuildLShr(builder, lhs.value(), rhs.value(),
                Pointer.pointerToCString(name)));
    }

    public Value buildAShr(Value lhs, Value rhs, String name) {
        return new Value(LLVMBuildAShr(builder, lhs.value(), rhs.value(),
                Pointer.pointerToCString(name)));
    }

    public Value buildAnd(Value lhs, Value rhs, String name) {
        return new Value(LLVMBuildAnd(builder, lhs.value(), rhs.value(),
                Pointer.pointerToCString(name)));
    }

    public Value buildOr(Value lhs, Value rhs, String name) {
        return new Value(LLVMBuildOr(builder, lhs.value(), rhs.value(),
                Pointer.pointerToCString(name)));
    }

    public Value buildXor(Value lhs, Value rhs, String name) {
        return new Value(LLVMBuildXor(builder, lhs.value(), rhs.value(),
                Pointer.pointerToCString(name)));
    }

    public Value buildBinOp(IntValuedEnum<LLVMOpcode> op, Value lhs, Value rhs,
            String name) {
        return new Value(LLVMBuildBinOp(builder, op, lhs.value(), rhs.value(),
                Pointer.pointerToCString(name)));
    }

    public Value buildNeg(Value v, String name) {
        return new Value(LLVMBuildNeg(builder, v.value(),
                Pointer.pointerToCString(name)));
    }

    public Value buildNSWNeg(Value v, String name) {
        return new Value(LLVMBuildNSWNeg(builder, v.value(),
                Pointer.pointerToCString(name)));
    }

    public Value buildNUWNeg(Value v, String name) {
        return new Value(LLVMBuildNUWNeg(builder, v.value(),
                Pointer.pointerToCString(name)));
    }

    public Value buildFNeg(Value v, String name) {
        return new Value(LLVMBuildFNeg(builder, v.value(),
                Pointer.pointerToCString(name)));
    }

    public Value buildNot(Value v, String name) {
        return new Value(LLVMBuildNot(builder, v.value(),
                Pointer.pointerToCString(name)));
    }

    public Value buildMalloc(LLVMTypeRef ty, String name) {
        return new Value(LLVMBuildMalloc(builder, ty,
                Pointer.pointerToCString(name)));
    }

    public Value buildArrayMalloc(LLVMTypeRef ty, Value val, String name) {
        return new Value(LLVMBuildArrayMalloc(builder, ty, val.value(),
                Pointer.pointerToCString(name)));
    }

    public Value buildAlloca(LLVMTypeRef ty, String name) {
        return new Value(LLVMBuildAlloca(builder, ty,
                Pointer.pointerToCString(name)));
    }

    public Value buildArrayAlloca(LLVMTypeRef ty, Value val, String name) {
        return new Value(LLVMBuildArrayAlloca(builder, ty, val.value(),
                Pointer.pointerToCString(name)));
    }

    public Value buildFree(Value pointerVal) {
        return new Value(LLVMBuildFree(builder, pointerVal.value()));
    }

    public Value buildLoad(Value pointerVal, String name) {
        return new Value(LLVMBuildLoad(builder, pointerVal.value(),
                Pointer.pointerToCString(name)));
    }

    public Value buildStore(Value val, Value ptr) {
        return new Value(LLVMBuildStore(builder, val.value(), ptr.value()));
    }

    public Value buildGEP(Value ptr, Pointer<LLVMValueRef> indices,
            int numIndices, String name) {
        return new Value(LLVMBuildGEP(builder, ptr.value(), indices,
                numIndices, Pointer.pointerToCString(name)));
    }

    public Value buildInBoundsGEP(Value ptr, Pointer<LLVMValueRef> indices,
            int numIndices, String name) {
        return new Value(LLVMBuildInBoundsGEP(builder, ptr.value(), indices,
                numIndices, Pointer.pointerToCString(name)));
    }

    public Value buildStructGEP(Value ptr, int idx, String name) {
        return new Value(LLVMBuildStructGEP(builder, ptr.value(), idx,
                Pointer.pointerToCString(name)));
    }

    public Value buildGlobalString(String str, String name) {
        return new Value(LLVMBuildGlobalString(builder,
                Pointer.pointerToCString(str), Pointer.pointerToCString(name)));
    }

    public Value buildGlobalStringPtr(String str, String name) {
        return new Value(LLVMBuildGlobalStringPtr(builder,
                Pointer.pointerToCString(str), Pointer.pointerToCString(name)));
    }

    public Value buildTrunc(Value val, LLVMTypeRef destTy, String name) {
        return new Value(LLVMBuildTrunc(builder, val.value(), destTy,
                Pointer.pointerToCString(name)));
    }

    public Value buildZExt(Value val, LLVMTypeRef destTy, String name) {
        return new Value(LLVMBuildZExt(builder, val.value(), destTy,
                Pointer.pointerToCString(name)));
    }

    public Value buildSExt(Value val, LLVMTypeRef destTy, String name) {
        return new Value(LLVMBuildSExt(builder, val.value(), destTy,
                Pointer.pointerToCString(name)));
    }

    public Value buildFPToUI(Value val, LLVMTypeRef destTy, String name) {
        return new Value(LLVMBuildFPToUI(builder, val.value(), destTy,
                Pointer.pointerToCString(name)));
    }

    public Value buildFPToSI(Value val, LLVMTypeRef destTy, String name) {
        return new Value(LLVMBuildFPToSI(builder, val.value(), destTy,
                Pointer.pointerToCString(name)));
    }

    public Value buildUIToFP(Value val, LLVMTypeRef destTy, String name) {
        return new Value(LLVMBuildUIToFP(builder, val.value(), destTy,
                Pointer.pointerToCString(name)));
    }

    public Value buildSIToFP(Value val, LLVMTypeRef destTy, String name) {
        return new Value(LLVMBuildSIToFP(builder, val.value(), destTy,
                Pointer.pointerToCString(name)));
    }

    public Value buildFPTrunc(Value val, LLVMTypeRef destTy, String name) {
        return new Value(LLVMBuildFPTrunc(builder, val.value(), destTy,
                Pointer.pointerToCString(name)));
    }

    public Value buildFPExt(Value val, LLVMTypeRef destTy, String name) {
        return new Value(LLVMBuildFPExt(builder, val.value(), destTy,
                Pointer.pointerToCString(name)));
    }

    public Value buildPtrToInt(Value val, LLVMTypeRef destTy, String name) {
        return new Value(LLVMBuildPtrToInt(builder, val.value(), destTy,
                Pointer.pointerToCString(name)));
    }

    public Value buildIntToPtr(Value val, LLVMTypeRef destTy, String name) {
        return new Value(LLVMBuildIntToPtr(builder, val.value(), destTy,
                Pointer.pointerToCString(name)));
    }

    public Value buildBitCast(Value val, LLVMTypeRef destTy, String name) {
        return new Value(LLVMBuildBitCast(builder, val.value(), destTy,
                Pointer.pointerToCString(name)));
    }

    public Value buildZExtOrBitCast(Value val, LLVMTypeRef destTy, String name) {
        return new Value(LLVMBuildZExtOrBitCast(builder, val.value(), destTy,
                Pointer.pointerToCString(name)));
    }

    public Value buildSExtOrBitCast(Value val, LLVMTypeRef destTy, String name) {
        return new Value(LLVMBuildSExtOrBitCast(builder, val.value(), destTy,
                Pointer.pointerToCString(name)));
    }

    public Value buildTruncOrBitCast(Value val, LLVMTypeRef destTy, String name) {
        return new Value(LLVMBuildTruncOrBitCast(builder, val.value(), destTy,
                Pointer.pointerToCString(name)));
    }

    public Value buildCast(IntValuedEnum<LLVMOpcode> op, Value val,
            LLVMTypeRef destTy, String name) {
        return new Value(LLVMBuildCast(builder, op, val.value(), destTy,
                Pointer.pointerToCString(name)));
    }

    public Value buildPointerCast(Value val, LLVMTypeRef destTy, String name) {
        return new Value(LLVMBuildPointerCast(builder, val.value(), destTy,
                Pointer.pointerToCString(name)));
    }

    /**
     * Signed cast.
     */
    public Value buildIntCast(Value val, LLVMTypeRef destTy, String name) {
        return new Value(LLVMBuildIntCast(builder, val.value(), destTy,
                Pointer.pointerToCString(name)));
    }

    public Value buildFPCast(Value val, LLVMTypeRef destTy, String name) {
        return new Value(LLVMBuildFPCast(builder, val.value(), destTy,
                Pointer.pointerToCString(name)));
    }

    public Value buildICmp(IntValuedEnum<LLVMIntPredicate> op, Value lhs,
            Value rhs, String name) {
        return new Value(LLVMBuildICmp(builder, op, lhs.value(), rhs.value(),
                Pointer.pointerToCString(name)));
    }

    public Value buildFCmp(IntValuedEnum<LLVMRealPredicate> op, Value lhs,
            Value rhs, String name) {
        return new Value(LLVMBuildFCmp(builder, op, lhs.value(), rhs.value(),
                Pointer.pointerToCString(name)));
    }

    public Value buildPhi(LLVMTypeRef ty, String name) {
        return new Value(LLVMBuildPhi(builder, ty,
                Pointer.pointerToCString(name)));
    }

    public Value buildCall(Value fn, Pointer<LLVMValueRef> args, int numArgs,
            String name) {
        return new Value(LLVMBuildCall(builder, fn.value(), args, numArgs,
                Pointer.pointerToCString(name)));
    }

    public Value buildSelect(Value if_, Value then, Value else_, String name) {
        return new Value(LLVMBuildSelect(builder, if_.value(), then.value(),
                else_.value(), Pointer.pointerToCString(name)));
    }

    public Value buildVAArg(Value list, LLVMTypeRef ty, String name) {
        return new Value(LLVMBuildVAArg(builder, list.value(), ty,
                Pointer.pointerToCString(name)));
    }

    public Value buildExtractElement(Value vecVal, Value index, String name) {
        return new Value(LLVMBuildExtractElement(builder, vecVal.value(),
                index.value(), Pointer.pointerToCString(name)));
    }

    public Value buildInsertElement(Value vecVal, Value eltVal, Value index,
            String name) {
        return new Value(LLVMBuildInsertElement(builder, vecVal.value(),
                eltVal.value(), index.value(), Pointer.pointerToCString(name)));
    }

    public Value buildShuffleVector(Value v1, Value v2, Value mask, String name) {
        return new Value(LLVMBuildShuffleVector(builder, v1.value(),
                v2.value(), mask.value(), Pointer.pointerToCString(name)));
    }

    public Value buildExtractValue(Value aggVal, int index, String name) {
        return new Value(LLVMBuildExtractValue(builder, aggVal.value(), index,
                Pointer.pointerToCString(name)));
    }

    public Value buildInsertValue(Value aggVal, Value eltVal, int index,
            String name) {
        return new Value(LLVMBuildInsertValue(builder, aggVal.value(),
                eltVal.value(), index, Pointer.pointerToCString(name)));
    }

    public Value buildIsNull(Value val, String name) {
        return new Value(LLVMBuildIsNull(builder, val.value(),
                Pointer.pointerToCString(name)));
    }

    public Value buildIsNotNull(Value val, String name) {
        return new Value(LLVMBuildIsNotNull(builder, val.value(),
                Pointer.pointerToCString(name)));
    }

    public Value buildPtrDiff(Value lhs, Value rhs, String name) {
        return new Value(LLVMBuildPtrDiff(builder, lhs.value(), rhs.value(),
                Pointer.pointerToCString(name)));
    }

}
