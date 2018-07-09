package it.chiarani.qrcoupons.fragments;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.widget.Toast;

import it.chiarani.qrcoupons.R;
import it.chiarani.qrcoupons.repository.QrItemRepository;

public class SettingsFragment extends PreferenceFragmentCompat
    implements Preference.OnPreferenceClickListener {

  private Preference delete_all_qr_from_db;
  private Preference feedback;
  private Preference app_version;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    delete_all_qr_from_db = findPreference("delete_all_qr");
    feedback = findPreference("feedback");
    app_version = findPreference("app_version");
  }

  @Override public void onCreatePreferences(Bundle bundle, String s) {
    addPreferencesFromResource(R.xml.preferences);
  }

  @Override public void onResume() {
    super.onResume();

    delete_all_qr_from_db.setOnPreferenceClickListener(this);
    feedback.setOnPreferenceClickListener(this);
    try {
      app_version.setSummary(this.getContext().getPackageManager().getPackageInfo(this.getContext().getPackageName(), 0).versionName);
    } catch(PackageManager.NameNotFoundException ex) { }
  }

  @Override public boolean onPreferenceClick(Preference preference) {
    switch (preference.getKey()) {
      case "delete_all_qr":
        QrItemRepository repo = new QrItemRepository(this.getActivity().getApplication());
        repo.deleteAll();
        Toast.makeText(this.getContext(), "Tutta la cache è stata pulita.", Toast.LENGTH_LONG).show();
        break;
      case "feedback":
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:fabio@chiarani.it"));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "[FEEDBACK] Qr code app");
        startActivity(emailIntent);
        break;
    }

    return true;
  }
}