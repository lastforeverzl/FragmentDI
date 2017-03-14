package com.zackyzhang.fragmentdi.mvp.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zackyzhang.fragmentdi.R;
import com.zackyzhang.fragmentdi.data.GithubRepo;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lei on 3/13/17.
 */

public class ReposAdapter extends RecyclerView.Adapter<ReposAdapter.Holder> {

    private final LayoutInflater mLayoutInflater;
    private List<GithubRepo> mRepos = new ArrayList<>();

    @Inject
    public ReposAdapter(Context context) {
        this.mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = this.mLayoutInflater.inflate(R.layout.list_item_layout, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        GithubRepo repo = this.mRepos.get(position);
        holder.bind(repo);
    }

    @Override
    public int getItemCount() {
        return mRepos.size();
    }

    public void loadRepos(List<GithubRepo> repos) {
        this.mRepos = repos;
        notifyDataSetChanged();
    }

    static class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.repo_name)
        TextView mTextView;

        Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(GithubRepo repo) {
            mTextView.setText(repo.getName());
        }

    }

}
