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
            Registry registry = LocateRegistry.getRegistry(1888);
            GappRmi stub = (GappRmi) registry.lookup("gapp");
            stub.runGapp();
        } catch (RemoteException | NotBoundException e) {
            System.out.println("Client error: " + e.toString());
            e.printStackTrace();
        }
    }
}
