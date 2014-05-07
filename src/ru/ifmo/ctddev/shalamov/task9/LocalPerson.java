package ru.ifmo.ctddev.shalamov.task9;

import java.io.Serializable;

/**
 * Created by viacheslav on 07.05.14.
 */
public class LocalPerson extends PersonImpl implements Serializable {
    public LocalPerson(String name, String surname, String passNum) {
        super(name, surname, passNum);
    }
}
