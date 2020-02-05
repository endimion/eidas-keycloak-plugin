/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.uaegean.pojo;

/**
 *
 * @author nikos
 */
public class EidasResponse {

    String samlResponse;
    String remoteAddress;

    public EidasResponse(String samlResponse, String remoteAddress) {
        this.samlResponse = samlResponse;
        this.remoteAddress = remoteAddress;
    }

    public EidasResponse() {
    }

    public String getSamlResponse() {
        return samlResponse;
    }

    public void setSamlResponse(String samlResponse) {
        this.samlResponse = samlResponse;
    }

    public String getRemoteAddress() {
        return remoteAddress;
    }

    public void setRemoteAddress(String remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

}
