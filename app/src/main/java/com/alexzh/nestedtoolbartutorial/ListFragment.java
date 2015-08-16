package com.alexzh.nestedtoolbartutorial;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.Arrays;
import java.util.List;

public class ListFragment extends Fragment implements View.OnClickListener {
    public final static String DETAIL = "detail";

    private RecyclerView mRecyclerView;
    private CountriesAdapter mAdapter;
    private List<String> mList;

    public void setList(List<String> list) {
        mList = list;
        if (mAdapter == null)
            mAdapter = new CountriesAdapter(mList, this);
        mAdapter.setList(list);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        if (mList == null)
            mList = Arrays.asList(getResources().getStringArray(R.array.countries));
        mAdapter = new CountriesAdapter(mList, this);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        getActivity().getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(R.id.content_frame, DetailFragment.newInstance(mList.get(mRecyclerView.getChildAdapterPosition(v))), DETAIL)
                .addToBackStack(DETAIL)
                .commit();
    }
}
