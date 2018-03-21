import java.io.*;
import java.net.*;
import java.util.*;

/**
 * This program which acts as a student (client) for the teacher (server) program. It asks for the hostname/ipaddress
 * you are going to connect to and using the port 2000 the teacher will send 10 true or false questions in
 * string (UTF) to the student and using the port 2001 the student will send its answers in boolean to the teacher.
 * The program willcontinuously ask the student to type either true or false only to finish the quiz.
 * After finishing the quiz the student will receive its score in integer from the teacher using the port 2000.
 * The program terminates after receiving the score of the student.
 *
 * @version 1.0 03/17/2018
 * @author Manaois, Royette A
 */

public class StudentClient {
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

			String ans;

            System.out.println(in.readUTF());

          for(int i = 0; i < 10; i++){
          		String question = in.readUTF();


                do{
                	 System.out.println(question);
               		 System.out.print("Answer: ");
               		 ans = scan.nextLine();

                }while(!ans.equalsIgnoreCase("true") && !ans.equalsIgnoreCase("false"));


                if(ans.equalsIgnoreCase("true")){
                	out.writeBoolean(true);
                }else{
                	out.writeBoolean(false);
                }


                out.flush();
           }


            System.out.println("Score: " +in.readInt());

            socketOutput.close();
            socketInput.close();
            out.close();
            in.close();

        } catch (UnknownHostException e) {
            System.err.println("Don't know about the host");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to the host");
            System.exit(1);
        }
    }


}
