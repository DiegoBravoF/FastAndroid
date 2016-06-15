package com.diveno.fastandroid.ui.base.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.diveno.fastandroid.R;
import com.diveno.fastandroid.data.model.Repo;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Diego on 15/06/2016.
 */
public class ReposAdapter extends RecyclerView.Adapter<ReposAdapter.ReposViewHolder> {

    private List<Repo> repos;
    private Context context;

    @Inject
    public ReposAdapter(Context context) {
        repos = new ArrayList<>();
        this.context = context;
    }

    public void setRepos(List<Repo> repos) {
        this.repos = repos;
    }

    @Override
    public ReposViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_repo, parent, false);
        return new ReposViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ReposViewHolder holder, int position) {
        Repo repo = repos.get(position);
        holder.nameTextView.setText(repo.getName());
        holder.languageTextView.setText(repo.getLanguage());
        holder.forksTextView.setText("" + repo.getForksCount());
        holder.starsTextView.setText("" + repo.getStargazersCount());
        Glide.with(context).load(repo.getOwner().getAvatarUrl()).into(holder.ownerImageView);
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }

    public class ReposViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_name)
        TextView nameTextView;
        @BindView(R.id.text_forks)
        TextView forksTextView;
        @BindView(R.id.text_stars)
        TextView starsTextView;
        @BindView(R.id.text_language)
        TextView languageTextView;
        @BindView(R.id.owner_image)
        ImageView ownerImageView;

        public ReposViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
