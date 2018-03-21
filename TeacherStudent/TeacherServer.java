import java.net.*;
import java.io.*;
import java.util.*;

/**
 *
 * This program which acts as a teacher (server) for the student (client) program.
 * The teacher has prepared 10 true or false questions with its answer keys. Using the
 * port 2000 the teacher will send those 10 questions in string (UTF) to student and using the port
 * 2001 it will receive the answers in boolean from the student. After receiving all the answers the program
 * will check it with the answer key provided and after counting the correct answers it will send the score in
 * integer to the student using the port 2000. The program terminates after sending the score to the student.
 *
 * @version 1.0 03/17/2018
 * @author Manaois, Royette A
 */

public class TeacherServer {
    public static void main(String[] args){


        String [] questions =  new String[10];
        questions[0] = "Labrador retrievers were originally bred to retrieve fishing nets";
        questions[1] = "The dark side of the Moon is the same as the far side of the Moon";
        questions[2] = "Ancient Olympic athletes competed nude";
        questions[3] = "Bangalore is the Silicon Valley of India";
        questions[4] = "Lightning never strikes the same place twice";
        questions[5] = "The Red Sea gets its name from a slaughter that occurred there during biblical times";
        questions[6] = "Warts can be caused by touching certain animals, especially toads";
        questions[7] = "As a human body grows larger, its number of bones gets smaller";
        questions[8] = "Franklin Roosevelt is one of the presidents depicted on Mount Rushmore";
        questions[9] = "Coffee originated in Brazil";

        boolean[] answerKey = {true,false,true,true, false, false,false,true, false, false};
        boolean[] answersFromClient = new boolean[10];

        try {
            ServerSocket serverSocketOutput = new ServerSocket(2000);
            ServerSocket serverSocketInput = new ServerSocket(2001);

            Socket clientSocketOutput = serverSocketOutput.accept();
            Socket clientSocketInput = serverSocketInput.accept();

            DataOutputStream out = new DataOutputStream(clientSocketOutput.getOutputStream());
            DataInputStream in = new DataInputStream(clientSocketInput.getInputStream());



      	    out.writeUTF("Welcome to the quiz. Answer true when the statement is true otherwise false");
            for(int i = 0; i < questions.length ; i++){
            		 out.writeUTF((i+1)+". "+questions[i]);
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



            serverSocketOutput.close();
            serverSocketInput.close();
            out.close();
            in.close();


        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on ports 2000 and 2001 or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
