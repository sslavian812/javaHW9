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
     * номер счета, изменение суммы счета, ТИП ПЕРЕДАЧИ.
     *
     * @param args
     */
    public static void main(String[] args) {
        Bank bank = null;

        if (args.length < 6) {
            System.out.println("usage: \n Client name surname passportNum account'sId modification wayToshareObject {rmi, serialisation})");
            System.exit(1);
        }
        if (!("rmi".equals(args[5]) || "serialisation".equals(args[5]))) {
            System.out.println("usage: \n Client name surname passportNum account'sId modification wayToshareObject {rmi, serialisation})");
            System.exit(1);
        }
        //  System.setSecurityManager(new SecurityManager());

        if ("rmi".equals(args[5])) {
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
                Person person = bank.getPerson(args[0], args[1], args[2], 1);
                System.out.println("created person: " + person.getName());
                Account account = person.getAccount(args[3]);
                System.out.println("get account = <" + account.getId() + ", " + account.getAmount() + ">");
                account.setAmount(account.getAmount() + Integer.parseInt(args[4]));
                System.out.println("changed amount = " + account.getAmount());

            } catch (RemoteException e) {
                System.out.println(e.getMessage());
                System.exit(1);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }
        }
        else
        {

        }
    }

}
