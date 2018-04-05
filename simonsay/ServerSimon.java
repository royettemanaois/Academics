import java.util.*;
import java.net.*;
import java.io.*;
import java.lang.*;

public class ServerSimon{

	public static ArrayList<Integer> responseID = new ArrayList<Integer>();

	static class Player extends Thread {
         Socket socket;
         int ID;

         public Player(Socket socket, int ID) {
            this.socket = socket;
            this.ID = ID;
         }

         public void run(){
         	try{

         	BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
         	 while (true) {
           		 //read an input from the BufferedReader
           		 String input = in.readLine();

            	//print (ID + " " + input) on the screen
				System.out.println(ID + " " + input);
             	//add the player's ID to the ArrayList named responseID, e.g.
             	responseID.add(ID);
             }
           }catch(Exception e){

           }
         }

      }

	public static void main(String[] args){

		try {

			//	a. Create a SocketServer on port 36000, e.g. ServerSocker server = ...
			ServerSocket server = new ServerSocket(36000);
			// b. Create a Scanner to read input from the keyboard
			Scanner consoleIn = new Scanner(System.in);
			// c. Create an array of three integers named score declared as: int[] tally = {0, 0, 0};
			int[] tally = {0,0,0};
			// d. Create an ArrayList of PrintWriters to send messages to clients declared as: ArrayList<PrintWriter> outList = new ArrayList<PrintWriter>();
			ArrayList<PrintWriter> outList = new ArrayList<PrintWriter>();


			for(int i = 0; i<3; i++){
				Socket client = server.accept();
				PrintWriter out = new PrintWriter(client.getOutputStream(), true);
				outList.add(out);
				Player p = new Player(client, i);
				p.start();
			}


			while (true) {
				//get an input [that the clients has to also say] from the keyboard. NOTE: input is a String
            	String input = consoleIn.nextLine();

            // send the string input to all the clients
            for (PrintWriter out: outList) {
               out.println("Simon says: "+input);
            }

            //clear the ArrayList responseID, e.g.

            responseID.clear();

            // wait for all clients to send a response
            while (responseID.size() != 3) {
               try {
                  Thread.sleep(100); // pause for 100 milliseconds
               } catch(Exception e) {}
            }

            // identify the client who sent the first response
            //get the ID of the client who responded first, e.g.
			int clientID = responseID.get(0);

            //increment the score of the client in the array named tally using clientID as the index, e.g.
            tally[clientID]++;

            if (tally[clientID] == 3) {
               // inform all the clients that clientID won the game
               for (PrintWriter out: outList) {
                  out.println("Player " + clientID + " won!");
               }

              break;

            } else {
               // send the clientID to all the clients (to inform them who answered first)
               for (PrintWriter out: outList) {
                  out.println("Player " + clientID + " was first");
               }
            }

         } // end of while (true) loop
		} catch (Exception e){

		}

		}
	}