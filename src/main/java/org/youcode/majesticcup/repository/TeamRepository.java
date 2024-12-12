package org.youcode.majesticcup.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.youcode.majesticcup.model.collections.Team;

@Repository
public interface TeamRepository extends MongoRepository<Team, ObjectId> {
}
