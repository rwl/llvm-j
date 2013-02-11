package main;

import llvm.*;

public class JitExample
{
	public static void echo(String s) {	System.out.println(s); }

	static { System.setProperty("jna.library.path", "C:/LLVM/llvm-3.0/bin/"); }

	public static void main(String[] args) {
		testSimpleFn();
	}

	static void testSimpleFn() {
		Module mod = Module.CreateWithName("my_module");
		TypeRef ty_i32 = TypeRef.IntType(32);
		TypeRef ty_func = TypeRef.FunctionType(ty_i32, ty_i32, ty_i32);

		// add a new function to the module, named "sum"
		Value f_sum = mod.AddFunction("sum", ty_func.type());

		// name the function's params
		f_sum.GetParam(0).SetValueName("a");
		f_sum.GetParam(1).SetValueName("b");

		// create a basic block named "entry"
		BasicBlock bb = f_sum.AppendBasicBlock("entry");

		// create an instruction builder, and position it.
		Builder builder = Builder.CreateBuilder();
		builder.PositionBuilderAtEnd(bb);

		// build some instructions
		Value tmp = builder.BuildAdd(f_sum.GetParam(0),	f_sum.GetParam(1), "tmp");
		builder.BuildRet(tmp);

		// show the assembly dump of this simple function
		mod.DumpModule();


		//# Create an execution engine.
		ExecutionEngine ee = ExecutionEngine.createForModule(mod);

		//# The arguments need to be passed as "GenericValue" objects.
		boolean SIGNED = true;
		GenericValue arg1 = GenericValue.Int(ty_i32, 100, SIGNED);
		GenericValue arg2 = GenericValue.Int(ty_i32, 42, SIGNED);

		//# Now let's compile and run!
		GenericValue retval = ee.RunFunction(f_sum, arg1, arg2);

		long retvalVal = retval.toInt(SIGNED);

		//# The return value is also GenericValue. Let's print it.
		echo("returned " + retvalVal);
	}

}
