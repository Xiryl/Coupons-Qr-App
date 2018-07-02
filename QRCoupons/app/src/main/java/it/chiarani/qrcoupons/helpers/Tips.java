package it.chiarani.qrcoupons.helpers;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.view.View;

import it.chiarani.qrcoupons.R;
import it.chiarani.qrcoupons.fragments.ScanQrFragment;
import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt;

public class Tips {
  public Tips() {

  }

  public static void MakeTipsForQrScanFragment(View rootView, ScanQrFragment fragment) {

    final MaterialTapTargetPrompt secondTips = new MaterialTapTargetPrompt.Builder(fragment)
        .setTarget(rootView.findViewById(R.id.qrscan_fragment_btn_valida))
        .setPrimaryText("Non riesci a scannerizzarlo?")
        .setSecondaryText("Inseriscilo manualmente da qua!").create();

    final MaterialTapTargetPrompt firstTips = new MaterialTapTargetPrompt.Builder(fragment)
        .setTarget(rootView.findViewById(R.id.qrscan_fragment_btn_scannerizzaqr))
        .setPrimaryText("Scannerizza il QR")
        .setSecondaryText("Il tuo copupon verrà aggiunto automaticamente!")
        .setPromptStateChangeListener(new MaterialTapTargetPrompt.PromptStateChangeListener()
        {
          @Override
          public void onPromptStateChanged(MaterialTapTargetPrompt prompt, int state)
          {
            if (
                state == MaterialTapTargetPrompt.STATE_DISMISSING        ||
                state == MaterialTapTargetPrompt.STATE_NON_FOCAL_PRESSED ||
                state == MaterialTapTargetPrompt.STATE_FOCAL_PRESSED
                ) {
              secondTips.show();
            }

          }
        }).show();
  }
}
