import java.util.*;
import java.io.*;
import java.net.*;
import java.lang.*;

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
            	simonSays = simonSays.substring(0,11);
            	String input;

            	do{

            		System.out.print("Input: ");
            		input = consoleIn.nextLine();

            	}while(!input.equals(simonSays));

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