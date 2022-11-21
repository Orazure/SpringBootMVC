package com.rioc.ws.services.bank;

import com.rioc.ws.exceptions.ApiException;
import com.rioc.ws.mappers.IBankMapper;
import com.rioc.ws.models.dao.Account;
import com.rioc.ws.models.dao.Bank;
import com.rioc.ws.models.dto.BankDto;
import com.rioc.ws.repositories.IAccountRepository;
import com.rioc.ws.repositories.IBankRepository;
import com.rioc.ws.services.account.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
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
        System.out.println("idAccount : " + idAccount);
        int idA=bankRepository.getAccountId(idAccount);
        System.out.println("idA : " + idA);
        String iban=bank.getBankIban();
        System.out.println("iban : " + iban);
        // if ida exist in account table
        if(idA==idAccount) {
            //get object account
            if(checkBankIban(bank.getBankIban())){
                Account acc=accountRepository.getReferenceById(idAccount);
                Bank bank1 = bankMapper.bankDtoToBank(bank);
                bank1.setAccount(acc);
                bankRepository.save(bank1);
                return bank;
            }else{
                System.out.println("Iban ne respecte pas le format");
                throw new ApiException("Iban ne respecte pas le format", HttpStatus.NOT_ACCEPTABLE);
            }
        }
        return null;
    }

    public List<BankDto> getAllBanks(){
        return bankRepository.findAll().stream().map(bankMapper::bankToDtoBank).collect(Collectors.toList());
    }



    public Bank getBankById(int id) {
        return bankRepository.getReferenceById(id);
    }

    public void deleteBankById(int id) {
        bankRepository.deleteById(id);
    }

    @Override
    public Bank deleteBank(Bank bank) {
        return null;
    }

    @Override
    public Bank updateBank(Bank bank) {
        return null;
    }


    //check if bankIban respect the format of bankIban (FR00 0000 0000 0000 0000 0000 000)
    public boolean checkBankIban(String bankIban) {
        if (bankIban.length() != 27)
            return false;
        if (bankIban.charAt(2) != ' ')
            return false;
        if (bankIban.charAt(5) != ' ')
            return false;
        if (bankIban.charAt(8) != ' ')
            return false;
        if (bankIban.charAt(11) != ' ')
            return false;
        if (bankIban.charAt(14) != ' ')
            return false;
        if (bankIban.charAt(17) != ' ')
            return false;
        if (bankIban.charAt(20) != ' ')
            return false;
        if (bankIban.charAt(23) != ' ')
            return false;
        if (bankIban.charAt(26) != ' ')
            return false;
        return true;
    }
}
