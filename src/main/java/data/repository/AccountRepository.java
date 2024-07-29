package data.repository;

import io.micronaut.data.mongodb.annotation.MongoRepository;
import io.micronaut.data.repository.CrudRepository;
import model.entity.account.Account;

import java.util.Optional;

@MongoRepository
public interface AccountRepository extends CrudRepository<Account, String> {
    Optional<Account> findById(String id);
}