package it.chiarani.qrcoupons.fragments;

import android.app.Activity;
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

import org.json.JSONException;
import org.json.JSONObject;

import it.chiarani.qrcoupons.R;
import it.chiarani.qrcoupons.helpers.Tips;
import it.chiarani.qrcoupons.views.AddQrActivity;
import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt;
import uk.co.samuelwall.materialtaptargetprompt.extras.backgrounds.RectanglePromptBackground;

import static android.content.Context.MODE_PRIVATE;

public class ScanQrFragment extends Fragment {

  final String PREFS_NAME = "AppPref";
  private EditText txt1;
  private Button btn_scanqr;
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

    txt1 = (EditText) rootView.findViewById(R.id.qrscan_fragment_edittext_qrcode);
    btn_scanqr = (Button) rootView.findViewById(R.id.qrscan_fragment_btn_scannerizzaqr);

    qrScan = IntentIntegrator.forSupportFragment(this);

    btn_scanqr.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            qrScan.initiateScan();
        }
    });


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
    /*if (result != null) {
      //if qrcode has nothing in it
      if (result.getContents() == null) {
        Toast.makeText(this.getContext(), "Result Not Found", Toast.LENGTH_LONG).show();
      } else {
        //if qr contains data
        try {
          //converting the data to json
          //JSONObject obj = new JSONObject(result.getContents());
          //setting values to textviews
          Toast.makeText(this.getContext(), result.getContents(), Toast.LENGTH_LONG).show();

        } catch (Exception e) {
          e.printStackTrace();
          //if control comes here
          //that means the encoded format not matches
          //in this case you can display whatever data is available on the qrcode
          //to a toast
          Toast.makeText(this.getContext(), result.getContents(), Toast.LENGTH_LONG).show();
        }
      }

    } else {
      super.onActivityResult(requestCode, resultCode, data);
    }*/

    Intent intent = new Intent(getActivity(), AddQrActivity.class);
    startActivity(intent);
  }
}
