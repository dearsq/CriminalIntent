package com.iyounix.android.criminalintent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

//抽象类
public abstract class SingleFragmentActivity extends AppCompatActivity {
    //抽象方法
    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

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
