package framework.utils;

import static framework.security.PasswordUtils.*;

import org.apache.shiro.crypto.hash.Sha512Hash;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PasswordUtilsTest {

	private static final Logger logger = LoggerFactory.getLogger(PasswordUtilsTest.class);
	
	@Test
	public void testGenerateSalt() {
		Assert.assertNotNull(generateSalt());
	}
	
	@Test
	public void testSalt() {
		String salt = generateSalt();
		Sha512Hash sha512Hash = getSha512Hash("password", salt, 10000);
		String salt2 = getSalt(sha512Hash);
		Assert.assertEquals("Salts has to be equals", salt, salt2);
	}
	
	@Test
	public void testSalt2() {
		String plainPassword = "password";
		
		Sha512Hash hash1 = getSha512Hash(plainPassword);
		String hashedPassword1 = hash1.toBase64();
		String salt1 = getSalt(hash1);
		
		logger.debug("hashedPassword1: " + hashedPassword1);
		logger.debug("salt1: " + salt1);
		
		Sha512Hash hash2 = getSha512Hash(plainPassword, salt1);
		String hashedPassword2 = hash2.toBase64();
		String salt2 = getSalt(hash2);
		
		logger.debug("hashedPassword2: " + hashedPassword2);
		logger.debug("salt2: " + salt2);
		
		Assert.assertEquals("Salts should be equal", salt1, salt2);
		Assert.assertEquals("Hashed passwords should be equal", hashedPassword1, hashedPassword2);
	}
}
