package com.iyounix.android.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrimeLab {
    private static CrimeLab sCrimeLab;  // s前缀表示静态变量约定

    private List<Crime> mCrimes;

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null){
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    public List<Crime> getCrimes() {
        return mCrimes;
    }

    public Crime getCrime(UUID id) {
        for(Crime crime: mCrimes) {
            if (crime.getId().equals(id)) {
                return crime;
            }
        }
        return null;
    }

    // 私有构造方法
    private CrimeLab(Context context){
        // 由于 private List<Crime> mCrimes; 所以编译器推断出 ArrayList 可存放 Crime 对象
        mCrimes = new ArrayList<>();  // 使用常规Java数组存储列表元素
        for(int i = 0 ; i < 100; i++) {
            Crime crime = new Crime();
            crime.setTitle("Crime #"+i);
            crime.setSolved(i % 2 == 0); //设置偶数没有被解决,奇数被解决
            mCrimes.add(crime);
        }
    }
}
