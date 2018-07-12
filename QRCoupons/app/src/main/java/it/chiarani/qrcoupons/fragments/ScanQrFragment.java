package it.chiarani.qrcoupons.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.google.zxing.qrcode.QRCodeReader;

import it.chiarani.qrcoupons.R;
import it.chiarani.qrcoupons.db.entity.QrItemEntity;
import it.chiarani.qrcoupons.helpers.Tips;
import it.chiarani.qrcoupons.repository.QrItemRepository;
import it.chiarani.qrcoupons.views.AddQrActivity;

import static android.app.Activity.RESULT_OK;
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
          Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

          startActivityForResult(i, 9999);
          /*
          if(txt_qr_code_self.getText().toString().isEmpty() ||
              txt_qr_code_self.getText().toString() == null ||
              txt_qr_code_self.getText().toString() == "") {
            Toast.makeText(this.getContext(), "Inserisci un codice prima!", Toast.LENGTH_LONG).show();
            return;
          }
          Toast.makeText(this.getContext(), "Codice Rilevato", Toast.LENGTH_LONG).show();
          Intent intent = new Intent(getActivity(), AddQrActivity.class);
          intent.putExtra(INTENT_QR_DATA, txt_qr_code_self.getText().toString());
          startActivity(intent);*/
        }
    );

    if (ContextCompat.checkSelfPermission(this.getActivity(),
        Manifest.permission.WRITE_EXTERNAL_STORAGE)
        != PackageManager.PERMISSION_GRANTED) {

      // Permission is not granted
      // Should we show an explanation?
      if (ActivityCompat.shouldShowRequestPermissionRationale(this.getActivity(),
          Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
        // Show an explanation to the user *asynchronously* -- don't block
        // this thread waiting for the user's response! After the user
        // sees the explanation, try again to request the permission.
      } else {
        // No explanation needed; request the permission
        ActivityCompat.requestPermissions(this.getActivity(),
            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 55);

        // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
        // app-defined int constant. The callback method gets the
        // result of the request.
      }
    } else {
      // Permission has already been granted
    }


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
    if (requestCode == 9999 && resultCode == RESULT_OK && null != data) {
      Uri uri =data.getData();
      String[]projection={MediaStore.Images.Media.DATA};

      Cursor cursor= getActivity().getApplicationContext().getContentResolver().query(uri, projection, null, null, null);
      cursor.moveToFirst();
      int columnIndex=cursor.getColumnIndex(projection[0]);
      String filePath=cursor.getString(columnIndex);
      cursor.close();

      String x = decodeQRImage(filePath);
      Toast.makeText(this.getContext(),  x, Toast.LENGTH_LONG).show();
    }


    if (result != null) {

      //if qrcode has nothing in it
      if (result.getContents() == null) {
        Toast.makeText(this.getContext(),  "Nessun Codice Rilevato", Toast.LENGTH_LONG).show();

        QrItemRepository repo = new QrItemRepository(getActivity().getApplication());

        // TODO: remove this line, is only for testing
        //repo.deleteAll();

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


  public static String decodeQRImage(String path) {
    Bitmap bMap = BitmapFactory.decodeFile(path);
    String decoded = null;

    int[] intArray = new int[bMap.getWidth() * bMap.getHeight()];
    bMap.getPixels(intArray, 0, bMap.getWidth(), 0, 0, bMap.getWidth(),
        bMap.getHeight());
    LuminanceSource source = new RGBLuminanceSource(bMap.getWidth(),
        bMap.getHeight(), intArray);
    BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

    Reader reader = new QRCodeReader();
    try {
      Result result = reader.decode(bitmap);
      decoded = result.getText();
    } catch (NotFoundException e) {
      e.printStackTrace();
    } catch (ChecksumException e) {
      e.printStackTrace();
    } catch (FormatException e) {
      e.printStackTrace();
    }
    return decoded;
  }
}
