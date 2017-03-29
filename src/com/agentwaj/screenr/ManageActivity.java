package com.agentwaj.screenr;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ManageActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manage);
		
		Button bHelp = (Button) findViewById(R.id.bHelp);
		bHelp.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(ManageActivity.this);
				builder.setTitle("Help")
						.setMessage("It's self-explanatory, amateur -__-")
						.setPositiveButton("OK", null).create().show();
			}
		});
		
		Button bAdd = (Button) findViewById(R.id.bAdd);
		bAdd.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(ManageActivity.this, AddNumberActivity.class));
			}
		});
		
		ListView lvNumbers = (ListView) findViewById(R.id.lvNumbers);
		
		final ArrayList<String> list = new ArrayList<String>();
//		list.add("");
		
		MyAdapter adapter = new MyAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, list);
		
		lvNumbers.setAdapter(adapter);
	}
	
	private class MyAdapter extends ArrayAdapter<String> {

		public MyAdapter(Context context, int textViewResourceId,
				List<String> objects) {
			super(context, textViewResourceId, objects);
		}

		
	}
}
