package com.agentwaj.screenr;

import java.util.ArrayList;

import org.json.JSONArray;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class NumberListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_number_list);
		
		TextView tvTemp = (TextView) findViewById(R.id.tvAddOption);
		EditText etAddOption = (EditText) findViewById(R.id.etAddOption);
		final ListView lvAvailableNumbers = (ListView) findViewById(R.id.lvAvailableNumbers);
		
		int option = getIntent().getIntExtra("option", 0);
		switch (option) {
		case 0: // Area Code
			tvTemp.setText("Enter area code:");
			etAddOption.setHint("e.g. 301-XXX-XXXX");
			etAddOption.setInputType(InputType.TYPE_CLASS_PHONE);
			etAddOption.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
			etAddOption.setFilters(new InputFilter[] {new InputFilter.LengthFilter(3)});
			etAddOption.addTextChangedListener(new TextWatcher() {
				public void onTextChanged(CharSequence s, int start, int before, int count) {}
				public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
				public void afterTextChanged(Editable s) {
					if (s.length() == 3) {
						try {
							JSONArray numbers = new GetNumbers().execute("area_code", s.toString()).get().getJSONArray(0);
							ArrayList<String> numList = new ArrayList<String>();
							for (int i = 0; i < numbers.length(); i++) {
								String num = numbers.getJSONObject(i).getString("number");
								String state = numbers.getJSONObject(i).getString("region");
								numList.add(num + "\t" + state);
							}
							if (numList.size() == 0)
								numList.add("No results!");
							lvAvailableNumbers.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, numList));
						} catch (Exception e) {
							Toast.makeText(getApplicationContext(), "Error! " + e.getClass(), Toast.LENGTH_SHORT).show();
						}
					}
				}
			});
			
			break;
		case 1: // First Five Digits
			tvTemp.setText("Enter first five digits:");
			etAddOption.setHint("e.g. 301-40X-XXXX");
			etAddOption.setInputType(InputType.TYPE_CLASS_PHONE);
			etAddOption.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
			etAddOption.setFilters(new InputFilter[] {new InputFilter.LengthFilter(6)});
			etAddOption.addTextChangedListener(new TextWatcher() {
				public void onTextChanged(CharSequence s, int start, int before, int count) {}
				public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
				public void afterTextChanged(Editable s) {
					if (s.length() == 6) {
						try {
							JSONArray numbers = new GetNumbers().execute("initial", s.toString()).get().getJSONArray(0);
							ArrayList<String> numList = new ArrayList<String>();
							for (int i = 0; i < numbers.length(); i++) {
								String num = numbers.getJSONObject(i).getString("number");
								String state = numbers.getJSONObject(i).getString("region");
								numList.add(num + "\t" + state);
							}
							if (numList.size() == 0)
								numList.add("No results!");
							lvAvailableNumbers.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, numList));
						} catch (Exception e) {
							Toast.makeText(getApplicationContext(), "Error! " + e.getClass(), Toast.LENGTH_SHORT).show();
						}
					}
				}
			});
			break;
		case 2: // State
			tvTemp.setText("Enter state code:");
			etAddOption.setHint("e.g. MD");
			etAddOption.setFilters(new InputFilter[] {new InputFilter.LengthFilter(2)});
			etAddOption.addTextChangedListener(new TextWatcher() {
				public void onTextChanged(CharSequence s, int start, int before, int count) {}
				public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
				public void afterTextChanged(Editable s) {
					if (s.length() == 2) {
						try {
							JSONArray numbers = new GetNumbers().execute("state", s.toString()).get().getJSONArray(0);
							ArrayList<String> numList = new ArrayList<String>();
							for (int i = 0; i < numbers.length(); i++) {
								String num = numbers.getJSONObject(i).getString("number");
								String state = numbers.getJSONObject(i).getString("region");
								numList.add(num + "\t" + state);
							}
							if (numList.size() == 0)
								numList.add("No results!");
							lvAvailableNumbers.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, numList));
						} catch (Exception e) {
							Toast.makeText(getApplicationContext(), "Error! " + e.getClass(), Toast.LENGTH_SHORT).show();
						}
					}
				}
			});
			break;
		}
	}

}
