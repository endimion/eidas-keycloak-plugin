/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.uagean.authenticators;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;
import org.jboss.logging.Logger;
import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.authentication.Authenticator;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.keycloak.models.utils.KeycloakModelUtils;
import org.springframework.util.StringUtils;

/**
 *
 * @author nikos
 */
public class AfterEidasAuthenticator implements Authenticator {

//    protected ParameterService paramServ = new ParameterServiceImpl();
    private static Logger LOG = Logger.getLogger(AfterEidasAuthenticator.class);

    private ObjectMapper mapper;

    @Override
    public void authenticate(AuthenticationFlowContext context) {
        try {
            KeycloakSession session = context.getSession();
            RealmModel realm = context.getRealm();
            mapper = new ObjectMapper();

            LOG.info("reached after-eidas-authenticator");

            String parsedEidasResponse = context.getHttpRequest().getUri().getQueryParameters().getFirst("eidas_response");
            if (StringUtils.isEmpty(parsedEidasResponse)) {
                LOG.info("no saml response found in assertions, authentication will proceed with attempted");
                LOG.info("will continue with attempted");
                context.attempted();
                return;
            }

            LOG.info("Got eIDAS response " + parsedEidasResponse);
            Map<String, String> attributesMap = mapper.readValue(parsedEidasResponse, Map.class);

            String userName = attributesMap.get("personIdentifier");
            UserModel user = KeycloakModelUtils.findUserByNameOrEmail(session, realm, userName);
            if (user == null) {
                user = session.users().addUser(realm, userName);
            } else {
                //TODO update user

            }

            user.setEnabled(true);
            user.setFirstName(attributesMap.get("firstName"));
            user.setLastName(attributesMap.get("familyName"));
            user.setEmail(userName + "@eidas.gr");
            user.setEmailVerified(true);

            user.setSingleAttribute("eidas-familyName", attributesMap.get("familyName"));
            user.setSingleAttribute("eidas-firstName", attributesMap.get("firstName"));
            user.setSingleAttribute("eidas-dateOfBirth", attributesMap.get("dateOfBirth"));
            user.setSingleAttribute("eidas-personIdentifier", attributesMap.get("personIdentifier"));
            user.setSingleAttribute("eidas-birthName", attributesMap.get("birthName"));
            user.setSingleAttribute("eidas-placeOfBirth", attributesMap.get("placeOfBirth"));
            user.setSingleAttribute("eidas-currentAddress", attributesMap.get("currentAddress"));
            user.setSingleAttribute("eidas-gender", attributesMap.get("gender"));
            user.setSingleAttribute("eidas-loa", attributesMap.get("loa"));

            context.setUser(user);
            LOG.info("user was set ok! " + user.getUsername());
            context.success();
        } catch (IOException ex) {
            LOG.error(ex.getMessage());
            //TODO failure is better?
            LOG.info("will continue with attempted");
            context.attempted();
        }
    }

    @Override
    public void action(AuthenticationFlowContext afc) {
        LOG.info("AFTER eidas actionImp called");
        LOG.info(afc.getUser());
        if (afc.getUser() != null) {
            afc.success();
        } else {
            afc.attempted();
        }
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
