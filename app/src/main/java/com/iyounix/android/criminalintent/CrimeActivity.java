package com.iyounix.android.criminalintent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

public class CrimeActivity extends SingleFragmentActivity {
    private static final String TAG = "YounixCrimeActivity";
    //改为使用抽象类
    @Override
    protected Fragment createFragment() {
        Log.d(TAG, "createFragment: ");
        return new CrimeFragment();
    }
}
