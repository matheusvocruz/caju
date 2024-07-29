package data.repository;

import io.micronaut.data.mongodb.annotation.MongoRepository;
import io.micronaut.data.repository.CrudRepository;
import model.entity.transaction.Transaction;

@MongoRepository
public interface TransactionRepository extends CrudRepository<Transaction, String> {
}
