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
import message.MessageReponseConnexion;
import message.MessageTicket;

public class Reseaux {
	private Socket socket;
	private String serverName;
	private int serverPort;

	public Reseaux(String serverName, int serverPort) {
		super();
		this.serverName = serverName;
		this.serverPort = serverPort;
	}

	public MessageReponseConnexion connexionServeur(String idUtilisateur, String motDePasse) {
		try {
			socket = new Socket(serverName, serverPort);

			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

			out.flush();

			MessageDemConnexion messageDemConnexion = new MessageDemConnexion(idUtilisateur, motDePasse);

			out.writeObject(messageDemConnexion);
			out.flush();

			Object objetRecu = in.readObject();

			MessageReponseConnexion messageReponse = (MessageReponseConnexion) objetRecu;

			in.close();
			out.close();

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
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			out.flush();

			out.writeObject(nouveauMessage);
			out.flush();

			out.close();
		} catch (UnknownHostException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public Message ecoute() {
		try {
			
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			if(in.available() > 0){
				Object objetRecu = in.readObject();
				Message messRecu;
				if(objetRecu instanceof MessageTicket){
					messRecu = (MessageTicket) objetRecu;
				} else {
					messRecu = (MessageGroupe) objetRecu;
				}
	
				in.close();
				
				return messRecu;
			}
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
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			out.flush();

			MessageDeconnexion messageDeconnexion = new MessageDeconnexion(idUtilisateur);

			out.writeObject(messageDeconnexion);
			out.flush();

			out.close();
			socket.close();
		} catch (UnknownHostException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
