package ru.ifmo.ctddev.shalamov.task9;

import java.io.Serializable;

/**
 * Created by viacheslav on 07.05.14.
 */
public class LocalPersonImpl extends PersonImpl implements LocalPerson {
    public LocalPersonImpl(String name, String surname, String passNum) {
        super(name, surname, passNum);
    }

    public LocalPersonImpl(Person person) {
        super(person);
    }
}
