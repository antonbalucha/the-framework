"use strict";

var translations = JSON.stringify({
	
	// MESSAGES
	
	"CORRECT_NAME_AND_PASSWORD" : {
		"EN_US" : "Entered correct user name and password; user is logged",
		"SK_SK" : "Prihlasovacie meno a heslo sú správne, používateľ je prihlásený"
	}, 
	
	"CREDENTIALS_NOT_VALID" : {
		"EN_US" : "Entered credentials are not valid",
		"SK_SK" : "Zadané prihlasovacie údaje nie sú správne"
	},
	
	"INTERNAL_SERVER_ERROR" : {
		"EN_US" : "Internal server error; not possible to log in; please contact administrator",
		"SK_SK" : "Neznámy problém na serveri, prihlásenie nie je možné, prosím kontaktuje administrátora"
	},
	
	"LOCKED_ACCOUNT" : {
		"EN_US" : "Your user account is locked; please contact administrator",
		"SK_SK" : "Vaše konto je zablokované, prosím kontaktuje administrátora"
	},
	
	"PASSWORD_IS_EMPTY" : {
		"EN_US" : "Entered password is empty",
		"SK_SK" : "Zadané heslo je prázdne"
	},
	
	"REQUEST_IS_EMPTY" : {
		"EN_US" : "Request is not valid; please contact administrator",
		"SK_SK" : "Nesprávne vyplnený formulár, prosím kontaktujte administrátora"
	},
	
	"UNKNOWN_PROBLEM_WITH_AUTHENTICATION" : {
		"EN_US" : "Unknown problem with authentication; not possible to to log in; please contact administrator",
		"SK_SK" : "Neznámy problém s prihlasovaním, príhlásenie nie je možné, prosím kontaktujte administrátora"
	},
	
	"USER_NAME_IS_EMPTY" : {
		"EN_US" : "Entered user name is empty",
		"SK_SK" : "Zadané používateľske meno je prázdne"
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
