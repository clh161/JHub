package com.jacob.jhub.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jacob.jhub.R;
import com.jacob.jhub.model.Repository;
import com.jacob.jhub.viewholder.RepositoryViewHolder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Jacob on 4/8/2017.
 */

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryViewHolder> {
    private List<Repository> mItems = new ArrayList<>();

    @Override
    public RepositoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_repository, parent, false);
        return new RepositoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RepositoryViewHolder holder, int position) {
        Repository repository = mItems.get(position);
        holder.name.setText(repository.getName());
        holder.language.setText(repository.getLanguage());
        holder.star.setText(String.valueOf(repository.getStargazersCount()));
        holder.fork.setText(String.valueOf(repository.getForksCount()));
        try {
            SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            Date date = inFormat.parse(repository.getUpdatedAt());
            SimpleDateFormat outFormat = new SimpleDateFormat("d MMM yyyy HH:mm");
            holder.lastUpdate.setText(outFormat.format(date));
        } catch (ParseException e) {
            holder.lastUpdate.setText("");
            Log.e(getClass().getSimpleName(), e.getMessage());
        }
        holder.divider.setVisibility(position == mItems.size() - 1 ? View.GONE : View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void setItems(List<Repository> items) {
        mItems = items;
    }
}
