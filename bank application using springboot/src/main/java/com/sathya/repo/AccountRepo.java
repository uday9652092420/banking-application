package com.sathya.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sathya.model.Account;

@Repository
public interface AccountRepo extends CrudRepository<Account, Integer> {

}
