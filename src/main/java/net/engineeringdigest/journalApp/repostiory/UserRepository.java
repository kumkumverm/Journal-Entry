package net.engineeringdigest.journalApp.repostiory;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;
import net.engineeringdigest.journalApp.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface UserRepository extends MongoRepository<User,ObjectId> {
    User findByUserName(String userName);
}
