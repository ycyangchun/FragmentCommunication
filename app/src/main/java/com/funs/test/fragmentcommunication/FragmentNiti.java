package com.funs.test.fragmentcommunication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.funs.test.fragmentcommunication.fragments.BaseFragment;
import com.funs.test.fragmentcommunication.fragments.FunctionException;

/**
 * Created by yc on 2017/9/11.
 */

public class FragmentNiti extends BaseFragment {

    public static final String NITIF =  FragmentNiti.class.getSimpleName()+"_PR";

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
                    String result = functionManager.invokeFunc(NITIF," YangChun ",String.class);
                    Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();
                } catch (FunctionException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
