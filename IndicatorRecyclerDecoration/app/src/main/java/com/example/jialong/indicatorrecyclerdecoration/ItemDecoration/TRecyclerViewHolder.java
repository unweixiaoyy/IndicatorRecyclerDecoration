package com.example.jialong.indicatorrecyclerdecoration.ItemDecoration;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.jialong.indicatorrecyclerdecoration.R;

/**
 * Created by chenjialong on 2017/12/11.
 */

public class TRecyclerViewHolder extends RecyclerView.ViewHolder {

    public TextView title;

    public TRecyclerViewHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.t_ttttt);
    }

}
