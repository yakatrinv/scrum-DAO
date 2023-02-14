package org.example.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author Katerina
 * @version 1.0
 * класс с данными о человеке.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PersonDto {
    /**
     * id человека.
     */
    private int id;
    /**
     * имя персоны.
     */
    private String name;
    /**
     * фамилия персоны.
     */
    private  String surname;
}
