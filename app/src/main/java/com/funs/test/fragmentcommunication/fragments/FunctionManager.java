package com.funs.test.fragmentcommunication.fragments;

import android.text.TextUtils;

import java.util.HashMap;

/**
 * Created by yc on 2017/9/11.
 */

public class FunctionManager {
    public static FunctionManager instance;

    private FunctionManager() {
        functionNoParamNoResultMap = new HashMap<>();
        functionOnlyParamMap = new HashMap<>();
        functionOnlyResultMap = new HashMap<>();
        functionParamResultMap = new HashMap<>();
    }

    public static FunctionManager getInstance() {
        if (instance == null) {
            synchronized (FunctionManager.class) {
                if (instance == null) {
                    instance = new FunctionManager();
                }
            }
        }
        return instance;
    }

    private HashMap<String, FunctionNoParamNoResult> functionNoParamNoResultMap;
    private HashMap<String, FunctionOnlyParam> functionOnlyParamMap;
    private HashMap<String, FunctionOnlyResult> functionOnlyResultMap;
    private HashMap<String, FunctionParamResult> functionParamResultMap;

    public FunctionManager addFunction(FunctionNoParamNoResult functionNoParamNoResult){
        functionNoParamNoResultMap.put(functionNoParamNoResult.FName,functionNoParamNoResult);
        return this;
    }
    public FunctionManager addFunction(FunctionOnlyParam functionOnlyParam){
        functionOnlyParamMap.put(functionOnlyParam.FName,functionOnlyParam);
        return this;
    }
    public FunctionManager addFunction(FunctionOnlyResult functionOnlyResult){
        functionOnlyResultMap.put(functionOnlyResult.FName,functionOnlyResult);
        return this;
    }
    public FunctionManager addFunction(FunctionParamResult functionParamResult){
        functionParamResultMap.put(functionParamResult.FName,functionParamResult);
        return this;
    }


    public void invokeFunc(String fName) throws FunctionException {
            if(TextUtils.isEmpty(fName)){
                return;
            }
            if(functionNoParamNoResultMap != null){
                FunctionNoParamNoResult f = functionNoParamNoResultMap.get(fName);
                if(f != null){
                    f.function();
                }

                if (f == null){
                    throw  new FunctionException("has no this function "+ fName);
                }
            }
    }

    public <Param> void invokeFunc(String fName , Param param) throws FunctionException {
        if(TextUtils.isEmpty(fName)){
            return;
        }
        if(functionOnlyParamMap != null){
            FunctionOnlyParam f = functionOnlyParamMap.get(fName);
            if(f != null){
                f.function(param);
            }

            if (f == null){
                throw  new FunctionException("has no this function "+ fName);
            }
        }
    }

    public <Result> Result invokeFunc(String fName , Class<Result> c ) throws FunctionException {
        if(TextUtils.isEmpty(fName)){
            return null;
        }
        if(functionOnlyResultMap != null){
            FunctionOnlyResult f = functionOnlyResultMap.get(fName);
            if(f != null){
                if(c != null)
                    return c.cast(f.function());
                 else
                    return (Result) f.function();
            }

            if (f == null){
                throw  new FunctionException("has no this function "+ fName);
            }
        }
        return  null;
    }

    public <Result,Param> Result invokeFunc(String fName ,Param param, Class<Result> c ) throws FunctionException {
        if(TextUtils.isEmpty(fName)){
            return null;
        }
        if(functionParamResultMap != null){
            FunctionParamResult f = functionParamResultMap.get(fName);
            if(f != null){
                if(c != null)
                    return c.cast(f.function(param));
                else
                    return (Result) f.function(param);
            }

            if (f == null){
                throw  new FunctionException("has no this function "+ fName);
            }
        }
        return  null;
    }
}
