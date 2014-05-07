package ru.ifmo.ctddev.shalamov.task9;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by viacheslav on 07.05.14.
 */
public class Server {
    /**
     * Creates a Bank at rmi://localhost/bank.
     */
    public static void main(String[] args) {
        Bank bank = new BankImpl();
        try {
            Bank stub = (Bank) UnicastRemoteObject.exportObject(bank, 0);
            Registry registry = LocateRegistry.createRegistry(8080);
            registry.bind("Bank", stub);
        } catch (RemoteException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }

       // while (true) ;
    }
}
//-Djava.security.policy=/policy.all
