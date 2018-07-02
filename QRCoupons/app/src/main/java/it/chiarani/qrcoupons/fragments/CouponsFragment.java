package it.chiarani.qrcoupons.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import it.chiarani.qrcoupons.R;

public class CouponsFragment extends Fragment {

  private static final String ARG_PARAM1 = "param1";
  private TextView txt1;
  private String mParam1;

  public CouponsFragment() {
  }


  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      mParam1 = getArguments().getString(ARG_PARAM1);
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView =  inflater.inflate(R.layout.coupons_fragment_layout, container, false);
    txt1 = (TextView) rootView.findViewById(R.id.frag_coupons_txt1);
    String title = getArguments().getString(ARG_PARAM1, "");
    txt1.setText(title);

    return rootView;
  }
}
