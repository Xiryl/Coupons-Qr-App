package it.chiarani.qrcoupons.views;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import it.chiarani.qrcoupons.R;

public class FullScreenImageActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.full_screen_image_activity);

    Intent intent = getIntent();
    Bitmap bitmap = (Bitmap) intent.getParcelableExtra("BitmapImage");
    ImageView img = findViewById(R.id.full_screen_imageview);
    img.setImageBitmap(bitmap);
  }
}
