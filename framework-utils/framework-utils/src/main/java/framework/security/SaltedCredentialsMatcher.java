package framework.security;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import framework.utils.StringUtils;

public class SaltedCredentialsMatcher implements CredentialsMatcher {

	private static final Logger logger = LoggerFactory.getLogger(SaltedCredentialsMatcher.class);

	public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {
		if (authenticationToken == null) {
			logger.error("Parameter 'authenticationToken' is null");
			throw new RuntimeException("Parameter 'authenticationToken' is null");
		} else if (authenticationInfo == null) {
			logger.error("Parameter 'authenticationInfo' is null");
			throw new RuntimeException("Parameter 'authenticationInfo' is null");
		} else {

			UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
			String uiUserName = token.getUsername();
			String uiPlainPassword = StringUtils.toString(token.getPassword());

			SimpleAuthenticationInfo info = (SimpleAuthenticationInfo) authenticationInfo;
			String dbUserName = StringUtils.toString(info.getPrincipals().getPrimaryPrincipal());
			String dbHashedPassword = StringUtils.toString(info.getCredentials());
			String dbSalt = PasswordUtils.getSalt(info.getCredentialsSalt());

			if (!uiUserName.equals(dbUserName)) {
				return false;
			} else {
				String uiHashedPassword = PasswordUtils.getHash(uiPlainPassword, dbSalt);
				return uiHashedPassword.equals(dbHashedPassword);
			}
		}
	}
}
