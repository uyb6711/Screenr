package com.agentwaj.screenr;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SendVerificationActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send_verification);
		
		final EditText etMyNumber = (EditText) findViewById(R.id.etMyNumber);
		etMyNumber.setInputType(InputType.TYPE_CLASS_PHONE);
		etMyNumber.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
		etMyNumber.setText(PhoneNumberUtils.formatNumber(((TelephonyManager)getApplicationContext()
				.getSystemService(Context.TELEPHONY_SERVICE)).getLine1Number()));
		
		Button bGetCode = (Button) findViewById(R.id.bGetCode);
		bGetCode.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String number = etMyNumber.getText().toString();
				try {
					new GetCode().execute(number).get();
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(), "Error!", Toast.LENGTH_SHORT).show();
				}
				Toast.makeText(getApplicationContext(), "Code sent to " + number, 
						Toast.LENGTH_SHORT).show();
				Intent i = new Intent(SendVerificationActivity.this, ConfirmVerificationActivity.class);
				i.putExtra("number", number);
				startActivity(i);
				finish();
			}
		});
	}

}
