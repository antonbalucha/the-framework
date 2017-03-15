<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Login into application</title>
		<script src="./../js/jquery-3.1.1.min.js"></script>
		<script src="./../js/translations.js"></script>
		<script>
			var translateLoginScreen;    
		
			$(document).ready(function() {
				
				// translate fields
				translateLoginScreen = function() {
					$("#userNameLabel").html(translate("USER_NAME"));
					$("#userNameInput").prop('title', translate("USER_NAME_INPUT_TOOLTIP"));
					
				    $("#passwordLabel").html(translate("PASSWORD"));
				    $("#passwordInput").prop('title', translate("PASSWORD_INPUT_TOOLTIP"));
				    
				    $("#rememberMeLabel").html(translate("REMEMBER_ME"));
				    $("#rememberMeInput").prop('title', translate("REMEMBER_ME_INPUT_TOOLTIP"));

				    $("#showPasswordButton").prop('value', translate("SHOW_PASSWORD"));
				    $("#showPasswordButton").prop('title', translate("SHOW_PASSWORD_BUTTON_TOOLTIP"));
				    
				    $("#loginButton").prop('value', translate("LOGIN"));
				    $("#loginButton").prop('title', translate("LOGIN_BUTTON_TOOLTIP"));
				};
			    
				translateLoginScreen();
				
				// define actions
				$("#loginButton").click(function() {
					
					if (!$("#userNameInput").val() || ($("#userNameInput").val() && $("#userNameInput").val().trim().length == 0)) {
						$("#messageLabel").html(translate("USER_NAME_IS_EMPTY"));
					} else if (!$("#passwordInput").val() || ($("#passwordInput").val() && $("#passwordInput").val().trim().length == 0)) {
						$("#messageLabel").html(translate("PASSWORD_IS_EMPTY"));
					} else {
						$.ajax({
							type: "POST",
							url: "./../api/login",
							contentType: "application/json",
							dataType: "json",
							async: true,
							data: JSON.stringify(
					            {
									"userName": $("#userNameInput").val().trim(),
									"password": $("#passwordInput").val(),
									"rememberMe" : true
								}),
							complete: function (event) {
								// if login was successful
								if (event && event.responseJSON && event.responseJSON.httpStatusCode && event.responseJSON.httpStatusCode == "200") {
									$("#messageLabel").html(translate(event.responseJSON.messageCode));
								// if login was not successful
								} else {
									$("#messageLabel").html(translate(event.responseJSON.messageCode));
								}
			          	 	}
				        });
					}
			    });
			    $("#showPasswordButton")
			    	.click(function() {
			    		if ($("#passwordInput").get(0).type == "text") {
			    			$("#passwordInput").get(0).type = "password";
			    			$("#showPasswordButton").prop('value', translate("SHOW_PASSWORD"));
						    $("#showPasswordButton").prop('title', translate("SHOW_PASSWORD_BUTTON_TOOLTIP"));
			    		} else {
			    			$("#passwordInput").get(0).type = "text";
			    			$("#showPasswordButton").prop('value', translate("HIDE_PASSWORD"));
						    $("#showPasswordButton").prop('title', translate("HIDE_PASSWORD_BUTTON_TOOLTIP"));
			    		}
			    	});
			});
		</script>
	</head>
	
	<body>
		<div id="loginSection">
			<table id="loginTable">
				<tr>
					<td><label id="userNameLabel" for="userNameInput"></label></td>
					<td><input id="userNameInput" type="text" tabindex="1" /></td>
				</tr>
				<tr>
					<td><label id="passwordLabel" for="passwordInput"></label></td>
					<td>
						<input id="passwordInput" type="password" tabindex="2" />
						<input id="showPasswordButton" type="submit" tabindex="3" />
					</td>
				</tr>
				<tr>
					<td><label id="rememberMeLabel"></label></td>
					<td><input id="rememberMeInput" type="checkbox" tabindex="4" /></td>
				</tr>
				<tr>
					<td></td>
					<td><input id="loginButton" type="submit" tabindex="5" /></td>
				</tr>
				<tr>
					<td></td>
					<td><label id="messageLabel"></label></td>
				</tr>
			</table>
		</div>
	</body>
</html>
