package com.stretchcom.livefeed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
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
	
	//private static final String HTTPS_BASE_URL = "http://mobilepulse.tr-sandbox.appspot.com/";
	private static final String HTTPS_BASE_URL = "http://localhost:8888/";  //development server.  Run->Run As->Web Application
	
	private static final String FEEDBACK_RESOURCE_URI = "rest/feedback";
	private static final String CRASH_DETECT_RESOURCE_URI = "rest/crashDetects";
	private static final String AUDIO_URI = "audio";
	private static final String CRASH_STACK_DATA_URI = "crashStackData";
	private static final String CRASH_STACK_DATA_FILE_EXTENSION = ".plcrash";
	private static final String CLIENT_LOG_RESOURCE_URI = "rest/clientLogs";

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
//		String recordedDate = "2011-10-24 13:21";
//		String userName = "joew";
//		String instanceUrl = "http://fruition18.service-now.com/";
//		String voice = "Terry, put your base64 encodedvoice data in here";
//		verifyCreateFeedback(userName, recordedDate, instanceUrl, voice);
		
		// ====================
		// GET LIST OF FEEDBACK
		// ====================
		// PARAMS:: verifyGetListOfFeedback(String theStatus)
		//verifyGetListOfFeedback(null); // default status of "new"
		//verifyGetListOfFeedback("new");
		//verifyGetListOfFeedback("archived");
		verifyGetListOfFeedback("all");
		
		// =================
		// GET FEEDBACK INFO
		// =================
		// PARAMS:: verifyGetFeedbackInfo(String theFeedbackId)
		//verifyGetFeedbackInfo("agp0ci1zYW5kYm94cg4LEghGZWVkYmFjaxgjDA");
		
		// ===============
		// UPDATE FEEDBACK
		// ===============
		// PARAMS:: verifyUpdateFeedback(String theFeedbackId, String theNewStatus)
		//verifyUpdateFeedback("agp0ci1zYW5kYm94cg4LEghGZWVkYmFjaxgeDA", "archived");
		//verifyUpdateFeedback("bad_id", "new");
		
		// =========
		// GET AUDIO
		// =========
		// PARMS:: verifyGetAudio(String theFeedbackId)
		//verifyGetAudio("agp0ci1zYW5kYm94cg4LEghGZWVkYmFjaxgCDA");
		
		// ====================
		// CRTEATE CRASH DETECT
		// ====================
		// PARAMS:: String verifyCreateCrashDetect(String theSummary, String theUserName, String theDetectedDate, String theInstanceUrl, String theCrashStackData)
//		String summary = "this record has REAL base64 encoded crash stack data";
//		String detectedDate = "2011-10-27 23:12";
//		String userName = "joew";
//		String instanceUrl = "http://fruition18.service-now.com/";
//		String crashStackDataBase64 = "dGhpcyBpcyB0ZXN0IGRhdGEgdGhhdCB3YXMgZW5jb2RlZCB1c2luZyBhbiBvbmxpbmUgdG9vbA==";
//		verifyCreateCrashDetect(summary, userName, detectedDate, instanceUrl, crashStackDataBase64);
		
		// =========================
		// GET LIST OF CRASH DETECTS
		// =========================
		// PARAMS:: verifyGetListOfCrashDetects(String theStatus)
		//verifyGetListOfCrashDetects(null); // default of 'new' status
		//verifyGetListOfCrashDetects("new");
		//verifyGetListOfCrashDetects("archived");
		//verifyGetListOfCrashDetects("all");
		
		// =====================
		// GET CRASH DETECT INFO
		// =====================
		// PARAMS:: verifyGetCrashDetectInfo(String theCrashDetectId)
		//verifyGetCrashDetectInfo("agp0ci1zYW5kYm94chELEgtDcmFzaERldGVjdBgkDA");
		
		// ===================
		// UPDATE CRASH DETECT
		// ===================
		// PARAMS:: verifyUpdateCrashDetect(String theCrashDetectId, String theNewStatus)
		//verifyUpdateCrashDetect("agp0ci1zYW5kYm94chELEgtDcmFzaERldGVjdBgVDA", "archived");
		//verifyUpdateCrashDetect("bad_id", "new");
		
		// ====================
		// GET CRASH STACK DATA
		// ====================
		// PARMS:: verifyGetCrashStackData(String theCrashDetectId)
		//verifyGetCrashStackData("agp0ci1zYW5kYm94chELEgtDcmFzaERldGVjdBgfDA");
		
		// ==================
		// CRTEATE CLIENT LOG
		// ==================
		// PARAMS:: String verifyCreateClientLog(String theUserName, String theInstanceUrl, String theLogLevel, String theMessage, String theStackBackTrace)
