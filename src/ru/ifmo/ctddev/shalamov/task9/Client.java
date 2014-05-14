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
     * номер счета, изменение суммы счета, возвращаемый тип
     *
     * @param args
     */
    public static void main(String[] args) {
        Bank bank = null;

        if (args.length < 6) {
            System.out.println("usage: \n Client name surname passportNum account'sId modification wayToshareObject {remote, serialisation})");
            System.exit(1);
        }
        if (!("remote".equals(args[5]) || "serialisation".equals(args[5]))) {
            System.out.println("usage: \n Client name surname passportNum account'sId modification wayToshareObject {remote, serialisation})");
            System.exit(1);
        }

        try {
            Integer.parseInt(args[4]);
        } catch (NumberFormatException e) {
            System.out.println("usage: \n Client name surname passportNum account'sId modification wayToshareObject {remote, serialisation})");
            System.exit(1);
        }

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

        if ("remote".equals(args[5])) {
            try {
                RemotePerson person = (RemotePerson) bank.getPerson(args[0], args[1], args[2], args[5]);
                System.out.println("created person: " + person.getName());


                Account account = person.getAccount(args[3]);
                System.out.println("get account = <" + account.getId() + ", " + account.getAmount() + ">");

                person.setToAccount(args[3], Integer.parseInt(args[4]) + account.getAmount());
                account = person.getAccount(args[3]);
                System.out.println("changed <" + account.getId() + "> amount = " + account.getAmount());


                person = (RemotePerson) bank.getPerson(args[0], args[1], args[2], args[5]);
                System.out.println("retreaved person: " + person.getName());
                account = person.getAccount(args[3]);
                System.out.println("get account = <" + account.getId() + ", " + account.getAmount() + ">");
//
//                person.setToAccount(args[3], Integer.parseInt(args[4]) + account.getAmount());
//                account = person.getAccount(args[3]);
//                System.out.println("changed <" + account.getId() + "> amount = " + account.getAmount());
//
//                System.out.println();
//                account = person.getAccount(args[3]);
//                System.out.println("get account = <" + account.getId() + ", " + account.getAmount() + ">");
//                System.out.println();
//
//                person = (RemotePerson) bank.getPerson(args[0], args[1], args[2], args[5]);
//                System.out.println("retrieved existing person: " + person.getName());
//                account = person.getAccount(args[3]);
//                System.out.println("get account = <" + account.getId() + ", " + account.getAmount() + ">");
//                System.out.println();
//
//
//                person.setToAccount("second", Integer.parseInt(args[4]));
//                account = person.getAccount("second");
//                System.out.println("changed <" + account.getId() + "> amount = " + account.getAmount());
//
//                System.out.println("before commit:");
//                account = person.getAccount(args[3]);
//                System.out.println("<" + account.getId() + "> amount = " + account.getAmount());
//                account = person.getAccount("second");
//                System.out.println("<" + account.getId() + "> amount = " + account.getAmount());
//
//                System.out.println("after commit");
//                bank.commitPerson(person);
//                account = person.getAccount(args[3]);
//                System.out.println("<" + account.getId() + "> amount = " + account.getAmount());
//                account = person.getAccount("second");
//                System.out.println("<" + account.getId() + "> amount = " + account.getAmount());


            } catch (RemoteException e) {
                System.out.println(e.getMessage());
                System.exit(1);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }
        } else {

            try {
                LocalPerson person = (LocalPersonImpl) bank.getPerson(args[0], args[1], args[2], args[5]);
                System.out.println("local person: " + person.getName());

                Account account = person.getAccount(args[3]);
                System.out.println("get account = <" + account.getId() + ", " + account.getAmount() + ">");

                person.setToAccount(args[3], Integer.parseInt(args[4]) + account.getAmount());
                account = person.getAccount(args[3]);
                System.out.println("changed <" + account.getId() + "> amount = " + account.getAmount());
            } catch (RemoteException e) {
                System.out.println(e.getMessage());
                System.exit(1);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }
        }
    }

}
