package com.auto1_test.api_aws_eu_qa_1.auto1codingtest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import com.auto1_test.api_aws_eu_qa_1.auto1codingtest.data.ManufacturerData;
import com.auto1_test.api_aws_eu_qa_1.auto1codingtest.data.ModelData;
import com.auto1_test.api_aws_eu_qa_1.auto1codingtest.data.YearData;
import com.auto1_test.api_aws_eu_qa_1.auto1codingtest.fragments.ManufactureFragment;
import com.auto1_test.api_aws_eu_qa_1.auto1codingtest.fragments.ModelItemFragment;
import com.auto1_test.api_aws_eu_qa_1.auto1codingtest.fragments.SummaryFragment;
import com.auto1_test.api_aws_eu_qa_1.auto1codingtest.fragments.YearFragment;

public class CarsMainActivity extends AppCompatActivity implements ManufactureFragment.OnListFragmentInteractionListener,
        ModelItemFragment.OnListFragmentInteractionListener, YearFragment.OnListFragmentInteractionListener {
    private  FragmentManager fragmentManager;
    FragmentTransaction mFragmentTransaction;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


//        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
//            @Override
//            public void onBackStackChanged() {
//                if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
//                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//                } else {
//                    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//                }
//            }
//        });
        fragmentManager = this.getSupportFragmentManager();
        if (savedInstanceState == null) {
            mFragmentTransaction = fragmentManager.beginTransaction();
            mFragmentTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
            Fragment frag = new ManufactureFragment();
           // frag.setRetainInstance(true);
           // fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            mFragmentTransaction.replace(R.id.fragment, frag);
            mFragmentTransaction.commit();
        }

    }

    @Override
    public void onListFragmentInteraction(ManufacturerData item) {
        mFragmentTransaction = fragmentManager.beginTransaction();
        mFragmentTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
        Fragment frag = new ModelItemFragment();
        Bundle bundle=new Bundle();
        bundle.putString("mManufacturerId",item.getmManufacturerId());
        bundle.putString("mManufacturerName",item.getmManufacturerName());
        frag.setArguments(bundle);
        mFragmentTransaction.addToBackStack(null).replace(R.id.fragment, frag).commit();
    }

    @Override
    public void onListFragmentInteraction(ModelData item) {
        mFragmentTransaction = fragmentManager.beginTransaction();
        mFragmentTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
        Fragment frag = new YearFragment();
        Bundle bundle=new Bundle();
        bundle.putString("mModel",item.mModel);
        bundle.putString("mManufacturerId",item.mManufacturerId);
        bundle.putString("mManufacturerName",item.getmManufacturerName);
        frag.setArguments(bundle);
        mFragmentTransaction.addToBackStack(null).replace(R.id.fragment, frag).commit();
    }

    @Override
    public void onListFragmentInteraction(YearData item) {
        mFragmentTransaction = fragmentManager.beginTransaction();
        mFragmentTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
        Fragment frag = new SummaryFragment();
        Bundle bundle=new Bundle();
        bundle.putString("mModel",item.mModel);
        bundle.putString("mManufacturerName",item.getmManufacturerName);
        bundle.putString("mYear",item.mYear);
        frag.setArguments(bundle);
        mFragmentTransaction.addToBackStack(null).replace(R.id.fragment, frag).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                getSupportFragmentManager().popBackStack();
            } else {
                super.onBackPressed();
            }
        }
        return true;
    }
}
