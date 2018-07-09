package it.chiarani.qrcoupons.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import it.chiarani.qrcoupons.R;
import it.chiarani.qrcoupons.databinding.ListCouponFragmentBinding;

public class AccountFragment extends Fragment {

  // --- PRIVATE FIELDS ---
  private ListCouponFragmentBinding binding;
  private RecyclerView mRecyclerView;
  private RecyclerView.Adapter mAdapter;
  private RecyclerView.LayoutManager mLayoutManager;
  // --- -------------- ---


  public AccountFragment() {
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.account_fragment, container, false);



    getChildFragmentManager().beginTransaction().replace(R.id.activity_settings_frame, new SettingsFragment())
        .commit();

    return rootView;
  }
}
