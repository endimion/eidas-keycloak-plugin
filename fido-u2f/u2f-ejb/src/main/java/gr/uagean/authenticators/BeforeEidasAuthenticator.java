/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.uagean.authenticators;

import gr.uaegean.pojo.Country;
import gr.uaegean.pojo.KeycloakSessionTO;
import gr.uaegean.singleton.MemcacheUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Response;
import net.spy.memcached.MemcachedClient;
import org.jboss.logging.Logger;
import org.keycloak.OAuth2Constants;
import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;

/**
 *
 * @author nikos
 */
public class BeforeEidasAuthenticator extends AbstractEidasAuthenticator {

//    protected ParameterService paramServ = new ParameterServiceImpl();
    private static final Logger LOG = Logger.getLogger(BeforeEidasAuthenticator.class);
    private MemcachedClient mcc;

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

    @Override
    public void authenticateImpl(AuthenticationFlowContext context) {
        try {
            KeycloakSession session = context.getSession();
            RealmModel realm = context.getRealm();

            this.mcc = MemcacheUtils.getCache();
            // grab oidc params
            String response_type = context.getHttpRequest().getUri().getQueryParameters().getFirst(OAuth2Constants.RESPONSE_TYPE);
            String client_id = context.getHttpRequest().getUri().getQueryParameters().getFirst(OAuth2Constants.CLIENT_ID);
            String redirect_uri = context.getHttpRequest().getUri().getQueryParameters().getFirst(OAuth2Constants.REDIRECT_URI);
            String state = context.getHttpRequest().getUri().getQueryParameters().getFirst(OAuth2Constants.STATE);
            String scope = context.getHttpRequest().getUri().getQueryParameters().getFirst(OAuth2Constants.SCOPE);

            String usersCountry = context.getHttpRequest().getUri().getQueryParameters().getFirst("country");

            int expiresInSec = 300;

            //Transfer Object that will be cached
            KeycloakSessionTO ksTO = new KeycloakSessionTO(state, response_type, client_id, redirect_uri, state, scope);
            LOG.info("will add with key:" + state + " object " + ksTO.toString());
            mcc.add(state, expiresInSec, ksTO);

//
//TODO fix supported countries list
            List<Country> countries = new ArrayList<>();
            countries.add(new Country("greece", "GR", true));
            countries.add(new Country("spain", "ES", true));

            Response challenge = context.form()
                    .setAttribute("countries", countries)
                    .setAttribute("bakend", super.propServ.getProp("backendserver"))
                    .setAttribute("eidas", super.propServ.getProp("eidas"))
                    .setAttribute("state", state) //keycloak-session
                    .setAttribute("country", usersCountry)
                    .createForm("wayf.ftl");
            LOG.info("will respond with force challenge");
//force challenge means that it will not proceed to other authentication providers
            context.forceChallenge(challenge);
        } catch (IOException ex) {
            LOG.error(ex.getMessage());
        }
    }

    @Override
    public void actionImpl(AuthenticationFlowContext afc) {
        LOG.info("before eidas actionImp called");
    }

}
