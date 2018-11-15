package com.iyounix.android.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.UUID;

public class CrimeActivity extends SingleFragmentActivity {
    private static final String TAG = "YounixCrimeActivity";

    public static final String EXTRA_CRIME_ID =
            "com.iyounix.android.criminalintent.crime_id";

    //改为使用抽象类
    @Override
    protected Fragment createFragment() {
        Log.d(TAG, "createFragment: ");
        return new CrimeFragment();
    }

    public static Intent newIntent(Context packageContext, UUID crimeId) {
        Intent intent = new Intent(packageContext,CrimeActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }
}
