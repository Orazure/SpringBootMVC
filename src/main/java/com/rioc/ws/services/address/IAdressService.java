package com.rioc.ws.services.address;

import com.rioc.ws.models.dto.AddressDto;

public interface IAdressService {
    boolean CheckExistingAdress(AddressDto address);
}
