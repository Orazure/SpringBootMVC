package com.rioc.ws.services.account;

import com.rioc.ws.models.dao.Account;
import com.rioc.ws.models.dto.AccountCreateUpdate;
import com.rioc.ws.models.dto.AccountDto;

import java.util.List;

public interface IAccountService {
    public AccountCreateUpdate postAccount(AccountCreateUpdate account);
    public List<AccountDto> getAllAccounts();
    public Account getAccountById(int idAccount);
    public void deleteAccount(int idAccount);
    public Account deleteAllAccount();
    public Account updateAccount(AccountCreateUpdate account, int idAccount);




}
