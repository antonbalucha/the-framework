package framework.utils.email;

public class Email {

	private String sender;
	
	private String recipient;
	
	private String subject;
	
	private String text;

	public String getSender() {
		return this.sender;
	}

	public Email setSender(String sender) {
		this.sender = sender;
		return this;
	}

	public String getRecipient() {
		return this.recipient;
	}

	public Email setRecipient(String recipient) {
		this.recipient = recipient;
		return this;
	}

	public String getSubject() {
		return this.subject;
	}

	public Email setSubject(String subject) {
		this.subject = subject;
		return this;
	}

	public String getText() {
		return this.text;
	}

	public Email setText(String text) {
		this.text = text;
		return this;
	}
	
	public void send() {
		EmailUtils.send(this);
	}
}
