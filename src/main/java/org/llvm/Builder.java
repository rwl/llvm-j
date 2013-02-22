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

	/* package */LLVMBuilderRef builder() {
		return builder;
	}

	/* package */Builder(LLVMBuilderRef builder) {
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

	public static Builder createBuilderInContext(Context C) {
		return new Builder(LLVMCreateBuilderInContext(C.context()));
	}

	public static Builder createBuilder() {
		return new Builder(LLVMCreateBuilder());
	}

	/* Navigation */

	public void positionBuilder(BasicBlock Block, Value Instr) {
		LLVMPositionBuilder(builder, Block.bb(), Instr.value());
	}

	public void positionBuilderBefore(Value Instr) {
		LLVMPositionBuilderBefore(builder, Instr.value());
	}

	public void positionBuilderAtEnd(BasicBlock Block) {
		LLVMPositionBuilderAtEnd(builder, Block.bb());
	}

	public BasicBlock getInsertBlock() {
		return new BasicBlock(LLVMGetInsertBlock(builder));
	}

	public void clearInsertionPosition() {
		LLVMClearInsertionPosition(builder);
	}

	public void insertIntoBuilder(Value Instr) {
		LLVMInsertIntoBuilder(builder, Instr.value());
	}

	public void insertIntoBuilderWithName(Value Instr, String Name) {
		LLVMInsertIntoBuilderWithName(builder, Instr.value(),
				Pointer.pointerToCString(Name));
	}

	public void setCurrentDebugLocation(Value L) {
		LLVMSetCurrentDebugLocation(builder, L.value());
	}

	public Value getCurrentDebugLocation() {
		return new Value(LLVMGetCurrentDebugLocation(builder));
	}

	public void setInstDebugLocation(Value Inst) {
		LLVMSetInstDebugLocation(builder, Inst.value());
	}

	/* Building */

	public Value buildRetVoid() {
		return new Value(LLVMBuildRetVoid(builder));
	}

	public Value buildRet(Value V) {
		return new Value(LLVMBuildRet(builder, V.value()));
	}

	public Value buildAggregateRet(Pointer<LLVMValueRef> RetVals, int N) {
		return new Value(LLVMBuildAggregateRet(builder, RetVals, N));
	}

	public Value buildBr(LLVMBasicBlockRef Dest) {
		return new Value(LLVMBuildBr(builder, Dest));
	}

	public Value buildCondBr(Value If, LLVMBasicBlockRef Then,
			LLVMBasicBlockRef Else) {
		return new Value(LLVMBuildCondBr(builder, If.value(), Then, Else));
	}

	public Value buildSwitch(Value V, LLVMBasicBlockRef Else, int NumCases) {
		return new Value(LLVMBuildSwitch(builder, V.value(), Else, NumCases));
	}

	public Value buildIndirectBr(Value Addr, int NumDests) {
		return new Value(LLVMBuildIndirectBr(builder, Addr.value(), NumDests));
	}

	public Value buildInvoke(Value Fn, Pointer<LLVMValueRef> Args, int NumArgs,
			LLVMBasicBlockRef Then, LLVMBasicBlockRef Catch, String Name) {
		return new Value(LLVMBuildInvoke(builder, Fn.value(), Args, NumArgs,
				Then, Catch, Pointer.pointerToCString(Name)));
	}

	/*public Value buildUnwind() {
		return new Value(LLVMBuildUnwind(builder));
	}*/

	public Value buildUnreachable() {
		return new Value(LLVMBuildUnreachable(builder));
	}

	// TODO: put these where they belong; they probably don't belong here.
	public void addCase(Value Switch, Value OnVal, LLVMBasicBlockRef Dest) {
		LLVMAddCase(Switch.value(), OnVal.value(), Dest);
	}

	public void addDestination(Value IndirectBr, LLVMBasicBlockRef Dest) {
		LLVMAddDestination(IndirectBr.value(), Dest);
	}

	public Value buildAdd(Value LHS, Value RHS, String Name) {
		return new Value(LLVMBuildAdd(builder, LHS.value(), RHS.value(),
				Pointer.pointerToCString(Name)));
	}

	public Value buildNSWAdd(Value LHS, Value RHS, String Name) {
		return new Value(LLVMBuildNSWAdd(builder, LHS.value(), RHS.value(),
				Pointer.pointerToCString(Name)));
	}

	public Value buildNUWAdd(Value LHS, Value RHS, String Name) {
		return new Value(LLVMBuildNUWAdd(builder, LHS.value(), RHS.value(),
				Pointer.pointerToCString(Name)));
	}

	public Value buildFAdd(Value LHS, Value RHS, String Name) {
		return new Value(LLVMBuildFAdd(builder, LHS.value(), RHS.value(),
				Pointer.pointerToCString(Name)));
	}

	public Value buildSub(Value LHS, Value RHS, String Name) {
		return new Value(LLVMBuildSub(builder, LHS.value(), RHS.value(),
				Pointer.pointerToCString(Name)));
	}

	public Value buildNSWSub(Value LHS, Value RHS, String Name) {
		return new Value(LLVMBuildNSWSub(builder, LHS.value(), RHS.value(),
				Pointer.pointerToCString(Name)));
	}

	public Value buildNUWSub(Value LHS, Value RHS, String Name) {
		return new Value(LLVMBuildNUWSub(builder, LHS.value(), RHS.value(),
				Pointer.pointerToCString(Name)));
	}

	public Value buildFSub(Value LHS, Value RHS, String Name) {
		return new Value(LLVMBuildFSub(builder, LHS.value(), RHS.value(),
				Pointer.pointerToCString(Name)));
	}

	public Value buildMul(Value LHS, Value RHS, String Name) {
		return new Value(LLVMBuildMul(builder, LHS.value(), RHS.value(),
				Pointer.pointerToCString(Name)));
	}

	public Value buildNSWMul(Value LHS, Value RHS, String Name) {
		return new Value(LLVMBuildNSWMul(builder, LHS.value(), RHS.value(),
				Pointer.pointerToCString(Name)));
	}

	public Value buildNUWMul(Value LHS, Value RHS, String Name) {
		return new Value(LLVMBuildNUWMul(builder, LHS.value(), RHS.value(),
				Pointer.pointerToCString(Name)));
	}

	public Value buildFMul(Value LHS, Value RHS, String Name) {
		return new Value(LLVMBuildFMul(builder, LHS.value(), RHS.value(),
				Pointer.pointerToCString(Name)));
	}

	public Value buildUDiv(Value LHS, Value RHS, String Name) {
		return new Value(LLVMBuildUDiv(builder, LHS.value(), RHS.value(),
				Pointer.pointerToCString(Name)));
	}

	public Value buildSDiv(Value LHS, Value RHS, String Name) {
		return new Value(LLVMBuildSDiv(builder, LHS.value(), RHS.value(),
				Pointer.pointerToCString(Name)));
	}

	public Value buildExactSDiv(Value LHS, Value RHS, String Name) {
		return new Value(LLVMBuildExactSDiv(builder, LHS.value(), RHS.value(),
				Pointer.pointerToCString(Name)));
	}

	public Value buildFDiv(Value LHS, Value RHS, String Name) {
		return new Value(LLVMBuildFDiv(builder, LHS.value(), RHS.value(),
				Pointer.pointerToCString(Name)));
	}

	public Value buildURem(Value LHS, Value RHS, String Name) {
		return new Value(LLVMBuildURem(builder, LHS.value(), RHS.value(),
				Pointer.pointerToCString(Name)));
	}

	public Value buildSRem(Value LHS, Value RHS, String Name) {
		return new Value(LLVMBuildSRem(builder, LHS.value(), RHS.value(),
				Pointer.pointerToCString(Name)));
	}

	public Value buildFRem(Value LHS, Value RHS, String Name) {
		return new Value(LLVMBuildFRem(builder, LHS.value(), RHS.value(),
				Pointer.pointerToCString(Name)));
	}

	public Value buildShl(Value LHS, Value RHS, String Name) {
		return new Value(LLVMBuildShl(builder, LHS.value(), RHS.value(),
				Pointer.pointerToCString(Name)));
	}

	public Value buildLShr(Value LHS, Value RHS, String Name) {
		return new Value(LLVMBuildLShr(builder, LHS.value(), RHS.value(),
				Pointer.pointerToCString(Name)));
	}

	public Value buildAShr(Value LHS, Value RHS, String Name) {
		return new Value(LLVMBuildAShr(builder, LHS.value(), RHS.value(),
				Pointer.pointerToCString(Name)));
	}

	public Value buildAnd(Value LHS, Value RHS, String Name) {
		return new Value(LLVMBuildAnd(builder, LHS.value(), RHS.value(),
				Pointer.pointerToCString(Name)));
	}

	public Value buildOr(Value LHS, Value RHS, String Name) {
		return new Value(LLVMBuildOr(builder, LHS.value(), RHS.value(),
				Pointer.pointerToCString(Name)));
	}

	public Value buildXor(Value LHS, Value RHS, String Name) {
		return new Value(LLVMBuildXor(builder, LHS.value(), RHS.value(),
				Pointer.pointerToCString(Name)));
	}

	public Value buildBinOp(IntValuedEnum<LLVMOpcode> Op, Value LHS, Value RHS,
			String Name) {
		return new Value(LLVMBuildBinOp(builder, Op, LHS.value(), RHS.value(),
				Pointer.pointerToCString(Name)));
	}

	public Value buildNeg(Value V, String Name) {
		return new Value(LLVMBuildNeg(builder, V.value(),
				Pointer.pointerToCString(Name)));
	}

	public Value buildNSWNeg(Value V, String Name) {
		return new Value(LLVMBuildNSWNeg(builder, V.value(),
				Pointer.pointerToCString(Name)));
	}

	public Value buildNUWNeg(Value V, String Name) {
		return new Value(LLVMBuildNUWNeg(builder, V.value(),
				Pointer.pointerToCString(Name)));
	}

	public Value buildFNeg(Value V, String Name) {
		return new Value(LLVMBuildFNeg(builder, V.value(),
				Pointer.pointerToCString(Name)));
	}

	public Value buildNot(Value V, String Name) {
		return new Value(LLVMBuildNot(builder, V.value(),
				Pointer.pointerToCString(Name)));
	}

	public Value buildMalloc(LLVMTypeRef Ty, String Name) {
		return new Value(LLVMBuildMalloc(builder, Ty,
				Pointer.pointerToCString(Name)));
	}

	public Value buildArrayMalloc(LLVMTypeRef Ty, Value Val, String Name) {
		return new Value(LLVMBuildArrayMalloc(builder, Ty, Val.value(),
				Pointer.pointerToCString(Name)));
	}

	public Value buildAlloca(LLVMTypeRef Ty, String Name) {
		return new Value(LLVMBuildAlloca(builder, Ty,
				Pointer.pointerToCString(Name)));
	}

	public Value buildArrayAlloca(LLVMTypeRef Ty, Value Val, String Name) {
		return new Value(LLVMBuildArrayAlloca(builder, Ty, Val.value(),
				Pointer.pointerToCString(Name)));
	}

	public Value buildFree(Value PointerVal) {
		return new Value(LLVMBuildFree(builder, PointerVal.value()));
	}

	public Value buildLoad(Value PointerVal, String Name) {
		return new Value(LLVMBuildLoad(builder, PointerVal.value(),
				Pointer.pointerToCString(Name)));
	}

	public Value buildStore(Value Val, Value Ptr) {
		return new Value(LLVMBuildStore(builder, Val.value(), Ptr.value()));
	}

	public Value buildGEP(Value Ptr, Pointer<LLVMValueRef> Indices,
			int NumIndices, String Name) {
		return new Value(LLVMBuildGEP(builder, Ptr.value(), Indices,
				NumIndices, Pointer.pointerToCString(Name)));
	}

	public Value buildInBoundsGEP(Value Ptr, Pointer<LLVMValueRef> Indices,
			int NumIndices, String Name) {
		return new Value(LLVMBuildInBoundsGEP(builder, Ptr.value(), Indices,
				NumIndices, Pointer.pointerToCString(Name)));
	}

	public Value buildStructGEP(Value Ptr, int Idx, String Name) {
		return new Value(LLVMBuildStructGEP(builder, Ptr.value(), Idx,
				Pointer.pointerToCString(Name)));
	}

	public Value buildGlobalString(String Str, String Name) {
		return new Value(LLVMBuildGlobalString(builder,
				Pointer.pointerToCString(Str), Pointer.pointerToCString(Name)));
	}

	public Value buildGlobalStringPtr(String Str, String Name) {
		return new Value(LLVMBuildGlobalStringPtr(builder,
				Pointer.pointerToCString(Str), Pointer.pointerToCString(Name)));
	}

	public Value buildTrunc(Value Val, LLVMTypeRef DestTy, String Name) {
		return new Value(LLVMBuildTrunc(builder, Val.value(), DestTy,
				Pointer.pointerToCString(Name)));
	}

	public Value buildZExt(Value Val, LLVMTypeRef DestTy, String Name) {
		return new Value(LLVMBuildZExt(builder, Val.value(), DestTy,
				Pointer.pointerToCString(Name)));
	}

	public Value buildSExt(Value Val, LLVMTypeRef DestTy, String Name) {
		return new Value(LLVMBuildSExt(builder, Val.value(), DestTy,
				Pointer.pointerToCString(Name)));
	}

	public Value buildFPToUI(Value Val, LLVMTypeRef DestTy, String Name) {
		return new Value(LLVMBuildFPToUI(builder, Val.value(), DestTy,
				Pointer.pointerToCString(Name)));
	}

	public Value buildFPToSI(Value Val, LLVMTypeRef DestTy, String Name) {
		return new Value(LLVMBuildFPToSI(builder, Val.value(), DestTy,
				Pointer.pointerToCString(Name)));
	}

	public Value buildUIToFP(Value Val, LLVMTypeRef DestTy, String Name) {
		return new Value(LLVMBuildUIToFP(builder, Val.value(), DestTy,
				Pointer.pointerToCString(Name)));
	}

	public Value buildSIToFP(Value Val, LLVMTypeRef DestTy, String Name) {
		return new Value(LLVMBuildSIToFP(builder, Val.value(), DestTy,
				Pointer.pointerToCString(Name)));
	}

	public Value buildFPTrunc(Value Val, LLVMTypeRef DestTy, String Name) {
		return new Value(LLVMBuildFPTrunc(builder, Val.value(), DestTy,
				Pointer.pointerToCString(Name)));
	}

	public Value buildFPExt(Value Val, LLVMTypeRef DestTy, String Name) {
		return new Value(LLVMBuildFPExt(builder, Val.value(), DestTy,
				Pointer.pointerToCString(Name)));
	}

	public Value buildPtrToInt(Value Val, LLVMTypeRef DestTy, String Name) {
		return new Value(LLVMBuildPtrToInt(builder, Val.value(), DestTy,
				Pointer.pointerToCString(Name)));
	}

	public Value buildIntToPtr(Value Val, LLVMTypeRef DestTy, String Name) {
		return new Value(LLVMBuildIntToPtr(builder, Val.value(), DestTy,
				Pointer.pointerToCString(Name)));
	}

	public Value buildBitCast(Value Val, LLVMTypeRef DestTy, String Name) {
		return new Value(LLVMBuildBitCast(builder, Val.value(), DestTy,
				Pointer.pointerToCString(Name)));
	}

	public Value buildZExtOrBitCast(Value Val, LLVMTypeRef DestTy, String Name) {
		return new Value(LLVMBuildZExtOrBitCast(builder, Val.value(), DestTy,
				Pointer.pointerToCString(Name)));
	}

	public Value buildSExtOrBitCast(Value Val, LLVMTypeRef DestTy, String Name) {
		return new Value(LLVMBuildSExtOrBitCast(builder, Val.value(), DestTy,
				Pointer.pointerToCString(Name)));
	}

	public Value buildTruncOrBitCast(Value Val, LLVMTypeRef DestTy, String Name) {
		return new Value(LLVMBuildTruncOrBitCast(builder, Val.value(), DestTy,
				Pointer.pointerToCString(Name)));
	}

	public Value buildCast(IntValuedEnum<LLVMOpcode> Op, Value Val,
			LLVMTypeRef DestTy, String Name) {
		return new Value(LLVMBuildCast(builder, Op, Val.value(), DestTy,
				Pointer.pointerToCString(Name)));
	}

	public Value buildPointerCast(Value Val, LLVMTypeRef DestTy, String Name) {
		return new Value(LLVMBuildPointerCast(builder, Val.value(), DestTy,
				Pointer.pointerToCString(Name)));
	}

	public Value buildIntCast(Value Val, LLVMTypeRef DestTy, String Name) {
		return new Value(LLVMBuildIntCast(builder, Val.value(), DestTy,
				Pointer.pointerToCString(Name)));
	}

	public Value buildFPCast(Value Val, LLVMTypeRef DestTy, String Name) {
		return new Value(LLVMBuildFPCast(builder, Val.value(), DestTy,
				Pointer.pointerToCString(Name)));
	}

	public Value buildICmp(IntValuedEnum<LLVMIntPredicate> Op, Value LHS,
			Value RHS, String Name) {
		return new Value(LLVMBuildICmp(builder, Op, LHS.value(), RHS.value(),
				Pointer.pointerToCString(Name)));
	}

	public Value buildFCmp(IntValuedEnum<LLVMRealPredicate> Op, Value LHS,
			Value RHS, String Name) {
		return new Value(LLVMBuildFCmp(builder, Op, LHS.value(), RHS.value(),
				Pointer.pointerToCString(Name)));
	}

	public Value buildPhi(LLVMTypeRef Ty, String Name) {
		return new Value(LLVMBuildPhi(builder, Ty,
				Pointer.pointerToCString(Name)));
	}

	public Value buildCall(Value Fn, Pointer<LLVMValueRef> Args, int NumArgs,
			String Name) {
		return new Value(LLVMBuildCall(builder, Fn.value(), Args, NumArgs,
				Pointer.pointerToCString(Name)));
	}

	public Value buildSelect(Value If, Value Then, Value Else, String Name) {
		return new Value(LLVMBuildSelect(builder, If.value(), Then.value(),
				Else.value(), Pointer.pointerToCString(Name)));
	}

	public Value buildVAArg(Value List, LLVMTypeRef Ty, String Name) {
		return new Value(LLVMBuildVAArg(builder, List.value(), Ty,
				Pointer.pointerToCString(Name)));
	}

	public Value buildExtractElement(Value VecVal, Value Index, String Name) {
		return new Value(LLVMBuildExtractElement(builder, VecVal.value(),
				Index.value(), Pointer.pointerToCString(Name)));
	}

	public Value buildInsertElement(Value VecVal, Value EltVal, Value Index,
			String Name) {
		return new Value(LLVMBuildInsertElement(builder, VecVal.value(),
				EltVal.value(), Index.value(), Pointer.pointerToCString(Name)));
	}

	public Value buildShuffleVector(Value V1, Value V2, Value Mask, String Name) {
		return new Value(LLVMBuildShuffleVector(builder, V1.value(),
				V2.value(), Mask.value(), Pointer.pointerToCString(Name)));
	}

	public Value buildExtractValue(Value AggVal, int Index, String Name) {
		return new Value(LLVMBuildExtractValue(builder, AggVal.value(), Index,
				Pointer.pointerToCString(Name)));
	}

	public Value buildInsertValue(Value AggVal, Value EltVal, int Index,
			String Name) {
		return new Value(LLVMBuildInsertValue(builder, AggVal.value(),
				EltVal.value(), Index, Pointer.pointerToCString(Name)));
	}

	public Value buildIsNull(Value Val, String Name) {
		return new Value(LLVMBuildIsNull(builder, Val.value(),
				Pointer.pointerToCString(Name)));
	}

	public Value buildIsNotNull(Value Val, String Name) {
		return new Value(LLVMBuildIsNotNull(builder, Val.value(),
				Pointer.pointerToCString(Name)));
	}

	public Value buildPtrDiff(Value LHS, Value RHS, String Name) {
		return new Value(LLVMBuildPtrDiff(builder, LHS.value(), RHS.value(),
				Pointer.pointerToCString(Name)));
	}

}
