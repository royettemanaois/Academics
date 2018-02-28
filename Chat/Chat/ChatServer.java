

import java.net.*;
import java.io.*;
import java.util.*;

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
                 System.out.print("Server: ");
                 outputLine = consoleIn.nextLine();
                 out.println("Server: " +outputLine);

               if (outputLine.equals("Bye."))
                  	System.exit(1);
            }

            clientSocket.close();
            serverSocket.close();

        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port " + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
