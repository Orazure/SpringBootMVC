package com.rioc.ws.mappers;

import com.rioc.ws.models.dao.*;
import com.rioc.ws.models.dto.*;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface IBankMapper {
    BankDto bankToDtoBank (Bank bank);
    Bank bankDtoToBank (BankDto bankDto);
}
