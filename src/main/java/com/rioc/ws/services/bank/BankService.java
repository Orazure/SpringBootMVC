package com.rioc.ws.services.bank;

import com.rioc.ws.exceptions.ApiException;
import com.rioc.ws.mappers.IBankMapper;
import com.rioc.ws.models.dao.Account;
import com.rioc.ws.models.dao.Bank;
import com.rioc.ws.models.dto.BankDto;
import com.rioc.ws.repositories.IAccountRepository;
import com.rioc.ws.repositories.IBankRepository;

import org.iban4j.*;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class BankService implements IBankService {
    private final IBankMapper bankMapper;
    private final IBankRepository bankRepository;
    private final IAccountRepository accountRepository;

    public BankService(IBankRepository bankRepository, IBankMapper bankMapper, IAccountRepository accountRepository) {
        super();
        this.bankRepository = bankRepository;
        this.accountRepository = accountRepository;
        this.bankMapper = bankMapper;
    }

    public BankDto postBank(BankDto bank, int idAccount) {
        List<Account> idA=accountRepository.findByAccountId(idAccount);
        // if ida exist in account table
        if(idA.size() > 0){
            // if iban is not exist in bank table
            String ibanIsExist = bankRepository.findByBankIban(bank.getBankIban()).toString();
            if (Objects.equals(ibanIsExist, "[]")) {
                //get object bank and save it
                if (checkBankIban(bank.getBankIban())) {
                    Account acc = accountRepository.getReferenceById(idAccount);
                    Bank bank1 = bankMapper.bankDtoToBank(bank);
                    bank1.setAccount(acc);
                    bankRepository.save(bank1);
                    return bank;
                } else {
                    System.out.println("Iban ne respecte pas le format");
                    throw new ApiException("Iban ne respecte pas le format", HttpStatus.NOT_ACCEPTABLE);
                }
            } else {
                System.out.println("Iban existe déjà");
                throw new ApiException("Iban existe déjà", HttpStatus.NOT_ACCEPTABLE);
            }
        }else {
            System.out.println("Account not found");
            throw new ApiException("Account not found", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    public List<BankDto> getAllBanks(){
        if(bankRepository.findAll().isEmpty()){
            throw new ApiException("No banks found", HttpStatus.NOT_FOUND);
        }
        return bankRepository.findAll().stream().map(bankMapper::bankToDtoBank).collect(Collectors.toList());
    }

    public List<BankDto> getBankById(int id) {
        Optional<Bank> banksList = bankRepository.findById(id);
        if(banksList.isEmpty()){
            throw new ApiException("No banks found", HttpStatus.NOT_FOUND);
        }
        return banksList.stream().map(bankMapper::bankToDtoBank).toList();
    }

    public void deleteBankById(int id) {
        try {
            bankRepository.findById(id).orElseThrow(() -> new ApiException("Bank not found", HttpStatus.NOT_FOUND));
            bankRepository.deleteById(id);
        } catch (Exception e) {
            throw new ApiException("Bank not found", HttpStatus.NOT_FOUND);
        }
    }



    public Bank updateBank(BankDto bank, int id) {
        Bank bank1 = bankRepository.findById(id).orElseThrow(() -> new ApiException("Bank not found", HttpStatus.NOT_FOUND));
        if(bank.getBankIban() != null) {
            if(checkBankIban(bank.getBankIban())) {
                bank1.setBankIban(bank.getBankIban());
            } else {
                throw new ApiException("Iban ne respecte pas le format", HttpStatus.NOT_ACCEPTABLE);
            }
        }
        if(bank.getBankName() != null)
            bank1.setBankName(bank.getBankName());
        if(bank.getBankCountryCode() != null)
            bank1.setBankCountryCode(bank.getBankCountryCode());
        if(bank.getBankCode() != null)
            bank1.setBankCode(bank.getBankCode());
        return bankRepository.save(bank1);
    }

    //delete all banks
    public void deleteAllBank() {
        bankRepository.deleteAll();
    }

    @Override
    public Bank deleteBank(Bank bank) {
        return null;
    }

    //check if bankIban respect the format of bankIban (FR00 0000 0000 0000 0000 0000 000)
    public boolean checkBankIban(String bankIban) {
        try {
            IbanUtil.validate(bankIban);
            return true;
            // valid
        } catch (IbanFormatException |
                 InvalidCheckDigitException |
                 UnsupportedCountryException e) {
            return false;
        }
    }
}
