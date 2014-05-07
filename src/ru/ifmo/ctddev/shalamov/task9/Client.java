package ru.ifmo.ctddev.shalamov.task9;


import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by viacheslav on 07.05.14.
 */
public class Client {
    /**
     * Аргументы командной строки: имя, фамилия, номер паспорта физического лица,
     * номер счета, изменение суммы счета.
     *
     * @param args
     */
    public static void main(String[] args) {
        Bank bank = null;
      //  System.setSecurityManager(new SecurityManager());

        try {
            Registry registry = LocateRegistry.getRegistry(null, 8080);
            bank = (Bank) registry.lookup("Bank");
        } catch (NotBoundException e) {
            System.out.println("not bound");
            System.exit(1);
        } catch (RemoteException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

        try {
            Person person = bank.getPerson("nA", "sA", "pA", 1);
            System.out.println("created person: " + person.getName());
            Account account = person.getAccount("first");
            account.setAmount(1000);
            System.out.println("changed amount = " + account.getAmount());
            account.setAmount(2);
            System.out.println("changed amount = " + person.getAccount("first").getAmount());

        } catch (RemoteException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

}
