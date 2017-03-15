package framework.utils.translations;

/**
 * Class contains list of error keys/messages provided to UI/send to user via API.  
 */
public class UIErrorKey {

	/** Entered limit value is invalid; must not be null and must be equal or greater then one */
	public static final String INVALID_LIMIT_VALUE = "INVALID_LIMIT_VALUE";
	
	/** Entered offset value is invalid; must not be null and must be equal or greater then zero */
	public static final String INVALID_OFFSET_VALUE = "INVALID_OFFSET_VALUE";
	
	/** User was successfully logged in */
	public static final String USER_SUCCESSFULLY_LOGGED_IN = "USER_SUCCESSFULLY_LOGGED_IN";
	
	/** User was successfully logged out */
	public static final String USER_SUCCESSFULLY_LOGGED_OUT = "USER_SUCCESSFULLY_LOGGED_OUT";
	
	/** Credentials are not valid */
	public static final String CREDENTIALS_NOT_VALID = "CREDENTIALS_NOT_VALID";

	/** Request is empty */
	public static final String REQUEST_IS_EMPTY = "REQUEST_IS_EMPTY";
	
	/** User name is empty */
	public static final String USER_NAME_IS_EMPTY = "USER_NAME_IS_EMPTY";
	
	/** E-mail is empty */
	public static final String EMAIL_IS_EMPTY = "EMAIL_IS_EMPTY";
	
	/** E-mail has invalid form */
	public static final String EMAIL_HAS_INVALID_FORM = "EMAIL_HAS_INVALID_FORM";
	
	/** Password is empty */
	public static final String PASSWORD_IS_EMPTY = "PASSWORD_IS_EMPTY";
	
	/** Entered user name is already registered */
	public static final String USER_NAME_ALREADY_REGISTERED = "USER_NAME_ALREADY_REGISTERED";
	
	/** Entered user e-mail is already registered */
	public static final String USER_EMAIL_ALREADY_REGISTERED = "USER_EMAIL_ALREADY_REGISTERED";
	
	/** User was successfully registered */
	public static final String USER_SUCCESSFULLY_REGISTERED = "USER_SUCCESSFULLY_REGISTERED";
	
	/** Activation token is empty */
	public static final String ACTIVATION_TOKEN_IS_EMPTY = "ACTIVATION_TOKEN_IS_EMPTY";
	
	/** Activation of user was successful */
	public static final String ACTIVATION_SUCCESSFUL = "ACTIVATION_SUCCESSFUL";
	
	/** Activation of user was not successful; please try again later or contact our administrators */
	public static final String ACTIVATION_NOT_SUCCESSFUL = "ACTIVATION_NOT_SUCCESSFUL";
	
	/** Inactivation of user was successful */
	public static final String INACTIVATION_SUCCESSFUL = "INACTIVATION_SUCCESSFUL";
	
	/** Inactivation of user was not successful; please try again later or contact our administrators */
	public static final String INACTIVATION_NOT_SUCCESSFUL = "INACTIVATION_NOT_SUCCESSFUL";

	/** Banning of user was successful */
	public static final String BANNING_SUCCESSFUL = "BANNING_SUCCESSFUL";
	
	/** Banning of user was not successful; please try again later or contact our administrators */
	public static final String BANNING_NOT_SUCCESSFUL = "BANNING_NOT_SUCCESSFUL";

	/** Deleting of user was successful */
	public static final String DELETING_SUCCESSFUL = "DELETING_SUCCESSFUL";
	
	/** Deleting of user was not successful; please try again later or contact our administrators */
	public static final String DELETING_NOT_SUCCESSFUL = "DELETING_NOT_SUCCESSFUL";

	/** Users language is empty */
	public static final String USERS_LANGUAGE_IS_EMPTY = "USERS_LANGUAGE_IS_EMPTY";
	
	/** Language is not supported */
	public static final String LANGUAGE_IS_NOT_SUPPORTED = "LANGUAGE_IS_NOT_SUPPORTED";
	
	/** Language successfully set */
	public static final String LANGUAGE_SUCCESSFULLY_SET = "LANGUAGE_SUCCESSFULLY_SET";
	
	/** Unknown problem with authentication; please try again later or contact our administrators */
	public static final String UNKNOWN_PROBLEM_WITH_AUTHENTICATION = "UNKNOWN_PROBLEM_WITH_AUTHENTICATION";
	
	/** Your account was locked */
	public static final String LOCKED_ACCOUNT = "LOCKED_ACCOUNT";
	
	/** Entered parameter 'id' is not valid. Parameter 'id' must not be null and must be greater than 0. */
	public static final String INVALID_PARAMETER_ID = "INVALID_PARAMETER_ID";
	
	/** Bad request; please try it again later or contact our administrators */
	public static final String BAD_REQUEST = "BAD_REQUEST";
	
	/** Internal server error; please try again later or contact our administrators */
	public static final String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";
	
	/** Alpha2 code used by countries is empty */
	public static final String ALPHA2_CODE_IS_EMPTY = "ALPHA2_CODE_IS_EMPTY";
	
	/** Alpha2 code length is not valid; length must be 2 characters */
	public static final String ALPHA2_CODE_LENGTH_IS_NOT_NVALID = "ALPHA2_CODE_LENGTH_IS_INVALID";
	
	/** Alpha3 code used by countries is empty */
	public static final String ALPHA3_CODE_IS_EMPTY = "ALPHA3_CODE_IS_EMPTY";
	
	/** Alpha3 code length is not valid; length must be 3 characters */
	public static final String ALPHA3_CODE_LENGTH_IS_NOT_VALID = "ALPHA3_CODE_LENGTH_IS_INVALID";
	
	/** Country code is empty */
	public static final String COUNTRY_CODE_IS_EMPTY = "COUNTRY_CODE_IS_EMPTY";
}
