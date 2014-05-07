package ru.ifmo.ctddev.shalamov.task9;

import java.awt.*;
import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by viacheslav on 07.05.14.
 */
public interface Account extends Remote, Serializable {
    public String getId() throws RemoteException;

    public int getAmount() throws RemoteException;

    public void setAmount(int amount) throws RemoteException;
}
