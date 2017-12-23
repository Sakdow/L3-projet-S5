package message;

public class MessageReponseConnexion extends Message {
	private static final long serialVersionUID = 1L;
	private boolean accepte;
	
	public MessageReponseConnexion(String adresseIp, boolean accepte){
		super(adresseIp);
		this.accepte=accepte;
	}

	public boolean getAccepte() {
		return this.accepte;
	}
	
}
