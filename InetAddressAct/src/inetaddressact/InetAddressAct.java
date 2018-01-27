package inetaddressact;

import java.net.InetAddress;
import java.util.Scanner;

/**
 *
 * @author Royette
 */
public class InetAddressAct {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String ans = "Y";
        int hostNumber = 1;

        while (ans.equalsIgnoreCase("Y")) {
            System.out.print("Host " + hostNumber + " - Type IP address/Hostname: ");
            Scanner input = new Scanner(System.in);

            String hostname = input.nextLine();
            try {
                InetAddress[] ia = InetAddress.getAllByName(hostname);

                System.out.printf("\t %20s\n", "Number of Hosts/IPs: " + ia.length);
                System.out.printf("\t %15s %-15s\n", "Host Name", "Host Address");

                for (int i = 0; i < ia.length; i++) {
                    String hostAddress = ia[i].getHostAddress();
                    String hostName = ia[i].getHostName();
                    System.out.printf("\t %15s %-15s\n", hostName, hostAddress);

                }

            } catch (Exception e) {
                System.out.println("IP address/Hostname not found!");
            }

            do {
                System.out.print("Search another? [y/n]: ");
                ans = input.nextLine();
            } while (!ans.equalsIgnoreCase("Y") && !ans.equalsIgnoreCase("N"));

            hostNumber++;
        }

        System.out.println("Thank you for using the program! ");
        System.exit(0);

    }

}
