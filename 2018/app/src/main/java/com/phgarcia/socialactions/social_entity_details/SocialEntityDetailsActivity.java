package com.phgarcia.socialactions.social_entity_details;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.phgarcia.socialactions.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SocialEntityDetailsActivity extends AppCompatActivity implements SocialEntityDetailsView {

    @BindView(R.id.tv_entity_details_name) TextView tv_entity_name;
    @BindView(R.id.tv_entity_details_description) TextView tv_entity_description;
    @BindView(R.id.iv_entity_details_header) ImageView iv_entity_cover_image;

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
    public void showDetails() {
        String entityName = getIntent().getStringExtra("ENTITY_NAME");
        String entityDescription = getIntent().getStringExtra("ENTITY_DESCRIPTION");
        String entityWebsite = getIntent().getStringExtra("ENTITY_WEBSITE");
        String entityCoverImageUrl = getIntent().getStringExtra("ENTITY_COVER_IMAGE");

        Picasso.with(this)
                .load(entityCoverImageUrl)
                .centerCrop()
                .fit()
                .into(iv_entity_cover_image);
        tv_entity_name.setText(entityName);
        tv_entity_description.setText(entityDescription);
    }
}
