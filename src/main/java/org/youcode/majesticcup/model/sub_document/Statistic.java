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
public class Statistic {
    private ObjectId playerId;
    private int goals;
    private int assists;
    private int yellowCards;
    private int redCards;
}
