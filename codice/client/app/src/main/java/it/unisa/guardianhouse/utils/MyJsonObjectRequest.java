package it.unisa.guardianhouse.utils;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * JsonObjectRequest personalizzata per le richieste POST con parametri
 * 
 */
public class MyJsonObjectRequest extends Request<JSONObject> {

	private Response.Listener<JSONObject> listener;
	private Map<String, String> params;

	public MyJsonObjectRequest(String url, Map<String, String> params,
			Response.Listener<JSONObject> responseListener,
			Response.ErrorListener errorListener) {
		super(Method.POST, url, errorListener);
		this.listener = responseListener;
		this.params = params;
	}

	public MyJsonObjectRequest(int method, String url,
			Map<String, String> params,
			Response.Listener<JSONObject> reponseListener,
			Response.ErrorListener errorListener) {
		super(method, url, errorListener);
		this.listener = reponseListener;
		this.params = params;
	}

	@Override
	protected Map<String, String> getParams()
			throws com.android.volley.AuthFailureError {
		return params;
	};

	@Override
	protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
		try {
			String jsonString = new String(response.data,
					HttpHeaderParser.parseCharset(response.headers));

			return Response.success(new JSONObject(jsonString),
					HttpHeaderParser.parseCacheHeaders(response));
		} catch (UnsupportedEncodingException e) {
			return Response.error(new ParseError(e));
		} catch (JSONException je) {
			return Response.error(new ParseError(je));
		}
	}

	@Override
	protected void deliverResponse(JSONObject response) {
		listener.onResponse(response);
	}
}