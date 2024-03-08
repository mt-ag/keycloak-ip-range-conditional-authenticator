package com.mt_itsolutions.keycloak.iprange;

import java.util.List;
import org.keycloak.Config;
import org.keycloak.authentication.authenticators.conditional.ConditionalAuthenticator;
import org.keycloak.authentication.authenticators.conditional.ConditionalAuthenticatorFactory;
import org.keycloak.models.AuthenticationExecutionModel;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.provider.ProviderConfigProperty;
import org.keycloak.provider.ProviderConfigurationBuilder;

public class AuthFactory implements ConditionalAuthenticatorFactory {

	public static final String AUTH_PROVIDER_ID = "ip-range";
	public static final String AUTH_IP_RANGE = "ip-range-configuration";

	private static final ConditionalAuthenticator AUTH_INSTANCE = new Auth();


	@Override
	public String getDisplayType() {
		return "Condition - In IP range";
	}

	@Override
	public boolean isConfigurable() {
		return true;
	}

	@Override
	public AuthenticationExecutionModel.Requirement[] getRequirementChoices() {
		return REQUIREMENT_CHOICES;
	}

	@Override
	public boolean isUserSetupAllowed() {
		return false;
	}

	@Override
	public String getHelpText() {
		return "This authenticator checks if the user's IP address is within specified ranges.";
	}

	@Override
	public List<ProviderConfigProperty> getConfigProperties() {
		return ProviderConfigurationBuilder.create()
			.property()
			.name(AUTH_IP_RANGE)
			.label("IP Ranges")
			.type(ProviderConfigProperty.STRING_TYPE)
			.helpText("IP ranges in CIDR notation.")
			.add()
			.build();
	}

	@Override
	public ConditionalAuthenticator getSingleton() {
		return AUTH_INSTANCE;
	}

	@Override
	public void init(Config.Scope scope) {
		// No initialization required
	}

	@Override
	public void postInit(KeycloakSessionFactory keycloakSessionFactory) {
		// No post initialization required
	}

	@Override
	public void close() {
		// No resources to close
	}

	@Override
	public String getId() {
		return AUTH_PROVIDER_ID;
	}
}
