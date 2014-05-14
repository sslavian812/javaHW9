package ru.ifmo.ctddev.shalamov.task9;

import java.rmi.RemoteException;

/**
 * Created by viacheslav on 07.05.14.
 */
public class AccountImpl implements Account {
    private String id;
    private int amount;

    /**
     * No default constructor available.
     */
    private AccountImpl() {
    }

    /**
     * Creates an account with specified Id and zero-amount.
     *
     * @param id specified Id
     */
    public AccountImpl(String id) {
        this.id = id;
        this.amount = 0;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public void setAmount(int amount) {
        this.amount = amount;
    }
}
