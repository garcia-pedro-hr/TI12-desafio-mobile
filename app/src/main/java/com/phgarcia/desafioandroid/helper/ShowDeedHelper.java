package com.phgarcia.desafioandroid.helper;

import android.widget.TextView;

import com.phgarcia.desafioandroid.R;
import com.phgarcia.desafioandroid.activity.ShowDeedActivity;
import com.phgarcia.desafioandroid.model.Deed;

/**
 * Created by garci on 12/20/2017.
 */

public class ShowDeedHelper {
    private final TextView nameField;
    private final TextView websiteField;
    private final TextView descriptionField;

    private Deed deed;

    public ShowDeedHelper (ShowDeedActivity activity, Deed deed) {
        this.nameField        = (TextView) activity.findViewById(R.id.deed_name);
        this.websiteField     = (TextView) activity.findViewById(R.id.deed_site);
        this.descriptionField = (TextView) activity.findViewById(R.id.deed_description);
        this.deed = deed;
    }

    public void fillInfo() {
        this.nameField.setText(this.deed.getName());
        this.websiteField.setText(this.deed.getName());
        this.descriptionField.setText(this.deed.getDescription());
    }
}
