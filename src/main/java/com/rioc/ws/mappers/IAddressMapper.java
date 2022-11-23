package com.rioc.ws.mappers;

import com.rioc.ws.models.dao.Address;
import com.rioc.ws.models.dto.AddressDto;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface IAddressMapper {
    public AddressDto addressToAddressDto(Address address);
    public Address addressDtoToAddress(AddressDto addressDto);

}
