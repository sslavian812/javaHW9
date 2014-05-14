package ru.ifmo.ctddev.shalamov.task9;

import java.io.Serializable;
import java.rmi.Remote;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by viacheslav on 08.05.14.
 */
public interface Person {

    /**
     * У физического лица (Person) можно запросить имя.
     *
     * @return
     * @throws Exception
     */
    public String getName() throws Exception;

    /**
     * У физического лица (Person) можно запросить фамилию.
     *
     * @return
     * @throws Exception
     */
    public String getSurname() throws Exception;

    /**
     * У физического лица (Person) можно запросить номер паспорта.
     *
     * @return
     * @throws Exception
     */
    public String getPassNum() throws Exception;

    /**
     * Provides account by account's ID. If not Exists - creates it woth 0-amount.
     * Если у физического лица отсутствует счет с указанным номером, то он создается с нулевым балансом.
     *
     * @param accountId account's id.
     * @return account with specified id
     * @throws Exception
     */
    public Account getAccount(String accountId) throws Exception;

    public int setToAccount(String accountId, int amount) throws Exception;
}
