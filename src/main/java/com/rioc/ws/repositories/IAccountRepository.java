package com.rioc.ws.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.rioc.ws.models.dao.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IAccountRepository extends JpaRepository<Account,Integer>{
    List<Account> findByLastName(String lastName);
    List<Account> findByfirstName(String firstName);
    List<Account> findByFirstNameAndLastName(String firstName, String lastName);
}
