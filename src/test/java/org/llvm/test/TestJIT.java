package org.llvm.test;

import org.llvm.BasicBlock;
import org.llvm.Builder;
import org.llvm.ExecutionEngine;
import org.llvm.GenericValue;
import org.llvm.Module;
import org.llvm.TypeRef;
import org.llvm.Value;

import junit.framework.TestCase;

public class TestJIT extends TestCase {

    public void testSimpleFn() {
        Module mod = Module.CreateWithName("test_module");
        TypeRef ty_i32 = TypeRef.IntType(32);
        TypeRef ty_func = TypeRef.FunctionType(ty_i32, ty_i32, ty_i32);

        // Add a new function to the module, named "sum"
        Value f_sum = mod.AddFunction("sum", ty_func.type());

        // Name the function's params
        f_sum.GetParam(0).SetValueName("a");
        f_sum.GetParam(1).SetValueName("b");

        // Create a basic block named "entry"
        BasicBlock bb = f_sum.AppendBasicBlock("entry");

        // Create an instruction builder, and position it.
        Builder builder = Builder.CreateBuilder();
        builder.PositionBuilderAtEnd(bb);

        // Build some instructions
        Value tmp = builder.BuildAdd(f_sum.GetParam(0),	f_sum.GetParam(1), "tmp");
        builder.BuildRet(tmp);

        // Show the assembly dump of this simple function
        //mod.DumpModule();


        // Create an execution engine.
        ExecutionEngine ee = ExecutionEngine.createForModule(mod);

        // The arguments need to be passed as "GenericValue" objects.
        boolean SIGNED = true;
        GenericValue arg1 = GenericValue.Int(ty_i32, 2, SIGNED);
        GenericValue arg2 = GenericValue.Int(ty_i32, 40, SIGNED);

        // Compile and run!
        GenericValue retval = ee.RunFunction(f_sum, arg1, arg2);

        long retvalVal = retval.toInt(SIGNED);

        // The return value is also GenericValue.
        assertEquals(42, retvalVal);
    }

}
