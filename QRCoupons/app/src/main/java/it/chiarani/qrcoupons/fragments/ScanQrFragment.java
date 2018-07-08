package it.chiarani.qrcoupons.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import it.chiarani.qrcoupons.R;
import it.chiarani.qrcoupons.db.entity.QrItemEntity;
import it.chiarani.qrcoupons.helpers.Tips;
import it.chiarani.qrcoupons.repository.QrItemRepository;
import it.chiarani.qrcoupons.views.AddQrActivity;

import static android.content.Context.MODE_PRIVATE;

public class ScanQrFragment extends Fragment {

  // --- PRIVATE FIELDS ---
  final static String PREFS_NAME = "AppPref";
  final static String INTENT_QR_DATA = "EXTRA_QR_DATA";
  private TextView txt_qr_code_self;
  private Button      btn_scanqr;
  private Button      btn_valida;
  // --- -------------- ---

  //qr code scanner object
  private IntentIntegrator qrScan;

  public ScanQrFragment() {
  }


  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView =  inflater.inflate(R.layout.qrscan_fragment_layout, container, false);

    CheckPref(rootView);

    txt_qr_code_self = (EditText) rootView.findViewById(R.id.qrscan_fragment_edittext_qrcode);
    btn_scanqr       = rootView.findViewById(R.id.qrscan_fragment_btn_scannerizzaqr);
    btn_valida       = rootView.findViewById(R.id.qrscan_fragment_btn_valida);

    qrScan = IntentIntegrator.forSupportFragment(this);

    btn_scanqr.setOnClickListener(
        view -> qrScan.initiateScan()
    );

    btn_valida.setOnClickListener(
        view -> {
          Toast.makeText(this.getContext(), "Codice Rilevato", Toast.LENGTH_LONG).show();
          Intent intent = new Intent(getActivity(), AddQrActivity.class);
          intent.putExtra(INTENT_QR_DATA, txt_qr_code_self.getText().toString());
          startActivity(intent);
        }
    );


    return rootView;
  }

    private void CheckPref(View rootView) {
      SharedPreferences settings = this.getContext().getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
      if(settings.getBoolean("my_first_time", true)) {
        //the app is being launched for first time, do something

        // first time task
        Tips.MakeTipsForQrScanFragment(rootView, this);

        // record the fact that the app has been started at least once
        settings.edit().putBoolean("my_first_time", false).commit();
      }
    }


  //Getting the scan results
  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
    if (result != null) {

      //if qrcode has nothing in it
      if (result.getContents() == null) {
        //Toast.makeText(this.getContext(),  "Nessun Codice Rilevato", Toast.LENGTH_LONG).show();

        QrItemRepository repo = new QrItemRepository(getActivity().getApplication());

        // TODO: remove this line, is only for testing
       // repo.deleteAll();

      } else {

        //if qr contains data
        try {
          Toast.makeText(this.getContext(), "Codice Rilevato", Toast.LENGTH_LONG).show();
          Intent intent = new Intent(getActivity(), AddQrActivity.class);
          intent.putExtra(INTENT_QR_DATA, result.getContents());
          startActivity(intent);

        } catch (Exception e) {
          e.printStackTrace();
          Toast.makeText(this.getContext(), "Ho riscontrato un errore. Riprova!", Toast.LENGTH_LONG).show();
        }
      }
    }
    else {
      super.onActivityResult(requestCode, resultCode, data);
    }
  }
}
