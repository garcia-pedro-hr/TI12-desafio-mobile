package com.phgarcia.socialactions.social_entity_details;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.phgarcia.socialactions.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SocialEntityDetailsActivity extends AppCompatActivity implements SocialEntityDetailsView {

    @BindView(R.id.tv_entity_details_name) TextView tvEntityName;
    @BindView(R.id.tv_entity_details_description) TextView tvEntityDescription;
    @BindView(R.id.iv_entity_details_header) ImageView ivEntityCoverImage;

    SocialEntityDetailsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_entity_details);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ButterKnife.bind(this);
        presenter = new SocialEntityDetailsPresenter(this);

        showDetails();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showDetails() {
        String entityName = getIntent().getStringExtra("ENTITY_NAME");
        String entityDescription = getIntent().getStringExtra("ENTITY_DESCRIPTION");
        String entityCoverImageUrl = getIntent().getStringExtra("ENTITY_COVER_IMAGE");

        Picasso.with(this)
                .load(entityCoverImageUrl)
                .centerCrop()
                .fit()
                .into(ivEntityCoverImage);
        tvEntityName.setText(entityName);
        tvEntityDescription.setText(entityDescription);
    }

    @OnClick(R.id.fab_go_to_website)
    public void openBrowser(){
        Intent openUrlInBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse(getIntent().getStringExtra("ENTITY_WEBSITE")));
        startActivity(openUrlInBrowser);
    }
}
