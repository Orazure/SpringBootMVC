package com.rioc.ws.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.rioc.ws.models.dao.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface IBankRepository extends JpaRepository<Bank,Integer>{
    List<Bank> findByBankIban(String bankIban);


    // get id account
    @Query("SELECT a FROM Bank a WHERE a.account.accountId = ?1")
    List<Bank> findByAccountId(int accountId);

    @Query("SELECT a FROM Bank a WHERE a.account.accountId = ?1")
    int findByAccountId1(int accountId);


}

