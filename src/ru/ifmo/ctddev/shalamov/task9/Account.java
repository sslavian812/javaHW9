package ru.ifmo.ctddev.shalamov.task9;

import java.awt.*;
import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by viacheslav on 07.05.14.
 */
public interface Account extends Serializable {
    /**
     * Provides an Id of account
     *
     * @return Id of account
     */
    public String getId();

    /**
     * Provides a Amount of account
     *
     * @return Amount of account
     */
    public int getAmount();

    /**
     * Sets account's amount.
     *
     * @param amount amount to de set
     */
    public void setAmount(int amount);
}
