package com.phgarcia.socialactions.list_social_entities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.phgarcia.socialactions.R;
import com.phgarcia.socialactions.entities.SocialEntity;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

class ListEntitiesAdapter extends RecyclerView.Adapter<ListEntitiesAdapter.ViewHolder> {

    private Context context;
    private OnRecyclerViewSelected onRecyclerViewSelected;
    private List<SocialEntity> socialEntityList;

    ListEntitiesAdapter(List<SocialEntity> socialEntityList, Context context) {
        this.socialEntityList = socialEntityList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create new view
        View v = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.social_entity_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SocialEntity socialEntity = socialEntityList.get(position);
        holder.tvEntityName.setText(socialEntity.getName());
        Picasso.with(context)
                .load(socialEntity.getCoverImageURL())
                .centerCrop()
                .fit()
                .into(holder.ivBackground);
    }

    @Override
    public int getItemCount() {
        return socialEntityList.size();
    }

    // ViewHolder Class Implementation
    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_entity_name) TextView tvEntityName;
        @BindView(R.id.iv_background) ImageView ivBackground;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.container)
        void onItemClick(View view) {
            if (onRecyclerViewSelected != null) {
                onRecyclerViewSelected.onClick(view, getAdapterPosition());
            }
        }

        @OnLongClick(R.id.container)
        boolean onItemLongClick(View view) {
            if (onRecyclerViewSelected != null) {
                onRecyclerViewSelected.onLongClick(view, getAdapterPosition());
            }
            return false;
        }
    }

    public void setOnRecyclerViewSelected(OnRecyclerViewSelected onRecyclerViewSelected) {
        this.onRecyclerViewSelected = onRecyclerViewSelected;
    }
}
