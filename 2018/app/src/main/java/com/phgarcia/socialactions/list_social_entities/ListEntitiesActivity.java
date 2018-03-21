package com.phgarcia.socialactions.list_social_entities;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.phgarcia.socialactions.R;
import com.phgarcia.socialactions.entities.SocialEntity;
import com.phgarcia.socialactions.social_entity_details.SocialEntityDetailsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListEntitiesActivity extends AppCompatActivity implements ListEntitiesView {

    @BindView(R.id.rv_social_entities_list) RecyclerView rvEntities;
    @BindView(R.id.swipe_refresh_layout) SwipeRefreshLayout swipeRefreshLayout;

    ListEntitiesPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_entities);

        ButterKnife.bind(this);
        presenter = new ListEntitiesPresenter(this);

        // Checks if there is a json to be loaded
        presenter.updateList(getIntent().getStringExtra("json"));

        // Swipe refresh layout refresh logic
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Method calls setRefreshing(false) when it's finished
                presenter.updateList(getIntent().getStringExtra("json"));
            }
        });
    }

    @Override
    public void updateList(List<SocialEntity> socialEntityList) {
        // Sets adapter
        ListEntitiesAdapter adapter = new ListEntitiesAdapter(socialEntityList, this);
        adapter.setOnRecyclerViewSelected(new OnRecyclerViewSelected() {
            @Override
            public void onClick(View view, int position) {
                Intent openDetailActivity = new Intent(ListEntitiesActivity.this, SocialEntityDetailsActivity.class);
                openDetailActivity.putExtra("ENTITY_NAME", presenter.getEntity(position).getName());
                openDetailActivity.putExtra("ENTITY_DESCRIPTION", presenter.getEntity(position).getDescription());
                openDetailActivity.putExtra("ENTITY_WEBSITE", presenter.getEntity(position).getWebsite());
                openDetailActivity.putExtra("ENTITY_COVER_IMAGE", presenter.getEntity(position).getCoverImageURL());
                startActivity(openDetailActivity);
            }

            @Override
            public void onLongClick(View view, int position) {
                Intent openUrlInBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse(presenter.getEntity(position).getWebsite()));
                startActivity(openUrlInBrowser);
            }
        });

        rvEntities.setAdapter(adapter);

        // Layout manager setting
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvEntities.setLayoutManager(layoutManager);

        // Adding item separator
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, layoutManager.getOrientation());
        rvEntities.addItemDecoration(dividerItemDecoration);

        // Stop refreshing if it is refreshing
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void showLoading() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
