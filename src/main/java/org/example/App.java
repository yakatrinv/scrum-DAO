package org.example;

import org.example.dao.DAOServicePerson;
import org.example.dao.IDAOPerson;
import org.example.entity.Person;


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
     * string for testing.
     */
    public static final String CREATE_STRING =
            "Поиск вновь созданных сущностей по id";
    /**
     * string for testing.
     */
    public static final String UPDATE_STRING = "Обновление сущности";
    /**
     * string for testing.
     */
    public static final String SELECT_ALL_STRING = "Вывод всех сущностей";

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

        IDAOPerson idaoPerson = new DAOServicePerson();
        for (Person person : personList) {
            int id = idaoPerson.createPerson(person);
            System.out.println(id);
        }

        System.out.println(CREATE_STRING);
        for (Person person : personList) {
            Person findPerson = (Person) idaoPerson.findPerson(person);
            System.out.println(findPerson);
        }

        System.out.println(UPDATE_STRING);
        Person person = personList.listIterator().next();
        person.setName(person.getName() + ADD_FOR_UPDATE);
        person.setSurname(person.getSurname() + ADD_FOR_UPDATE);
        idaoPerson.updatePerson(person);
        System.out.println(person);

        idaoPerson.deletePerson(personList.get(personList.size() - 1));

        System.out.println(SELECT_ALL_STRING);
        List<Object> listPeople = idaoPerson.findAllPerson(Person.class);
        listPeople.stream().map(p -> (Person) p).forEach(System.out::println);
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

