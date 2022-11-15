package com.rioc.ws.services.account;

import com.rioc.ws.models.dao.Account;
import com.rioc.ws.models.dto.AccountDto;

import java.util.List;

public interface IAccountService {
    public AccountDto postAccount(AccountDto account);
    public List<AccountDto> getAllAccounts();
    public Account getAccountById(int idAccount);
    public void deleteAccountById(int idAccount);
    public Account deleteAccount(Account account);
    public Account updateAccount(Account account);


}
