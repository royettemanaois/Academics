
import java.net.*;
import java.io.*;
import java.util.Scanner;

/**
 * This program will serve as the server for the ChatClient program. The server will ask for the port
 * to be used and then waits for a client to connect.
 *
 * @author Manaois, Royette A
 * @version 1.0, 03/17/2018
 */

public class ChatServer {
    public static void main(String[] args) throws IOException {


        String [] questions = {"1. Labrador retrievers were originally bred to retrieve fishing nets",
        					   "2. The dark side of the Moon is the same as the far side of the Moon",
        					   "3. Ancient Olympic athletes competed nude",
        					   "4. Bangalore is the Silicon Valley of India",
        					   "5. Lightning never strikes the same place twice",
        					   "6. The Red Sea gets its name from a slaughter that occurred there during biblical times",
        					   "7. Warts can be caused by touching certain animals, especially toads",
        					   "8. As a human body grows larger, its number of bones gets smaller",
        					   "9. Franklin Roosevelt is one of the presidents depicted on Mount Rushmore",
        					   "10. Coffee originated in Brazil"};

        boolean[] answerKey = {true, false, true, true,false,false,false,true,false,false};

        try {
            ServerSocket serverSocketOutput = new ServerSocket(2000);
            ServerSocket serverSocketInput = new ServerSocket(2001);

            Socket clientSocketOutput = serverSocketOutput.accept();
            Socket clientSocketInput = serverSocketInput.accept();


            DataOutputStream out = new DataOutputStream(clientSocketOutput.getOutputStream());
            DataInputStream in = new DataInputStream(clientSocketInput.getInputStream());

            boolean[] answersFromClient = new boolean[10];            
            
      		
          
            
            for(int i = 0; i < questions.length ; i++){
            		 out.writeUTF(questions[i]);
            		 answersFromClient[i] = in.readBoolean();
            		 out.flush();
            }
            
            int score = 0;

            for(int i = 0; i < questions.length; i++){
            	if(answerKey[i] == answersFromClient[i]){
            		score++;
            	}
            }

            out.writeInt(score);
            clientSocketOutput.close();
            clientSocketInput.close();
            serverSocketOutput.close();
            serverSocketInput.close();
            out.close();
            in.close();


        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port  2000 or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
