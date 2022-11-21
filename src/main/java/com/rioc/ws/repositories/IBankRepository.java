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
    @Query("SELECT a.accountId FROM Account a WHERE a.accountId = ?1")
    int getAccountId(int accountId);



}

