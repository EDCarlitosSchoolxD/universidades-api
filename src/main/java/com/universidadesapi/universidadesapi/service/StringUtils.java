package com.universidadesapi.universidadesapi.service;

import java.text.Normalizer;

import org.springframework.stereotype.Service;

@Service
public class StringUtils {
    
    public String slug(String string){
        return cleanString(string).toLowerCase().replaceAll("[^a-z0-9-]", "-");
    }


    public static String cleanString(String texto) {
        texto = Normalizer.normalize(texto, Normalizer.Form.NFD);
        texto = texto.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return texto;
    }
}
