package com.knuchel.start.android;

//TUTO : http://www.ace-art.fr/wordpress/2010/10/13/tutoriel-android-partie-11-les-tabhost/

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.knuchel.start.android.utils.Strings;

public class TabHostActivity extends TabActivity {
	private Context c;
	private Button retBtn;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tabhost);
		c = getApplicationContext();
		setUp();
		onCLickValidate();
		createAllTabs();
	}

	protected void setUp() {
		retBtn = (Button) findViewById(R.id.hostTabRetBtn);
	}

	private void onCLickValidate() {
		retBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				setResult(RESULT_OK, intent);
				finish();
			}
		});
	}

	private void createAllTabs() {
		TabHost tabHost = getTabHost(); // Conteneur des onglets. sert de lien
										// entre l'IHM et le code.
		TabSpec tabSpec; // spécifie les informations de l'onglet

		// creation d'un premier onglet
		Intent intent = new Intent(this, TabFirstActivity.class);
		intent.putExtra("valeur", Strings.get(c, R.string.THA_first_value));
		tabSpec = tabHost.newTabSpec("tabcoucou")
				.setIndicator(Strings.get(c, R.string.THA_first_tab))
				.setContent(intent);
		tabHost.addTab(tabSpec);

		// creation d'un second onglet
		Intent intent2 = new Intent(this, TabFirstActivity.class);
		intent2.putExtra("valeur", Strings.get(c, R.string.THA_second_value));
		tabSpec = tabHost.newTabSpec("tabsecond")
				.setIndicator("", getResources().getDrawable(R.drawable.icon))
				.setContent(intent2);
		tabHost.addTab(tabSpec);

		// creation d'un second onglet
		Intent intent3 = new Intent(this, TabSecondActivity.class);
		intent3.putExtra("valeur", Strings.get(c, R.string.THA_third_value));
		tabSpec = tabHost
				.newTabSpec("othertab")
				.setIndicator(Strings.get(c, R.string.THA_third_tab),
						getResources().getDrawable(R.drawable.settings))
				.setContent(intent3);
		tabHost.addTab(tabSpec);
	}
}
