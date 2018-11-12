package com.iyounix.android.criminalintent;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class CrimeFragment extends Fragment{

    private Crime mCrime;
    private EditText mTitleField;
    private Button mDateButton;

    /**
     * 公共方法, 为了让托管的 Activity 可以调用
     * 创建实例, 但是未生成视图
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCrime = new Crime();
    }

    /**
     * onCreateView 的时候 onAttach onCreate onCreateView
     * 实例化 fragment 视图的布局, 返回实例化的 View 给托管的 Activity
     * @param inflater
     * @param container
     * @param savedInstanceState 用来存储恢复数据, 可以供该方法从保存状态下重建视图
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 传入布局资源ID , 视图的父视图, 告诉布局生成器 是否将生成的视图添加给父视图
        View v = inflater.inflate(R.layout.fragment_crime, container, false);

        // 生成并使用 EditText 组件
        mTitleField = v.findViewById(R.id.crime_title);
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //This space intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCrime.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mDateButton = v.findViewById(R.id.crime_date);
        mDateButton.setText(mCrime.getDate().toString());
        mDateButton.setEnabled(false); //禁能 显示为灰色

        return v;
    }
}

