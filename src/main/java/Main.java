package main.java;

import main.java.Connection.HTTPConnection;

public class Main {
	// Just to distinguish a request from a normal http request using a different port
	public static int port = 8989;
	public static void main(String[] args) {
		//NewHTTPServer httpServer = new NewHTTPServer();

		HTTPConnection comp = new HTTPConnection.ComputerBuilder().withHTTPHeaders().build();
		System.out.println(comp);
	}
}
