package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import message.Message;
import message.MessageDeconnexion;
import message.MessageDemConnexion;
import message.MessageGroupe;
import message.MessageMessageConversation;
import message.MessageReponseConnexion;
import message.MessageTicket;

public class Reseaux {
	private Socket socket;
	private String serverName;
	private int serverPort;
	private ObjectInputStream in;
	private ObjectOutputStream out;

	public Reseaux(String serverName, int serverPort) {
		super();
		this.serverName = serverName;
		this.serverPort = serverPort;
	}

	public MessageReponseConnexion connexionServeur(String idUtilisateur, String motDePasse) {
		try {
			socket = new Socket(serverName, serverPort);

			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());

			out.flush();

			MessageDemConnexion messageDemConnexion = new MessageDemConnexion(idUtilisateur, motDePasse);

			out.writeObject(messageDemConnexion);
			out.flush();

			Object objetRecu = in.readObject();

			MessageReponseConnexion messageReponse = (MessageReponseConnexion) objetRecu;

			return messageReponse;
		} catch (UnknownHostException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

		return null;
	}

	public void envoyerMessage(Message nouveauMessage) {
		try {
			out.flush();

			out.writeObject(nouveauMessage);
			out.flush();
		} catch (UnknownHostException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public Message ecoute() {
		try {

			Object objetRecu = in.readObject();
			Message messRecu;
			if (objetRecu instanceof MessageTicket) {
				messRecu = (MessageTicket) objetRecu;
			} else if (objetRecu instanceof MessageGroupe) {
				messRecu = (MessageGroupe) objetRecu;
			} else {
				messRecu = (MessageMessageConversation) objetRecu;
			}
			return messRecu;
		} catch (UnknownHostException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return null;
	}

	public void deconnexionServeur(String idUtilisateur) {
		try {
			out.flush();

			MessageDeconnexion messageDeconnexion = new MessageDeconnexion(idUtilisateur);

			out.writeObject(messageDeconnexion);
			out.flush();

			in.close();
			out.close();
			socket.close();
		} catch (UnknownHostException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
