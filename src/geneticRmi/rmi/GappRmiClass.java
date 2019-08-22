package geneticRmi.rmi;

import java.rmi.RemoteException;

public class GappRmiClass implements GappRmi {

    @Override
    public void runGapp() throws RemoteException {
        System.out.println("I am alive!");
    }
}
