package geneticRmi.rmi;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RmiServer extends GappRmiClass {
    public RmiServer() {}

    public static void main(String args[]) {
        try {
            GappRmiClass grc = new GappRmiClass();
            GappRmi stub = (GappRmi) UnicastRemoteObject.exportObject(grc, 1888);
            Registry registry = LocateRegistry.createRegistry(1888);

            registry.bind("gapp", stub);
            System.out.println("Server up and running.... ");
        } catch (RemoteException | AlreadyBoundException e) {
            System.out.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

}
