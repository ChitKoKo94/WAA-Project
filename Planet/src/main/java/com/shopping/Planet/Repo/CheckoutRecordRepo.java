package com.shopping.Planet.Repo;

import com.shopping.Planet.Domain.CheckoutRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckoutRecordRepo extends MongoRepository<CheckoutRecord, String> {
}
