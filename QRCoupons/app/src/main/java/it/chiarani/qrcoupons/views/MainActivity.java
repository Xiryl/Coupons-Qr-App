package it.chiarani.qrcoupons.views;

import android.app.FragmentTransaction;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import it.chiarani.qrcoupons.R;
import it.chiarani.qrcoupons.databinding.MainLayoutBinding;
import it.chiarani.qrcoupons.fragments.ListCouponFragment;
import it.chiarani.qrcoupons.fragments.ScanQrFragment;
import it.chiarani.qrcoupons.fragments.SettingsFragment;

public class MainActivity extends AppCompatActivity {

  // ---- PRIVATE FIELDS ----
  private static final String TAG_SCAN_QR = "tag_scan_qr";
  private static final String TAG_LIST_QR = "tag_list_qr";
  private static final String TAG_ACCO_QR = "tag_acco_qr";

  private BottomNavigationView bottomNavigationView;
  private ScanQrFragment       scanqrF = new ScanQrFragment();
  private ListCouponFragment   listCouponF = new ListCouponFragment();
  private MainLayoutBinding    binding;
  // ---- -------------- ----

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // set view
    binding = DataBindingUtil.setContentView(this, R.layout.main_layout);

    // set bottom navbar
    bottomNavigationView = (BottomNavigationView) findViewById(R.id.main_activity_bottom_nav);

    // set scanqr as checked item
    bottomNavigationView.setSelectedItemId(R.id.bottombaritem_scanqr);
    bottomNavigationView.setOnNavigationItemSelectedListener(
        new BottomNavigationView.OnNavigationItemSelectedListener() {
          @Override
          public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
              case R.id.bottombaritem_account:
                getSupportFragmentManager()
                .beginTransaction().replace(R.id.main_activity_fragment_holder, new SettingsFragment())
                    .commit();
                return true;
              case R.id.bottombaritem_coupons:
                getSupportFragmentManager()
                    .beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .replace(R.id.main_activity_fragment_holder, listCouponF, TAG_LIST_QR)
                    .commit();
                return true;
              case R.id.bottombaritem_scanqr:
                getSupportFragmentManager()
                    .beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .replace(R.id.main_activity_fragment_holder, scanqrF, TAG_SCAN_QR)
                    .commit();
                return true;
            }
            return false;
          }
        });

    // set scanqr fragment
    getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.main_activity_fragment_holder, scanqrF, TAG_SCAN_QR)
        .commit();
  }
}
