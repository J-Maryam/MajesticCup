package org.youcode.majesticcup.model.collections;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.youcode.majesticcup.model.sub_document.MatchResult;

@Document(collection = "matches")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Match {
    @Id
    private ObjectId id;
    private int round;
    private ObjectId team1;
    private ObjectId team2;
    private MatchResult result;
    private ObjectId winner;
}
