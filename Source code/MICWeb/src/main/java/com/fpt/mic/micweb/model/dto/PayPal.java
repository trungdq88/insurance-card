package com.fpt.mic.micweb.model.dto;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletResponse;


/**
 * Created by TriPQMSE60746 on 06/07/2015.
 * Reference: Paypal Express Checkout API from https://demo.paypal.com/us/home.
 */
public class PayPal  {

    private String gvAPIUserName;
    private String gvAPIPassword;
    private String gvAPISignature;
    private String gvAPIEndpoint;
    private String gvBNCode;
    private String gvVersion;
    private String paypalUrl;
    private String userActionFlag;
    private String sellerEmail;
    private String environment;

    public PayPal() {

            //get the property value from config.properties file
            this.setUserActionFlag("false");
            String strSandbox = "";
            environment = "sandbox";
            //ButtonSource Tracker Code
            gvBNCode	= "PP-DemoPortal-EC-IC-java";
            sellerEmail = "minhtridn2001-facilitator@yahoo.com";
            gvAPIUserName	= "minhtridn2001-facilitator_api1.yahoo.com";
            this.setGvAPIUserName(gvAPIUserName);
            gvAPIPassword	= "U7UQ4DAGDXFMRGLS";
            gvAPISignature = "An5ns1Kso7MWUdW4ErQKJJJ4qi4-AgmqQzzyvUC0Q0K4LzROG4gPxSzK";

            gvAPIEndpoint = "https://api-3t.sandbox.paypal.com/nvp";
            paypalUrl = "https://www.sandbox.paypal.com/checkoutnow?token=";

            gvVersion	= "109.0";
            System.setProperty("https.protocols", "TLSv1");

    }



    /*********************************************************************************
     * CallShortcutExpressCheckout: Function to perform the SetExpressCheckout API call
     *
     * Inputs:
     *		request:     the item details, prices and taxes
     *		returnURL:			the page where buyers return to after they are done with the payment review on PayPal
     *		cancelURL:			the page where buyers return to when they cancel the payment review on PayPal
     *
     * Output: Returns a HashMap object containing the response from the server.
     *********************************************************************************/
    public Map<String, String> callShortcutExpressCheckout( Map<String,String> checkoutDetails, String returnURL, String cancelURL)
    {

        // Construct the parameter string that describes the SetExpressCheckout API call in the shortcut implementation
        // For more information on the customizing the parameters passed refer: https://developer.paypal.com/docs/classic/express-checkout/integration-guide/ECCustomizing/

        //Mandatory parameters for SetExpressCheckout API call

        StringBuilder nvpstr = new StringBuilder("");
        if(isSet(checkoutDetails.get("PAYMENTREQUEST_0_AMT") )){
            nvpstr.append( "&PAYMENTREQUEST_0_AMT=").append(checkoutDetails.get("PAYMENTREQUEST_0_AMT"));
        }

        if(isSet(returnURL))
            nvpstr.append("&RETURNURL=").append(returnURL);

        if(isSet(cancelURL))
            nvpstr.append( "&CANCELURL=").append(cancelURL);

        //Optional parameters for SetExpressCheckout API call
        if(isSet(checkoutDetails.get("currencyCodeType") )){
            nvpstr.append( "&PAYMENTREQUEST_0_CURRENCYCODE=").append(checkoutDetails.get("currencyCodeType"));
        }

        if(isSet(checkoutDetails.get("PAYMENTREQUEST_0_ITEMAMT") )){
            nvpstr.append( "&PAYMENTREQUEST_0_ITEMAMT=").append(checkoutDetails.get("PAYMENTREQUEST_0_ITEMAMT"));
        }

        if(isSet(checkoutDetails.get("PAYMENTREQUEST_0_TAXAMT") )){
            nvpstr.append( "&PAYMENTREQUEST_0_TAXAMT=").append(checkoutDetails.get("PAYMENTREQUEST_0_TAXAMT"));
        }


        if(isSet(checkoutDetails.get("L_PAYMENTREQUEST_0_NAME0")))
            nvpstr.append( "&L_PAYMENTREQUEST_0_NAME0=").append(checkoutDetails.get("L_PAYMENTREQUEST_0_NAME0"));

        if(isSet(checkoutDetails.get("L_PAYMENTREQUEST_0_NUMBER0")))
            nvpstr.append( "&L_PAYMENTREQUEST_0_NUMBER0=").append(checkoutDetails.get("L_PAYMENTREQUEST_0_NUMBER0"));

        if(isSet(checkoutDetails.get("L_PAYMENTREQUEST_0_DESC0")))
            nvpstr.append( "&L_PAYMENTREQUEST_0_DESC0=").append(checkoutDetails.get("L_PAYMENTREQUEST_0_DESC0"));

        if(isSet(checkoutDetails.get("PAYMENTREQUEST_0_ITEMAMT")))
            nvpstr.append( "&L_PAYMENTREQUEST_0_AMT0=").append(checkoutDetails.get("PAYMENTREQUEST_0_ITEMAMT"));

        if(isSet(checkoutDetails.get("L_PAYMENTREQUEST_0_QTY0")))
            nvpstr.append( "&L_PAYMENTREQUEST_0_QTY0=").append(checkoutDetails.get("L_PAYMENTREQUEST_0_QTY0"));

        if(isSet(checkoutDetails.get("LOGOIMG")))
            nvpstr.append( "&LOGOIMG="+ checkoutDetails.get("LOGOIMG"));

	
	/*
    Make the call to PayPal to get the Express Checkout token
    If the API call succeeded, then redirect the buyer to PayPal
    to begin to authorize payment.  If an error occurred, show the
    resulting errors
    */
        return httpcall("SetExpressCheckout", nvpstr.toString());

    }

