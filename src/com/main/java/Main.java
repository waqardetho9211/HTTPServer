package com.main.java;

public class Main {
	public static int port = 9000;
	public static void main(String[] args) {
		NewHTTPServer httpServer = new NewHTTPServer();
		httpServer.Start(port);
	}
}
