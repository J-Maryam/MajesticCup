package org.youcode.majesticcup.model.sub_document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    private ObjectId id;
    private String name;
    private String surname;
    private String position;
    private int number;
}
