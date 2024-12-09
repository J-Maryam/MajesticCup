package org.youcode.majesticcup.model.collections;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "competitions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Competition {
    @Id
    private ObjectId id;
    private String name;
    private int numberOfTeams;
    private List<ObjectId> teams;
    private int currentRound;
    private List<ObjectId> rounds;
}
