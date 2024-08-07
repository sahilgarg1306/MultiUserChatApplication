package com.dit.chatapp.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JTextArea;

import com.dit.chatapp.utils.ConfigReader;

public class Client {
	Socket socket;
	OutputStream out;
	InputStream in;
	ClientWorker worker;
	JTextArea textArea;
	public Client(JTextArea textArea) throws UnknownHostException, IOException {
		int PORT = Integer.parseInt(ConfigReader.getValue("PORTNO"));
		socket = new Socket(ConfigReader.getValue("SERVER_IP"), PORT);
		out= socket.getOutputStream();
		in= socket.getInputStream();
		this.textArea = textArea;
		readmessage();
	}
	
	public void sendMessage(String message) throws IOException {
		message = message +"\n";
		out.write(message.getBytes());
	}
	
	public void readmessage() {
		worker = new ClientWorker(in, textArea);
		worker.start();
	}
}
