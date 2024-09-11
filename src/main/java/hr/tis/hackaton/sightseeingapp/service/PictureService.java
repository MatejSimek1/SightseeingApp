package hr.tis.hackaton.sightseeingapp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PictureService {



    //String uploadPicture(MultipartFile file) throws IOException;

    void getPicturePath(String path) throws IOException;

    public byte[] getPictureByLocationAndAttraction(String location, String attraction, Long id) throws IOException;

}
