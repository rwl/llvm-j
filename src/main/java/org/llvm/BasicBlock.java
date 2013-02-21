package org.llvm;

import org.bridj.Pointer;

import static org.llvm.binding.LLVMLibrary.*;


public class BasicBlock 
{
	private LLVMBasicBlockRef bb;
	/*package*/ LLVMBasicBlockRef bb() { return bb; }
	
	BasicBlock(LLVMBasicBlockRef bb) { this.bb = bb; }

	public Value BasicBlockAsValue() { return new Value(LLVMBasicBlockAsValue(bb)); }
	public Value GetBasicBlockParent() { return new Value(LLVMGetBasicBlockParent(bb)); }

	public BasicBlock GetNextBasicBlock() { return new BasicBlock(LLVMGetNextBasicBlock(bb)); }
	public BasicBlock GetPreviousBasicBlock() { return new BasicBlock(LLVMGetPreviousBasicBlock(bb)); }
	
	// insert a new bb before this bb, and return it 
	public BasicBlock InsertBasicBlock(String Name) { return new BasicBlock(LLVMInsertBasicBlock(bb, Pointer.pointerToCString(Name))); }

	// this.value is BB: insert a new BB before this bb, and return it
	public BasicBlock InsertBasicBlockInContext(Context C, String Name) { return new BasicBlock(LLVMInsertBasicBlockInContext(C.context(), bb, Pointer.pointerToCString(Name))); }
	
	public void DeleteBasicBlock() { LLVMDeleteBasicBlock(bb); }
	
	public void MoveBasicBlockBefore(BasicBlock MovePos) { LLVMMoveBasicBlockBefore(bb, MovePos.bb()); }
	public void MoveBasicBlockAfter(BasicBlock MovePos) { LLVMMoveBasicBlockAfter(bb, MovePos.bb()); }
	
	public Value GetFirstInstruction() { return new Value(LLVMGetFirstInstruction(bb)); }
	public Value GetLastInstruction() { return new Value(LLVMGetLastInstruction(bb)); }

}
