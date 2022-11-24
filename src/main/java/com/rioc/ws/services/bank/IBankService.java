package com.rioc.ws.services.bank;

import com.rioc.ws.models.dao.Bank;
import com.rioc.ws.models.dto.BankDto;

import java.util.List;


public interface IBankService {

    public BankDto postBank(BankDto bank, int idAccount);

    public List<BankDto> getBankById(int idBank);


    public void deleteBankById(int idBank);

    public void deleteAllBank();
    public Bank deleteBank(Bank bank);
    public Bank updateBank(BankDto bank, int idBank);

    public List<BankDto> getAllBanks();


}
