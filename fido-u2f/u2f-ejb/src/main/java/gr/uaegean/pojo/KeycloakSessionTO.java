/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.uaegean.pojo;

import java.io.Serializable;

/**
 *
 * @author nikos
 */
public class KeycloakSessionTO implements Serializable {

    /*
     redirectUri.addParam(OAuth2Constants.RESPONSE_TYPE, mcc.get("response_typ").toString());
        redirectUri.addParam(OAuth2Constants.CLIENT_ID, mcc.get("cliend_it").toString());
        redirectUri.addParam(OAuth2Constants.REDIRECT_URI, clientRedirectUri);
        redirectUri.addParam(OAuth2Constants.STATE, mcc.get("state").toString());
        redirectUri.addParam(OAuth2Constants.SCOPE, mcc.gets("scope").toString());
     */
    private String session;
    private String responseType;
    private String clientId;
    private String clientRedirectUri;
    private String state;
    private String scope;

    public KeycloakSessionTO() {
    }

    public KeycloakSessionTO(String session, String responseType, String clientId, String clientRedirectUri, String state, String scope) {
        this.session = session;
        this.responseType = responseType;
        this.clientId = clientId;
        this.clientRedirectUri = clientRedirectUri;
        this.state = state;
        this.scope = scope;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientRedirectUri() {
        return clientRedirectUri;
    }

    public void setClientRedirectUri(String clientRedirectUri) {
        this.clientRedirectUri = clientRedirectUri;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    @Override
    public String toString() {
        return "KeycloakSessionTO{" + "session=" + session + ", responseType=" + responseType + ", clientId=" + clientId + ", clientRedirectUri=" + clientRedirectUri + ", state=" + state + ", scope=" + scope + '}';
    }

}
