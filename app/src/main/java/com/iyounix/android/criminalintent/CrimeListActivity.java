package com.iyounix.android.criminalintent;


import android.support.v4.app.Fragment;
import android.util.Log;

public class CrimeListActivity extends SingleFragmentActivity {

    private static final String TAG = "YounixCrimeListActivity";

    @Override
    protected Fragment createFragment() {
        Log.d(TAG, "createFragment: ");
        return new CrimeListFragment();
    }
}
