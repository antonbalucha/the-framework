"use strict";

var translations = JSON.stringify({
	
	// MESSAGES
	
	"ACTIVATION_NOT_SUCCESSFUL" : {
		"EN_US" : "Activation of user was not successful; please try again later or contact our administrators",
		"SK_SK" : "Aktivácia používateľa nebola úspešná; skúste prosím neskôr alebo kontaktujte administrátorov"
	},
	
	"ACTIVATION_SUCCESSFUL" : {
		"EN_US" : "Activation of user was successful",
		"SK_SK" : "Aktivácia používateľa bola úspešná"
	}, 
	
	"ACTIVATION_TOKEN_IS_EMPTY" : {
		"EN_US" : "Activation token is empty",
		"SK_SK" : "Aktivačný token je prázdny"
	}, 
	
	"ALPHA2_CODE_IS_EMPTY" : {
		"EN_US" : "Alpha2 code used by countries is empty",
		"SK_SK" : "Alpha2 kód je prázdny"
	},
	
	"ALPHA2_CODE_LENGTH_IS_NOT_VALID" : {
		"EN_US" : "Alpha2 code length is not valid; length must be 2 characters",
		"SK_SK" : "Počet znakov alpha2 kódu nie je správny; počet znakov musí byť 2"
	},
	
	"ALPHA3_CODE_IS_EMPTY" : {
		"EN_US" : "Alpha3 code used by countries is empty",
		"SK_SK" : "Alpha3 kód je prázdny"
	},
	
	"ALPHA3_CODE_LENGTH_IS_NOT_VALID" : {
		"EN_US" : "Alpha3 code length is not valid; length must be 3 characters",
		"SK_SK" : "Počet znakov alpha3 kódu nie je správny; počet znakov musí byť 3"
	},
	
	"BAD_REQUEST" : {
		"EN_US" : "Bad request; please try it again later or contact our administrators",
		"SK_SK" : "Nesprávny request; skúste prosím neskôr alebo kontaktujte administrátorov"
	},
	
	"BANNING_NOT_SUCCESSFUL" : {
		"EN_US" : "Banning of user was not successful; please try again later or contact our administrators",
		"SK_SK" : "Zakázanie používateľa nebolo úspešné; skúste prosím neskôr alebo kontaktujte administrátorov"
	},
	
	"BANNING_SUCCESSFUL" : {
		"EN_US" : "Banning of user was successful",
		"SK_SK" : "Zakázanie používateľa bolo úspešné"
	},
	
	"COUNTRY_CODE_IS_EMPTY" : {
		"EN_US" : "Country code is empty",
		"SK_SK" : "Kód krajiny je prázdny"
	},
	
	"CREDENTIALS_NOT_VALID" : {
		"EN_US" : "Credentials are not valid",
		"SK_SK" : "Prihlasovacie údaje nie sú správne"
	},
	
	"DELETING_NOT_SUCCESSFUL" : {
		"EN_US" : "Deleting of user was not successful; please try again later or contact our administrators",
		"SK_SK" : "Zmazanie používateľa nebolo úspešné; skúste prosím nekôr alebo kontaktujte administrátorov"
	},
	
	"DELETING_SUCCESSFUL" : {
		"EN_US" : "Deleting of user was successful",
		"SK_SK" : "Zmazanie používateľa bolo úspešné"
	},
	
	"EMAIL_HAS_INVALID_FORM" : {
		"EN_US" : "E-mail has invalid form",
		"SK_SK" : "E-mail nemá správnu formu"
	},
	
	"EMAIL_IS_EMPTY" : {
		"EN_US" : "E-mail is empty",
		"SK_SK" : "E-mail je prázdny"
	},
	
	"INTERNAL_SERVER_ERROR" : {
		"EN_US" : "Internal server error; please try again later or contact our administrators",
		"SK_SK" : "Neznámy problém na serveri; skúste prosím neskôr alebo kontaktuje administrátorov"
	},
	
	"INACTIVATION_NOT_SUCCESSFUL" : {
		"EN_US" : "Inactivation of user was not successful; please try again later or contact our administrators",
		"SK_SK" : "Deaktivácia používateľa nebola úspešná; skúste prosím neskôr alebo kontaktujte administrátorov"
	},
	
	"INACTIVATION_SUCCESSFUL" : {
		"EN_US" : "Inactivation of user was successful",
		"SK_SK" : "Deaktivácia používateľa bola úspešná"
	},
	
	"INVALID_LIMIT_VALUE" : {
		"EN_US" : "Entered limit value is invalid; must not be null and must be equal or greater then 1",
		"SK_SK" : "Zadaný limit (limit) nie je správny; nesmie byť prázdny a musí byť rovný alebo väčší ako 1"
	},

	"INVALID_OFFSET_VALUE" : {
		"EN_US" : "Entered offset value is invalid; must not be null and must be equal or greater then zero",
		"SK_SK" : "Zadaný odstup (offset) nie je správny; nesmie byť prázdny a musí byť rovný alebo väčší ako 0"
	},

	"INVALID_PARAMETER_ID" : {
		"EN_US" : "Entered parameter 'id' is not valid; must not be null and must be greater than 0",
		"SK_SK" : "Zadaný parameter 'id' nie je správny; nesmie byť prázdny a musí byť väčší ako 0"
	},
	
	"LANGUAGE_IS_NOT_SUPPORTED" : {
		"EN_US" : "Language is not supported",
		"SK_SK" : "Jazyk nie je podporovaný"
	},

	"LANGUAGE_SUCCESSFULLY_SET" : {
		"EN_US" : "Language successfully set",
		"SK_SK" : "Jazyk úspešne nastavený"
	},

	"LOCKED_ACCOUNT" : {
		"EN_US" : "Your user account is locked; please contact our administrators",
		"SK_SK" : "Vaše konto je zablokované, prosím kontaktuje administrátorov"
	},
	
	"PASSWORD_IS_EMPTY" : {
		"EN_US" : "Entered password is empty",
		"SK_SK" : "Zadané heslo je prázdne"
	},
	
	"REQUEST_IS_EMPTY" : {
		"EN_US" : "Request is not valid; please contact our administrators",
		"SK_SK" : "Nesprávna požiadavka na server, prosím kontaktujte administrátorov"
	},
	
	"UNKNOWN_PROBLEM_WITH_AUTHENTICATION" : {
		"EN_US" : "Unknown problem with authentication; not possible to to log in; please contact administrator",
		"SK_SK" : "Neznámy problém s prihlasovaním, príhlásenie nie je možné, prosím kontaktujte administrátora"
	},
	
	"USER_EMAIL_ALREADY_REGISTERED" : {
		"EN_US" : "Entered user e-mail is already registered",
		"SK_SK" : "Zadaný e-mail je už zaregistrovaný"
	},
	
	"USERS_LANGUAGE_IS_EMPTY" : {
		"EN_US" : "Users language is empty",
		"SK_SK" : "Jazyk používateľa je prázdny"
	},
	
	"USER_NAME_ALREADY_REGISTERED" : {
		"EN_US" : "Entered user name is already registered",
		"SK_SK" : "Zadané používateľské meno je už zaregistrované"
	},
	
	"USER_NAME_IS_EMPTY" : {
		"EN_US" : "Entered user name is empty",
		"SK_SK" : "Zadané používateľske meno je prázdne"
	},
	
	"USER_SUCCESSFULLY_LOGGED_IN" : {
		"EN_US" : "User was successfully logged in",
		"SK_SK" : "Používateľ bol úspešne prihlásený"
	},
	
	"USER_SUCCESSFULLY_LOGGED_OUT" : {
		"EN_US" : "User was successfully logged out",
		"SK_SK" : "Používateľ bol úspešne odhlásený"
	},

	"USER_SUCCESSFULLY_REGISTERED" : {
		"EN_US" : "User was successfully registered",
		"SK_SK" : "Používateľ bol úspešne zaregistrovaný"
	},
	
	// FIELDS
	
	"USER_NAME" : {
		"EN_US" : "User name",
		"SK_SK" : "Používateľské meno"
	},
	
	"USER_NAME_INPUT_TOOLTIP" : {
		"EN_US" : "Enter user name used for log in",
		"SK_SK" : "Zadajte používateľské meno určené na prihlásenie"
	},
	
	"PASSWORD" : {
		"EN_US" : "Password",
		"SK_SK" : "Heslo"
	},
	
	"PASSWORD_INPUT_TOOLTIP" : {
		"EN_US" : "Enter password used for log in",
		"SK_SK" : "Zadajte používateľské heslo určené na prihlásenie"
	},
	
	"REMEMBER_ME" : {
		"EN_US" : "Remember me",
		"SK_SK" : "Zapamätaj si ma"
	},
	
	"REMEMBER_ME_INPUT_TOOLTIP" : {
		"EN_US" : "Check, if you want to be remembered",
		"SK_SK" : "Zaškrtnite, ak chce byť zapamätaný"
	},
	
	"SHOW_PASSWORD" : {
		"EN_US" : "Show",
		"SK_SK" : "Ukázať"
	},

	"SHOW_PASSWORD_BUTTON_TOOLTIP" : {
		"EN_US" : "Show password",
		"SK_SK" : "Zobraziť heslo"
	},
	
	"HIDE_PASSWORD" : {
		"EN_US" : "Hide",
		"SK_SK" : "Skryť"
	},

	"HIDE_PASSWORD_BUTTON_TOOLTIP" : {
		"EN_US" : "Hide password",
		"SK_SK" : "Skryť heslo"
	},
	
	"LOGIN" : {
		"EN_US" : "Log in",
		"SK_SK" : "Prihlásiť sa"
	},
	
	"LOGIN_BUTTON_TOOLTIP" : {
		"EN_US" : "Click for log in",
		"SK_SK" : "Kliknite pre prihlásenie sa"
	}
});

var parsedTranslations = $.parseJSON(translations);

var listOfLanguages = ["EN_US", "SK_SK"]; 

var defaultLanguage = listOfLanguages[0];

var selectedLanguage = defaultLanguage;

function translate(code, language) {
	
	if (!language) {
		language = selectedLanguage;
	}
	
	if (parsedTranslations && parsedTranslations[code] && parsedTranslations[code][language]) {
		return parsedTranslations[code][language];
	} else {
		return code;
	}
}
