package com.fpt.mic.micweb.utils;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by trungdq on 04/08/2014.
 */


public class NetworkUtils {

    private static List<String> cookies;
    private static final String USER_AGENT = "Mozilla/5.0";

    /** Get Data Fom URL Using GET Method */
    public static String getResponseFromGetRequest(String url) {
        HttpParams httpParameters = new BasicHttpParams();
        int timeoutConnection = 20000;
        HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
        int timeoutSocket = 20000;
        HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
        HttpClient httpClient = new DefaultHttpClient(httpParameters);
        HttpGet httpGet = new HttpGet(url);

        /** after prepare for data. prepare for sending */
        try {
            /**
             * HttpResponse is an interface just like HttpGet
             * therefore we can't initialize them
             */
            HttpResponse httpResponse = httpClient.execute(httpGet);
            return parseHttpResponse(httpResponse);
        } catch (IllegalStateException ex) {
            // Log.d("LiveVideoActivity", "Request IllegalStateException");
            ex.printStackTrace();
        } catch (ClientProtocolException ex) {
            // Log.d("LiveVideoActivity", "Request ClientProtocolException");
            ex.printStackTrace();
        } catch (IOException ex) {
            // Log.d("LiveVideoActivity", "Request IOException");
            ex.printStackTrace();
            return "IOException";
        }
        return null;
    }

    /** Get Data Fom URL Using POST Method */
    public static String getResponseFromPOSTRequest(String url, Map<String, String> params, int timeout) {

        final HttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, timeout);
        HttpClient httpClient = new DefaultHttpClient(httpParams);

        /**
         * In a POST request, we don't pass the values in the URL.
         * Therefore we use only the web page URL as the parameter of the HtpPost arguments
         */
        HttpPost httpPost = new HttpPost(url);

        /**
         * Because we are not passing values over the URL, we should have a mechanism to pass the values that can be
         * uniquely separate by the other end.
         * To achieve that we use BasicNameValuePair
         */
        List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            BasicNameValuePair basicNameValuePair = new BasicNameValuePair(entry.getKey(), entry.getValue());

            /**
             * We add the content that we want to pass with the POST request to as name-value pairs
             * Now we put those sending details to an ArrayList with type safe of NameValuePair
             */
            nameValuePairList.add(basicNameValuePair);
        }

        /** after prepare for data. prepare for sending */
        try {

            /**
             *  UrlEncodedFormEntity is an entity composed of a list of url-encoded pairs.
             *  This is typically useful while sending an HTTP POST request.
             */
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(nameValuePairList);

            /** setEntity() hands the entity (here it is urlEncodedFormEntity) to the request. */
            httpPost.setEntity(urlEncodedFormEntity);

            try {
                /**
                 * HttpResponse is an interface just like HttpPost
                 * therefore we can't initialize them
                 */
                HttpResponse httpResponse = httpClient.execute(httpPost);
                return parseHttpResponse(httpResponse);


            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String parseHttpResponse(HttpResponse httpResponse) {
        /**
         * according to the JAVA API, InputStream constructor do nothing.
         * So we can't initialize InputStream although it is not an interface
         */
        InputStream inputStream;
        try {
            inputStream = httpResponse.getEntity().getContent();
            /** buffer for performance */
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            /** StringBuilder for performance */
            StringBuilder stringBuilder = new StringBuilder();

            String bufferedStrChunk;

            while((bufferedStrChunk = bufferedReader.readLine()) != null){
                stringBuilder.append(bufferedStrChunk);
            }

            // Log.i(APP_TAG, stringBuilder.toString());
            return stringBuilder.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getPageContentHttps(String url) throws Exception {
        URL obj = new URL(url);
        HttpsURLConnection.setFollowRedirects(false);
        HttpsURLConnection conn = (HttpsURLConnection) obj.openConnection();
        // default is GET
        conn.setRequestMethod("GET");
        return getPageContent(conn);
    }
    private static String getPageContent(URLConnection conn) throws Exception {
        conn.setUseCaches(false);

        // act like a browser
        conn.setRequestProperty("User-Agent", USER_AGENT);
        conn.setRequestProperty("Accept",
                "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        conn.setRequestProperty("Accept-Language", "vi-VN,vi;q=0.8,fr-FR;q=0.6,fr;q=0.4,en-US;q=0.2,en;q=0.2");
        if (cookies != null) {
            for (String cookie : cookies) {
                conn.addRequestProperty("Cookie", cookie.split(";", 1)[0]);
            }
        }
        try {
            int responseCode = ((HttpURLConnection) conn).getResponseCode();
            System.out.println("Response Code : " + responseCode);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String redirectedUrl = conn.getHeaderField("Location");
        if (redirectedUrl != null && redirectedUrl.startsWith("http://download")) {
            return redirectedUrl;
        }

        BufferedReader in =
                new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // Get the response cookies
        cookies = conn.getHeaderFields().get("Set-Cookie");

        return response.toString();

    }

}
