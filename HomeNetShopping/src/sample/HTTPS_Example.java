package sample;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;

import org.json.JSONObject;


//import org.json.simple.JSONObject;

public class HTTPS_Example {

	public static void main(String[] args) {
		HTTPS_Example obj = new HTTPS_Example();
  
		obj.getHttpsConnection();
	}

 public void getHttpsConnection() {

  HttpsURLConnection conn = null;
  try {
	  URL url = new URL("https://msecure.e-himart.co.kr/app/order/get/list/goods/article/ajax");

	  conn = (HttpsURLConnection) url.openConnection();
   
	  conn.setRequestMethod("POST");
	  conn.setRequestProperty("User-Agent", "Mozilla/5.0");
	  conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
	  conn.setDoInput(true);
	  conn.setDoOutput(true);
	  conn.setUseCaches(false);
	  conn.setHostnameVerifier(new HostnameVerifier() {
		  //@Override
		  public boolean verify(String hostname, SSLSession session) {
			  return true;
		  }
	  });
   
	  // SSL setting
	  SSLContext context = SSLContext.getInstance("TLS");
	  context.init(null, new TrustManager[]{
		  new javax.net.ssl.X509TrustManager() {
  
			  //@Override
			  public X509Certificate[] getAcceptedIssuers() {
				  return null;
			  }
  
			  //@Override
			  public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			  }
  
			  //@Override
			  public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			  }
		  }
	  }, null);
	  conn.setSSLSocketFactory(context.getSocketFactory());
      
	  // Connect to host 
	  //   conn.connect(); 
	  //   conn.setInstanceFollowRedirects(true); 
   
	  String param = "{\"artcDpthNo\":\"1\"}";
   
	  DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
	  wr.write(param.getBytes("UTF-8"));
	  wr.flush();
	  wr.close();
	  
	  int responseCode = conn.getResponseCode();
	  System.out.println("responseCode=["+responseCode+"]");
	  
	  // Print response from host 
	  InputStream in = new BufferedInputStream(conn.getInputStream());
      String result = org.apache.commons.io.IOUtils.toString(in, "UTF-8");
      JSONObject jsonObject = new JSONObject(result);
      System.out.println("result=["+result+"]");
      System.out.println("jsonObject=["+jsonObject.toString()+"]");

	  in.close();

  	} catch (Exception e) {
  		e.printStackTrace();
  	} finally {
  		if(conn != null) {
  			conn.disconnect();
  		}
  	}
 }

}