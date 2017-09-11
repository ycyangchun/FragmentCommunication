package com.funs.test.fragmentcommunication.fragments;

/**
 * Created by yc on 2017/9/11.
 */

public abstract class  FunctionOnlyParam<Param> extends Function {

    public FunctionOnlyParam(String FName) {
        super(FName);
    }
    public abstract  void  function(Param param);
}
