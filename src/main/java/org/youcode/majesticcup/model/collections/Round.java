package org.youcode.majesticcup.model.collections;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "rounds")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Round {
    @Id
    private ObjectId id;
    private int roundNumber;
    private ObjectId competitionId;
    private List<ObjectId> matches;
}
