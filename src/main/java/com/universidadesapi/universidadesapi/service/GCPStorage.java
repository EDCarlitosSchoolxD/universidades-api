package com.universidadesapi.universidadesapi.service;

import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;


@Service
public class GCPStorage {


    private String bucket = "universidades-api";
    private String pathFile = "/home/edcarlitos/.credentials/universidades-pruebas-470cbae7e2aa.json";

    


    public String uploadFile(String nombre, byte[] file, String typeFile,String ruta){

        try {
            Credentials credentials = GoogleCredentials.fromStream(new FileInputStream(pathFile));
            Storage storage = StorageOptions.newBuilder().setCredentials(credentials).setProjectId("universidades-pruebas").build().getService();
            BlobId blobId = BlobId.of(bucket, ruta);

            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(typeFile).build();

            return storage.create(blobInfo, file).getMediaLink();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    
        return "";

    }

    public boolean deleteFile(String ruta){
        try {
            Credentials credentials = GoogleCredentials.fromStream(new FileInputStream(pathFile));
            Storage storage = StorageOptions.newBuilder().setCredentials(credentials).setProjectId("universidades-pruebas").build().getService();

            Blob archivo = storage.get(bucket, ruta);
            if(archivo == null){
                return false;
            }
            return storage.delete(bucket, ruta);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return false;

    }

}
