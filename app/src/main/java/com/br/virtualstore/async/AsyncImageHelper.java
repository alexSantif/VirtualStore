package com.br.virtualstore.async;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Alex on 22/01/2018.
 */

public class AsyncImageHelper extends AsyncTask<String, Void, Bitmap> {

    private ImageView imageView;

    public AsyncImageHelper(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... urls) {
        String imgUrl = urls[0];
        Bitmap bitmapImg = null;

        try {
            InputStream inputStream = new URL(imgUrl).openStream();
            bitmapImg = BitmapFactory.decodeStream(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bitmapImg;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }
}
