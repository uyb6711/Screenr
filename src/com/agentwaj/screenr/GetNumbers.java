package com.agentwaj.screenr;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;

import android.os.AsyncTask;

public class GetNumbers extends AsyncTask<String, Void, JSONArray>  {

	protected JSONArray doInBackground(String... params) {
		/*
		 * area_code - area_code
		 * initial - initial 
		 * state - state
		 * 
		 */
		JSONArray result = null;
		try {
			String url = "http://screenr.herokuapp.com/api/" + params[0];
			HttpPost post = new HttpPost(url);
			List<NameValuePair> postParams = new ArrayList<NameValuePair>();
			String value = params[1].toUpperCase().replaceAll("[^A-Z0-9]", "");
			postParams.add(new BasicNameValuePair(params[0], value));
			post.setEntity(new UrlEncodedFormEntity(postParams));
			HttpResponse response = new DefaultHttpClient().execute(post);
			String encoded = convertStreamToString(response.getEntity().getContent());
			result = new JSONArray(encoded);
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