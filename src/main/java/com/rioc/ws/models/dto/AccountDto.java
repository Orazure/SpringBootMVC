package com.rioc.ws.models.dto;

import com.rioc.ws.models.dao.Bank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;


@Data @NoArgsConstructor @AllArgsConstructor
@Builder
public class AccountDto {

    @NotNull
    @Size(min = 2)
    private String firstName;

    @NotNull
    @Size(min = 2)
    private String lastName;

    private AddressDto address;

    private Set<BankDto> banks;



}
