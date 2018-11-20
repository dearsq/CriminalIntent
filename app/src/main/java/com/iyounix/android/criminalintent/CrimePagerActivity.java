package com.iyounix.android.criminalintent;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

public class CrimePagerActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private List<Crime> mCrimes;

    private static final String EXTRA_CRIME_ID =
            "com.iyounix.android.criminalintent.crime_id";

    public static Intent newIntent(Context packageContext, UUID crimeId) {
        Intent intent = new Intent(packageContext, CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);

        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);

        mViewPager = findViewById(R.id.crime_view_pager);

        mCrimes = CrimeLab.get(this).getCrimes();
        FragmentManager fragmentManager = getSupportFragmentManager();

        //设置 adapter 为FragmentStatePagerAdapter的一个匿名实例
        // 这个匿名实例 是我们的代理，负责管理与 ViewPager 的对话并协同工作
        // 它首先将 getItem(int) 方法返回的 fragment 添加给 activity，然后才能使用 fragment 完成自己的工作
        // 代理所做的工作有:
        // 1. 将返回的 fragment 添加给托管 activity
        // 2. 帮助 ViewPager 找到 fragment 视图并一一对应
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager){

            // 数组列表中包含的列表项数目
            @Override
            public int getCount() {
                return mCrimes.size();
            }

            // 获取数据集中指定位置的 Crime 实例, 然后利用实例ID创建并返回经过有效配置的CrimeFragment
            @Override
            public Fragment getItem(int position) {
                Crime crime = mCrimes.get(position);
                return CrimeFragment.newInstance(crime.getId());
            }
        });

        // 循环检查 crime ID , 找到所选 crime 在数组中的索引位置
        // 如果 Crime实例的 crimeId == intent extra 的 crimeId
        // 则显示指定位置的列表项
        checkCrimeId(crimeId);
    }

    private void checkCrimeId(UUID crimeId) {
        for (int i = 0; i < mCrimes.size(); i++) {
            if(mCrimes.get(i).getId().equals(crimeId)){
                mViewPager.setCurrentItem(i);
                 break;
            }
        }
    }

    //FragmentPagerAdapter  FragmentStatePagerAdapter 两者用法一致, 区别在于卸载不用的fragment时,采用的处理方法不同
    //FragmentStatePagerAdapter 优点是更省内存

    //具体差别:
    //FragmentStatePagerAdapter //会销毁不需要的 fragment , commit 后, activity 的 fragmentmanager 中的 fragment 会被彻底删除
    // 其中 state 表明, 在销毁 fragment 时, 可以 onSaveInstanceState(Bundle)方法中保存fragment的Bundle信息
    //FragmentPagerAdapter  //会选择调用事务的 detach 而非 remove , 即 销毁了视图, 实例还在
}
