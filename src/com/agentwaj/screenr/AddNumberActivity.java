package com.agentwaj.screenr;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class AddNumberActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_number);
		
//		Button bCancel = (Button) findViewById(R.id.bCancel);
//		bCancel.setOnClickListener(new OnClickListener() {
//			public void onClick(View v) {
//				finish();
//			}
//		});
//		
//		Button bAdd = (Button) findViewById(R.id.bAdd);
//		bAdd.setOnClickListener(new OnClickListener() {
//			public void onClick(View v) {
//				Toast.makeText(getApplicationContext(), "Test", Toast.LENGTH_SHORT).show();
//			}
//		});
		
		ListView lvAddOptions = (ListView) findViewById(R.id.lvAddOptions);
		
		ArrayList<String> addOptions = new ArrayList<String>();
		addOptions.add("Area Code");
		addOptions.add("First Five Digits");
		addOptions.add("State Code");
		
		lvAddOptions.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, addOptions));
		
		lvAddOptions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//				Toast.makeText(getApplicationContext(), "Pos: " + position, Toast.LENGTH_SHORT).show();
				Intent i = new Intent(AddNumberActivity.this, NumberListActivity.class);
				i.putExtra("option", position);
				startActivity(i);
			}
		});
	}
	
}
