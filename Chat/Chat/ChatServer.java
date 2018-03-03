
import java.net.*;
import java.io.*;
import java.util.Scanner;

/**
 * This program will serve as the server for the ChatClient program. The server will ask for the port
 * to be used and then waits for a client to connect.
 *
 * @author Balantin, Renphil Ian G
 * @author Manaois, Royette A
 * @version 1.0, 03/03/2018
 */

public class ChatServer {
    public static void main(String[] args) throws IOException {
		Scanner consoleIn = new Scanner(System.in);

		System.out.print("Supply port # to open: ");
        int portNumber = Integer.parseInt(consoleIn.nextLine());

        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);

            Socket clientSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String inputLine, outputLine;
			out.println("Server: hello there");
            while ((inputLine = in.readLine()) != null) {
                 System.out.println(inputLine);
                 if (inputLine.equals("Client: Bye.")){
                 	out.println("Server: Bye.");
                  	System.exit(1);
           		 }
                 System.out.print("Server: ");
                 outputLine = consoleIn.nextLine();
                 out.println("Server: " +outputLine);

            }

            clientSocket.close();
            serverSocket.close();

        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port " + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
