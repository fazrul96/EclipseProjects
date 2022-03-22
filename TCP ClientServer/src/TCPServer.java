import java.net.*;import java.io.*;
public class TCPServer {
	public static void main(String[] args) throws IOException {
		// TCP component
		ServerSocket serverSocket = null;
		Socket clientSocket = null;
		int port = 8081;
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("Waiting for connection...");
			System.out.println();
		} catch (IOException e) {
			System.out.println(e.toString() + " :Could not listen on port: " + port);
			System.exit(1);
		}
		try {
			clientSocket = serverSocket.accept();
			System.out.println("TCP Session established.");
			System.out.println();
		} catch (IOException e) {
			System.out.println(e.toString() + " :Accept failed.");
			System.exit(1);
		}
		PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		String inputLine, outputLine;
		TCPProtocol nis = new TCPProtocol();
		outputLine = nis.processAnswer(null);
		out.println(outputLine);
		while ((inputLine = in.readLine()) != null) {
			outputLine = nis.processAnswer(inputLine);
			out.println(outputLine);
			if (outputLine.equalsIgnoreCase("Is ok. Give It A Try Next Time. Have a Nice Day =)"))
				break;
			else if (outputLine.equalsIgnoreCase("Thank For Your Guessing. Have a Nice Day =)"))
				break;
			else if (outputLine.equalsIgnoreCase("Have a nice day friend, let's play again ^_^"))
				break;
		}
		// End connection
		out.flush();
		out.close();
		in.close();
		if (!clientSocket.isClosed())
			clientSocket.close();
		if (!serverSocket.isClosed())
			serverSocket.close();
		System.out.println("TCP session closed.");}}