    private boolean isSet(Object value){
        return (value !=null && value.toString().length()!=0);
    }
    private String encode(Object object){
        try {
            return URLEncoder.encode((String) object, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return (String) object;
    }

    /*********************************************************************************
     * CallMarkExpressCheckout: Function to perform the SetExpressCheckout API call
     *
     *
     * Output: Returns a HashMap object containing the response from the server.
     *********************************************************************************/
    public HashMap<String, String> callMarkExpressCheckout(Map<String,String> checkoutDetails, String returnURL, String cancelURL)
    {
        //------------------------------------------------------------------------------------------------------------------------------------
        // Construct the parameter string that describes the SetExpressCheckout API call in the shortcut implementation
        StringBuilder nvpstr = new StringBuilder("");
        //Mandatory parameters for SetExpressCheckout API call
        if(isSet(checkoutDetails.get("PAYMENTREQUEST_0_AMT")))
        {
            nvpstr.append("&PAYMENTREQUEST_0_AMT=").append(checkoutDetails.get("PAYMENTREQUEST_0_AMT"));
        }

        if(isSet(checkoutDetails.get("paymentType")))
        {
            nvpstr.append("&PAYMENTREQUEST_0_PAYMENTACTION=").append(checkoutDetails.get("paymentType"));
        }

        nvpstr.append("&RETURNURL=").append(returnURL).append("&CANCELURL=").append(cancelURL).append("&PAYMENTREQUEST_0_SELLERPAYPALACCOUNTID=").append(this.getSellerEmail());

        //Optional parameters for SetExpressCheckout API call
        if(isSet(checkoutDetails.get("currencyCodeType")))
        {
            nvpstr.append("&PAYMENTREQUEST_0_CURRENCYCODE=").append(checkoutDetails.get("currencyCodeType"));
        }

        if(isSet(checkoutDetails.get("PAYMENTREQUEST_0_ITEMAMT")))
        {
            nvpstr.append("&PAYMENTREQUEST_0_ITEMAMT=").append(checkoutDetails.get("PAYMENTREQUEST_0_ITEMAMT"));
        }

        if(isSet(checkoutDetails.get("PAYMENTREQUEST_0_TAXAMT")))
        {
            nvpstr.append("&PAYMENTREQUEST_0_TAXAMT=").append(checkoutDetails.get("PAYMENTREQUEST_0_TAXAMT"));
        }



        if(isSet(checkoutDetails.get("L_PAYMENTREQUEST_0_NAME0")))
            nvpstr.append("&L_PAYMENTREQUEST_0_NAME0=").append(checkoutDetails.get("L_PAYMENTREQUEST_0_NAME0"));

        if(isSet(checkoutDetails.get("L_PAYMENTREQUEST_0_NUMBER0")))
            nvpstr.append("&L_PAYMENTREQUEST_0_NUMBER0=").append(checkoutDetails.get("L_PAYMENTREQUEST_0_NUMBER0"));

        if(isSet(checkoutDetails.get("L_PAYMENTREQUEST_0_DESC0")))
            nvpstr.append("&L_PAYMENTREQUEST_0_DESC0=").append(checkoutDetails.get("L_PAYMENTREQUEST_0_DESC0"));

        if(isSet(checkoutDetails.get("PAYMENTREQUEST_0_ITEMAMT")))
            nvpstr.append("&L_PAYMENTREQUEST_0_AMT0=").append(checkoutDetails.get("PAYMENTREQUEST_0_ITEMAMT"));

        if(isSet(checkoutDetails.get("L_PAYMENTREQUEST_0_QTY0")))
            nvpstr.append("&L_PAYMENTREQUEST_0_QTY0=").append(checkoutDetails.get("L_PAYMENTREQUEST_0_QTY0"));

        if(isSet(checkoutDetails.get("LOGOIMG")))
            nvpstr.append("&LOGOIMG="+ checkoutDetails.get("LOGOIMG"));


        nvpstr.append("&ADDROVERRIDE=1");

	
	    /*
	    Make the call to PayPal to set the Express Checkout token
	    If the API call succeeded, then redirect the buyer to PayPal
	    to begin to authorize payment.  If an error occurred, show the
	    resulting errors
	    */
        return httpcall("SetExpressCheckout", nvpstr.toString());

    }


    /*********************************************************************************
     * getDetails: Function to perform the GetExpressCheckoutDetails API call
     *
     * Inputs:  token
     *
     * Output: Returns a HashMap object containing the response from the server.
     *********************************************************************************/
    public Map<String, String> getDetails(String token)
    {
    /*
    Build a second API request to PayPal, using the token as the
    ID to get the details on the payment authorization
    */

        String nvpstr= "&TOKEN=" + token;

   /*
    Make the API call and store the results in an array.  If the
    call was a success, show the authorization details, and provide
    an action to complete the payment.  If failed, show the error
    */

        return httpcall("GetExpressCheckoutDetails", nvpstr);

    }

    /*********************************************************************************
     * Purpose: 	Prepares the parameters for the DoExpressCheckoutPayment API Call.
     * Inputs:   FinalPaymentAmount:	The total transaction amount.
     * Returns: 	The NVP Collection object of the DoExpressCheckoutPayment Call Response.
     *********************************************************************************/
    public HashMap confirmPayment(Map<String,String>checkoutDetails, String serverName)
    {

	/* Gather the information to make the final call to finalize the PayPal payment.  The variable nvpstr
     * holds the name value pairs
	 */
        String finalPaymentAmount = encode(checkoutDetails.get("PAYMENTREQUEST_0_AMT"));
        StringBuilder nvpstr = new StringBuilder("");
        //mandatory parameters in DoExpressCheckoutPayment call
        if(isSet(checkoutDetails.get("TOKEN")))
            nvpstr.append("&TOKEN=").append(encode(checkoutDetails.get("TOKEN")));

        if(isSet(checkoutDetails.get("payer_id")))
            nvpstr.append( "&PAYERID=").append(encode(checkoutDetails.get("payer_id")));

        nvpstr.append( "&PAYMENTREQUEST_0_SELLERPAYPALACCOUNTID=").append(this.getSellerEmail());

        if(isSet(checkoutDetails.get("paymentType")))
            nvpstr.append( "&PAYMENTREQUEST_0_PAYMENTACTION=").append(encode(checkoutDetails.get("paymentType")));

        if(isSet(serverName))
            nvpstr.append( "&IPADDRESS=").append(encode(serverName));

        nvpstr.append( "&PAYMENTREQUEST_0_AMT=").append(finalPaymentAmount);

        //Check for additional parameters that can be passed in DoExpressCheckoutPayment API call
        if(isSet(checkoutDetails.get("currencyCodeType")))
            nvpstr.append( "&PAYMENTREQUEST_0_CURRENCYCODE=").append(encode(checkoutDetails.get("currencyCodeType").toString()));

        if(isSet(checkoutDetails.get("PAYMENTREQUEST_0_ITEMAMT")))
            nvpstr.append( "&PAYMENTREQUEST_0_ITEMAMT=").append(encode(checkoutDetails.get("PAYMENTREQUEST_0_ITEMAMT").toString()));

        if(isSet(checkoutDetails.get("PAYMENTREQUEST_0_TAXAMT")))
            nvpstr.append( "&PAYMENTREQUEST_0_TAXAMT=").append(encode(checkoutDetails.get("PAYMENTREQUEST_0_TAXAMT").toString()));
	
	
	 /*
	    Make the call to PayPal to finalize payment
	    If an error occurred, show the resulting errors
	  */
        return httpcall("DoExpressCheckoutPayment", nvpstr.toString());

    }


    /*********************************************************************************
     * httpcall: Function to perform the API call to PayPal using API signature
     * 	@ methodName is name of API  method.
     * 	@ nvpStr is nvp string.
     * returns a NVP string containing the response from the server.
     *********************************************************************************/
    public HashMap<String, String> httpcall( String methodName, String nvpStr)
    {

        String version = "2.3";
        String agent = "Mozilla/4.0";
        StringBuilder respText = new StringBuilder("");
        HashMap nvp = null;

        //deformatNVP( nvpStr );
        StringBuilder encodedData = new StringBuilder("METHOD=").append(methodName).append("&VERSION=").append(gvVersion).append("&PWD=").append(gvAPIPassword).append("&USER=").append(gvAPIUserName).append("&SIGNATURE=").append(gvAPISignature).append(nvpStr).append("&BUTTONSOURCE=").append(gvBNCode);

        try
        {
            URL postURL = new URL( gvAPIEndpoint );

            HttpURLConnection conn = (HttpURLConnection)postURL.openConnection();

            // Set connection parameters. We need to perform input and output,
            // so set both as true.
            conn.setDoInput (true);
            conn.setDoOutput (true);

            // Set the content type we are POSTing. We impersonate it as
            // encoded form data
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty( "User-Agent", agent );

            //conn.setRequestProperty( "Content-Type", type );
            conn.setRequestProperty( "Content-Length", String.valueOf( encodedData.length()) );
            conn.setRequestMethod("POST");

            // get the output stream to POST to.
            DataOutputStream output = new DataOutputStream( conn.getOutputStream());
            output.writeBytes( encodedData.toString() );
            output.flush();
            output.close ();

            // Read input from the input stream.
            int rc = conn.getResponseCode();
            if ( rc != -1)
            {
                BufferedReader is = new BufferedReader(new InputStreamReader( conn.getInputStream()));
                String line = null;
                while(((line = is.readLine()) !=null))
                {
                    respText.append(line);
                }
                nvp = deformatNVP( respText.toString() );
            }
            return nvp;
        }
        catch( IOException e )
        {
            // handle the error here
            return null;
        }
    }

    /*********************************************************************************
     * deformatNVP: Function to break the NVP string into a HashMap
     * 	pPayLoad is the NVP string.
     * returns a HashMap object containing all the name value pairs of the string.
     *********************************************************************************/
    public HashMap deformatNVP( String pPayload )
    {
        HashMap nvp = new HashMap();
        StringTokenizer stTok = new StringTokenizer( pPayload, "&");
        while (stTok.hasMoreTokens())
        {
            StringTokenizer stInternalTokenizer = new StringTokenizer( stTok.nextToken(), "=");
            if (stInternalTokenizer.countTokens() == 2)
            {           String key;
                try {
                    key = URLDecoder.decode(stInternalTokenizer.nextToken(), "UTF-8");
                    String value;
                    value = URLDecoder.decode(stInternalTokenizer.nextToken(), "UTF-8");
                    nvp.put( key.toUpperCase(), value );
                } catch (UnsupportedEncodingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        }
        return nvp;
    }

    /*********************************************************************************
     * RedirectURL: Function to redirect the user to the PayPal site
     * 	token is the parameter that was returned by PayPal
     * returns a HashMap object containing all the name value pairs of the string.
     *********************************************************************************/
    public void redirectURL( HttpServletResponse response, String token, boolean action )
    {
        String payPalURL = paypalUrl + token;
        if(action)
            payPalURL = payPalURL + "&useraction=commit";
        response.setStatus(302);
        response.setHeader( "Location", payPalURL );
        response.setHeader( "Connection", "close" );
    }

    public String getUserActionFlag() {
        return userActionFlag;
    }

    private void setUserActionFlag(String userActionFlag) {
        this.userActionFlag = userActionFlag;
    }

    public String getGvAPIUserName() {
        return gvAPIUserName;
    }

    public void setGvAPIUserName(String gvAPIUserName) {
        this.gvAPIUserName = gvAPIUserName;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }
    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

//end class
}

