package team404.aster.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import team404.aster.domain.User;

public interface UserRepository extends MongoRepository<User, String> {
}
