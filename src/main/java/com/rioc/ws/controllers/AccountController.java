package com.rioc.ws.controllers;

import com.rioc.ws.models.dao.Account;
import com.rioc.ws.models.dto.AccountCreateUpdate;
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
        //check if they are accounts
        return service.getAllAccounts();
    }

    @GetMapping("/account/{idAccount}")
    public Account getAccount(@PathVariable int idAccount){
        return service.getAccountById(idAccount);
    }

    @DeleteMapping("/account/{idAccount}")
    public void deleteAccountFromId(@PathVariable int idAccount){
        service.deleteAccount(idAccount);
    }

    @DeleteMapping("/account")
    public Account deleteAllAccount(){
        return service.deleteAllAccount();
    }

    @PutMapping("/account/{idAccount}")
    public Account updateAccount(@RequestBody AccountCreateUpdate account, @PathVariable int idAccount){
        return service.updateAccount(account,idAccount);
    }
}
