package geneticRmi.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GappRmi extends Remote {
    void runGapp() throws RemoteException;
}
