/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.uagean.authenticators;

import java.net.URI;
import java.net.URISyntaxException;
import org.jboss.logging.Logger;
import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.authentication.Authenticator;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.keycloak.sessions.AuthenticationSessionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


/**
 *
 * @author nikos
 
 
 */
public class TestEidasAuthenticator implements Authenticator {

//    protected ParameterService paramServ = new ParameterServiceImpl();
    private static Logger LOG = Logger.getLogger(TestEidasAuthenticator.class);

    @Override
    public void authenticate(AuthenticationFlowContext context) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            UserModel user = context.getUser();
            if (user != null) {
                //if the user is already loged in, in the keycloak context do nothing
                LOG.info("current user: " + user.getUsername() + ", id: " + user.getId());
            } else {
                //if there is no user logged in, do some stuff and then present the login screen
                LOG.info("current user: null");
                AuthenticationSessionModel authSession = context.getAuthenticationSession();
                final String baseUrl = "https://3d3a51e6.ngrok.io" + "/request";
                URI uri = new URI(baseUrl);
                ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
                //continue
                LOG.info("Reply::");
                LOG.info(result.getBody());

                authSession.setAuthNote("SAML_REQUEST", result.getBody());
                authSession.setAuthNote("EIDAS_NODE_URL", "https://pre.eidas.gov.gr/httpEidasNode/ServiceProvider");

                LOG.info("EidasAuthenticator authenticate will continue now");
            }

            context.success();
        } catch (URISyntaxException ex) {
            LOG.error(ex.getMessage());
        }
    }

    @Override
    public void action(AuthenticationFlowContext afc) {
    }

    @Override
    public boolean requiresUser() {
        return false;
    }

    @Override
    public boolean configuredFor(KeycloakSession ks, RealmModel rm, UserModel um) {
        return true;
    }

    @Override
    public void setRequiredActions(KeycloakSession ks, RealmModel rm, UserModel um) {
    }

    @Override
    public void close() {

    }

}
