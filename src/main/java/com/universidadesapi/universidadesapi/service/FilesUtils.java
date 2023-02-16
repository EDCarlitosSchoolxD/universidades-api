package com.universidadesapi.universidadesapi.service;

import org.springframework.stereotype.Service;



@Service
public class FilesUtils {
    

    public String generateNameFile(){
            String letters = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZabcdefghijklmnñopqrstuvwxyz1234567890";
            String name = "";


            for(int i = 0; i <= 30; i++){
                int num = (int) Math.ceil(Math.random()*63);
                name += letters.charAt(num);
            }
            
            return name;
    }

    public String checkFileExtension(String fileName){

        if(fileName != null && fileName.contains(".")){
            String[] extensionList = {"jpg","png","jpeg","pdf"};

            for(String extension: extensionList){
                if(fileName.endsWith(extension)){
                    return extension;
                }
            }

        }
        return null;

    }


    public String nameFile(String fileName, String extension){
        return fileName+"."+extension;
    }

}
