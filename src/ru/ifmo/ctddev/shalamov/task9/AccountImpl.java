package ru.ifmo.ctddev.shalamov.task9;

import java.rmi.RemoteException;

/**
 * Created by viacheslav on 07.05.14.
 */
public class AccountImpl implements Account {
    private String id;
    private int amount;

    private AccountImpl() {
    }

    public AccountImpl(String id) {
        this.id = id;
        this.amount = 0;
    }

    @Override
    public String getId() throws RemoteException {
        return id;
    }

    @Override
    public int getAmount() throws RemoteException {
        return amount;
    }

    @Override
    public void setAmount(int amount) throws RemoteException {
        this.amount = amount;
    }
}
