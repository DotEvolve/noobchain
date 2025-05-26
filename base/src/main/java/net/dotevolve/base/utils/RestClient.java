/*******************************************************************************
 * @Copyright (c) 2023 DotEvolve, All rights reserved
 * @author DotEvolve
 * @since 05/03/23, 2:03 am
 *
 *
 ******************************************************************************/

package net.dotevolve.base.utils;

import javax.annotation.PostConstruct;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.Base64;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Component
public class RestClient {

    private static final String S_S = "%s:%s";
    private static final String AUTHORIZATION = "Authorization";
    private static final String BASIC = "Basic ";
    Logger logger = LogManager.getLogger(RestClient.class);

    private RestTemplate rest;
    private HttpHeaders headers;

    @PostConstruct
    void init() {
        //disableCertificateVerification();
        this.rest = new RestTemplate();
        this.headers = addDefaultHeaders();

        logger.info("REST client initiated");
    }

    private void disableCertificateVerification() {
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            @Override
            public void checkClientTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
                    throws CertificateException {
            }

            @Override
            public void checkServerTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
                    throws CertificateException {
            }
        }};

        try {
            SSLContext sslContext = SSLContext.getInstance("TLSv1.3");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(
                    (hostname, session) -> hostname.equals("IPADDRESS"));
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            logger.error("Error disabling certificate verification", e);
        }
    }

    public String get(String uri) {
        HttpEntity<String> requestEntity = new HttpEntity<>("", headers);
        ResponseEntity<String> responseEntity = rest.exchange(uri, HttpMethod.GET, requestEntity, String.class);
        return responseEntity.getBody();
    }

    public String post(String uri, String json) {
        HttpEntity<String> requestEntity = new HttpEntity<>(json, headers);
        ResponseEntity<String> responseEntity = rest.exchange(uri, HttpMethod.POST, requestEntity, String.class);
        return responseEntity.getBody();
    }

    public String post(String uri, String json, String username, String password) {
        String plainCreds = String.format(S_S, username, password);
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.getEncoder().encode(plainCredsBytes);
        String base64Creds = Arrays.toString(base64CredsBytes);
        HttpHeaders headers = addDefaultHeaders();
        headers.add(AUTHORIZATION, BASIC + base64Creds);
        HttpEntity<String> requestEntity = new HttpEntity<>(json, headers);
        ResponseEntity<String> responseEntity = rest.exchange(uri, HttpMethod.POST, requestEntity, String.class);
        return responseEntity.getBody();
    }

    private HttpHeaders addDefaultHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");
        return headers;

    }

    public String getWithDataAndBasicAuth(String uri, String json, String username, String password) {
        String plainCreds = String.format(S_S, username, password);
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.getEncoder().encode(plainCredsBytes);
        String base64Creds = Arrays.toString(base64CredsBytes);
        HttpHeaders headers = addDefaultHeaders();
        headers.add(AUTHORIZATION, BASIC + base64Creds);
        HttpEntity<String> requestEntity = new HttpEntity<>(json, headers);
        ResponseEntity<String> responseEntity = rest.exchange(uri, HttpMethod.GET, requestEntity, String.class);
        return responseEntity.getBody();
    }

    public String put(String uri, String json) {
        HttpEntity<String> requestEntity = new HttpEntity<>(json, headers);
        ResponseEntity<String> responseEntity = rest.exchange(uri, HttpMethod.PUT, requestEntity, String.class);
        return responseEntity.getBody();
    }

    public String putWithBasicAuth(String uri, String json, String username, String password) {
        String plainCreds = String.format(S_S, username, password);
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.getEncoder().encode(plainCredsBytes);
        String base64Creds = Arrays.toString(base64CredsBytes);
        HttpHeaders headers = addDefaultHeaders();
        headers.add(AUTHORIZATION, BASIC + base64Creds);
        HttpEntity<String> requestEntity = new HttpEntity<>(json, headers);
        ResponseEntity<String> responseEntity = rest.exchange(uri, HttpMethod.PUT, requestEntity, String.class);
        return responseEntity.getBody();
    }

    public void delete(String uri, String username, String password) {
        String plainCreds = String.format(S_S, username, password);
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.getEncoder().encode(plainCredsBytes);
        String base64Creds = Arrays.toString(base64CredsBytes);
        HttpHeaders headers = addDefaultHeaders();
        headers.add(AUTHORIZATION, BASIC + base64Creds);
        HttpEntity<String> requestEntity = new HttpEntity<>("", headers);
        rest.exchange(uri, HttpMethod.DELETE, requestEntity, String.class);
    }

}