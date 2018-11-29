package com.iyounix.android.criminalintent;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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

import java.util.UUID;

public class CrimeFragment extends Fragment{

    private static final String TAG = "YounixCrimeFragment";
    private Crime mCrime;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;

    private static final String DIALOG_DATE = "DialogDate";
    private static final String ARG_CRIME_ID = "crime_id";

    private static final int REQUEST_CODE = 0;

    /**
     * 公共方法, 为了让托管的 Activity 可以调用
     * 创建实例, 但是未生成视图
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");

//        // 获取 UUID (extra 数据)的实现代码
//        UUID crimeId = (UUID)getActivity().getIntent() /*getIntent 返回用来启动 CrimeActivity 的 Intent */
//                .getSerializableExtra(CrimeActivity.EXTRA_CRIME_ID); /*获取 UUID 并存入变量中*/
        //从 fragment 的 argument 中获取 UUID
        UUID crimeId = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);

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
        mTitleField.setText(mCrime.getTitle());
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
        mDateButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                // Date 是时间戳
                DatePickerFragment dialog = DatePickerFragment.newInstance(mCrime.getDate());
                //将 DatePickerFragment 的 TargetFragment 设置为 CrimeFragment , 即关联起来
                dialog.setTargetFragment(CrimeFragment.this, REQUEST_CODE);
                //Dialog show
                dialog.show(manager, DIALOG_DATE);
            }
        });

        mSolvedCheckBox = v.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setChecked(mCrime.isSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCrime.setSolved(isChecked);
            }
        });

        return v;
    }

    /**
     * 完成 fragment 实例以及 Bundle 对象的创建, 然后将 argument 放入 Bundle 中, 最后再附加给 fragment
     * @param crimeId
     * @return
     */
    public static CrimeFragment newInstance(UUID crimeId) {

        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID,crimeId);

        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void returnResult() {
        getActivity().setResult(Activity.RESULT_OK, null);
    }

}

