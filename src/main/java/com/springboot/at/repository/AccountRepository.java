package com.springboot.at.repository;


import com.springboot.at.entity.Account;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

  Optional<Account> findByName(String name);
}
