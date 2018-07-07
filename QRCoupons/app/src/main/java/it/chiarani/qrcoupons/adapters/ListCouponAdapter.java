package it.chiarani.qrcoupons.adapters;

import android.arch.lifecycle.LiveData;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;

import org.w3c.dom.Text;

import java.util.List;

import it.chiarani.qrcoupons.R;
import it.chiarani.qrcoupons.db.entity.QrItemEntity;

public class ListCouponAdapter extends RecyclerView.Adapter<ListCouponAdapter.ViewHolder> {

  List<QrItemEntity> _items;

  public ListCouponAdapter(List<QrItemEntity> items) {
    this._items = items;

  }

  public static class ViewHolder extends RecyclerView.ViewHolder {
    TextView txtTest;
    ImageView imgIcon;
    public ViewHolder(View view) {
      super(view);

      txtTest = (TextView) view.findViewById(R.id.tv_list_item_text);
      imgIcon = (ImageView) view.findViewById(R.id.image_view);

    }
  }
  @Override
  public ListCouponAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    Boolean attachViewImmediatelyToParent = false;
    View singleItemLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.qr_item_list_layout,parent,attachViewImmediatelyToParent);
    ViewHolder myViewHolder = new ViewHolder(singleItemLayout);
    return myViewHolder;
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    holder.txtTest.setText(_items.get(position).getName());
    TextDrawable drawable = TextDrawable.builder()
        .buildRect(_items.get(position).getName().toString().substring(0,1), Color.RED);
    holder.imgIcon.setImageDrawable(drawable);
  }


  @Override
  public int getItemCount() {
    //Return the number of items in your list
    return _items.size();
  }
}
