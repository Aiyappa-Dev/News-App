package com.aimright.admin.demoapp.apiutils;
import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient1 {
    public static final String BASE_URL1 = "https://newsapi.org/v2/";
    //public static final String BASE_URL = "https://apis.nuntium.in/";
    public static Retrofit retrofit;

    public static Retrofit getApiClient(String s){

        /*if (!(s.equalsIgnoreCase("news")) || retrofit == null  ){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .client(getUnsafeOkHTTPClient().build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }*/
        if (s.equalsIgnoreCase("news") || s.equalsIgnoreCase("US Headlines"))
        {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL1)
                    .client(getUnsafeOkHTTPClient().build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    private static OkHttpClient.Builder getUnsafeOkHTTPClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            return builder;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