//		String userName = "joew";
//		String instanceUrl = "http://fruition18.service-now.com/";
//		String logLevel = "error";
//		String message = "first client log message after date refactoring";
//		String stackBackTrace = "method1() method2() method3()";
//		verifyCreateClientLog(userName, instanceUrl, logLevel, message, stackBackTrace);
		
		// =======================
		// GET LIST OF CLIENT LOGS
		// =======================
		// PARAMS:: verifyGetListOfClientLogs(String theStatus)
		//verifyGetListOfClientLogs(null); // default status of 'new'
		//verifyGetListOfClientLogs("new");
		//verifyGetListOfClientLogs("archived");
		//verifyGetListOfClientLogs("all");
		
		// ===================
		// GET CLIENT LOG INFO
		// ===================
		// PARAMS:: verifyGetClientLogsInfo(String theClientLogId)
		//verifyGetClientLogsInfo("agp0ci1zYW5kYm94cg8LEglDbGllbnRMb2cYJQw");
		
		// =================
		// UPDATE CLIENT LOG
		// =================
		// PARAMS:: verifyUpdateClientLog(String theClientLogId, String theNewStatus)
		//verifyUpdateClientLog("agp0ci1zYW5kYm94cg8LEglDbGllbnRMb2cYGQw", "archived");
		//verifyUpdateClientLog("bad_id", "new");
	}
	
	private static String verifyCreateFeedback(String theUserName, String theRecordedDate, String theInstanceUrl, String theVoice) {
		if(isLoggingEnabled) System.out.println("\n\n verifyCreateFeedback() starting .....\n");
		String urlStr = HTTPS_BASE_URL + FEEDBACK_RESOURCE_URI;
		JSONObject json = new JSONObject();
		String token = "";
		try {
			if(theUserName != null) json.put("userName", theUserName);
			if(theRecordedDate != null) json.put("date", theRecordedDate);
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
	
	private static void verifyGetListOfFeedback(String theStatus) {
		System.out.println("\n\n verifyGetListOfFeedback() starting .....\n");
		String urlStr = HTTPS_BASE_URL + FEEDBACK_RESOURCE_URI;
		if(theStatus != null) {
			String encodedStatus = ClientTest.encode(theStatus);
			urlStr = urlStr + "?" + "status=" + encodedStatus;
		}
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
	
	private static String verifyCreateCrashDetect(String theSummary, String theUserName, String theDetectedDate, String theInstanceUrl, String theCrashStackData) {
		if(isLoggingEnabled) System.out.println("\n\n verifyCreateCrashDetect() starting .....\n");
		String urlStr = HTTPS_BASE_URL + CRASH_DETECT_RESOURCE_URI;
		JSONObject json = new JSONObject();
		String token = "";
		try {
			if(theSummary != null) json.put("summary", theSummary);
			if(theUserName != null) json.put("userName", theUserName);
			if(theDetectedDate != null) json.put("date", theDetectedDate);
			if(theInstanceUrl != null) json.put("instanceUrl", theInstanceUrl);
			if(theCrashStackData != null) json.put("stackData", theCrashStackData);
			
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
			System.out.println("verifyCreateCrashDetect() complete");
		}
		return token;
	}
	
	private static void verifyGetListOfCrashDetects(String theStatus) {
		System.out.println("\n\n verifyGetListOfCrashDetects() starting .....\n");
		String urlStr = HTTPS_BASE_URL + CRASH_DETECT_RESOURCE_URI;
		if(theStatus != null) {
			String encodedStatus = ClientTest.encode(theStatus);
			urlStr = urlStr + "?" + "status=" + encodedStatus;
		}
		System.out.println("urlStr = " + urlStr + "\n");
		
		try {
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_GET, null, "login", null);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			System.out.println("\n");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyGetListOfCrashDetects() complete\n");
		}
	}
	
	private static void verifyGetCrashDetectInfo(String theCrashDetectId) {
		System.out.println("\n\n verifyGetCrashDetectInfo() starting .....\n");
		String urlStr = HTTPS_BASE_URL + CRASH_DETECT_RESOURCE_URI + "/" + theCrashDetectId;
		System.out.println("urlStr = " + urlStr + "\n");
		
		try {
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_GET, null, "login", null);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			System.out.println("\n");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyGetCrashDetectInfo() complete\n");
		}
	}
	
	private static void verifyUpdateCrashDetect(String theCrashDetectId, String theNewStatus) {
		System.out.println("\n\n verifyUpdateCrashDetect() starting .....\n");
		String urlStr = HTTPS_BASE_URL + CRASH_DETECT_RESOURCE_URI + "/" + theCrashDetectId;
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
			System.out.println("verifyUpdateCrashDetect() complete\n");
		}
	}
	
	private static void verifyGetCrashStackData(String theCrashDetectId) {
		System.out.println("\n\n verifyGetCrashStackData() starting .....\n");
		String urlStr = HTTPS_BASE_URL + CRASH_STACK_DATA_URI + "/" + theCrashDetectId + CRASH_STACK_DATA_FILE_EXTENSION;
		System.out.println("urlStr = " + urlStr + "\n");
		
		try {
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_GET, null, "login", null);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			System.out.println("\n");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyGetCrashStackData() complete\n");
		}
	}
	
	private static String verifyCreateClientLog(String theUserName, String theInstanceUrl, String theLogLevel, String theMessage, String theStackBackTrace) {
		if(isLoggingEnabled) System.out.println("\n\n verifyCreateClientLog() starting .....\n");
		String urlStr = HTTPS_BASE_URL + CLIENT_LOG_RESOURCE_URI;
		JSONObject json = new JSONObject();
		String token = "";
		try {
			if(theUserName != null) json.put("userName", theUserName);
			if(theInstanceUrl != null) json.put("instanceUrl", theInstanceUrl);
			if(theLogLevel != null) json.put("logLevel", theLogLevel);
			if(theMessage != null) json.put("message", theMessage);
			if(theStackBackTrace != null) json.put("stackBackTrace", theStackBackTrace);
			
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
			System.out.println("verifyCreateClientLog() complete");
		}
		return token;
	}
	
	private static void verifyGetListOfClientLogs(String theStatus) {
		System.out.println("\n\n verifyGetListOfClientLogs() starting .....\n");
		String urlStr = HTTPS_BASE_URL + CLIENT_LOG_RESOURCE_URI;
		if(theStatus != null) {
			String encodedStatus = ClientTest.encode(theStatus);
			urlStr = urlStr + "?" + "status=" + encodedStatus;
		}
		System.out.println("urlStr = " + urlStr + "\n");
		
		try {
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_GET, null, "login", null);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			System.out.println("\n");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyGetListOfClientLogs() complete\n");
		}
	}
	
	private static void verifyGetClientLogsInfo(String theClientLogId) {
		System.out.println("\n\n verifyGetClientLogsInfo() starting .....\n");
		String urlStr = HTTPS_BASE_URL + CLIENT_LOG_RESOURCE_URI + "/" + theClientLogId;
		System.out.println("urlStr = " + urlStr + "\n");
		
		try {
			URL url = new URL(urlStr);
			String response = ClientTest.send(url, ClientTest.HTTP_GET, null, "login", null);
			if(isLoggingEnabled) System.out.println("repStr = " + response);
			System.out.println("\n");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("verifyGetClientLogsInfo() complete\n");
		}
	}
	
	private static void verifyUpdateClientLog(String theClientLogId, String theNewStatus) {
		System.out.println("\n\n verifyUpdateClientLog() starting .....\n");
		String urlStr = HTTPS_BASE_URL + CLIENT_LOG_RESOURCE_URI + "/" + theClientLogId;
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
			System.out.println("verifyUpdateClientLog() complete\n");
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
	
	private static String encode(String theInput) {
		String output = "";
		try {
			output = URLEncoder.encode(theInput, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			System.out.println("encode exception = " + e.getMessage());
		}
		return output;
	}
}	

