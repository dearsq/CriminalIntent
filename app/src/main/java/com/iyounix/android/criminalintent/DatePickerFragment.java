package com.iyounix.android.criminalintent;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;

public class DatePickerFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //法一: 通过定义 XML ,创建 Dialog
        View v = LayoutInflater.from(getActivity())
                        .inflate(R.layout.dialog_date, null);
        //法二: 直接代码生成 //缺点: 不好调整布局
        //DatePicker datePicker = new DatePicker(getActivity());

        //使用 AlertDialog.Builder 类创建 AlertDialog 实例
        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.date_picker_title)
                .setPositiveButton(android.R.string.ok,null)
                .create();
    }
}
