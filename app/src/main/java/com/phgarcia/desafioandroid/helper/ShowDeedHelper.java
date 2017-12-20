package com.phgarcia.desafioandroid.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.media.MediaScannerConnection;
import android.media.audiofx.EnvironmentalReverb;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.phgarcia.desafioandroid.R;
import com.phgarcia.desafioandroid.activity.ShowDeedActivity;
import com.phgarcia.desafioandroid.model.Deed;
import com.phgarcia.desafioandroid.util.Constants;

import java.io.File;

/**
 * Created by garci on 12/20/2017.
 */

public class ShowDeedHelper {
    private final TextView  nameField;
    private final TextView  descriptionField;
    private final ImageView imageField;

    private Deed deed;

    public ShowDeedHelper (ShowDeedActivity activity, Deed deed) {
        this.nameField        = (TextView)  activity.findViewById(R.id.deed_name);
        this.descriptionField = (TextView)  activity.findViewById(R.id.deed_description);
        this.imageField       = (ImageView) activity.findViewById(R.id.deed_image);
        this.deed = deed;
    }

    public void fillInfo(Context context) {
        this.nameField.setText(this.deed.getName());
        this.descriptionField.setText(this.deed.getDescription());

        File image = new File(deed.getImagePath());

        if (image.exists()) {
            Bitmap bmp = BitmapFactory.decodeFile(image.getAbsolutePath());
            imageField.setImageBitmap(bmp);
        } else {
            Log.d("Image", "Image file doesn't exist");
        }

    }
}
