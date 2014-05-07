package ru.ifmo.ctddev.shalamov.task9;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by viacheslav on 07.05.14.
 */
public class BankImpl implements Bank {
    private ConcurrentHashMap<String, Person> persons;

    public BankImpl() {
        persons = new ConcurrentHashMap<String, Person>();
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
    public Person getPerson(String name, String surname, String passNum, int type) throws RemoteException {
        String uid = name + "_" + surname + "_" + passNum;
        if (!persons.containsKey(uid)) {
            persons.put(uid, new PersonImpl(name, surname, passNum));
        }
        return persons.get(uid);
    }

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
}
