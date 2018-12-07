package com.iyounix.android.criminalintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CrimeListFragment extends Fragment {
    //TODO
    // Nothing to do
    private static final String TAG = "YounixCrimeListFragment";

    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;


    private static final int REQUEST_CRIME = 1;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);

        mCrimeRecyclerView = view.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    //创建右上角的菜单
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_crime_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_crime:
                Crime crime = new Crime();
                CrimeLab.get(getActivity()).addCrime(crime);
                Intent intent = CrimePagerActivity.newIntent(getActivity(), crime.getId());
                startActivity(intent);
                return true; //表明任务已经完成
            default: //如果 ID 项不存在, 超类版本方法会被调用
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateUI() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();

        if (mAdapter == null) {
            mAdapter = new CrimeAdapter(crimes);
            mCrimeRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        //通知 FragmentManager : 其管理的 fragment 应该接受 onCreateOptionsMenu 方法的调用指令
        setHasOptionsMenu(true);
    }

    // CrimeHolder 中完成视图绑定工作
    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

        private TextView mTitleTextView;
        private TextView mDateTextView;
        private ImageView mSolvedImageView;

        private Crime mCrime;


        // 构造函数中实例化 list_item_crime 布局, 传给 super
        public CrimeHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_crime, parent, false));

            itemView.setOnClickListener(this);

            mTitleTextView = itemView.findViewById(R.id.crime_title);
            mDateTextView = itemView.findViewById(R.id.crime_date);
            mSolvedImageView = itemView.findViewById(R.id.crime_solved);
        }

        // 每次有新的 Crime 要在 CrimeHolder 中显示时,会调用 bind
        public void bind(Crime crime) {
            mCrime = crime;
            mTitleTextView.setText(mCrime.getTitle());
            mDateTextView.setText(mCrime.getDate().toString());
            mSolvedImageView.setVisibility(crime.isSolved() ? View.VISIBLE : View.GONE);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(getActivity(),mCrime.getTitle() + " clicked!", Toast.LENGTH_SHORT).show();
            //Intent intent = CrimeActivity.newIntent(getActivity(), mCrime.getId());
            Intent intent = CrimePagerActivity.newIntent(getActivity(), mCrime.getId());
//            startActivity(intent);
            startActivityForResult(intent, REQUEST_CRIME);
        }
    }

    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {
        private List<Crime> mCrimes;

        public CrimeAdapter(List<Crime> crimes){
            mCrimes = crimes;
        }

        /**
         * 当 RecyclerView 需要 ViewHolder 来显示列表时, 会调用 onCreateViewHolder 方法
         * @param parent
         * @param viewType
         * @return
         */
        @NonNull
        @Override
        public CrimeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // 我们需要创建一个 LayoutInflater , 用它来创建 CrimeHolder
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new CrimeHolder(layoutInflater, parent);
        }

        /**
         * 需要保证这个方法轻量高效
         * @param holder
         * @param position
         */
        @Override
        public void onBindViewHolder(@NonNull CrimeHolder holder, int position) {
            Crime crime = mCrimes.get(position);
            holder.bind(crime);
        }



        @Override
        public int getItemCount() {
            return mCrimes.size();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CRIME) {
            // Handle result
        }
    }
}
