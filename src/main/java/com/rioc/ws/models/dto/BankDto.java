package com.rioc.ws.models.dto;

import com.rioc.ws.models.dao.Account;
import com.rioc.ws.models.dao.Address;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data @NoArgsConstructor @AllArgsConstructor
@Builder
public class BankDto {
    private String bankName;
    private String bankIban;
    private String bankCountryCode;
    private String bankCode;



}
