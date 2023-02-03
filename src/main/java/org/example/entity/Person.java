package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.anno.MyColumn;
import org.example.anno.MyTable;
import org.example.anno.PrimaryKey;

/**
 * @author Katerina
 * @version 1.0
 * класс с данными о человеке.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@MyTable(name = "person")
public class Person {
    /**
     * id человека.
     */
    @PrimaryKey(name = "id")
    @MyColumn(name = "id")
    private int id;
    /**
     * имя персоны.
     */
    @MyColumn(name = "name")
    private String name;
    /**
     * фамилия персоны.
     */
    @MyColumn(name = "surname")
    private  String surname;
}
