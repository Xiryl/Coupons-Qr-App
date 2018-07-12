package it.chiarani.qrcoupons.views;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.google.firebase.crash.FirebaseCrash;

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


    //  Declare a new thread to do a preference check
    Thread t = new Thread(new Runnable() {
      @Override
      public void run() {
        //  Initialize SharedPreferences
        SharedPreferences getPrefs = PreferenceManager
            .getDefaultSharedPreferences(getBaseContext());

        //  Create a new boolean and preference and set it to true
        boolean isFirstStart = getPrefs.getBoolean("firstStart", true);

        //  If the activity has never started before...
        if (isFirstStart) {

          //  Launch app intro
          final Intent i = new Intent(MainActivity.this, AppIntroActivity.class);

          runOnUiThread(new Runnable() {
            @Override public void run() {
              startActivity(i);
            }
          });

          //  Make a new preferences editor
          SharedPreferences.Editor e = getPrefs.edit();

          //  Edit preference to make it false because we don't want this to run again
          e.putBoolean("firstStart", false);

          //  Apply changes
          e.apply();
        }
      }
    });

    // Start the thread
    t.start();

    // set scanqr fragment
    getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.main_activity_fragment_holder, scanqrF, TAG_SCAN_QR)
        .commit();
  }
}
