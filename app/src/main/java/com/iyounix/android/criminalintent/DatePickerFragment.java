package com.iyounix.android.criminalintent;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;

public class DatePickerFragment extends DialogFragment {

    private static final String ARG_DATE = "date";
    private DatePicker mDatePicker;

    // 利用 Bundle 传递数据
    public static DatePickerFragment newInstance(Date date) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE,date);

        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //从 argument 中获取 Date 对象,用它和 Calendar 对象初始化 DatePicker
        // 实现了 Fragm
        Date date = (Date)getArguments().getSerializable(ARG_DATE);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        //法一: 通过定义 XML ,创建 Dialog
        View v = LayoutInflater.from(getActivity())
                        .inflate(R.layout.dialog_date, null);
        //法二: 直接代码生成 //缺点: 不好调整布局
        //DatePicker datePicker = new DatePicker(getActivity());

        mDatePicker = v.findViewById(R.id.dialog_date_picker);
        mDatePicker.init(year,month,day,null);

        //使用 AlertDialog.Builder 类创建 AlertDialog 实例
        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.date_picker_title)
                .setPositiveButton(android.R.string.ok,null)
                .create();
    }
}
