package org.example;

import org.example.service.DAOServicePerson;
import org.example.entity.Person;
import org.example.dataSource.JDBCConnection;


import java.util.ArrayList;
import java.util.List;

/**
 * Application DAO.
 */
public final class App {
    /**
     * name for testing.
     */
    public static final String MIKE = "Mike";
    /**
     * surname for testing.
     */
    public static final String ANDERSON = "Anderson";
    /**
     * name for testing.
     */
    public static final String JOHN = "John";
    /**
     * surname for testing.
     */
    public static final String PARSON = "Parson";
    /**
     * name for testing.
     */
    public static final String FEDERICO = "Federico";
    /**
     * surname for testing.
     */
    public static final String CLINTON = "Clinton";
    /**
     * new name for testing.
     */
    public static final String NEW_NAME = "new name";
    /**
     * index for testing.
     */
    public static final int ONE = 1;
    /**
     * index for testing.
     */
    public static final int ZERO = 0;

    /**
     * constructor.
     */
    private App() {
    }
    /**
     * @param args
     * method for running project.
     */
    public static void main(final String[] args) {
        List<Person> personList = fillPersonList();
        DAOServicePerson daoPerson = new DAOServicePerson();
        daoPerson.setConnection(JDBCConnection.initConnection());

        for (Person person : personList) {
            daoPerson.createPerson(person);
        }
        for (Person person : personList) {
            daoPerson.findPersonById(person);
        }
        personList.get(ONE).setName(NEW_NAME);
        daoPerson.updatePerson(personList.get(ONE));
        daoPerson.deletePerson(personList.get(ZERO));

        JDBCConnection.closeConnection(daoPerson);
    }

    /**
     * @return
     * fill testing list.
     */
    public static List<Person> fillPersonList() {
        List<Person> personList = new ArrayList<>();
        personList.add(Person.builder()
                .name(MIKE)
                .surname(ANDERSON).build());
        personList.add(Person.builder()
                .name(JOHN)
                .surname(PARSON).build());
        personList.add(Person.builder()
                .name(FEDERICO)
                .surname(CLINTON).build());
        return personList;
    }
}
