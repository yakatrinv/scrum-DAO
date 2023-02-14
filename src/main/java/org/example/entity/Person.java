package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import static org.example.entity.Util.ID;
import static org.example.entity.Util.NAME;
import static org.example.entity.Util.PERSON;
import static org.example.entity.Util.SURNAME;

/**
 * @author Katerina
 * @version 1.0
 * класс с данными о человеке.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = PERSON)
public class Person {
    /**
     * id человека.
     */
    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
     * имя персоны.
     */
    @Column(name = NAME)
    private String name;
    /**
     * фамилия персоны.
     */
    @Column(name = SURNAME)
    private  String surname;
}
