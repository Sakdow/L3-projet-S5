package modele;

import java.io.Serializable;
import java.util.NavigableSet;
import java.util.TreeSet;

public class FilDiscussion implements Serializable {
	private static final long serialVersionUID = 1L;
	private NavigableSet<MessageConversation> ensembleMessage = new TreeSet<>();

	public FilDiscussion(MessageConversation premierMessage) {
		super();
		if (premierMessage != null)
			this.ajouterMessage(premierMessage);
	}

	public void ajouterMessage(MessageConversation message) {
		ensembleMessage.add(message);
	}

	public int getNombreMessage() {
		return ensembleMessage.size();
	}

	public NavigableSet<MessageConversation> getEnsembleMessage() {
		return ensembleMessage;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof FilDiscussion))
			return false;
		FilDiscussion other = (FilDiscussion) obj;

		if (ensembleMessage == other.ensembleMessage)
			return true;
		if (ensembleMessage == null)
			return false;

		if (this.ensembleMessage.size() != other.ensembleMessage.size())
			return false;
		
		return this.ensembleMessage.containsAll(other.ensembleMessage);
	}

}
