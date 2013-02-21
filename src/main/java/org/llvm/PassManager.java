package org.llvm;

import static org.llvm.binding.LLVMLibrary.*;


public class PassManager {
	
	private LLVMPassManagerRef manager;
	/*package*/ LLVMPassManagerRef manager() { return manager; }
	
	PassManager(LLVMPassManagerRef manager) { this.manager = manager; }
	
	public static PassManager create() {
		return new PassManager(LLVMCreatePassManager());
	}
	
	public static PassManager createForModule(Module M) {
		return new PassManager(LLVMCreateFunctionPassManagerForModule(M.module()));
	}
	
	public static PassManager createFPM(LLVMModuleProviderRef MP) {
		return new PassManager(LLVMCreateFunctionPassManager(MP));
	}
	
	public void finalize() { dispose(); }
	
	public void dispose() { 		
		boolean err = LLVMFinalizeFunctionPassManager(manager) != 0;		
		LLVMDisposePassManager(manager);
		manager = null; 
		if (err) throw new RuntimeException("error in LLVMFinalizeFunctionPassManager");
	}
	
	////////////////PassManager/////////////////////
//	public static native int LLVMRunPassManager(LLVMPassManagerRef PM, LLVMModuleRef M);
//	
	
	public void Initialize() {
		boolean err = LLVMInitializeFunctionPassManager(manager) != 0;
		if (err) throw new RuntimeException("error in LLVMInitializeFunctionPassManager");
	}
	// run for Module
	public void RunForModule(Module M) {
		boolean err = LLVMRunPassManager(manager, M.module()) != 0;
		if (err) throw new RuntimeException("error in LLVMRunPassManager");
	}
	// run for Function
	public void RunForFunction(Value F) {
		boolean err = LLVMRunFunctionPassManager(manager, F.value()) != 0;
		if (err) throw new RuntimeException("error in LLVMRunFunctionPassManager");
	}
	
	
	/////////////////FPM////////////////////////////////////
	public void AddArgumentPromotionPass() { LLVMAddArgumentPromotionPass(manager); }
	public void AddConstantMergePass() { LLVMAddConstantMergePass(manager); }
	public void AddDeadArgEliminationPass() { LLVMAddDeadArgEliminationPass(manager); }
//public void AddDeadTypeEliminationPass() { LLVMAddDeadTypeEliminationPass(manager); }
	public void AddFunctionAttrsPass() { LLVMAddFunctionAttrsPass(manager); }
	public void AddFunctionInliningPass() { LLVMAddFunctionInliningPass(manager); }
	public void AddGlobalDCEPass() { LLVMAddGlobalDCEPass(manager); }
	public void AddGlobalOptimizerPass() { LLVMAddGlobalOptimizerPass(manager); }
	public void AddIPConstantPropagationPass() { LLVMAddIPConstantPropagationPass(manager); }
//	public void AddLowerSetJmpPass() { LLVMAddLowerSetJmpPass(manager); }
	public void AddPruneEHPass() { LLVMAddPruneEHPass(manager); }
	public void AddIPSCCPPass() { LLVMAddIPSCCPPass(manager); }
	public void AddInternalizePass(boolean AllButMain) { LLVMAddInternalizePass(AllButMain ? 1 : 0); }
//public void AddRaiseAllocationsPass() { LLVMAddRaiseAllocationsPass(manager); }
	public void AddStripDeadPrototypesPass() { LLVMAddStripDeadPrototypesPass(manager); }
	public void AddStripSymbolsPass() { LLVMAddStripSymbolsPass(manager); }
	public void AddAggressiveDCEPass() { LLVMAddAggressiveDCEPass(manager); }
	public void AddCFGSimplificationPass() { LLVMAddCFGSimplificationPass(manager); }
	public void AddDeadStoreEliminationPass() { LLVMAddDeadStoreEliminationPass(manager); }
	public void AddGVNPass() { LLVMAddGVNPass(manager); }
	public void AddIndVarSimplifyPass() { LLVMAddIndVarSimplifyPass(manager); }
	public void AddInstructionCombiningPass() { LLVMAddInstructionCombiningPass(manager); }
	public void AddJumpThreadingPass() { LLVMAddJumpThreadingPass(manager); }
	public void AddLICMPass() { LLVMAddLICMPass(manager); }
	public void AddLoopDeletionPass() { LLVMAddLoopDeletionPass(manager); }
	public void AddLoopRotatePass() { LLVMAddLoopRotatePass(manager); }
	public void AddLoopUnrollPass() { LLVMAddLoopUnrollPass(manager); }
	public void AddLoopUnswitchPass() { LLVMAddLoopUnswitchPass(manager); }
	public void AddMemCpyOptPass() { LLVMAddMemCpyOptPass(manager); }
	public void AddPromoteMemoryToRegisterPass() { LLVMAddPromoteMemoryToRegisterPass(manager); }
	public void AddReassociatePass() { LLVMAddReassociatePass(manager); }
	public void AddSCCPPass() { LLVMAddSCCPPass(manager); }
	public void AddScalarReplAggregatesPass() { LLVMAddScalarReplAggregatesPass(manager); }
	public void AddScalarReplAggregatesPassWithThreshold(int Threshold) { LLVMAddScalarReplAggregatesPassWithThreshold(manager, Threshold); }
	public void AddSimplifyLibCallsPass() { LLVMAddSimplifyLibCallsPass(manager); }
	public void AddTailCallEliminationPass() { LLVMAddTailCallEliminationPass(manager); }
	public void AddConstantPropagationPass() { LLVMAddConstantPropagationPass(manager); }
	public void AddDemoteMemoryToRegisterPass() { LLVMAddDemoteMemoryToRegisterPass(manager); }
	public void AddVerifierPass() { LLVMAddVerifierPass(manager); }

}
