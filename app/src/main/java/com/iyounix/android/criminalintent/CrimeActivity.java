package com.iyounix.android.criminalintent;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CrimeActivity extends AppCompatActivity {

    /**
     * 保护方法
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime);

        // 获取 Activity 的 FragmentManager // FragmentManager 管理着 fragment事务会退栈
        FragmentManager fm = getSupportFragmentManager();

        // 将我们的 fragment 给 fm 管理
        Fragment fragment = fm.findFragmentById(R.id.fragment_container); //fragment 在 -> 销毁时 FM 会优先获取保存的队列, 然后重建
        if (fragment == null) { // fragment 不在, 启动新 fragment 事务
            fragment = new CrimeFragment();
            fm.beginTransaction().      // 创建并返回实例
                    add(R.id.fragment_container, fragment). //执行 fragment 添加操作
                    commit(); //提交事务
        }
    }
}
