package ru.ifmo.ctddev.shalamov.task9;

import java.io.Serializable;
import java.rmi.Remote;


/**
 * Created by viacheslav on 07.05.14.
 */
public class RemotePersonImpl extends PersonImpl implements RemotePerson {

    public RemotePersonImpl(String name, String surname, String passNum) {
        super(name, surname, passNum);
    }

    public RemotePersonImpl(Person person) {
        super(person);
    }

}
