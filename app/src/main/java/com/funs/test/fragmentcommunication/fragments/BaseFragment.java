package com.funs.test.fragmentcommunication.fragments;

import android.app.Fragment;
import android.content.Context;

import com.funs.test.fragmentcommunication.MainActivity;

/**
 * Created by yc on 2017/9/11.
 */

public class BaseFragment extends Fragment {

    protected  FunctionManager functionManager;
    private MainActivity mBaseActivity;

    public void setFunctionManager(FunctionManager functionManager) {
        this.functionManager = functionManager;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if( context instanceof MainActivity){
            mBaseActivity = (MainActivity)context;
            mBaseActivity.setFunctionsForFragment(getTag());
        }
    }
}
