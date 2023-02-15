package com.universidadesapi.universidadesapi.service;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.universidadesapi.universidadesapi.entity.Image;
import com.universidadesapi.universidadesapi.repository.ImageRepository;

@Service
public class ImageService {
    

    @Autowired
    private GCPStorage gcpStorage;

    @Autowired
    private ImageRepository imageRepository;


    public Image saveImage(Image image){
        String encode = image.getEncode();
        String tipeFile = image.getTipo();
        String nombre = image.getNombre();

        if(encode==null)return null;
        if(tipeFile==null)return null;
        if(nombre==null)return null;


        byte[] imageDecode = Base64.getDecoder().decode(encode);


        String url = gcpStorage.uploadFile(nombre, imageDecode, tipeFile);

        image.setRuta(url);

        return imageRepository.save(image);


    }
}
