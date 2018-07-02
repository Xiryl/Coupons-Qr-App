package it.chiarani.qrcoupons.views;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import it.chiarani.qrcoupons.R;
import it.chiarani.qrcoupons.databinding.MainActivityLayoutBinding;
import it.chiarani.qrcoupons.fragments.ScanQrFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

  private static final String TAG_SCAN_QR = "tag_scan_qr";

  private MainActivityLayoutBinding binding;
  private BottomNavigationView      bottomNavigationView;
  private ScanQrFragment            scanqrF = new ScanQrFragment();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // set view
    binding = DataBindingUtil.setContentView(this, R.layout.main_activity_layout);

    // set bottom navbar
    bottomNavigationView = (BottomNavigationView) findViewById(R.id.main_activity_bottom_nav);

    // set scanqr as checked item
    bottomNavigationView.setSelectedItemId(R.id.bottombaritem_scanqr);

    // set scanqr fragment
    getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.main_activity_fragment_holder, scanqrF, TAG_SCAN_QR)
        .commit();
  }

  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.bottombaritem_account:
        // TODO
        return true;
      case R.id.bottombaritem_coupons:
        // TODO
        return true;
      case R.id.bottombaritem_scanqr:
        // TODO
        return true;
    }
    return false;
  }



}
