package it.chiarani.qrcoupons.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import it.chiarani.qrcoupons.R;

public class ScanQrFragment extends Fragment {


  public ScanQrFragment() {
  }


  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView =  inflater.inflate(R.layout.qrscan_fragment_layout, container, false);

    return rootView;
  }
}
