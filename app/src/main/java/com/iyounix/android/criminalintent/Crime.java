package com.iyounix.android.criminalintent;

import java.util.UUID; // 工具类
import java.util.Date;

public class Crime {
    private UUID mId;
    private Date mDate;
    private String mTitle;
    private boolean mSolved;

    //需要警方介入的 Crime
    private int mRequiresPolice;

    public Crime(){
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public int getRequiresPolice() {
        return mRequiresPolice;
    }

    public void setRequiresPolice(int requiresPolice) {
        mRequiresPolice = requiresPolice;
    }
}
