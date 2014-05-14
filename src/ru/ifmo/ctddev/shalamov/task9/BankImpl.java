package ru.ifmo.ctddev.shalamov.task9;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by viacheslav on 07.05.14.
 */
public class BankImpl implements Bank {
    private ConcurrentHashMap<String, RemotePerson> persons;
    private static Registry registry = null;

    /**
     * initialises Bank with emply Map of clients and a registery.
     */
    public BankImpl() {
        persons = new ConcurrentHashMap<String, RemotePerson>();
        try {
            registry = LocateRegistry.createRegistry(8080);
        } catch (RemoteException e) {
            e.printStackTrace();
            System.exit(2);
        }
    }

    /**
     * starts bank.
     *
     * @param args
     */
    public static void main(String[] args) {
        Bank bank = new BankImpl();
        try {
            Bank stub = (Bank) UnicastRemoteObject.exportObject(bank, 8080);
            registry.bind("Bank", stub);
        } catch (RemoteException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Person getPerson(String passNum, String type) throws RemoteException {

        if (!("remote".equals(type) || "serialisation".equals(type))) {
            return null;
        }

        if (!persons.containsKey(passNum))
            return null;

        if ("serialisation".equals(type)) {
            return new LocalPersonImpl(persons.get(passNum));
        }
        if ("remote".equals(type)) {
            return persons.get(passNum);
        }
        return null;
    }

    @Override
    public boolean createPerson(String name, String surname, String passNum) {
        try {
            if (!persons.containsKey(passNum)) {
                persons.put(passNum, new RemotePersonImpl(name, surname, passNum));
                return true;
            } else {
                return false;
            }

        } catch (RemoteException e) {
            return false;
        }
    }
}
