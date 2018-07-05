package it.chiarani.qrcoupons.adapters;

import android.arch.lifecycle.LiveData;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import it.chiarani.qrcoupons.db.entity.QrItemEntity;

public class ListCouponAdapter extends RecyclerView.Adapter<ListCouponAdapter.ViewHolder> {

  List<QrItemEntity> _items;

  public ListCouponAdapter(List<QrItemEntity> items) {
    this._items = items;

  }

  public static class ViewHolder extends RecyclerView.ViewHolder {
    public ViewHolder(View view) {
      super(view);
    }
  }
  @Override
  public ListCouponAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    //Define a layout file for individual list item
    return null;
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    //Set data to the individual list item
  }


  @Override
  public int getItemCount() {
    //Return the number of items in your list
    return _items.size();
  }
}
