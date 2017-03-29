package com.agentwaj.screenr;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;
import android.util.Log;

public class GetCode extends AsyncTask<String, Void, HttpResponse>  {

	protected HttpResponse doInBackground(String... params) {
		HttpResponse response = null;
		try {
			HttpPost post = new HttpPost("http://screenr.herokuapp.com/api/signup");
			List<NameValuePair> postParams = new ArrayList<NameValuePair>();
			String number = params[0].replaceAll("[^0-9]", "");
			postParams.add(new BasicNameValuePair("signup_number", number));
			post.setEntity(new UrlEncodedFormEntity(postParams));
			Log.i("zzz", number);
			response = new DefaultHttpClient().execute(post);
			Log.i("zzz", convertStreamToString(response.getEntity().getContent()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	static String convertStreamToString(java.io.InputStream is) {
	    java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
	    return s.hasNext() ? s.next() : "";
	}
	
}