package gr.uagean.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gr.uaegean.pojo.EidasResponse;
import gr.uaegean.pojo.KeycloakSessionTO;
import gr.uaegean.services.PropertiesService;
import gr.uaegean.singleton.MemcacheUtils;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import net.spy.memcached.MemcachedClient;
import org.keycloak.OAuth2Constants;
import org.keycloak.models.KeycloakSession;
import org.keycloak.protocol.oidc.utils.OIDCRedirectUriBuilder;
import org.keycloak.protocol.oidc.utils.OIDCResponseMode;
import org.keycloak.services.managers.AppAuthManager;
import org.keycloak.services.managers.AuthenticationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class SpRestResource {

    private final KeycloakSession session;
    @SuppressWarnings("unused")
    private final AuthenticationManager.AuthResult auth;

    private PropertiesService propServ;

    private final static Logger LOG = LoggerFactory.getLogger(SpRestResource.class);

    private MemcachedClient mcc;

    public SpRestResource(KeycloakSession session) {
        this.session = session;
        this.auth = new AppAuthManager().authenticateBearerToken(session, session.getContext().getRealm());
        try {
            this.propServ = new PropertiesService();
        } catch (IOException ex) {
            LOG.error("error reading properties");
            LOG.error(ex.getMessage());
        }
    }

    @GET
    @Path("metadata")
    @Produces("application/xml")
    public Response getEidasMetadata() {
        try {
            RestTemplate restTemplate = new RestTemplate();

            final String baseUrl = this.propServ.getProp("proxy-url") + "/metadata";
            URI uri = new URI(baseUrl);
            ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
            LOG.info("Response: " + result.getBody());

            return Response
                    .status(Response.Status.OK)
                    .entity(result.getBody())
                    .build();
        } catch (URISyntaxException ex) {
            LOG.error(ex.getMessage());
        }
        return null;
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN, MediaType.APPLICATION_FORM_URLENCODED})
    @Path("eidasResponse")
    public Response processEidasResponse(@Context HttpServletRequest httpServletRequest,
            @Context HttpServletResponse httpServletResponse, @FormParam("SAMLResponse") String samlResponse) throws URISyntaxException, JsonProcessingException, IOException {

        String remoteAddress = httpServletRequest.getRemoteAddr();
        LOG.info("remoteAddress is " + remoteAddress);
        LOG.info("got the samlResponse from eIDAS" + samlResponse);

        // tested with:: curl -i -d 'saml=tok123' -X POST http://localhost:8080/auth/realms/test/sp/eidasResponse
        // see also keycloak OIDCLoginProtocolc lass method authenticated
        OIDCResponseMode responseMode = OIDCResponseMode.QUERY;

        String proceedWithAuthenticationUrl = this.propServ.getProp("proceed-auth");
        OIDCRedirectUriBuilder redirectUri = OIDCRedirectUriBuilder.fromUri(proceedWithAuthenticationUrl, responseMode);

//        String clientId = this.propServ.getProp("client-id");
//        LOG.info("ClientId:: " + clientId);
//        String clientRedirectUri = this.propServ.getProp("client-redirect-uri");
//        LOG.info("Will finally redirect to:: " + clientRedirectUri);
//        String state = "1*VSCHAR"; //TODO persist state between calls?
//        String oidcScopes = this.propServ.getProp("oidc-scopes");
//        LOG.info("Found the following client scopes:: " + oidcScopes);
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = this.propServ.getProp("proxy-url") + "/processResponse";
        URI uri = new URI(baseUrl);
        EidasResponse receivedFromEidas = new EidasResponse(samlResponse, remoteAddress);
        Map<String, String> parsedResponse = restTemplate.postForObject(uri, receivedFromEidas, Map.class);

// ########################################################
        this.mcc = MemcacheUtils.getCache();
        // retrieve from the cache the client attributes
        String keycloakSession = parsedResponse.get("keycloakSession");
        LOG.info("I got session:" + keycloakSession);
        KeycloakSessionTO ksTo = (KeycloakSessionTO) mcc.get(keycloakSession);
        LOG.info(ksTo.toString());

        redirectUri.addParam(OAuth2Constants.RESPONSE_TYPE, ksTo.getResponseType());
        redirectUri.addParam(OAuth2Constants.CLIENT_ID, ksTo.getClientId());
        redirectUri.addParam(OAuth2Constants.REDIRECT_URI, ksTo.getClientRedirectUri());
        redirectUri.addParam(OAuth2Constants.STATE, ksTo.getState());
        redirectUri.addParam(OAuth2Constants.SCOPE, ksTo.getScope());

        ObjectMapper mapper = new ObjectMapper();

        redirectUri.addParam("eidas_response", mapper.writeValueAsString(parsedResponse));

        LOG.info("eidasResponse concluded ok");

        return redirectUri.build();
    }

}
