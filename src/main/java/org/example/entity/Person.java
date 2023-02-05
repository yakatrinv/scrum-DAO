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

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = Constant.PERSON)
public final class Person {
    /**
     * id человека.
     */
    @Id
    @Column(name = Constant.ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
     * имя персоны.
     */
    @Column(name = Constant.NAME)
    private String name;
    /**
     * фамилия персоны.
     */
    @Column(name = Constant.SURNAME)
    private  String surname;
}
