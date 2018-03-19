package com.phgarcia.socialactions.list_social_entities;

import android.view.View;

interface OnRecyclerViewSelected {
    void onClick(View view, int position);
    void onLongClick(View view, int position);
}
