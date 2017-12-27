//package client;
//
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.net.Socket;
//import java.net.UnknownHostException;
//
//import message.MessageDeconnexion;
//import message.MessageDemConnexion;
//
//public class ThreadEcoute {
//	Socket socket;
//	static final String serverName = "localhost";
//	static final int serverPort = 9999;
//
//	public void connexionServeur(String idUtilisateur, String motDePasse) {
//		try {
//			socket = new Socket(serverName, serverPort);
//
//			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
//			out.flush();
//
//			MessageDemConnexion messageDemConnexion = new MessageDemConnexion(idUtilisateur, motDePasse);
//
//			out.writeObject(messageDemConnexion);
//			out.flush();
//
//			out.close();
//		} catch (UnknownHostException e) {
//
//			e.printStackTrace();
//
//		} catch (IOException e) {
//
//			e.printStackTrace();
//		}
//	}
//
//	public void ecoute() {
//
//	}
//
//	public void deconnexionServeur(String idUtilisateur) {
//		try {
//			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
//			out.flush();
//
//			MessageDeconnexion messageDeconnexion = new MessageDeconnexion(idUtilisateur);
//
//			out.writeObject(messageDeconnexion);
//			out.flush();
//
//			out.close();
//			socket.close();
//		} catch (UnknownHostException e) {
//
//			e.printStackTrace();
//
//		} catch (IOException e) {
//
//			e.printStackTrace();
//		}
//	}
//}

package client;

<<<<<<< HEAD
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
=======
import message.MessageTicket;
>>>>>>> bda6cd70c59a43adc19a00f9854a8b4537293d36

public class ThreadEcoute implements Runnable {

<<<<<<< HEAD
public class ThreadEcoute {
	Socket socket;
	static final String serverName = "92.146.114.89";
	static final int serverPort = 1705;

	public void connexionServeur(String idUtilisateur, String motDePasse) {
		try {
			socket = new Socket(serverName, serverPort);

			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			out.flush();

			MessageDemConnexion messageDemConnexion = new MessageDemConnexion(idUtilisateur, motDePasse);

			out.writeObject(messageDemConnexion);
			out.flush();

			out.close();
		} catch (UnknownHostException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void ecoute() {
=======
	private Reseaux reseaux;
	private Client client;
>>>>>>> bda6cd70c59a43adc19a00f9854a8b4537293d36

	public ThreadEcoute(Reseaux reseaux, Client client) {
		super();
		this.reseaux = reseaux;
		this.client = client;
	}

	public void run() {
		while (true) {
			MessageTicket messageTicket = reseaux.ecoute();
			client.ajouterTicket(messageTicket.getTicket());
		}
	}

<<<<<<< HEAD
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThreadEcoute test = new ThreadEcoute();
		test.connexionServeur("Blabla", "motDePasse");
	}

=======
>>>>>>> bda6cd70c59a43adc19a00f9854a8b4537293d36
}
