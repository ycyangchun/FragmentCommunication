package com.funs.test.fragmentcommunication;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.funs.test.fragmentcommunication.fragments.BaseFragment;
import com.funs.test.fragmentcommunication.fragments.FunctionManager;
import com.funs.test.fragmentcommunication.fragments.FunctionNoParamNoResult;
import com.funs.test.fragmentcommunication.fragments.FunctionOnlyParam;
import com.funs.test.fragmentcommunication.fragments.FunctionParamResult;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    List<BaseFragment> fragmentList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fragmentList = new ArrayList<>();
        fragmentList.add(new FragmentHome());
        fragmentList.add(new FragmentDashboard());
        fragmentList.add(new FragmentNiti());
        addFragment(R.id.navigation_home);
    }

    private void addFragment(int id){
        FragmentManager fm =  this.getFragmentManager();
        FragmentTransaction t = fm.beginTransaction();
        switch (id) {
            case R.id.navigation_home:
                t.replace(R.id.fragment, fragmentList.get(0), "home");
                break;
            case R.id.navigation_dashboard:
                t.replace(R.id.fragment, fragmentList.get(1) , "dashboard");
                break;
            case R.id.navigation_notifications:
                t.replace(R.id.fragment, fragmentList.get(2) , "niti");
                break;
        }
        t.commitAllowingStateLoss();
    }

    public void setFunctionsForFragment(String tag){
        FragmentManager fm = this.getFragmentManager();
        BaseFragment baseFragment = (BaseFragment) fm.findFragmentByTag(tag);
        FunctionManager functionManager =  FunctionManager.getInstance();
        baseFragment.setFunctionManager(
         functionManager.addFunction(new FunctionNoParamNoResult(FragmentHome.HOMEF) {
            @Override
            public void function() {
                Toast.makeText(MainActivity.this, " home invoke  NoParamNoResult, toast in main ", Toast.LENGTH_SHORT).show();
            }
//        }) .addFunction(new FunctionOnlyResult<String>(FragmentDashboard.DASHBOARDF) {
//            @Override
//            public String function() {
//                return " dashboard invoke  OnlyResult ,toast in dashboard ";
//            }
        }) .addFunction(new FunctionParamResult<String,String>(FragmentNiti.NITIF) {
             @Override
             public String function(String o) {
                 return " notification invoke  ParamResult "+ o +" ,toast in notification ";
             }
         }).addFunction(new FunctionOnlyParam<String>(FragmentDashboard.DASHBOARDF) {
             @Override
             public void function(String o) {
                 Toast.makeText(MainActivity.this, " dashboard invoke  OnlyParam"+ o +", toast in main ", Toast.LENGTH_SHORT).show();
             }
         }));
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            addFragment(item.getItemId());
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };
}
