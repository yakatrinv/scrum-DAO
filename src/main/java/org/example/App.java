package org.example;

import org.example.dao.PersonDAO;
import org.example.dao.impl.PersonDaoImpl;
import org.example.entity.Person;
import org.example.services.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * Application DAO.
 */
public final class App {
    /**
     * name for testing.
     */
    public static final String[] NAMES = {"Mike", "John", "Federico"};
    /**
     * surname for testing.
     */
    public static final String[] SURNAMES = {"Anderson", "Parson", "Clinton"};
    /**
     * random.
     */
    private static final Random RANDOM = new Random();
    /**
     * new name for testing.
     */
    public static final String ADD_FOR_UPDATE = " update";

    /**
     * constructor.
     */
    private App() {
    }

    /**
     * @param args method for running project.
     */
    public static void main(final String[] args) {
        List<Person> personList = fillPersonList();
        PersonDAO personDAO = new PersonDaoImpl();

        for (Person person : personList) {
            personDAO.insert(person);
        }
        System.out.println("Created person");
        for (Person person : personList) {
            Person findPerson = personDAO.selectById(person.getId());
            System.out.println(findPerson);
        }

        Person person = personList.listIterator().next();
        person.setName(person.getName() + ADD_FOR_UPDATE);
        person.setSurname(person.getSurname() + ADD_FOR_UPDATE);
        personDAO.update(person);

        personDAO.delete(personList.get(personList.size() - 1));

        System.out.println("\n\nAll people");
        List<Person> listPeople = personDAO.selectAll();
        listPeople.forEach(System.out::println);

        HibernateUtil.close();
    }

    /**
     * @return fill testing list.
     */
    public static List<Person> fillPersonList() {
        List<Person> personList = new ArrayList<>();
        personList.add(Person.builder()
                .name(getName())
                .surname(getSurname()).build());
        personList.add(Person.builder()
                .name(getName())
                .surname(getSurname()).build());
        personList.add(Person.builder()
                .name(getName())
                .surname(getSurname()).build());
        return personList;
    }

    /**
     * полчение имени для тестирования.
     *
     * @return имя
     */
    private static String getName() {
        return NAMES[RANDOM.nextInt(NAMES.length)];
    }

    /**
     * получение фамилии для тестирования.
     *
     * @return фамилию
     */
    private static String getSurname() {
        return SURNAMES[RANDOM.nextInt(SURNAMES.length)];
    }
}
