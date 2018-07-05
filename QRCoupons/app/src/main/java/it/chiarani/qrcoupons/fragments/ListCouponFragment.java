package it.chiarani.qrcoupons.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import it.chiarani.qrcoupons.R;
import it.chiarani.qrcoupons.databinding.ListCouponFragmentBinding;

public class ListCouponFragment extends Fragment {

  private ListCouponFragmentBinding binding;

  public ListCouponFragment() {
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.list_coupon_fragment, container, false);
  }
}
