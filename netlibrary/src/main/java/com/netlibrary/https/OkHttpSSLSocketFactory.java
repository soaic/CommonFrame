package com.netlibrary.https;

import android.content.Context;

import com.netlibrary.R;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/**
 * OkHttp SSL验证
 *
 * @author XiaoSai
 */
public class OkHttpSSLSocketFactory{

    /**
     * 获取SSLSocketFactory忽略证书 
     * @return SSLSocketFactory
     */
    public static SSLSocketFactory getSocketFactory(){
        SSLContext sslContext = null;
        try{
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null,new TrustManager[]{ getTrustManager() }, new SecureRandom());
            
        }catch(KeyManagementException | NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        assert sslContext != null;
        return sslContext.getSocketFactory();
    }

    /**
     * 获取单向认证证书 无密码
     * @param context
     * @return
     */
    public static SSLSocketFactory getSocketFactory(Context context){
        SSLContext sslContext = null;
        try{

            InputStream certificate = context.getResources().openRawResource(R.raw.srca);//12306证书
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            keyStore.setCertificateEntry("1", certificateFactory.generateCertificate(certificate));
            if (certificate != null)
                certificate.close();

            TrustManagerFactory trustManagerFactory =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);

            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustManagerFactory.getTrustManagers() , new SecureRandom());

        }catch(KeyManagementException | NoSuchAlgorithmException | IOException | CertificateException | KeyStoreException e){
            e.printStackTrace();
        }
        assert sslContext != null;
        return sslContext.getSocketFactory();
    }

    /**
     * 获取双向认证证书
     * @param context
     * @return
     */
    public static SSLSocketFactory getSocketFactoryAll(Context context){
        String KEY_STORE_PASSWORD = "123456";//证书密码 客户端证书密码   
        String KEY_STORE_TRUST_PASSWORD = "123456";//授信证书密码 服务端证书密码   
        InputStream trust_input = context.getResources().openRawResource(R.raw.srca);//服务器证书 
        InputStream client_input = context.getResources().openRawResource(R.raw.srca);//客户端证书
        SSLContext sslContext = null;
        try{
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            keyStore.setCertificateEntry("1", certificateFactory.generateCertificate(trust_input));
            
            //师傅端证书认证
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(trust_input, KEY_STORE_TRUST_PASSWORD.toCharArray());
            TrustManagerFactory trustManagerFactory =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(trustStore);

            //客户端证书认证
            KeyStore cleanStore = KeyStore.getInstance("PKCS12");
            cleanStore.load(client_input, KEY_STORE_PASSWORD.toCharArray());
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(cleanStore, KEY_STORE_PASSWORD.toCharArray());
            
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), new SecureRandom());

        }catch(KeyManagementException | NoSuchAlgorithmException | IOException | CertificateException 
                | UnrecoverableKeyException | KeyStoreException e){
            e.printStackTrace();
        }finally{
            try {
                if(trust_input!=null) trust_input.close();
                if(client_input!=null) client_input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        assert sslContext != null;
        return sslContext.getSocketFactory();
    }
    

    /**
     * 获取TrustManager
     * @return TrustManager
     */
    public static X509TrustManager getTrustManager(){
        return new X509TrustManager(){
            @Override
            public void checkClientTrusted(X509Certificate[] chain,String authType){
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain,String authType){
            }

            @Override
            public X509Certificate[] getAcceptedIssuers(){
                return new X509Certificate[0];
            }
        };
    }

    /**
     * 忽略主机认证
     * @return HostnameVerifier
     */
    public static HostnameVerifier getHostnameVerifier(){
        return new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
    }
}
