import java.util.*;
import java.io.*;
import java.net.*;
import java.lang.*;

/*
 *
 * This program which acts as a player (client) for Simon (server) program. It connects to the server program
 * with the loopback address and with the use of port 36000, The players will receive a word or phrase from Simon and
 * the players will need to copy it, if the input of a player is not equal to the word or phrase that Simon sent, the
 * player will repeat it as many times as necessary while if it's equal the player will receive a response from Simon
 * indicating who among the players finishes first in copying the word or phrase that Simon sent and gains a point.
 * If one of the players already got 3 points, the players will received a response from Simon who is that player and will
 * be declared as a winner then the server program terminates with the player program.
 *
 * @author Renphil Ian Balantin
 * @author Royete Manaois
 * @version 1.0 04/05/2018
 *
 */


public class PlayerClient{
	public static void main(String[] args){

		try{
			Socket socket = new Socket("127.0.0.1", 36000);
			Scanner consoleIn = new Scanner(System.in);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String simonSays;
			ArrayList<PrintWriter> outList = new ArrayList<PrintWriter>();

			while ((simonSays = br.readLine()) != null) {
            	System.out.println(simonSays);
            	String input;

            	do{
            		System.out.print("Input: ");
            		input = consoleIn.nextLine();
            	}while(!input.equals(simonSays.replace("Simon says: ","")));

            out.println(input);
            simonSays = br.readLine();
            System.out.println(simonSays);
         }
		}catch(Exception e){
			System.err.println("errors");
			e.printStackTrace();
		}

	}
}