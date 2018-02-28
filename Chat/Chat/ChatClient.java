

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) {
		Scanner consoleIn = new Scanner(System.in);

		System.out.print("Host name/IP address: ");
        String hostName = consoleIn.nextLine();

        System.out.print("Port # to connect to: ");
        int portNumber = Integer.parseInt(consoleIn.nextLine());

        try {
            Socket socket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			Scanner scan = new Scanner (System.in);


			String name = "";
			String fromServer = "";
			String user = "";


            while ((fromServer = in.readLine()) != null) {
                System.out.println(fromServer);
                System.out.print("Client: ");
                user = scan.nextLine();
                out.println("Client: " + user);


           }

            socket.close();
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        }
    }
}
