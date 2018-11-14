package com.iyounix.android.criminalintent;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

public class CrimeFragment extends Fragment{

    private static final String TAG = "YounixCrimeFragment";
    private Crime mCrime;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;

    /**
     * 公共方法, 为了让托管的 Activity 可以调用
     * 创建实例, 但是未生成视图
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
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

        /**
         * inflate, 加载一个布局文件.  将一个 xml 定义的布局文件查找出来
         * 传入布局资源ID , 将该布局文件加载到 Activity 中来操作
         * 视图的父视图, 需要附加到 res 资源文件的根控件
         * 告诉布局生成器 是否将生成的视图添加给父视图 , true就是立刻加载,false就是不加载 等待代码去加载
         */
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

        mSolvedCheckBox = v.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCrime.setSolved(isChecked);
            }
        });

        return v;
    }
}

