import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 *This program is amazing :) check it out ;)
 *
 * @author Manaois, Royette A
 * @version 1.0, 03/17/2018
 */

public class ChatClient {
    public static void main(String[] args) {
		Scanner consoleIn = new Scanner(System.in);


		System.out.print("Host name/IP address: ");
        String hostName = consoleIn.nextLine();


        try {
            Socket socketOutput = new Socket(hostName, 2001);
            Socket socketInput = new Socket(hostName, 2000);
            DataOutputStream out = new DataOutputStream(socketOutput.getOutputStream());
            DataInputStream in = new DataInputStream(socketInput.getInputStream());
			Scanner scan = new Scanner (System.in);



			boolean answer;
			String ans;

          for(int i = 0; i < 10; i++){
          		String question = in.readUTF();
                System.out.println(question);
                System.out.print("Answer: ");
                ans = scan.nextLine();
                answer = Boolean.getBoolean(ans);
                System.out.println(answer);
                out.writeBoolean(answer);
                out.flush();
           }


            System.out.println("Score: " +in.readInt());

            socketOutput.close();
            socketInput.close();
            out.close();
            in.close();

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host ");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to ");
            System.exit(1);
        }
    }


}
