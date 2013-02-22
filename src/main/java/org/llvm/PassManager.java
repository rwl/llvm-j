package org.llvm;

import static org.llvm.binding.LLVMLibrary.*;

public class PassManager {

    private LLVMPassManagerRef manager;

    /*package*/LLVMPassManagerRef manager() {
        return manager;
    }

    PassManager(LLVMPassManagerRef manager) {
        this.manager = manager;
    }

    public static PassManager create() {
        return new PassManager(LLVMCreatePassManager());
    }

    public static PassManager createForModule(Module M) {
        return new PassManager(
                LLVMCreateFunctionPassManagerForModule(M.module()));
    }

    public static PassManager createFPM(LLVMModuleProviderRef MP) {
        return new PassManager(LLVMCreateFunctionPassManager(MP));
    }

    public void finalize() {
        dispose();
    }

    public void dispose() {
        boolean err = LLVMFinalizeFunctionPassManager(manager) != 0;
        LLVMDisposePassManager(manager);
        manager = null;
        if (err) {
            throw new RuntimeException(
                    "error in LLVMFinalizeFunctionPassManager");
        }
    }

    /* PassManager */
    // public static native int LLVMRunPassManager(LLVMPassManagerRef PM, LLVMModuleRef M);

    public void initialize() {
        boolean err = LLVMInitializeFunctionPassManager(manager) != 0;
        if (err) {
            throw new RuntimeException(
                    "error in LLVMInitializeFunctionPassManager");
        }
    }

    public void runForModule(Module M) {
        boolean err = LLVMRunPassManager(manager, M.module()) != 0;
        if (err) {
            throw new RuntimeException("error in LLVMRunPassManager");
        }
    }

    public void runForFunction(Value F) {
        boolean err = LLVMRunFunctionPassManager(manager, F.value()) != 0;
        if (err) {
            throw new RuntimeException("error in LLVMRunFunctionPassManager");
        }
    }

    /* Function Pass Manager */
    public void addArgumentPromotionPass() {
        LLVMAddArgumentPromotionPass(manager);
    }

    public void addConstantMergePass() {
        LLVMAddConstantMergePass(manager);
    }

    public void addDeadArgEliminationPass() {
        LLVMAddDeadArgEliminationPass(manager);
    }

    /*public void addDeadTypeEliminationPass() {
        LLVMAddDeadTypeEliminationPass(manager);
    }*/

    public void addFunctionAttrsPass() {
        LLVMAddFunctionAttrsPass(manager);
    }

    public void addFunctionInliningPass() {
        LLVMAddFunctionInliningPass(manager);
    }

    public void addGlobalDCEPass() {
        LLVMAddGlobalDCEPass(manager);
    }

    public void addGlobalOptimizerPass() {
        LLVMAddGlobalOptimizerPass(manager);
    }

    public void addIPConstantPropagationPass() {
        LLVMAddIPConstantPropagationPass(manager);
    }

    /*public void addLowerSetJmpPass() {
        LLVMAddLowerSetJmpPass(manager);
    }*/

    public void addPruneEHPass() {
        LLVMAddPruneEHPass(manager);
    }

    public void addIPSCCPPass() {
        LLVMAddIPSCCPPass(manager);
    }

    public void addInternalizePass(boolean AllButMain) {
        LLVMAddInternalizePass(AllButMain ? 1 : 0);
    }

    /*public void addRaiseAllocationsPass() {
        LLVMAddRaiseAllocationsPass(manager);
    }*/

    public void addStripDeadPrototypesPass() {
        LLVMAddStripDeadPrototypesPass(manager);
    }

    public void addStripSymbolsPass() {
        LLVMAddStripSymbolsPass(manager);
    }

    public void addAggressiveDCEPass() {
        LLVMAddAggressiveDCEPass(manager);
    }

    public void addCFGSimplificationPass() {
        LLVMAddCFGSimplificationPass(manager);
    }

    public void addDeadStoreEliminationPass() {
        LLVMAddDeadStoreEliminationPass(manager);
    }

    public void addGVNPass() {
        LLVMAddGVNPass(manager);
    }

    public void addIndVarSimplifyPass() {
        LLVMAddIndVarSimplifyPass(manager);
    }

    public void addInstructionCombiningPass() {
        LLVMAddInstructionCombiningPass(manager);
    }

    public void addJumpThreadingPass() {
        LLVMAddJumpThreadingPass(manager);
    }

    public void addLICMPass() {
        LLVMAddLICMPass(manager);
    }

    public void addLoopDeletionPass() {
        LLVMAddLoopDeletionPass(manager);
    }

    public void addLoopRotatePass() {
        LLVMAddLoopRotatePass(manager);
    }

    public void addLoopUnrollPass() {
        LLVMAddLoopUnrollPass(manager);
    }

    public void addLoopUnswitchPass() {
        LLVMAddLoopUnswitchPass(manager);
    }

    public void addMemCpyOptPass() {
        LLVMAddMemCpyOptPass(manager);
    }

    public void addPromoteMemoryToRegisterPass() {
        LLVMAddPromoteMemoryToRegisterPass(manager);
    }

    public void addReassociatePass() {
        LLVMAddReassociatePass(manager);
    }

    public void addSCCPPass() {
        LLVMAddSCCPPass(manager);
    }

    public void addScalarReplAggregatesPass() {
        LLVMAddScalarReplAggregatesPass(manager);
    }

    public void addScalarReplAggregatesPassWithThreshold(int Threshold) {
        LLVMAddScalarReplAggregatesPassWithThreshold(manager, Threshold);
    }

    public void addSimplifyLibCallsPass() {
        LLVMAddSimplifyLibCallsPass(manager);
    }

    public void addTailCallEliminationPass() {
        LLVMAddTailCallEliminationPass(manager);
    }

    public void addConstantPropagationPass() {
        LLVMAddConstantPropagationPass(manager);
    }

    public void addDemoteMemoryToRegisterPass() {
        LLVMAddDemoteMemoryToRegisterPass(manager);
    }

    public void addVerifierPass() {
        LLVMAddVerifierPass(manager);
    }

}
