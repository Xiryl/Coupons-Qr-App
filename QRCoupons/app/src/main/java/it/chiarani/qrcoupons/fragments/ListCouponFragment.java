package it.chiarani.qrcoupons.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import it.chiarani.qrcoupons.R;
import it.chiarani.qrcoupons.databinding.ListCouponFragmentBinding;

public class ListCouponFragment extends Fragment {

  // --- PRIVATE FIELDS ---
  private ListCouponFragmentBinding binding;
  private RecyclerView mRecyclerView;
  private RecyclerView.Adapter mAdapter;
  private RecyclerView.LayoutManager mLayoutManager;
  // --- -------------- ---


  public ListCouponFragment() {
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.list_coupon_fragment, container, false);


    mRecyclerView = (RecyclerView) rootView.findViewById(R.id.list_coupon_fragment_recycler_view);
    mRecyclerView.setHasFixedSize(true);

    // set linearlayoyt manager
    mLayoutManager = new LinearLayoutManager(rootView.getContext());
    mRecyclerView.setLayoutManager(mLayoutManager);


    // specify an adapter (see also next example)
    //mAdapter = new MyAdapter(myDataset);
    //mRecyclerView.setAdapter(mAdapter);

    return rootView;
  }
}
