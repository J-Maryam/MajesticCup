package org.youcode.majesticcup.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.youcode.majesticcup.model.collections.Competition;

@Repository
public interface CompetitionRepository extends MongoRepository<Competition, ObjectId> {
}
