package modele;

import java.util.NavigableSet;
import java.util.TreeSet;

public class FilDiscussion {
	private NavigableSet<MessageConversation> ensembleMessage;
	
	public FilDiscussion(MessageConversation premierMessage){
		super();
		this.ensembleMessage = new TreeSet<>();
		this.ajouterMessage(premierMessage);
	}
	
	public void ajouterMessage(MessageConversation message){
		ensembleMessage.add(message);
	}

	public NavigableSet<MessageConversation> getEnsembleMessage() {
		return ensembleMessage;
	}
	
	
}
