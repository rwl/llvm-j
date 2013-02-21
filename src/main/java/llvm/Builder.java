package llvm;

import org.bridj.IntValuedEnum;
import org.bridj.Pointer;
import org.bridj.ValuedEnum;

import static llvm.binding.LLVMLibrary.*;


public class Builder
{
	private LLVMBuilderRef builder;
	/*package*/ LLVMBuilderRef builder() { return builder; }

	/*package*/ Builder(LLVMBuilderRef builder) { this.builder = builder; }

	public void finalize() { dispose(); }
	public void dispose() { LLVMDisposeBuilder(builder); builder = null; }

	// creation
	public static Builder CreateBuilderInContext(Context C) { return new Builder( LLVMCreateBuilderInContext(C.context())); }
	public static Builder CreateBuilder() { return new Builder( LLVMCreateBuilder()); }

	// navigation
	public void PositionBuilder(BasicBlock Block, Value Instr) { LLVMPositionBuilder(builder, Block.bb(), Instr.value()); }
	public void PositionBuilderBefore(Value Instr) { LLVMPositionBuilderBefore(builder, Instr.value()); }
	public void PositionBuilderAtEnd(BasicBlock Block) { LLVMPositionBuilderAtEnd(builder, Block.bb()); }

	public BasicBlock GetInsertBlock() { return new BasicBlock(LLVMGetInsertBlock(builder)); }
	public void ClearInsertionPosition() { LLVMClearInsertionPosition(builder); }
	public void InsertIntoBuilder(Value Instr) { LLVMInsertIntoBuilder(builder, Instr.value()); }
	//public void InsertIntoBuilderWithName(Value Instr, String Name) { Pointer<Byte> cstr = Pointer.pointerToCString(Name); LLVMInsertIntoBuilderWithName(builder, Instr.value(), cstr); }
	public void InsertIntoBuilderWithName(Value Instr, String Name) { LLVMInsertIntoBuilderWithName(builder, Instr.value(), Pointer.pointerToCString(Name)); }
	public void SetCurrentDebugLocation(Value L) { LLVMSetCurrentDebugLocation(builder, L.value()); }
	public Value GetCurrentDebugLocation() { return new Value(LLVMGetCurrentDebugLocation(builder)); }
	public void SetInstDebugLocation(Value Inst) { LLVMSetInstDebugLocation(builder, Inst.value()); }


	// building
	public Value BuildRetVoid() { return new Value(LLVMBuildRetVoid(builder)); }

	public Value BuildRet(Value V) { return new Value(LLVMBuildRet(builder, V.value())); }
	public Value BuildAggregateRet(Pointer<LLVMValueRef> RetVals, int N) { return new Value(LLVMBuildAggregateRet(builder, RetVals, N)); }
	public Value BuildBr(LLVMBasicBlockRef Dest) { return new Value(LLVMBuildBr(builder, Dest)); }
	public Value BuildCondBr(Value If, LLVMBasicBlockRef Then, LLVMBasicBlockRef Else) { return new Value(LLVMBuildCondBr(builder, If.value(), Then, Else)); }
	public Value BuildSwitch(Value V, LLVMBasicBlockRef Else, int NumCases) { return new Value(LLVMBuildSwitch(builder, V.value(), Else, NumCases)); }
	public Value BuildIndirectBr(Value Addr, int NumDests) { return new Value(LLVMBuildIndirectBr(builder, Addr.value(), NumDests)); }
	public Value BuildInvoke(Value Fn, Pointer<LLVMValueRef> Args, int NumArgs, LLVMBasicBlockRef Then, LLVMBasicBlockRef Catch, String Name) { return new Value(LLVMBuildInvoke(builder, Fn.value(), Args, NumArgs, Then, Catch, Pointer.pointerToCString(Name))); }
//	public Value BuildUnwind() { return new Value(LLVMBuildUnwind(builder)); }
	public Value BuildUnreachable() { return new Value(LLVMBuildUnreachable(builder)); }

