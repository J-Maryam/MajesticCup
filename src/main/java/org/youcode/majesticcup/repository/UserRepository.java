package org.youcode.majesticcup.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.youcode.majesticcup.model.collections.AppUser;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<AppUser, ObjectId> {
    Optional<AppUser> findByUsername(String username);
}
