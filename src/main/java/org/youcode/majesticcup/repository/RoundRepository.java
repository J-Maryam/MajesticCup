package org.youcode.majesticcup.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.youcode.majesticcup.model.collections.Competition;
import org.youcode.majesticcup.model.collections.Round;

import java.util.List;

@Repository
public interface RoundRepository extends MongoRepository<Round, ObjectId> {
    List<Round> findByCompetitionId(Competition competition);
}
