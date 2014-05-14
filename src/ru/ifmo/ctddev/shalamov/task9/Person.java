package ru.ifmo.ctddev.shalamov.task9;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by viacheslav on 08.05.14.
 */
public interface Person {

    /**
     * Provides name.
     * У физического лица (Person) можно запросить имя.
     *
     * @return name of person
     * @throws Exception if something goes wrong
     */
    public String getName() throws Exception;

    /**
     * Provides surmene.
     * У физического лица (Person) можно запросить фамилию.
     *
     * @return surname of person.
     * @throws Exception if something goes wrong
     */
    public String getSurname() throws Exception;

    /**
     * Provides passport number.
     * У физического лица (Person) можно запросить номер паспорта.
     *
     * @return passport number of person
     * @throws Exception if something goes wrong
     */
    public String getPassNum() throws Exception;

    /**
     * Provides all of the accouts of corresponding person.
     *
     * @return accounts of corresponding person.
     * @throws Exception if something goes wrong
     */
    public ConcurrentHashMap<String, Account> getAccounts() throws Exception;

    /**
     * Sets a value to specifies account.
     *
     * @param accountId account's Id
     * @param amount    new value
     * @return new amount of account
     * @throws Exception if something goes wrong
     */
    public int setToAccount(String accountId, int amount) throws Exception;

    /**
     * Provides String representation of a specified account of this person.
     *
     * @param key account's Id
     * @return String representation of a specified account of this person.
     * @throws Exception if something goes wrong
     */
    public String showMoney(String key) throws Exception;

    /**
     * Provides money amount of the specified account of this person.
     *
     * @param key account's Id
     * @return money amount of the specified account of this person.
     * @throws Exception if something goes wrong
     */
    public int getMoney(String key) throws Exception;

}
