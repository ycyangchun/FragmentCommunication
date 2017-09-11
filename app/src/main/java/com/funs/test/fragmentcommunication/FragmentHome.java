package com.funs.test.fragmentcommunication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.funs.test.fragmentcommunication.fragments.BaseFragment;
import com.funs.test.fragmentcommunication.fragments.FunctionException;

/**
 * Created by yc on 2017/9/11.
 */

public class FragmentHome extends BaseFragment {

    public static final String HOMEF =  FragmentHome.class.getSimpleName()+"_NPNR";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getView().findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    functionManager.invokeFunc(HOMEF);
                } catch (FunctionException functionException) {
                    functionException.printStackTrace();
                }
            }
        });
    }
}
