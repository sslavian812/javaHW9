package ru.ifmo.ctddev.shalamov.task9;

import java.io.Serializable;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by viacheslav on 07.05.14.
 */
public class PersonImpl implements Person {

    /**
     * У физического лица может быть несколько счетов, к которым должен предоставляться доступ.
     */
    protected ConcurrentHashMap<String, Account> accounts;

    protected String name;
    protected String surname;
    protected String passNum;

    public PersonImpl(String name, String surname, String passNum) {
        this.name = name;
        this.surname = surname;
        this.passNum = passNum;
        accounts = new ConcurrentHashMap<String, Account>();
    }

    public String getName() throws Exception {
        return name;
    }

    public String getSurname() throws Exception {
        return surname;
    }

    public String getPassNum() throws Exception {
        return passNum;
    }


    public Account getAccount(String accountId) throws Exception {
        if (!accounts.containsKey(accountId)) {
            accounts.put(accountId, new AccountImpl(accountId));
        }
        return accounts.get(accountId);
    }
}
