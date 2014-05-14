package ru.ifmo.ctddev.shalamov.task9;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by viacheslav on 07.05.14.
 */
public interface Bank extends Remote {

    /**
     * Provides a RemotePerson or LocalPerson corresponding to type argument.
     * <p/>
     * Должна быть возможность поиска физического лица по номеру паспорта, с выбором типа возвращаемого лица.
     *
     * @param passNum passNum of person
     * @param type    which type should be provided. can be "remote" or "serialisation"
     * @return requested Person object
     * @throws RemoteException if something fails
     */
    public Person getPerson(String passNum, String type) throws RemoteException;


    /**
     * Должна быть возможность создания записи о физическом лице по его данным.
     *
     * @return true if thr person is successfully created, false - otherwise.
     */
    public boolean createPerson(String name, String surname, String passNum) throws RemoteException;
}
