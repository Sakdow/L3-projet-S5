package message;

import java.io.Serializable;

public class Message implements Serializable {
	private static final long serialVersionUID = 1L;
	private String adresseIp;

	public Message(String adresseIp) {
		super();
		this.adresseIp = adresseIp;
	}

	public String getAdresseIp() {
		return this.adresseIp;
	}

}
