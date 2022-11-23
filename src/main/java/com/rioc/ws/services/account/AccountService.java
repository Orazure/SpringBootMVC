package com.rioc.ws.services.account;

import com.rioc.ws.exceptions.ApiException;
import com.rioc.ws.mappers.IAccountCreateUpdate;
import com.rioc.ws.mappers.IAccountMapper;
import com.rioc.ws.mappers.IAddressMapper;
import com.rioc.ws.models.dao.Account;
import com.rioc.ws.models.dto.AccountCreateUpdate;
import com.rioc.ws.models.dto.AccountDto;
import com.rioc.ws.repositories.IAccountRepository;
import com.rioc.ws.services.address.IAdressService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService implements IAccountService {
    private final IAccountRepository repository;
    private final IAccountMapper mapper;

    private final IAdressService adressService;
    private final IAddressMapper addressMapper;

    private final IAccountCreateUpdate accountCreateUpdate;

    public AccountService(IAccountRepository repository, IAccountMapper mapper, IAdressService adressService, IAddressMapper addressMapper, IAccountCreateUpdate accountCreateUpdate) {
        super();
        this.repository = repository;
        this.mapper = mapper;
        this.adressService = adressService;
        this.addressMapper = addressMapper;
        this.accountCreateUpdate = accountCreateUpdate;
    }


    public AccountCreateUpdate postAccount(AccountCreateUpdate account){
        boolean is_existing = checkExistingAccount(account.getFirstName(),account.getLastName());
        System.out.println("exist : " + is_existing);
        if (is_existing) {
            System.out.println("existe déjà");
            throw new ApiException("The account arleady exist.", HttpStatus.NOT_ACCEPTABLE);
        }
        if (!adressService.CheckExistingAdress(account.getAddress())) {
            System.out.println("adresse non valide");
            throw new ApiException("The address is not valid.", HttpStatus.NOT_ACCEPTABLE);
        }
        repository.save(accountCreateUpdate.accountDtoToAccount(account));
        return account;
    }


    public Account getAccountById(int idAccount){
        Account account = repository.findById(idAccount).orElseThrow(() -> new ApiException("Account not found", HttpStatus.NOT_ACCEPTABLE));
        return repository.getReferenceById(idAccount);
    }


    public void deleteAccount(int idAccount){
        System.out.println("delete account");
        try {
            Account account = repository.getReferenceById(idAccount);
            System.out.println("account : " + account);
            if (account == null) {
                System.out.println("account not found");
                throw new ApiException("Account not found.", HttpStatus.NOT_ACCEPTABLE);
            }
            repository.deleteById(idAccount);
        } catch (Exception e) {
            System.out.println("error delete account");
            throw new ApiException("The account does not exist.", HttpStatus.NOT_ACCEPTABLE);
        }

    }

    public Account deleteAllAccount(){
        repository.deleteAll();
        return null;
    }


    public List<AccountDto> getAllAccounts(){
        //get all accounts with bank
        if(repository.findAll().isEmpty()){
            throw new ApiException("No accounts found", HttpStatus.NOT_ACCEPTABLE);
        }
        return repository.findAll().stream().map(mapper::accountToDtoAccount).collect(Collectors.toList());
    }

    public Account updateAccount(AccountCreateUpdate account, int idAccount) {
        // update account and search if the account exist and the account variable is changed
        Account account1 = repository.findById(idAccount).orElseThrow(() -> new ApiException("Account not found", HttpStatus.NOT_ACCEPTABLE));
        if (account1 == null) {
            System.out.println("Le compte n'existe pas");
            throw new ApiException("The address is not valid.", HttpStatus.NOT_ACCEPTABLE);
        }
        if (account1.equals(accountCreateUpdate.accountDtoToAccount(account))){
            System.out.println("Aucun changement sur le compte");
            throw new ApiException("The address is not valid.", HttpStatus.NOT_ACCEPTABLE);
        }
        if (!adressService.CheckExistingAdress(account.getAddress())) {
            System.out.println("adresse non valide");
            throw new ApiException("The address is not valid.", HttpStatus.NOT_ACCEPTABLE);
        }
        account1.setFirstName(account.getFirstName());
        account1.setLastName(account.getLastName());
        account1.setAddress(addressMapper.addressDtoToAddress(account.getAddress()));
        return repository.save(account1);
    }

    private boolean checkExistingAccount (String accountFirstName, String accountLastName){
        // test if the combo of first and last name arleady exist in the db
        List<Account> accounts = repository.findByFirstNameAndLastName(accountFirstName, accountLastName);
        System.out.println(accounts);
        return !accounts.isEmpty();
    }

}
