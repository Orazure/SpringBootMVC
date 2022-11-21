package com.rioc.ws.controllers;

import com.rioc.ws.models.dao.Account;
import com.rioc.ws.models.dto.AccountDto;
import com.rioc.ws.services.account.IAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;

import java.util.List;
import javax.validation.Valid;


@RestController
public class AccountController
{
    private IAccountService service;

    //private IAdressService adressService;

    public AccountController(IAccountService service)
    {
        super();
        this.service = service;
    }

    @PostMapping("/accounts")
    public ResponseEntity<AccountDto> postAccount (@Valid @RequestBody AccountDto account,BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);


        return new ResponseEntity<>(service.postAccount(account), HttpStatus.CREATED);
    }

    @GetMapping("/accounts")
    public List<AccountDto> getAccounts(){

        return service.getAllAccounts();
    }

    @GetMapping("/account/{idAccount}")
    public Account getAccount(@PathVariable int idAccount){
        return service.getAccountById(idAccount);
    }

    @DeleteMapping("/account/{idAccount}")
    public String deleteAccountFromId(@PathVariable int idAccount){
        service.deleteAccountById(idAccount);
        return "Deletion OK";
    }

    @DeleteMapping("/account")
    public Account deleteAccount(@RequestBody Account account){
        return service.deleteAccount(account);
    }

    @PutMapping("/account")
    public Account updateAccount(@RequestBody Account account){
        return service.updateAccount(account);
    }
}
