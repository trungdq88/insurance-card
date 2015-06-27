package com.fpt.mic.mobile.checker.app.ApiRequest;

import android.os.AsyncTask;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by trungdq on 04/08/2014.
 */
public class RequestSender extends AsyncTask<String, Integer, String> {
    IRequestSenderComplete callback;
    public String method = "GET";
    public Map<String, String> param;

    public void start(String url, IRequestSenderComplete callback) {
        this.callback = callback;
        this.execute(url);
    }

    protected String doInBackground(String... urls) {
        try {
            if (method.equals("GET")) {
                return NetworkUtils.getResponseFromGetRequest(urls[0]);
            } else {
                return NetworkUtils.getResponseFromPOSTRequest(urls[0], param, 5000);
            }
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    protected void onProgressUpdate(Integer... progress) {
        // setProgressPercent(progress[0]);
    }

    protected void onPostExecute(String result) {
        if (this.callback != null) {
            this.callback.onRequestComplete(result);
        }
    }

    public interface IRequestSenderComplete {
        void onRequestComplete(String result);
    }

}
