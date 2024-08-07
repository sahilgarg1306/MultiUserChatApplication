package com.dit.chatapp.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.dit.chatapp.utils.ConfigReader;

public class Server {
	ServerSocket serverSocket;
	ArrayList<ServerWorker> workers = new ArrayList<>();
	public Server() throws IOException {
		int PORT = Integer.parseInt(ConfigReader.getValue("PORTNO"));
		serverSocket = new ServerSocket(PORT);
		System.out.println("Server Started and waiting for the clients to join");
		handleClientRequest();
		
	}
	
	public void handleClientRequest() throws IOException {
		while(true) {
			Socket clientSocket = serverSocket.accept();
			ServerWorker serverWorker = new ServerWorker(clientSocket,this);
			workers.add(serverWorker);
			serverWorker.start();
		}
	}
	
	
	/*  For Single Client 
	public Server() throws IOException {
		int PORT = Integer.parseInt(ConfigReader.getValue("PORTNO"));
		serverSocket = new ServerSocket(PORT);
		System.out.println("Server Started and waiting for the client connection");
		Socket socket = serverSocket.accept();
		System.out.println("Client joins the server");
		InputStream in= socket.getInputStream();
		byte arr[] =in.readAllBytes();
		String str = new String(arr);
		System.out.println("Message Recived from client:  "+ str);
		in.close();
		socket.close();
	}*/

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Server server = new Server();
	}
}