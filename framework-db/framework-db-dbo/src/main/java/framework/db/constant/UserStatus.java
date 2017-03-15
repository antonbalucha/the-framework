package framework.db.constant;

/**
 * Enumeration defines statuses which can user have. Current allowed statuses are:
 * 
 * <ul>
 * <li>{@linkplain framework.db.constant.UserStatus#R R} - registered</li>
 * <li>{@linkplain framework.db.constant.UserStatus#A A} - active</li>
 * <li>{@linkplain framework.db.constant.UserStatus#I I} - inactive</li>
 * <li>{@linkplain framework.db.constant.UserStatus#B B} - banned</li>
 * <li>{@linkplain framework.db.constant.UserStatus#D D} - deleted</li>
 * </li>
 */
public enum UserStatus {

	/** User is registered */
	R("R"),
	
	/** User is registered and active */
	A("A"),
	
	/** User is registered and inactive, can be activated */
	I("I"),
	
	/** User is registered and banned, can be activated */
	B("B"),
	
	/** User is deleted, must be registered again but with different user name */
	D("D");
	
	private String status;
	
	private UserStatus(String status) {
		this.status = status;
	}
	
	public String getValue() {
		return this.status;
	}
	
	public String toString() {
		return this.status;
	}
}
