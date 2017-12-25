package modele;

import java.util.NavigableSet;
import java.util.TreeSet;

public class FilDiscussion {
	private NavigableSet<MessageConversation> ensembleMessage;
	private int nombreMessage=0;
	
	public FilDiscussion(MessageConversation premierMessage){
		super();
		this.ensembleMessage = new TreeSet<>();
		this.ajouterMessage(premierMessage);
	}
	
	public void ajouterMessage(MessageConversation message){
		ensembleMessage.add(message);
		nombreMessage++;
	}
	
	public int getNombreMessage(){
		return nombreMessage;
	}
	
	public NavigableSet<MessageConversation> getEnsembleMessage() {
		return ensembleMessage;
	}
	
	
}
