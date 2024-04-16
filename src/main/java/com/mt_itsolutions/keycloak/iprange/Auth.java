package com.mt_itsolutions.keycloak.iprange;

import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.authentication.authenticators.conditional.ConditionalAuthenticator;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;

@Slf4j
public class Auth implements ConditionalAuthenticator {

	@Override
	public boolean matchCondition(AuthenticationFlowContext authenticationFlowContext) {
		var ipRange = getIpRange(authenticationFlowContext);
		log.debug("Auth.matchCondition: IP range {}", ipRange);

		String remoteAddress = authenticationFlowContext.getConnection().getRemoteAddr();
		log.debug("Auth.matchCondition: Remote address: {}", remoteAddress);

		if (ipRange == null || ipRange.isBlank()) {
			log.warn("Auth.matchCondition: No IP range configured");
			return false;
		}

		return IPUtil.checkIPWithMask(remoteAddress, ipRange);
	}

	@Override
	public void action(AuthenticationFlowContext context) {
		// No action
	}

	@Override
	public boolean requiresUser() {
		return true;
	}

	@Override
	public void setRequiredActions(KeycloakSession session, RealmModel realm, UserModel user) {
		// No required actions
	}

	@Override
	public void close() {
		// No resources to close
	}

	private String getIpRange(AuthenticationFlowContext context) {
		return context.getAuthenticatorConfig().getConfig().get(AuthFactory.AUTH_IP_RANGE);
	}

}
