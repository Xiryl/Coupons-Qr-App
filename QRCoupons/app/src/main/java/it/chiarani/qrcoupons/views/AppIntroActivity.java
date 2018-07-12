package it.chiarani.qrcoupons.views;

import android.Manifest;
import android.graphics.Color;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntroFragment;

import it.chiarani.qrcoupons.R;

public class AppIntroActivity extends AppIntro2 {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // Add your slide's fragments here
    // AppIntro will automatically generate the dots indicator and buttons.
  /*  addSlide(first_fragment);
    addSlide(second_fragment);
    addSlide(third_fragment);
    addSlide(fourth_fragment);*/

    // Instead of fragments, you can also use our default slide
    // Just set a title, description, background and image. AppIntro will do the rest
    addSlide(AppIntroFragment.newInstance("Benvenuto in COUPONS-QR", "Utilizzami per scansionare e salvare i tuoi codici QR!", R.drawable.ic_scan_qr, Color.parseColor("#222222")));
    addSlide(AppIntroFragment.newInstance("Approvazione dei Permessi", "I pernessi di scrittura mi serviranno per poterti salvare in modo permanente in loco i tuoi codici QR scansionati!", R.drawable.ic_storage, Color.parseColor("#51B9D1")));
    addSlide(AppIntroFragment.newInstance("Siamo pronti!", "...Iniziamo!", R.drawable.checked, Color.parseColor("#66AC5B")));

    // OPTIONAL METHODS

    // SHOW or HIDE the statusbar
    showStatusBar(true);

    // Edit the color of the nav bar on Lollipop+ devices

    // Turn vibration on and set intensity
    // NOTE: you will need to ask VIBRATE permission in Manifest if you haven't already
    setVibrate(true);
    setVibrateIntensity(30);

    // Animations -- use only one of the below. Using both could cause errors.
    setFadeAnimation(); // OR
  /*  setZoomAnimation(); // OR
    setFlowAnimation(); // OR
    setSlideOverAnimation(); // OR
    setDepthAnimation(); // OR
    setCustomTransformer(yourCustomTransformer);*/

    // Permissions -- takes a permission and slide number
    askForPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
  }

  @Override
  public void onNextPressed() {
    // Do something when users tap on Next button.
  }

  @Override
  public void onDonePressed() {
    // Do something when users tap on Done button.
    finish();
  }

  @Override
  public void onSlideChanged() {
    // Do something when slide is changed
  }
}