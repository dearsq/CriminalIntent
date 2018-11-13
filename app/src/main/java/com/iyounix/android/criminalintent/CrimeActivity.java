package com.iyounix.android.criminalintent;

import android.support.v4.app.Fragment;

public class CrimeActivity extends SingleFragmentActivity {

    //改为使用抽象类
    @Override
    protected Fragment createFragment() {
        return new CrimeFragment();
    }
}
