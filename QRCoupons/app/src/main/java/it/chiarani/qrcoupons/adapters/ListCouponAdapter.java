package it.chiarani.qrcoupons.adapters;

import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import net.glxn.qrgen.android.QRCode;

import java.io.File;
import java.util.List;

import it.chiarani.qrcoupons.R;
import it.chiarani.qrcoupons.db.entity.QrItemEntity;
import it.chiarani.qrcoupons.repository.QrItemRepository;

public class ListCouponAdapter extends RecyclerView.Adapter<ListCouponAdapter.ViewHolder> {

  List<QrItemEntity> _items;
  Application _app;


  public ListCouponAdapter(List<QrItemEntity> items, Application _app) {
    this._items = items;
    this._app = _app;
  }

  public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    TextView txtTitle;
    TextView txtEndDate;
    ImageView imgIcon;

    public ViewHolder(View view) {
      super(view);

      txtTitle = (TextView) view.findViewById(R.id.qr_item_list_title);
      imgIcon = (ImageView) view.findViewById(R.id.qr_item_list_icon);
      txtEndDate = (TextView) view.findViewById(R.id.qr_item_list_end_date);

      view.setOnClickListener(this);
      view.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View v) {
      int pos = this.getLayoutPosition();


      final Dialog dialog = new Dialog(v.getRootView().getContext());
      dialog.setContentView(R.layout.custom_dialog_layout);
      dialog.setTitle("Title...");
      dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

      ImageView test = dialog.findViewById(R.id.custom_dialog_img_qr);
      Bitmap myBitmap = QRCode.from(_items.get(pos).getCode()).bitmap();
      test.setImageBitmap(myBitmap);

      TextView txt_name, txt_description, txt_startDate, txt_endDate;

      txt_name = dialog.findViewById(R.id.custom_dialog_txt_title);
      txt_description = dialog.findViewById(R.id.custom_dialog_txt_description);
      txt_startDate = dialog.findViewById(R.id.custom_dialog_txt_cretedDate);
      txt_endDate = dialog.findViewById(R.id.custom_dialog_txt_expiriedDate);

      txt_name.setText(_items.get(pos).getName());
      txt_description.setText(_items.get(pos).getDescription());
      txt_startDate.setText("Data di scansione: " +_items.get(pos).getCreatedDate());
      txt_endDate.setText("Data di scadenza: "+ _items.get(pos).getExpirationDate());

      ImageView img_delete = dialog.findViewById(R.id.custom_dialog_img_delete);

      img_delete.setOnClickListener(
          view -> {
            QrItemRepository repo = new QrItemRepository(_app);
            repo.deleteByNameDescriptionCode(_items.get(getLayoutPosition()).getName(),
                _items.get(getLayoutPosition()).getDescription(),
                _items.get(getLayoutPosition()).getCode());
            _items.remove(getLayoutPosition());
            notifyItemRemoved(getLayoutPosition());
            Toast.makeText(v.getRootView().getContext(), "Elemento rimosso.", Toast.LENGTH_LONG).show();
            dialog.cancel();
          }
      );

      dialog.show();

    }


    @Override
    public boolean onLongClick(View v) {
      AlertDialog.Builder builder1 = new AlertDialog.Builder(v.getRootView().getContext());
      builder1.setMessage("Eliminare " + _items.get(getLayoutPosition()).getName() + " ?");
      builder1.setCancelable(true);

      builder1.setPositiveButton(
          "Elimina",
          new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
              QrItemRepository repo = new QrItemRepository(_app);
              repo.deleteByNameDescriptionCode(_items.get(getLayoutPosition()).getName(),
                  _items.get(getLayoutPosition()).getDescription(),
                  _items.get(getLayoutPosition()).getCode());
              _items.remove(getLayoutPosition());
              notifyItemRemoved(getLayoutPosition());
              Toast.makeText(v.getRootView().getContext(), "Elemento rimosso.", Toast.LENGTH_LONG).show();
              dialog.cancel();
            }
          });

      builder1.setNegativeButton(
          "annulla",
          new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
              dialog.cancel();
            }
          });

      AlertDialog alert11 = builder1.create();
      alert11.show();

      return true;
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
