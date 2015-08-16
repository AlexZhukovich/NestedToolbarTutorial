package com.alexzh.nestedtoolbartutorial;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.ViewHolder> {

    private List<String> mList;
    private View.OnClickListener mListener;

    public CountriesAdapter(List<String> list, View.OnClickListener listener) {
        mList = list;
        mListener = listener;
    }

    public void setList(List<String> list) {
        mList = list;
        notifyDataSetChanged();
    }

    @Override
    public CountriesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_card,
                parent,
                false);
        mView.setOnClickListener(mListener);
        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.text.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView text;

        public ViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.text);
        }
    }
}
