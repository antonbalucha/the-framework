package framework.security;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.JdbcUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SaltedJdbcRealm extends JdbcRealm {

	private static final Logger logger = LoggerFactory.getLogger(SaltedJdbcRealm.class);

	public SaltedJdbcRealm() {
		setSaltStyle(SaltStyle.COLUMN);
	}

	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

		if (authenticationToken == null) {
			logger.error("Parameter 'authenticationToken' is null");
			throw new RuntimeException("Parameter 'authenticationToken' is null");
		} else if (!(authenticationToken instanceof UsernamePasswordToken)) {
			logger.error("Parameter 'authenticationToken' is not instance of 'UsernamePasswordToken'");
			throw new RuntimeException("Parameter 'authenticationToken' is not instance of 'UsernamePasswordToken'");
		} else {
			String userName = ((UsernamePasswordToken) authenticationToken).getUsername();

			if (StringUtils.isBlank(userName)) {
				logger.error("Parameter 'userName' of entered 'authenticationToken' is null or empty");
				throw new AccountException("Parameter 'userName' of entered 'authenticationToken' is null or empty");
			} else {
				Connection connection = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
				
				SimpleAuthenticationInfo info = null;
				
				try {
					connection = dataSource.getConnection();
					
					String password = null;
					String salt = null;
					
					ps = connection.prepareStatement(authenticationQuery);
					ps.setString(1, userName);
					rs = ps.executeQuery();

					// loop over results - although we are only expecting one result, since userName should be unique
					boolean alreadyfoundResult = false;

					while (rs.next()) {

						// check to ensure only one row is processed
						if (alreadyfoundResult) {
							logger.error("More than one user row found for user with userName = '" + userName + "'. User name must be unique.");
							throw new AuthenticationException("More than one user row found for user with userName = '" + userName + "'. User name must be unique.");
						}

						password = rs.getString(1);
						salt = rs.getString(2);

						alreadyfoundResult = true;
					}

					if (StringUtils.isBlank(password)) {
						logger.error("For user with 'userName' = '" + userName + "' is not correctly created account. Database value 'password' is blank.");
						throw new UnknownAccountException("For user with 'userName' = '" + userName + "' is not correctly created account. Database value 'password' is blank.");
					} else if (StringUtils.isBlank(salt)) {
						logger.error("For user with 'userName' = '" + userName + "' is not correctly created account. Database value 'salt' is blank.");
						throw new UnknownAccountException("For user with 'userName' = '" + userName + "' is not correctly created account. Database value 'salt' is blank.");
					} else {
						info = new SimpleAuthenticationInfo(userName, password, ByteSource.Util.bytes(salt), getName());
					}
				} catch (SQLException e) {
					logger.error("SQLException: " + e.getMessage() + "; SQL error while selecting data for user with 'userName' = '" + userName + "'");
					throw new AuthenticationException(e);
				} finally {
					JdbcUtils.closeResultSet(rs);
					JdbcUtils.closeStatement(ps);
					JdbcUtils.closeConnection(connection);
				}

				return info;
			}
		}
	}
}
