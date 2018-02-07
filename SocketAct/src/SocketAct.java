import java.net.*;
import java.util.Scanner;

/**
 * 
 * This program checks if the given ports is running
 * on a specific hostname that is given by a user. The
 * program also specify what is the protocol for the
 * specific port.
 * 
 * Author: Manaois, Royette A.
 * Date: February 3, 2018
 * Title: Activity 2: Socket Programming
 * 
 *
 */


public class SocketAct {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter hostname: ");
        String hostName = input.nextLine();

        int[] port = {20, 22, 23, 25, 53, 67, 69, 80};
        String[] protocol = {"File Transfer Protocol (FTP)",
            "Secure Shell (SSH)",
            "Telnet", "Simple Mail Transfer Protocol (SMTP)",
            "Domain Name System (DNS)", "Dynamic Host Configuration Protocol (DHCP)",
            "Trivial File Transfer Protocol (TFTP)",
            "Hypertext Transfer Protocol (HTTP)"};

        System.out.println("Checking port: 20, 22, 23, 25, 53, 67, 69, 80");

        for (int i = 0; i < port.length; i++) {
            try {
                Socket sock = new Socket(hostName, port[i]);
                System.out.println(hostName + "'s status for port " + port[i] + ", " + protocol[i] + ", is open.");
                sock.close();

            } catch (java.net.UnknownHostException ex) {
                System.err.println(hostName + " does not exist!");
                System.exit(0);

            } catch (java.io.IOException ex) {
                System.err.println(hostName + "'s status for port " + port[i] + ", " + protocol[i] + ", is close.");

            } catch (java.lang.SecurityException ex) {
                System.err.println("You are not permitted to have a connection to " + hostName);
                System.exit(0);

            }
        }
        
        

    }

}
