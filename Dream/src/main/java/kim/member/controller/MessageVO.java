package kim.member.controller;

public class MessageVO {

	String messageno ;
	String fk_sender_userid;
	String fk_Recipient_userid ;
	String title ;
	String Contents;
	String Shipping ;
	String read_check;
	String sender_delete ;
	String Recipient_delete ;
	
	
	
	public String getMessageno() {
		return messageno;
	}
	public void setMessageno(String messageno) {
		this.messageno = messageno;
	}
	public String getFk_sender_userid() {
		return fk_sender_userid;
	}
	public void setFk_sender_userid(String fk_sender_userid) {
		this.fk_sender_userid = fk_sender_userid;
	}
	public String getFk_Recipient_userid() {
		return fk_Recipient_userid;
	}
	public void setFk_Recipient_userid(String fk_Recipient_userid) {
		this.fk_Recipient_userid = fk_Recipient_userid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return Contents;
	}
	public void setContents(String contents) {
		Contents = contents;
	}
	public String getShipping() {
		return Shipping;
	}
	public void setShipping(String shipping) {
		Shipping = shipping;
	}
	public String getRead_check() {
		return read_check;
	}
	public void setRead_check(String read_check) {
		this.read_check = read_check;
	}
	public String getSender_delete() {
		return sender_delete;
	}
	public void setSender_delete(String sender_delete) {
		this.sender_delete = sender_delete;
	}
	public String getRecipient_delete() {
		return Recipient_delete;
	}
	public void setRecipient_delete(String recipient_delete) {
		Recipient_delete = recipient_delete;
	}
	
	
	
}
