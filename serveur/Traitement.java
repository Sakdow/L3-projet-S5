package serveur;

import java.net.Socket;
import java.util.Queue;

import message.Message;

public class Traitement implements Runnable {

	private Queue<AssocMessageSocket> messagessATraiter;

	public Traitement(Queue<AssocMessageSocket> messagessATraiter) {
		this.messagessATraiter = messagessATraiter;
	}

	@Override
	public void run() {
		for (;;) {
			AssocMessageSocket assocMessageATraiter = messagessATraiter.poll();
			if (assocMessageATraiter != null) {
				Message message = assocMessageATraiter.getMessage();
				Socket socket = assocMessageATraiter.getSocket();
				traitementMessage(message, socket);
			}
		}
	}

	private void traitementMessage(Message message, Socket socket) {
		// TODO: Traiter le message et le renvoyer sur le bon socket
	}

}
