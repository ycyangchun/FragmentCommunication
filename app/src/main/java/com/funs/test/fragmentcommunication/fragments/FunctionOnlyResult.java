package com.funs.test.fragmentcommunication.fragments;

/**
 * Created by yc on 2017/9/11.
 */

public abstract class FunctionOnlyResult<Result> extends Function {

    public FunctionOnlyResult(String FName) {
        super(FName);
    }
    public abstract  Result  function();
}
