package com.jacob.jhub.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.jacob.jhub.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jacob on 4/8/2017.
 */

public class RepositoryViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.name)
    public TextView name;
    @BindView(R.id.language)
    public TextView language;
    @BindView(R.id.star)
    public TextView star;
    @BindView(R.id.fork)
    public TextView fork;
    @BindView(R.id.lastUpdate)
    public TextView lastUpdate;
    @BindView(R.id.divider)
    public View divider;

    public RepositoryViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
