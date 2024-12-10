package org.youcode.majesticcup.model.sub_document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatchResult {
    private int team1Goals;
    private int team2Goals;
    private List<Statistic> statistics;
}
