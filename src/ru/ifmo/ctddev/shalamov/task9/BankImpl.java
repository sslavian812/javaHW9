package ru.ifmo.ctddev.shalamov.task9;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
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

    public BankImpl() {
        persons = new ConcurrentHashMap<String, RemotePerson>();
        try {
            registry = LocateRegistry.createRegistry(0);
        } catch (RemoteException e) {
            e.printStackTrace();
            System.exit(2);
        }
    }

    /**
     * Если информация об указанном физическом лице отсутствует, то оно должно быть добавлено.
     * В противном случае - - должны быть проверены его данные.
     *
     * @param name
     * @param surname
     * @param passNum
     * @param type
     * @return requested Person object
     * @throws RemoteException
     */
    @Override
    public Person getPerson(String name, String surname, String passNum, String type) throws RemoteException {
        String uid = name + "_" + surname + "_" + passNum;

        if (!("remote".equals(type) || "serialisation".equals(type))) {
            return null;
        }

        if (!persons.containsKey(uid)) {
            persons.put(uid, new RemotePersonImpl(name, surname, passNum));
        }

        if ("serialisation".equals(type)) {
            return new LocalPersonImpl(persons.get(uid));
        }
        if ("remote".equals(type)) {
            RemotePerson person = persons.get(uid);

            try {
                if (registry == null)
                    registry = LocateRegistry.createRegistry(0);
                try {
                    return (Person) registry.lookup(uid);
                } catch (NotBoundException e) {
                    RemotePerson stub = (RemotePerson) UnicastRemoteObject.exportObject(person, 0);
                    registry.bind(uid, stub);
                    return (Person) registry.lookup(uid);
                }

            } catch (RemoteException e) {
                System.out.println(e.getMessage());
                System.exit(1);
            } catch (AlreadyBoundException e) {
                e.printStackTrace();
            } catch (NotBoundException e) {
                System.out.println("not bound");
                System.exit(1);
            }
        }
        return null;
    }

//    @Override
//    public String commitPerson(Person p) throws RemoteException {
//        try {
//            String uid = p.getName() + "_" + p.getSurname() + "_" + p.getPassNum();
//
//            if (p == null || !persons.containsKey(uid)) {
//                return null;
//            }
//            persons.put(uid, new RemotePersonImpl(p));
//            return uid;
//        } catch (Exception e) {
//            return null;
//        }
//    }

//    /**
//     * Должна быть возможность создания записи о физическом лице по его данным.
//     * @param name
//     * @param surname
//     * @param passNum
//     * @param type
//     * @return
//     * @throws RemoteException
//     */
//    @Override
//    public Person createPerson(String name, String surname, String passNum, int type) throws RemoteException {
//        String uid = name + "_" + surname + "_" + passNum;
//        persons.put(uid, new Person(name, surname, passNum));
//        return persons.get(uid);
//    }


    public static void main(String[] args) {
        Bank bank = new BankImpl();
        try {
            Bank stub = (Bank) UnicastRemoteObject.exportObject(bank, 8080);
            Registry registry = LocateRegistry.createRegistry(8080);
            registry.bind("Bank", stub);
        } catch (RemoteException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }

        //while (true) ;
        //-Djava.security.policy=/policy.all
    }
}
