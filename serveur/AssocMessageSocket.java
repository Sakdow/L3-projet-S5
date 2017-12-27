package serveur;

import java.net.Socket;

import message.Message;

class AssocMessageSocket {
	private Message message;
	private Socket socket;

	public AssocMessageSocket(Message message, Socket socket) {
		this.message = message;
		this.socket = socket;
	}

	public Message getMessage() {
		return message;
	}

	public Socket getSocket() {
		return socket;
	}

}
