package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public final class PersonDTO {
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
