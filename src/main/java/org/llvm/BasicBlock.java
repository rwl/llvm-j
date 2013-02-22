package org.llvm;

import org.bridj.Pointer;

import static org.llvm.binding.LLVMLibrary.*;

/**
 * This represents a single basic block in LLVM. A basic block is simply a
 * container of instructions that execute sequentially.
 */
public class BasicBlock {
	private LLVMBasicBlockRef bb;

	/* package */LLVMBasicBlockRef bb() {
		return bb;
	}

	BasicBlock(LLVMBasicBlockRef bb) {
		this.bb = bb;
	}

	public Value basicBlockAsValue() {
		return new Value(LLVMBasicBlockAsValue(bb));
	}

	public Value getBasicBlockParent() {
		return new Value(LLVMGetBasicBlockParent(bb));
	}

	public BasicBlock getNextBasicBlock() {
		return new BasicBlock(LLVMGetNextBasicBlock(bb));
	}

	public BasicBlock getPreviousBasicBlock() {
		return new BasicBlock(LLVMGetPreviousBasicBlock(bb));
	}

	/**
	 * Insert a new bb before this bb, and return it
	 */
	public BasicBlock insertBasicBlock(String Name) {
		return new BasicBlock(LLVMInsertBasicBlock(bb,
				Pointer.pointerToCString(Name)));
	}

	/**
	 * Insert a new BB before this bb, and return it
	 */
	public BasicBlock InsertBasicBlockInContext(Context C, String Name) {
		return new BasicBlock(LLVMInsertBasicBlockInContext(C.context(), bb,
				Pointer.pointerToCString(Name)));
	}

	public void deleteBasicBlock() {
		LLVMDeleteBasicBlock(bb);
	}

	public void moveBasicBlockBefore(BasicBlock MovePos) {
		LLVMMoveBasicBlockBefore(bb, MovePos.bb());
	}

	public void moveBasicBlockAfter(BasicBlock MovePos) {
		LLVMMoveBasicBlockAfter(bb, MovePos.bb());
	}

	public Value getFirstInstruction() {
		return new Value(LLVMGetFirstInstruction(bb));
	}

	public Value getLastInstruction() {
		return new Value(LLVMGetLastInstruction(bb));
	}

}
