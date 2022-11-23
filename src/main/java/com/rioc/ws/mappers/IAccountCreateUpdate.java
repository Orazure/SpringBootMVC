package com.rioc.ws.mappers;

import com.rioc.ws.models.dao.Account;
import com.rioc.ws.models.dto.AccountCreateUpdate;
import com.rioc.ws.models.dto.AccountDto;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface IAccountCreateUpdate {

    AccountCreateUpdate accountToDtoAccount (Account account);
    Account accountDtoToAccount (AccountCreateUpdate accountDto);
}
