package com.funs.test.fragmentcommunication.fragments;

/**
 * Created by yc on 2017/9/11.
 */

public abstract class FunctionParamResult<Param,Result> extends Function {

    public FunctionParamResult(String FName) {
        super(FName);
    }
    public abstract  Result  function(Param param);
}
