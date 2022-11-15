package com.rioc.ws.services.address;


import com.rioc.ws.exceptions.ApiException;
import com.rioc.ws.mappers.IAccountMapper;
import com.rioc.ws.models.dao.Account;
import com.rioc.ws.models.dto.AccountDto;
import com.rioc.ws.models.dto.AddressDto;
import com.rioc.ws.repositories.IAccountRepository;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdressService implements IAdressService {


    public AdressService() {
        super();
    }

    public boolean CheckExistingAdress(AddressDto address) {

        String response = WebClient.create()
                .get()
                .uri("https://api-adresse.data.gouv.fr/search/?q=" + address.getStreet().replaceAll("\\s+", "+") + "&postcode=" + address.getZipCode().replaceAll("\\s+", "+") + "&limit=1")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        if (response.contains("features")) {
            for (String s : response.split(",")) {
                if (s.contains("score")) {
                    String[] score = s.split(":");
                    if (Double.parseDouble(score[1]) > 0.8) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //create model for address response with webclient



}


