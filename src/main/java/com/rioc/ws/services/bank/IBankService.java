package com.rioc.ws.services.bank;

import com.rioc.ws.models.dao.Bank;
import com.rioc.ws.models.dto.BankDto;

import java.util.List;


public interface IBankService {

    public BankDto postBank(BankDto bank, int idAccount);

    public Bank getBankById(int idBank);


    public void deleteBankById(int idBank);
    public Bank deleteBank(Bank bank);
    public Bank updateBank(Bank bank);

    public List<BankDto> getAllBanks();


}
