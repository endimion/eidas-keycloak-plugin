package gr.uaegean.providers;

import java.util.Locale;
import java.util.Properties;
import javax.ws.rs.core.UriBuilder;
import org.jboss.logging.Logger;
import org.keycloak.forms.login.LoginFormsPages;
import org.keycloak.forms.login.freemarker.FreeMarkerLoginFormsProvider;
import org.keycloak.models.KeycloakSession;
import org.keycloak.theme.FreeMarkerUtil;
import org.keycloak.theme.Theme;

public class EidasLoginFormsProvider extends FreeMarkerLoginFormsProvider {

    private static final Logger LOG = Logger.getLogger(EidasLoginFormsProvider.class);

    public EidasLoginFormsProvider(KeycloakSession session, FreeMarkerUtil freeMarker) {
        super(session, freeMarker);
    }

    @Override
    protected void createCommonAttributes(Theme theme, Locale locale, Properties messagesBundle, UriBuilder baseUriBuilder, LoginFormsPages page) {
        super.createCommonAttributes(theme, locale, messagesBundle, baseUriBuilder, page);
        LOG.info("will set attributes");
        if (authenticationSession != null) {
            String saml = authenticationSession.getAuthNote("SAML_REQUEST");
            String eidas = authenticationSession.getAuthNote("EIDAS_NODE_URL");
            attributes.put("saml", saml);
            attributes.put("eidasUrl", eidas);
            LOG.info(" eidasUrl " + eidas);
            LOG.info(" SAML" + saml);

            LOG.info("EidasLoginFormsProvider:: Create Common Attributes Called");

        }
    }

}
