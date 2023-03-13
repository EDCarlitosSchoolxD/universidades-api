package com.universidadesapi.universidadesapi.service;

import org.springframework.stereotype.Service;

@Service
public class StringUtils {
    
    public String slug(String string){
        return string.toLowerCase().replaceAll("[^a-z0-9-]", "-");
    }

}
