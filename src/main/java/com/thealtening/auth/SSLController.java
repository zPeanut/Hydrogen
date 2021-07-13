/*
 * Copyright (C) 2019 TheAltening
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.thealtening.auth;

import javax.net.ssl.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

public final class SSLController {
    private static final TrustManager[] ALL_TRUSTING_TRUST_MANAGER;
    private static final HostnameVerifier ALTENING_HOSTING_VERIFIER;

    private final SSLSocketFactory allTrustingFactory;
    private final SSLSocketFactory originalFactory;

    private final HostnameVerifier originalHostVerifier;

    public SSLController() {
        SSLContext sc = null;
        try {
            sc = SSLContext.getInstance("SSL");
            sc.init(null, ALL_TRUSTING_TRUST_MANAGER, new SecureRandom());
        }catch (NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }

        this.allTrustingFactory = sc.getSocketFactory();
        this.originalFactory = HttpsURLConnection.getDefaultSSLSocketFactory();
        this.originalHostVerifier = HttpsURLConnection.getDefaultHostnameVerifier();
    }

    public void enableCertificateValidation() {
        updateCertificateValidation(this.originalFactory, this.originalHostVerifier);
    }

    public void disableCertificateValidation() {
        updateCertificateValidation(this.allTrustingFactory, ALTENING_HOSTING_VERIFIER);
    }

    private void updateCertificateValidation(SSLSocketFactory factory, HostnameVerifier hostnameVerifier) {
        HttpsURLConnection.setDefaultSSLSocketFactory(factory);
        HttpsURLConnection.setDefaultHostnameVerifier(hostnameVerifier);
    }

    static {
        ALL_TRUSTING_TRUST_MANAGER = new TrustManager[] {
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                    public void checkClientTrusted(X509Certificate[] certs, String authType) {}
                    public void checkServerTrusted(X509Certificate[] certs, String authType) {}
                }
        };

        ALTENING_HOSTING_VERIFIER = (hostname, session) ->
                hostname.equals("authserver.thealtening.com") || hostname.equals("sessionserver.thealtening.com");
    }
}
