package it.chiarani.qrcoupons.views;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import it.chiarani.qrcoupons.R;
import it.chiarani.qrcoupons.databinding.MainActivityLayoutBinding;
import it.chiarani.qrcoupons.fragments.CouponsFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

  private MainActivityLayoutBinding binding;
  private BottomNavigationView      bottomNavigationView;
  private CouponsFragment           couponsF = new CouponsFragment();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // set view
    binding = DataBindingUtil.setContentView(this, R.layout.main_activity_layout);

    // set bottom navbar
    bottomNavigationView = (BottomNavigationView) findViewById(R.id.main_activity_bottom_nav);

    getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.main_activity_fragment_holder, couponsF, "tag1")
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
