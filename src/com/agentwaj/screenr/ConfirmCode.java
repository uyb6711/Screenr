package com.agentwaj.screenr;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class ConfirmCode extends AsyncTask<String, Void, Boolean>  {

	protected Boolean doInBackground(String... params) {
		Boolean result = false;
		try {
			HttpPost post = new HttpPost("http://screenr.herokuapp.com/api/verify");
			List<NameValuePair> postParams = new ArrayList<NameValuePair>();
			String code = params[0].toUpperCase().replaceAll("[^A-Z0-9]", "");
			postParams.add(new BasicNameValuePair("validation_code", code));
			post.setEntity(new UrlEncodedFormEntity(postParams));
			Log.i("zzz", code);
			HttpResponse response = new DefaultHttpClient().execute(post);
			String encoded = convertStreamToString(response.getEntity().getContent());
			String decoded = new JSONObject(encoded).getString("final");
			result = !decoded.equals("error");
			Log.i("zzz", "Result: " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	static String convertStreamToString(java.io.InputStream is) {
	    java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
	    return s.hasNext() ? s.next() : "";
	}
	
}