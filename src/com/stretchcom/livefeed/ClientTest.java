package com.stretchcom.livefeed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ClientTest {
	
	//private static final String HTTPS_BASE_URL = "http://mobilepulse.tr-sandbox.appspot.com/rest/";
	private static final String HTTPS_BASE_URL = "http://localhost:8888/";  //development server.  Run->Run As->Web Application
	
	private static final String FEEDBACK_RESOURCE_URI = "rest/feedback";
	private static final String AUDIO_URI = "audio";

	private static int totalAssertCount = 0;
	private static int passingAssertCount = 0;
	private static int failingAssertCount = 0;
	private static Boolean isLoggingEnabled = true;
	
	// HTTP Methods
	private static final String HTTP_PUT = "PUT";
	private static final String HTTP_POST = "POST";
	private static final String HTTP_GET = "GET";
	private static final String HTTP_DELETE = "DELETE";
	
	private static final int TEN_SECONDS_IN_MILLIS = 10000;

	
	public static void main(String[] args) throws Exception {
		totalAssertCount = 0;
		passingAssertCount = 0;
		failingAssertCount = 0;
		isLoggingEnabled = true;
		//=====================================================================================================================
		//=====================================================================================================================
		// GAE ==> Entity Keys
		//=====================================================================================================================
		//		
		//
		//=====================================================================================================================
		
		//=====================================================================================================================
		//=====================================================================================================================
		// DEVELOPMENT SERVER(Localhost:8888) ==> Entity Keys
		//=====================================================================================================================
		//
		//=====================================================================================================================
		
		// ================
		// CRTEATE FEEDBACK
		// ================
		// PARAMS:: String verifyCreateFeedback(String theUserName, String theRecordedDate, String theInstanceUrl, String theVoice)
		String recordedDate = "2011-10-22 05:55";
		String userName = "joew";
		String instanceUrl = "http://fruition18.service-now.com/";
		String voice = "Terry, put your base64 encodedvoice data in here";
		verifyCreateFeedback("joew", recordedDate, instanceUrl, voice);

		//verifyGetListOfFeedback();
		
		// ====================
		// GET LIST OF FEEDBACK
		// ====================
		// PARAMS:: verifyGetListOfFeedback()
		//verifyGetListOfFeedback();
		
		// =================
		// GET FEEDBACK INFO
		// =================
		// PARAMS:: verifyGetFeedbackInfo(String theFeedbackId)
		//verifyGetFeedbackInfo("agp0ci1zYW5kYm94cg4LEghGZWVkYmFjaxgCDA");
		
		// ===============
		// UPDATE FEEDBACK
		// ===============
		// PARAMS:: verifyUpdateFeedback(String theFeedbackId, String theNewStatus)
		//verifyUpdateFeedback("agp0ci1zYW5kYm94cg4LEghGZWVkYmFjaxgCDA", "new");
		//verifyUpdateFeedback("bad_id", "new");
		
		// =========
		// GET AUDIO
		// =========
		// PARMS:: verifyGetAudio(String theFeedbackId)
		verifyGetAudio("agp0ci1zYW5kYm94cg4LEghGZWVkYmFjaxgCDA");
	}
	
	private static String verifyCreateFeedback(String theUserName, String theRecordedDate, String theInstanceUrl, String theVoice) {
		if(isLoggingEnabled) System.out.println("\n\n verifyCreateFeedback() starting .....\n");
		String urlStr = HTTPS_BASE_URL + FEEDBACK_RESOURCE_URI;
		JSONObject json = new JSONObject();
		String token = "";
		try {
			if(theUserName != null) json.put("userName", theUserName);
			if(theRecordedDate != null) json.put("recordedDate", theRecordedDate);
			if(theInstanceUrl != null) json.put("instanceUrl", theInstanceUrl);
			if(theVoice != null) json.put("voice", theVoice);
			
			System.out.println(json.toString());
			
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_POST, json.toString(), null, null);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			JSONObject jsonReturn = new JSONObject(response);
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyCreateFeedback() complete");
		}
		return token;
	}
	
	private static void verifyGetListOfFeedback() {
		System.out.println("\n\n verifyGetListOfFeedback() starting .....\n");
		String urlStr = HTTPS_BASE_URL + FEEDBACK_RESOURCE_URI;
		System.out.println("urlStr = " + urlStr + "\n");
		
		try {
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_GET, null, "login", null);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			System.out.println("\n");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyGetListOfFeedback() complete\n");
		}
	}
	
	private static void verifyGetFeedbackInfo(String theFeedbackId) {
		System.out.println("\n\n verifyGetFeedbackInfo() starting .....\n");
		String urlStr = HTTPS_BASE_URL + FEEDBACK_RESOURCE_URI + "/" + theFeedbackId;
		System.out.println("urlStr = " + urlStr + "\n");
		
		try {
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_GET, null, "login", null);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			System.out.println("\n");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyGetFeedbackInfo() complete\n");
		}
	}
	
	private static void verifyUpdateFeedback(String theFeedbackId, String theNewStatus) {
		System.out.println("\n\n verifyUpdateFeedback() starting .....\n");
		String urlStr = HTTPS_BASE_URL + FEEDBACK_RESOURCE_URI + "/" + theFeedbackId;
		System.out.println("urlStr = " + urlStr + "\n");
		JSONObject json = new JSONObject();
		
		try {
			json.put("status", theNewStatus);
			
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_PUT, json.toString(), "login", null);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			System.out.println("\n");
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyUpdateFeedback() complete\n");
		}
	}
	
	
	private static void verifyGetAudio(String theFeedbackId) {
		System.out.println("\n\n verifyGetAudio() starting .....\n");
		String urlStr = HTTPS_BASE_URL + AUDIO_URI + "/" + theFeedbackId;
		System.out.println("urlStr = " + urlStr + "\n");
		
		try {
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_GET, null, "login", null);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			System.out.println("\n");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyGetAudio() complete\n");
		}
	}
	
	// theUrl: complete url
	// thePayload: the JSON payload to send, if any.  Can be null.
	// theHttpMethod: one of GET POST HEAD OPTIONS PUT DELETE TRACE
	static private String send(URL theUrl, String theHttpMethod, String theJsonPayload, 
			                   String theBasicAuthUserName, String theBasicAuthPassword) {
		System.out.println("ClientTest::send theUrl = " + theUrl.toString());
		System.out.println("ClientTest::send theJsonPayload = " + theJsonPayload);
		System.out.println("ClientTest::send theHttpMethod = " + theHttpMethod);

		String response = "";
		HttpURLConnection connection = null;
		OutputStreamWriter writer = null;
		InputStreamReader reader = null;
		try {
			/////////////////////
			// Prepare connection
			/////////////////////
			connection = (HttpURLConnection)theUrl.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setAllowUserInteraction(false);
			connection.setRequestMethod(theHttpMethod);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setConnectTimeout(TEN_SECONDS_IN_MILLIS);
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("accept-encoding", "*/*");
			
			
			///////////////////////
			// Basic Authentication
			///////////////////////
			if(theBasicAuthUserName != null && theBasicAuthPassword != null) {
				StringBuilder buf = new StringBuilder(theBasicAuthUserName);
				buf.append(':');
				buf.append(theBasicAuthPassword);
				byte[] bytes = null;
				try {
					bytes = buf.toString().getBytes("ISO-8859-1");
				} catch (java.io.UnsupportedEncodingException uee) {
					System.out.println("base64 encoding failed: " + uee.getMessage());
				}

				//String header = "Basic " + Base64.encode(bytes);
				String header = "";
				connection.setRequestProperty("Authorization", header);
			}

			////////////////////
			// Send HTTP Request
			////////////////////
			connection.connect();
			
			if(theJsonPayload == null) {
				theJsonPayload = "{}";
			}
			if(!theHttpMethod.equalsIgnoreCase(HTTP_GET) && !theHttpMethod.equalsIgnoreCase(HTTP_DELETE)) {
				writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
				writer.write(theJsonPayload);
				writer.flush();
			}

			////////////////////
			// Get HTTP response
			////////////////////
			int responseCode = connection.getResponseCode();
			System.out.println("responseCode = " + responseCode);
			
			if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
				// read-back the response
				reader = new InputStreamReader(connection.getInputStream());
				BufferedReader in = new BufferedReader(reader);
				StringBuffer responseBuffer = new StringBuffer();
				while (true) {
					String inputLine = in.readLine();
					if(inputLine == null) {break;}
					responseBuffer.append(inputLine);
				}
				in.close();
				response = responseBuffer.toString();
			} else // Server returned HTTP error code.
			{
				System.out.println("ClientTest::send() server returned error code: " + responseCode);
			}

		} catch (UnsupportedEncodingException ex) {
			System.out.println("ClientTest::send() UnsupportedEncodingException: " + ex);
		} catch (MalformedURLException ex) {
			System.out.println("ClientTest::send() MalformedURLException: " + ex);
		} catch (IOException ex) {
			System.out.println("ClientTest::send() IOException: " + ex);
		} finally {
			try {
				if (writer != null) {writer.close();}
			} catch (Exception ex) {
				System.out.println("ClientTest::send() Exception closing writer: " + ex);
			}
			try {
				if (reader != null) {reader.close();}
			} catch (Exception ex) {
				System.out.println("ClientTest::send() Exception closing reader: " + ex);
			}
			if (connection != null) {connection.disconnect();}
		}

		return response;
	}
}	

