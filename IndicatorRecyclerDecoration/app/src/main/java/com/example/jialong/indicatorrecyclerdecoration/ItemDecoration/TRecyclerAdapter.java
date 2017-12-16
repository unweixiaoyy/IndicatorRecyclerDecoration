package com.example.jialong.indicatorrecyclerdecoration.ItemDecoration;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.jialong.indicatorrecyclerdecoration.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenjialong on 2017/12/11.
 */

public class TRecyclerAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<Info> data = new ArrayList<>();
    private AdapterView.OnItemClickListener onItemClickListener;


    public TRecyclerAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Info> data) {
        this.data.addAll(data);
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = R.layout.trecyclerview_viewholder;
        return new TRecyclerViewHolder(LayoutInflater.from(context).inflate(layoutId, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        TRecyclerViewHolder recyclerViewHolder = (TRecyclerViewHolder) holder;
        recyclerViewHolder.title.setText(data.get(position).title);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(null, holder.itemView, position, -1);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    ViewPager viewPager;

    public void setPage(ViewPager viewPager) {
        this.viewPager = viewPager;

    }

    public static class Info {
        public String title;


        public Info(String title) {
            this.title = title;
        }
    }
}
