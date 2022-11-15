package com.rioc.ws.mappers;

import com.rioc.ws.models.dao.Account;
import com.rioc.ws.models.dto.AccountDto;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface IAccountMapper {
    AccountDto accountToDtoAccount (Account account);
    Account accountDtoToAccount (AccountDto accountDto);

}
