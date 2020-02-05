/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.uagean.authenticators;

import gr.uaegean.services.PropertiesService;
import java.io.IOException;
import org.jboss.logging.Logger;
import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.authentication.AuthenticationFlowError;
import org.keycloak.authentication.Authenticator;

/**
 *
 * @author nikos
 */
public abstract class AbstractEidasAuthenticator implements Authenticator {

//    protected ParameterService paramServ = new ParameterServiceImpl();
    private static final Logger LOG = Logger.getLogger(AbstractEidasAuthenticator.class);

    protected PropertiesService propServ;

    protected abstract void actionImpl(AuthenticationFlowContext context);

    protected abstract void authenticateImpl(AuthenticationFlowContext context);

    public void initServices() throws IOException {
        LOG.info("will inti properites");
        this.propServ = new PropertiesService();
    }

    @Override
    public void action(AuthenticationFlowContext context) {
        actionImpl(context);
    }

    @Override
    public void authenticate(AuthenticationFlowContext context) {

        try {
            initServices();
            authenticateImpl(context);
        } catch (IOException ex) {
            LOG.error("error reading properties");
            LOG.error(ex.getMessage());
            // TODO maybe this should be failure?
            context.failure(AuthenticationFlowError.INTERNAL_ERROR);
        }
    }

}
