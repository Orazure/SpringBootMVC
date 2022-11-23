package com.rioc.ws.controllers;

import com.rioc.ws.models.dao.*;
import com.rioc.ws.models.dto.*;
import com.rioc.ws.services.bank.IBankService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;


import javax.validation.Valid;
import java.util.List;


@RestController
public class BankController {
    private final IBankService bankService;

    public BankController(IBankService bankService) {
        super();
        this.bankService = bankService;
    }


    //create a bank account
    // ton compte il est existe ,

    // create a bank with an account
    @PostMapping("/banks/{idAccount}/accounts")
    public ResponseEntity<BankDto> postBankWithAccount (@Valid @RequestBody BankDto bank,BindingResult bindingResult, @PathVariable int idAccount)
    {
        if(bindingResult.hasErrors())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(bankService.postBank(bank,idAccount), HttpStatus.CREATED);
    }

    // get a bank by id
    @GetMapping("/banks/{id}")
    public List<BankDto> getBankById(@PathVariable int id)
    {
        return bankService.getBankById(id);
    }

    //delete a bank by id
    @DeleteMapping("/banks/{id}")
    public ResponseEntity<String> deleteBankById(@PathVariable int id)
    {
        bankService.deleteBankById(id);
        return new ResponseEntity<>("Delete OK", HttpStatus.OK);
    }

    //get all banks
    @GetMapping("/banks")
    public List<BankDto> getAllBanks()
    {
        return bankService.getAllBanks();
    }

    //update a bank
    @PutMapping("/banks/{id}")
    public Bank updateBank(@Valid @RequestBody BankDto bank, @PathVariable int id)
    {
        return bankService.updateBank(bank,id);
    }


}
