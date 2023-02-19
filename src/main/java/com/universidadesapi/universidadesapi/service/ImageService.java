package com.universidadesapi.universidadesapi.service;

import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.universidadesapi.universidadesapi.Abstracs.ContainImage;
import com.universidadesapi.universidadesapi.entity.Image;
import com.universidadesapi.universidadesapi.entity.Municipio;
import com.universidadesapi.universidadesapi.entity.Universidad;
import com.universidadesapi.universidadesapi.repository.ImageRepository;

@Service
public class ImageService {
    

    @Autowired
    private GCPStorage gcpStorage;

    @Autowired
    private ImageRepository imageRepository;


    @Autowired(required = true)
    FilesUtils filesUtils;

    

    public Image saveImage(Image image,String carpeta){
        String encode = image.getEncode();
        String tipeFile = image.getTipo();
        String nombre = image.getNombre();

        
        if(encode==null)return null;
        if(tipeFile==null)return null;
        if(nombre==null)return null;

        //Decodifico el archivo
        byte[] imageDecode = Base64.getDecoder().decode(encode);

        //Genero un nombre aleatorio para la imagen
        String nameRandom = filesUtils.generateNameFile();
        String extension = filesUtils.checkFileExtension(nombre);

        String fileName = filesUtils.nameFile(nameRandom, extension);

        //Se crea una ruta para la imagen y se guarda en google cloud
        String rutaCloud = createRuta(carpeta, fileName);
        String url = gcpStorage.uploadFile(nombre, imageDecode, tipeFile, rutaCloud);


        //Se setean los valores para guardar en la base de datos
        image.setRuta(url);
        image.setRutaCloud(rutaCloud);
        image.setNombre(fileName);

        return imageRepository.save(image);


    }


    public Image updateImage(Image image){
        String encode = image.getEncode();
        String tipeFile = image.getTipo();
        String nombre = image.getNombre();

        if(encode==null)return null;
        if(tipeFile==null)return null;
        if(nombre==null)return null;

        Long id = image.getId();

        Optional<Image> optionalImage = imageRepository.findById(id);

        if(!optionalImage.isPresent())return null;

        image.setRutaCloud(optionalImage.get().getRutaCloud());
        image.setNombre(optionalImage.get().getNombre());

        byte[] imageDecode = Base64.getDecoder().decode(encode);

        String url = gcpStorage.uploadFile(nombre,imageDecode,tipeFile,image.getRutaCloud());
        image.setRuta(url);
        return image;

    }

    public boolean deleteImage(Image image){
        return gcpStorage.deleteFile(image.getRutaCloud());
    }



     public void deleteAllImage(ContainImage[] containImage ){
        Arrays.asList(containImage).stream().forEach(contain -> {
            this.deleteImage(contain.getImage());
        });;

    }





    public String createRuta(String ruta,String fileName){

        if(fileName ==null)return "";

        
         return ruta+"/"+fileName;
    }

}
