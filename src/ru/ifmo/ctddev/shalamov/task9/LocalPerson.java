package ru.ifmo.ctddev.shalamov.task9;

import java.io.Serializable;

/**
 * Created by viacheslav on 08.05.14.
 */
public interface LocalPerson extends Serializable, Person {
    public String getName();

    public String getSurname();

    public String getPassNum();

    public int setToAccount(String accountId, int amount);

    public String showMoney(String key);

    public int getMoney(String key);
}
