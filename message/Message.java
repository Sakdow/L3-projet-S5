package message;

import java.io.Serializable;
import java.net.InetAddress;

public class Message implements Serializable {
	private static final long serialVersionUID = 1L;
	private InetAddress adresseIp;

	public Message(InetAddress adresseIp) {
		super();
		this.adresseIp = adresseIp;
	}

	public InetAddress getAdresseIp() {
		return this.adresseIp;
	}

}
