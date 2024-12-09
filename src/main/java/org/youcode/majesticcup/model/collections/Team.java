package org.youcode.majesticcup.model.collections;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.youcode.majesticcup.model.sub_document.Player;

import java.util.List;

@Document(collection = "teams")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Team {

    @Id
    private ObjectId id;
    private String name;
    private String city;

    private List<Player> players;
}