	// TODO: put these where they belong; they probably don't belong here.
	public void AddCase(Value Switch, Value OnVal, LLVMBasicBlockRef Dest) { LLVMAddCase(Switch.value(), OnVal.value(), Dest); }
	public void AddDestination(Value IndirectBr, LLVMBasicBlockRef Dest) { LLVMAddDestination(IndirectBr.value(), Dest); }

	public Value BuildAdd(Value LHS, Value RHS, String Name) { return new Value(LLVMBuildAdd(builder, LHS.value(), RHS.value(), Pointer.pointerToCString(Name))); }
	public Value BuildNSWAdd(Value LHS, Value RHS, String Name) { return new Value(LLVMBuildNSWAdd(builder, LHS.value(), RHS.value(), Pointer.pointerToCString(Name))); }
	public Value BuildNUWAdd(Value LHS, Value RHS, String Name) { return new Value(LLVMBuildNUWAdd(builder, LHS.value(), RHS.value(), Pointer.pointerToCString(Name))); }
	public Value BuildFAdd(Value LHS, Value RHS, String Name) { return new Value(LLVMBuildFAdd(builder, LHS.value(), RHS.value(), Pointer.pointerToCString(Name))); }
	public Value BuildSub(Value LHS, Value RHS, String Name) { return new Value(LLVMBuildSub(builder, LHS.value(), RHS.value(), Pointer.pointerToCString(Name))); }
	public Value BuildNSWSub(Value LHS, Value RHS, String Name) { return new Value(LLVMBuildNSWSub(builder, LHS.value(), RHS.value(), Pointer.pointerToCString(Name))); }
	public Value BuildNUWSub(Value LHS, Value RHS, String Name) { return new Value(LLVMBuildNUWSub(builder, LHS.value(), RHS.value(), Pointer.pointerToCString(Name))); }
	public Value BuildFSub(Value LHS, Value RHS, String Name) { return new Value(LLVMBuildFSub(builder, LHS.value(), RHS.value(), Pointer.pointerToCString(Name))); }
	public Value BuildMul(Value LHS, Value RHS, String Name) { return new Value(LLVMBuildMul(builder, LHS.value(), RHS.value(), Pointer.pointerToCString(Name))); }
	public Value BuildNSWMul(Value LHS, Value RHS, String Name) { return new Value(LLVMBuildNSWMul(builder, LHS.value(), RHS.value(), Pointer.pointerToCString(Name))); }
	public Value BuildNUWMul(Value LHS, Value RHS, String Name) { return new Value(LLVMBuildNUWMul(builder, LHS.value(), RHS.value(), Pointer.pointerToCString(Name))); }
	public Value BuildFMul(Value LHS, Value RHS, String Name) { return new Value(LLVMBuildFMul(builder, LHS.value(), RHS.value(), Pointer.pointerToCString(Name))); }
	public Value BuildUDiv(Value LHS, Value RHS, String Name) { return new Value(LLVMBuildUDiv(builder, LHS.value(), RHS.value(), Pointer.pointerToCString(Name))); }
	public Value BuildSDiv(Value LHS, Value RHS, String Name) { return new Value(LLVMBuildSDiv(builder, LHS.value(), RHS.value(), Pointer.pointerToCString(Name))); }
	public Value BuildExactSDiv(Value LHS, Value RHS, String Name) { return new Value(LLVMBuildExactSDiv(builder, LHS.value(), RHS.value(), Pointer.pointerToCString(Name))); }
	public Value BuildFDiv(Value LHS, Value RHS, String Name) { return new Value(LLVMBuildFDiv(builder, LHS.value(), RHS.value(), Pointer.pointerToCString(Name))); }
	public Value BuildURem(Value LHS, Value RHS, String Name) { return new Value(LLVMBuildURem(builder, LHS.value(), RHS.value(), Pointer.pointerToCString(Name))); }
	public Value BuildSRem(Value LHS, Value RHS, String Name) { return new Value(LLVMBuildSRem(builder, LHS.value(), RHS.value(), Pointer.pointerToCString(Name))); }
	public Value BuildFRem(Value LHS, Value RHS, String Name) { return new Value(LLVMBuildFRem(builder, LHS.value(), RHS.value(), Pointer.pointerToCString(Name))); }
	public Value BuildShl(Value LHS, Value RHS, String Name) { return new Value(LLVMBuildShl(builder, LHS.value(), RHS.value(), Pointer.pointerToCString(Name))); }
	public Value BuildLShr(Value LHS, Value RHS, String Name) { return new Value(LLVMBuildLShr(builder, LHS.value(), RHS.value(), Pointer.pointerToCString(Name))); }
	public Value BuildAShr(Value LHS, Value RHS, String Name) { return new Value(LLVMBuildAShr(builder, LHS.value(), RHS.value(), Pointer.pointerToCString(Name))); }
	public Value BuildAnd(Value LHS, Value RHS, String Name) { return new Value(LLVMBuildAnd(builder, LHS.value(), RHS.value(), Pointer.pointerToCString(Name))); }
	public Value BuildOr(Value LHS, Value RHS, String Name) { return new Value(LLVMBuildOr(builder, LHS.value(), RHS.value(), Pointer.pointerToCString(Name))); }
	public Value BuildXor(Value LHS, Value RHS, String Name) { return new Value(LLVMBuildXor(builder, LHS.value(), RHS.value(), Pointer.pointerToCString(Name))); }
	public Value BuildBinOp(IntValuedEnum<LLVMOpcode > Op, Value LHS, Value RHS, String Name) { return new Value(LLVMBuildBinOp(builder, Op, LHS.value(), RHS.value(), Pointer.pointerToCString(Name))); }
	public Value BuildNeg(Value V, String Name) { return new Value(LLVMBuildNeg(builder, V.value(), Pointer.pointerToCString(Name))); }
	public Value BuildNSWNeg(Value V, String Name) { return new Value(LLVMBuildNSWNeg(builder, V.value(), Pointer.pointerToCString(Name))); }
	public Value BuildNUWNeg(Value V, String Name) { return new Value(LLVMBuildNUWNeg(builder, V.value(), Pointer.pointerToCString(Name))); }
	public Value BuildFNeg(Value V, String Name) { return new Value(LLVMBuildFNeg(builder, V.value(), Pointer.pointerToCString(Name))); }
	public Value BuildNot(Value V, String Name) { return new Value(LLVMBuildNot(builder, V.value(), Pointer.pointerToCString(Name))); }
	public Value BuildMalloc(LLVMTypeRef Ty, String Name) { return new Value(LLVMBuildMalloc(builder, Ty, Pointer.pointerToCString(Name))); }
	public Value BuildArrayMalloc(LLVMTypeRef Ty, Value Val, String Name) { return new Value(LLVMBuildArrayMalloc(builder, Ty, Val.value(), Pointer.pointerToCString(Name))); }
	public Value BuildAlloca(LLVMTypeRef Ty, String Name) { return new Value(LLVMBuildAlloca(builder, Ty, Pointer.pointerToCString(Name))); }
	public Value BuildArrayAlloca(LLVMTypeRef Ty, Value Val, String Name) { return new Value(LLVMBuildArrayAlloca(builder, Ty, Val.value(), Pointer.pointerToCString(Name))); }
	public Value BuildFree(Value PointerVal) { return new Value(LLVMBuildFree(builder, PointerVal.value())); }
	public Value BuildLoad(Value PointerVal, String Name) { return new Value(LLVMBuildLoad(builder, PointerVal.value(), Pointer.pointerToCString(Name))); }
	public Value BuildStore(Value Val, Value Ptr) { return new Value(LLVMBuildStore(builder, Val.value(), Ptr.value())); }
	public Value BuildGEP(Value Ptr, Pointer<LLVMValueRef> Indices, int NumIndices, String Name) { return new Value(LLVMBuildGEP(builder, Ptr.value(), Indices, NumIndices, Pointer.pointerToCString(Name))); }
	public Value BuildInBoundsGEP(Value Ptr, Pointer<LLVMValueRef> Indices, int NumIndices, String Name) { return new Value(LLVMBuildInBoundsGEP(builder, Ptr.value(), Indices, NumIndices, Pointer.pointerToCString(Name))); }
	public Value BuildStructGEP(Value Ptr, int Idx, String Name) { return new Value(LLVMBuildStructGEP(builder, Ptr.value(), Idx, Pointer.pointerToCString(Name))); }
	public Value BuildGlobalString(String Str, String Name) { return new Value(LLVMBuildGlobalString(builder, Pointer.pointerToCString(Str), Pointer.pointerToCString(Name))); }
	public Value BuildGlobalStringPtr(String Str, String Name) { return new Value(LLVMBuildGlobalStringPtr(builder, Pointer.pointerToCString(Str), Pointer.pointerToCString(Name))); }
	public Value BuildTrunc(Value Val, LLVMTypeRef DestTy, String Name) { return new Value(LLVMBuildTrunc(builder, Val.value(), DestTy, Pointer.pointerToCString(Name))); }
	public Value BuildZExt(Value Val, LLVMTypeRef DestTy, String Name) { return new Value(LLVMBuildZExt(builder, Val.value(), DestTy, Pointer.pointerToCString(Name))); }
	public Value BuildSExt(Value Val, LLVMTypeRef DestTy, String Name) { return new Value(LLVMBuildSExt(builder, Val.value(), DestTy, Pointer.pointerToCString(Name))); }
	public Value BuildFPToUI(Value Val, LLVMTypeRef DestTy, String Name) { return new Value(LLVMBuildFPToUI(builder, Val.value(), DestTy, Pointer.pointerToCString(Name))); }
	public Value BuildFPToSI(Value Val, LLVMTypeRef DestTy, String Name) { return new Value(LLVMBuildFPToSI(builder, Val.value(), DestTy, Pointer.pointerToCString(Name))); }
	public Value BuildUIToFP(Value Val, LLVMTypeRef DestTy, String Name) { return new Value(LLVMBuildUIToFP(builder, Val.value(), DestTy, Pointer.pointerToCString(Name))); }
	public Value BuildSIToFP(Value Val, LLVMTypeRef DestTy, String Name) { return new Value(LLVMBuildSIToFP(builder, Val.value(), DestTy, Pointer.pointerToCString(Name))); }
	public Value BuildFPTrunc(Value Val, LLVMTypeRef DestTy, String Name) { return new Value(LLVMBuildFPTrunc(builder, Val.value(), DestTy, Pointer.pointerToCString(Name))); }
	public Value BuildFPExt(Value Val, LLVMTypeRef DestTy, String Name) { return new Value(LLVMBuildFPExt(builder, Val.value(), DestTy, Pointer.pointerToCString(Name))); }
	public Value BuildPtrToInt(Value Val, LLVMTypeRef DestTy, String Name) { return new Value(LLVMBuildPtrToInt(builder, Val.value(), DestTy, Pointer.pointerToCString(Name))); }
	public Value BuildIntToPtr(Value Val, LLVMTypeRef DestTy, String Name) { return new Value(LLVMBuildIntToPtr(builder, Val.value(), DestTy, Pointer.pointerToCString(Name))); }
	public Value BuildBitCast(Value Val, LLVMTypeRef DestTy, String Name) { return new Value(LLVMBuildBitCast(builder, Val.value(), DestTy, Pointer.pointerToCString(Name))); }
	public Value BuildZExtOrBitCast(Value Val, LLVMTypeRef DestTy, String Name) { return new Value(LLVMBuildZExtOrBitCast(builder, Val.value(), DestTy, Pointer.pointerToCString(Name))); }
	public Value BuildSExtOrBitCast(Value Val, LLVMTypeRef DestTy, String Name) { return new Value(LLVMBuildSExtOrBitCast(builder, Val.value(), DestTy, Pointer.pointerToCString(Name))); }
	public Value BuildTruncOrBitCast(Value Val, LLVMTypeRef DestTy, String Name) { return new Value(LLVMBuildTruncOrBitCast(builder, Val.value(), DestTy, Pointer.pointerToCString(Name))); }
	public Value BuildCast(IntValuedEnum<LLVMOpcode > Op, Value Val, LLVMTypeRef DestTy, String Name) { return new Value(LLVMBuildCast(builder, Op, Val.value(), DestTy, Pointer.pointerToCString(Name))); }
	public Value BuildPointerCast(Value Val, LLVMTypeRef DestTy, String Name) { return new Value(LLVMBuildPointerCast(builder, Val.value(), DestTy, Pointer.pointerToCString(Name))); }
	public Value BuildIntCast(Value Val, LLVMTypeRef DestTy, String Name) { return new Value(LLVMBuildIntCast(builder, Val.value(), DestTy, Pointer.pointerToCString(Name))); }
	public Value BuildFPCast(Value Val, LLVMTypeRef DestTy, String Name) { return new Value(LLVMBuildFPCast(builder, Val.value(), DestTy, Pointer.pointerToCString(Name))); }
	public Value BuildICmp(IntValuedEnum<LLVMIntPredicate > Op, Value LHS, Value RHS, String Name) { return new Value(LLVMBuildICmp(builder, Op, LHS.value(), RHS.value(), Pointer.pointerToCString(Name))); }
	public Value BuildFCmp(IntValuedEnum<LLVMRealPredicate > Op, Value LHS, Value RHS, String Name) { return new Value(LLVMBuildFCmp(builder, Op, LHS.value(), RHS.value(), Pointer.pointerToCString(Name))); }
	public Value BuildPhi(LLVMTypeRef Ty, String Name) { return new Value(LLVMBuildPhi(builder, Ty, Pointer.pointerToCString(Name))); }
	public Value BuildCall(Value Fn, Pointer<LLVMValueRef> Args, int NumArgs, String Name) { return new Value(LLVMBuildCall(builder, Fn.value(), Args, NumArgs, Pointer.pointerToCString(Name))); }
	public Value BuildSelect(Value If, Value Then, Value Else, String Name) { return new Value(LLVMBuildSelect(builder, If.value(), Then.value(), Else.value(), Pointer.pointerToCString(Name))); }
	public Value BuildVAArg(Value List, LLVMTypeRef Ty, String Name) { return new Value(LLVMBuildVAArg(builder, List.value(), Ty, Pointer.pointerToCString(Name))); }
	public Value BuildExtractElement(Value VecVal, Value Index, String Name) { return new Value(LLVMBuildExtractElement(builder, VecVal.value(), Index.value(), Pointer.pointerToCString(Name))); }
	public Value BuildInsertElement(Value VecVal, Value EltVal, Value Index, String Name) { return new Value(LLVMBuildInsertElement(builder, VecVal.value(), EltVal.value(), Index.value(), Pointer.pointerToCString(Name))); }
	public Value BuildShuffleVector(Value V1, Value V2, Value Mask, String Name) { return new Value(LLVMBuildShuffleVector(builder, V1.value(), V2.value(), Mask.value(), Pointer.pointerToCString(Name))); }
	public Value BuildExtractValue(Value AggVal, int Index, String Name) { return new Value(LLVMBuildExtractValue(builder, AggVal.value(), Index, Pointer.pointerToCString(Name))); }
	public Value BuildInsertValue(Value AggVal, Value EltVal, int Index, String Name) { return new Value(LLVMBuildInsertValue(builder, AggVal.value(), EltVal.value(), Index, Pointer.pointerToCString(Name))); }
	public Value BuildIsNull(Value Val, String Name) { return new Value(LLVMBuildIsNull(builder, Val.value(), Pointer.pointerToCString(Name))); }
	public Value BuildIsNotNull(Value Val, String Name) { return new Value(LLVMBuildIsNotNull(builder, Val.value(), Pointer.pointerToCString(Name))); }
	public Value BuildPtrDiff(Value LHS, Value RHS, String Name) { return new Value(LLVMBuildPtrDiff(builder, LHS.value(), RHS.value(), Pointer.pointerToCString(Name))); }

}
