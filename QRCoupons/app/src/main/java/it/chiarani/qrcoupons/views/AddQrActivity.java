package it.chiarani.qrcoupons.views;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import it.chiarani.qrcoupons.databinding.AddQrLayoutBinding;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

import it.chiarani.qrcoupons.R;

public class AddQrActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {


  private AddQrLayoutBinding binding;


  @SuppressLint("ClickableViewAccessibility")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // set view
    binding = DataBindingUtil.setContentView(this, R.layout.add_qr_layout);

    binding.addQrLayoutEdittextEndDate.setOnTouchListener(new View.OnTouchListener() {
      @Override
      public boolean onTouch(View v, MotionEvent event) {
        final int DRAWABLE_RIGHT = 2;

        if(event.getAction() == MotionEvent.ACTION_UP) {
          if(event.getRawX() >= (binding.addQrLayoutEdittextEndDate.getRight() - binding.addQrLayoutEdittextEndDate.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
            // your action here
            Calendar now = Calendar.getInstance();
            DatePickerDialog dpd = DatePickerDialog.newInstance(
                AddQrActivity.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
            );
            dpd.show(getFragmentManager(), "Datepickerdialog");
            return true;
          }
        }
        return false;
      }
    });
  }

  @Override
  public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
    String date = "You picked the following date: "+dayOfMonth+"/"+(monthOfYear+1)+"/"+year;
    //dateTextView.setText(date);
  }
}
