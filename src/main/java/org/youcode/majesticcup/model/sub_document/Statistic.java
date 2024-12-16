package org.youcode.majesticcup.model.sub_document;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
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

    @Positive(message = "Assists must be a positive number.")
    private int assists;

    @PositiveOrZero(message = "Yellow cards must be 0 or positive.")
    private int yellowCards;

    @PositiveOrZero(message = "Red cards must be 0 or positive.")
    private int redCards;

    @Positive(message = "Goals must be a positive number.")
    private int goals;
}
