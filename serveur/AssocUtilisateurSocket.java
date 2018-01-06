package serveur;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import modele.Utilisateur;

class AssocUtilisateurSocket implements Comparable<AssocUtilisateurSocket> {
	private Utilisateur utilisateur;
	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;

	public AssocUtilisateurSocket(Utilisateur utilisateur, Socket socket, ObjectOutputStream out,
			ObjectInputStream in) {
		this.utilisateur = utilisateur;
		this.socket = socket;
		this.out = out;
		this.in = in;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((in == null) ? 0 : in.hashCode());
		result = prime * result + ((out == null) ? 0 : out.hashCode());
		result = prime * result + ((socket == null) ? 0 : socket.hashCode());
		result = prime * result + ((utilisateur == null) ? 0 : utilisateur.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof AssocUtilisateurSocket))
			return false;
		AssocUtilisateurSocket obj = (AssocUtilisateurSocket) o;
		return utilisateur.equals(obj.utilisateur);
	}


	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public Socket getSocket() {
		return socket;
	}

	public ObjectOutputStream getOut() {
		return out;
	}

	public ObjectInputStream getIn() {
		return in;
	}

	@Override
	public int compareTo(AssocUtilisateurSocket o) {
		return this.utilisateur.getIdUtilisateur().compareTo(o.utilisateur.getIdUtilisateur());
	}
	
	

}
