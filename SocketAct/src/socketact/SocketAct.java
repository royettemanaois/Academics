package socketact;

import java.net.*;
import java.util.Scanner;
import java.io.*;

/**
 *
 * @author Royette Manaois
 */
public class SocketAct {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter hostname: ");
        String hostName = input.nextLine();

        int[] port = {20, 22, 23, 25, 53, 67, 69, 80};
        String[] protocol = {"File Transfer Protocol (FTP)",
                            "Secure Shell (SSH)",
                            "Telnet","Simple Mail Transfer Protocol (SMTP)",
                            "Domain Name System (DNS)","Dynamic Host Configuration Protocol (DHCP)",
                            "Trivial File Transfer Protocol (TFTP)",
                            "Hypertext Transfer Protocol (HTTP)"};

        for (int i = 0; i < port.length; i++) {
            try {
                Socket sock = new Socket(hostName, port[i]);
                System.out.println("Connected to " + hostName + " port " + port[i] + " for "+protocol[i]+" is open!");
                sock.close();
            } catch (java.io.IOException e) {
                System.err.println("Error connecting to " + hostName + ": " + e);
            }
        }

    }

}
