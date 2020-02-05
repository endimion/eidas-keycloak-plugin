package gr.uagean.rest;

import org.keycloak.Config.Scope;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.services.resource.RealmResourceProvider;
import org.keycloak.services.resource.RealmResourceProviderFactory;

public class SpResourceProviderFactory implements RealmResourceProviderFactory {

	public static final String ID = "sp";

	@Override
	public String getId() {
		return ID;
	}
	
	@Override
	public RealmResourceProvider create(KeycloakSession session) {
		 return new SpResourceProvider(session);
	}

	@Override
	public void init(Scope config) {
	}

	@Override
	public void postInit(KeycloakSessionFactory factory) {
	}

	@Override
	public void close() {
	}

}
