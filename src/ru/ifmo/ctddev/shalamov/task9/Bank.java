package ru.ifmo.ctddev.shalamov.task9;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by viacheslav on 07.05.14.
 */
public interface Bank extends Remote {

    /**
     * Должна быть возможность создания записи о физическом лице по его данным.
     * Должна быть возможность поиска физического лица по номеру паспорта, с выбором типа возвращаемого лица.
     *
     * @param name
     * @param surname
     * @param passNum
     * @param type
     * @return
     * @throws RemoteException
     */
    public Person getPerson(String name, String surname, String passNum, String type) throws RemoteException;

//    /**
//     * updates the information about this person in persons
//     * @param p a person to be updated.
//     * @return
//     */
//    public String commitPerson(Person p) throws RemoteException;

//    /**
//     * Должна быть возможность создания записи о физическом лице по его данным.
//     * @param name
//     * @param surname
//     * @param passNum
//     * @param type
//     * @return
//     * @throws RemoteException
//     */
//    public Person createPerson(String name, String surname, String passNum, int type) throws RemoteException;
}
