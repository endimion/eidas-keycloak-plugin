package gr.uagean.rest;

import org.keycloak.models.KeycloakSession;
import org.keycloak.services.resource.RealmResourceProvider;

public class SpResourceProvider implements RealmResourceProvider {

	private KeycloakSession session;

	public SpResourceProvider(KeycloakSession session) {
        this.session = session;
    }

	@Override
	public Object getResource() {
		return new SpRestResource(session);
	}
	
	@Override
	public void close() {
	}

}
