package it.chiarani.qrcoupons.fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import it.chiarani.qrcoupons.R;
import it.chiarani.qrcoupons.adapters.ListCouponAdapter;
import it.chiarani.qrcoupons.databinding.ListCouponFragmentBinding;
import it.chiarani.qrcoupons.db.entity.QrItemEntity;
import it.chiarani.qrcoupons.repository.QrItemRepository;

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

    this.getActivity().getWindow().setStatusBarColor(Color.rgb(239, 121, 66));
    // set linearlayoyt manager
    mLayoutManager = new LinearLayoutManager(rootView.getContext());
    mRecyclerView.setLayoutManager(mLayoutManager);

    List<QrItemEntity> tmp = new ArrayList<>();
    QrItemRepository repo = new QrItemRepository(getActivity().getApplication());
    repo.getAll().observeForever( entries -> {
      if (entries != null) {

        tmp.addAll(entries);

        // specify an adapter (see also next example)
        mAdapter = new ListCouponAdapter(tmp);
        mRecyclerView.setAdapter(mAdapter);
      }
    });





    return rootView;
  }
}
