package it.chiarani.qrcoupons.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.util.List;

import it.chiarani.qrcoupons.R;
import it.chiarani.qrcoupons.db.entity.QrItemEntity;

public class ListCouponAdapter extends RecyclerView.Adapter<ListCouponAdapter.ViewHolder> {

  List<QrItemEntity> _items;


  public ListCouponAdapter(List<QrItemEntity> items) {
    this._items = items;
  }

  public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView txtTitle;
    TextView txtEndDate;
    ImageView imgIcon;

    public ViewHolder(View view) {
      super(view);

      txtTitle   = (TextView)  view.findViewById(R.id.qr_item_list_title);
      imgIcon    = (ImageView) view.findViewById(R.id.qr_item_list_icon);
      txtEndDate = (TextView)  view.findViewById(R.id.qr_item_list_end_date);
      view.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
      int pos = this.getLayoutPosition();
      Toast.makeText(v.getRootView().getContext(), "Posizione: " + pos, Toast.LENGTH_LONG).show();
    }

  }



  @Override
  public ListCouponAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    Boolean attachViewImmediatelyToParent = false;
    View singleItemLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.qr_item_list_layout,parent,attachViewImmediatelyToParent);
    ViewHolder myViewHolder = new ViewHolder(singleItemLayout);

    singleItemLayout.setClickable(true);

    return myViewHolder;
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    ColorGenerator generator = ColorGenerator.MATERIAL;
    String name = _items.get(position).getName();
    String description =  _items.get(position).getDescription();

    if(description.length() >= 21) {
      description = description.substring(0,21) + "...";
    }


    holder.txtTitle.setText(name + "\n" + description);

    String first_letter = _items.get(position).getName().substring(0,1).toUpperCase();
    TextDrawable drawable = TextDrawable.builder()
        .buildRound(first_letter, generator.getColor(first_letter));

    holder.imgIcon.setImageDrawable(drawable);

    holder.txtEndDate.setText("Scade il:\n" + _items.get(position).getExpirationDate());
  }


  @Override
  public int getItemCount() {
    //Return the number of items in your list
    return _items.size();
  }
}
