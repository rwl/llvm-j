package org.llvm;

import static org.llvm.binding.LLVMLibrary.*;

public class PassManager {

    private LLVMPassManagerRef manager;

    LLVMPassManagerRef manager() {
        return manager;
    }

    PassManager(LLVMPassManagerRef manager) {
        this.manager = manager;
    }

    /**
     * Constructs a new whole-module pass pipeline. This type of pipeline is<br>
     * suitable for link-time optimization and whole-module transformations.<br>
     *
     * @see llvm::PassManager::PassManager
     */
    public static PassManager create() {
        return new PassManager(LLVMCreatePassManager());
    }

    /**
     * Constructs a new function-by-function pass pipeline over the module<br>
     * provider. It does not take ownership of the module provider. This type of<br>
     * pipeline is suitable for code generation and JIT compilation tasks.<br>
     *
     * @see llvm::FunctionPassManager::FunctionPassManager
     */
    public static PassManager createForModule(Module m) {
        return new PassManager(
                LLVMCreateFunctionPassManagerForModule(m.module()));
    }

    /**
     * Deprecated: Use LLVMCreateFunctionPassManagerForModule instead.
     */
    public static PassManager createFPM(LLVMModuleProviderRef mp) {
        return new PassManager(LLVMCreateFunctionPassManager(mp));
    }

    public void finalize() {
        dispose();
    }

    /**
     * Finalizes all of the function passes scheduled in in the function pass<br>
     * manager. Returns 1 if any of the passes modified the module, 0 otherwise.<br>
     * Frees the memory of a pass pipeline. For function pipelines, does not
     * free<br>
     * the module provider.
     */
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
    // public static native int LLVMRunPassManager(LLVMPassManagerRef pm, LLVMModuleRef m);

    /**
     * Initializes all of the function passes scheduled in the function pass<br>
     * manager. Returns 1 if any of the passes modified the module, 0 otherwise.<br>
     *
     * @see llvm::FunctionPassManager::doInitialization
     */
    public void initialize() {
        boolean err = LLVMInitializeFunctionPassManager(manager) != 0;
        if (err) {
            throw new RuntimeException(
                    "error in LLVMInitializeFunctionPassManager");
        }
    }

    /**
     * Initializes, executes on the provided module, and finalizes all of the<br>
     * passes scheduled in the pass manager. Returns 1 if any of the passes<br>
     * modified the module, 0 otherwise.<br>
     *
     * @see llvm::PassManager::run(Module&)
     */
    public void runForModule(Module m) {
        boolean err = LLVMRunPassManager(manager, m.module()) != 0;
        if (err) {
            throw new RuntimeException("error in LLVMRunPassManager");
        }
    }

    /**
     * Executes all of the function passes scheduled in the function pass
     * manager<br>
     * on the provided function. Returns 1 if any of the passes modified the<br>
     * function, false otherwise.<br>
     *
     * @see llvm::FunctionPassManager::run(Function&)
     */
    public void runForFunction(Value f) {
        boolean err = LLVMRunFunctionPassManager(manager, f.value()) != 0;
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

    public void addInternalizePass(boolean allButMain) {
        LLVMAddInternalizePass(allButMain ? 1 : 0);
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

    public void addScalarReplAggregatesPassWithThreshold(int threshold) {
        LLVMAddScalarReplAggregatesPassWithThreshold(manager, threshold);
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
