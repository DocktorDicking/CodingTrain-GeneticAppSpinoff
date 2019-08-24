package geneticRmi.rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RmiClient {
    public RmiClient() {
    }

    public static void main(String[] args) {
        try {
            System.out.println("Connecting...");
            Registry registry = LocateRegistry.getRegistry(1888);
            GappRmi stub = (GappRmi) registry.lookup("gapp");

            System.out.println("Connection successful, Sending request.. ");
            Thread.sleep(250);
            stub.runGapp();
            System.out.println("Request has been completed\n");
            System.out.println(stub.getFinalOutput());
        } catch (RemoteException | NotBoundException | InterruptedException e) {
            System.out.println("Client error: " + e.toString());
            e.printStackTrace();
        }
    }
}

