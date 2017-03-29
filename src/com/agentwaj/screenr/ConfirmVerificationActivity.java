package com.agentwaj.screenr;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ConfirmVerificationActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_confirm_verification);

		Button bResend = (Button) findViewById(R.id.bResend);
		Button bSubmitCode = (Button) findViewById(R.id.bSubmitCode);
		final EditText etEnterCode = (EditText) findViewById(R.id.etEnterCode);

		bResend.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(ConfirmVerificationActivity.this);
				DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						String number = getIntent().getStringExtra("number");
						new GetCode().execute(number);
						Toast.makeText(getApplicationContext(), "Code sent to " + number, 
								Toast.LENGTH_SHORT).show();
					}
				};
				builder.setTitle("Are you sure?").setPositiveButton("Yes", listener)
				.setNegativeButton("No", null).create().show();
			}
		});

		bSubmitCode.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(ConfirmVerificationActivity.this);
				DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						startActivity(new Intent(ConfirmVerificationActivity.this, ManageActivity.class));
						finish();
					}
				};
				String code = etEnterCode.getText().toString();
				Log.i("zzz", "code: " + code);
				try {
					if (new ConfirmCode().execute(code).get()) {
						SharedPreferences.Editor editor = getSharedPreferences("save", MODE_PRIVATE).edit();
						editor.putBoolean("verified", true);
						editor.commit();
						builder.setTitle("Verification successful!")
								.setPositiveButton("OK", listener).create().show();
					} else {
						builder.setTitle("Verification failed!")
								.setMessage("Please double check the code and try again.")
								.setPositiveButton("OK", null).create().show();
					}
				} catch (Exception e) {
					builder.setTitle("Error encountered!")
					.setPositiveButton("OK", null).create().show();
				}
			}
		});
	}
}
