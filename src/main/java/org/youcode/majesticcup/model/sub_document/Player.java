package org.youcode.majesticcup.model.sub_document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    @Field("_id")
    @Id
    private ObjectId id;

    @NotBlank(message = "Player's name cannot be blank.")
    private String name;

    @NotBlank(message = "Player's surname cannot be blank.")
    private String surname;

    @NotBlank(message = "Player's position cannot be blank.")
    private String position;

    @Positive(message = "Player's number must be a positive integer.")
    private int number;

    public Player(String name,  String surname, String position,  int number) {
        this.name = name;
        this.surname = surname;
        this.position = position;
        this.number = number;
    }
}
