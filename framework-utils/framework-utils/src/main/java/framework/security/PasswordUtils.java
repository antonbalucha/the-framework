package framework.security;

import java.security.SecureRandom;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.apache.shiro.util.ByteSource;

public class PasswordUtils {

	/** GENERAL CONFIGURATION */
	
	private static final int DEFAULT_NUMBER_OF_ITERATIONS = 10000;
	private static final int DEFAULT_NUMBER_OF_SEEDS = 512;
	
	/** GENERATE RANDOM SALT */
	
	public static final String generateSalt() {
		return new String(Base64.encode(new SecureRandom().generateSeed(DEFAULT_NUMBER_OF_SEEDS)));
	}
	
	/** GET HASH FROM PASSWORD */

	public static final String getHash(String password) {
		return getHash(password, generateSalt());
	}
	
	public static final String getHash(String password, String salt) {
		return getHash(password, salt, DEFAULT_NUMBER_OF_ITERATIONS);
	}
	
	public static final String getHash(String password, String salt, int numberOfIterations) {
		return new String(getSha512Hash(password, salt, numberOfIterations).toBase64());
	}
	
	public static final Sha512Hash getSha512Hash(String password) {
		return getSha512Hash(password, generateSalt());
	}
	
	public static final Sha512Hash getSha512Hash(String password, String salt) {
		return getSha512Hash(password, salt, DEFAULT_NUMBER_OF_ITERATIONS);
	}
	
	public static final Sha512Hash getSha512Hash(String password, String salt, int numberOfIterations) {
		return new Sha512Hash(password, salt, numberOfIterations);
	}
	
	/** METHODS WHICH RETURNS SALT */
	
	public static final String getSalt(Sha512Hash hash) {
		return getSalt(hash.getSalt());
	}
	
	public static final String getSalt(ByteSource hash) {
		return new String(hash.getBytes());
	}
}
