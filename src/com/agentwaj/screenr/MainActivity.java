package com.agentwaj.screenr;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		SharedPreferences prefs = getSharedPreferences("save", MODE_PRIVATE);
		if (prefs.getBoolean("verified", false)) {
			startActivity(new Intent(MainActivity.this, ManageActivity.class));
			finish();
			// maybe instead modify "Verify" button to say manage instead
		}
		
		Button bVerify = (Button) findViewById(R.id.bVerify);
		bVerify.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, SendVerificationActivity.class));
			}
		});
		
	}

}
