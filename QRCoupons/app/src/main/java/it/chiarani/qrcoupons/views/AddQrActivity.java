package it.chiarani.qrcoupons.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import it.chiarani.qrcoupons.R;
import it.chiarani.qrcoupons.databinding.AddQrLayoutBinding;
import it.chiarani.qrcoupons.db.entity.QrItemEntity;
import it.chiarani.qrcoupons.repository.QrItemRepository;

import static java.sql.Types.NULL;

public class AddQrActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

  // ---- PRIVATE FIELDS ----
  final static String INTENT_QR_DATA = "EXTRA_QR_DATA";

  private SimpleDateFormat today_date_format = new SimpleDateFormat("dd/MM/yyyy");
  private AddQrLayoutBinding binding;
  // ---- -------------- ----


  @SuppressLint("ClickableViewAccessibility")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // set view
    binding = DataBindingUtil.setContentView(this, R.layout.add_qr_layout);

    // set UI text
    String qr_data    = getIntent().getStringExtra(INTENT_QR_DATA);
    String today_date = today_date_format.format(new Date());

    binding.addQrLayoutTxtQrValue.setText("Codice: " + qr_data);
    binding.addQrLayoutTxtQrDate.setText("Data Scansione: " + today_date);

    // datetime picker on edittext
    binding.addQrLayoutEdittextEndDate.setOnClickListener(
        view -> {

           Calendar now = Calendar.getInstance();
          DatePickerDialog dpd = DatePickerDialog.newInstance(
              AddQrActivity.this,
              now.get(Calendar.YEAR),
              now.get(Calendar.MONTH),
              now.get(Calendar.DAY_OF_MONTH)
          );
          dpd.show(getFragmentManager(), "Datepickerdialog");
        });

    setBackClick();
    setSaveClick();
  }

  @Override
  public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
    String date = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
    binding.addQrLayoutEdittextEndDate.setText(date);
  }

  private void setBackClick() {
    binding.addQrLayoutBtnBack.setOnClickListener(
        view -> LaunchMainActivity(view.getContext())
    );
  }

  private void setSaveClick() {
    binding.addQrLayoutBtnSalva.setOnClickListener(
        view -> {

          // controllo che la data sia corretta
          try {
            DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
            // if this fails the date is not correct format
            Date date = sourceFormat.parse(binding.addQrLayoutEdittextEndDate.getText().toString());

            QrItemRepository repo = new QrItemRepository(getApplication());

            // create entry
            QrItemEntity qrEntryTemplate = new QrItemEntity(
                NULL,
                binding.addQrLayoutEdittextName.getText().toString(),
                binding.addQrLayoutEdittextDescription.getText().toString(),
                binding.addQrLayoutTxtQrDate.getText().toString(),
                binding.addQrLayoutEdittextEndDate.getText().toString(),
                binding.addQrLayoutTxtQrValue.getText().toString(),
                "");

            // insert entry on db
            repo.insertIt(qrEntryTemplate);

            // UI
            Toast.makeText(view.getContext(), "Codice Aggiunto.", Toast.LENGTH_LONG).show();
            LaunchMainActivity(this.getApplicationContext());
          }
          catch (java.text.ParseException ex) {
            Toast.makeText(view.getContext(), "Data non valida, riprova.", Toast.LENGTH_LONG).show();
            return;
          }
        }
    );
  }

  @Override
  public void onBackPressed() {
    LaunchMainActivity(this.getApplicationContext());
  }

  private void LaunchMainActivity(Context context) {
    Intent intent = new Intent(context, MainActivity.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);
  }
}
