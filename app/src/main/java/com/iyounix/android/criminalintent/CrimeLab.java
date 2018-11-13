package com.iyounix.android.criminalintent;

import android.content.Context;

public class CrimeLab {
    private static CrimeLab sCrimeLab;  // s前缀表示静态变量约定

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null){
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    // 私有构造方法
    private CrimeLab(Context context){

    }
}
