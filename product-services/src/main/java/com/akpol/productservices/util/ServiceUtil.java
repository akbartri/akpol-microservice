package com.akpol.productservices.util;

import com.akpol.commons.model.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ServiceUtil {
    @Autowired
    private RestTemplate restTemplate;

    public ResponseDTO getDTO(String url) {
        return restTemplate.getForObject(url, ResponseDTO.class);
    }
}