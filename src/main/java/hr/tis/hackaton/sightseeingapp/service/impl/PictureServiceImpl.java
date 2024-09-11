package hr.tis.hackaton.sightseeingapp.service.impl;

import hr.tis.hackaton.sightseeingapp.exception.NoPictureFoundException;
import hr.tis.hackaton.sightseeingapp.model.Picture;
import hr.tis.hackaton.sightseeingapp.repository.PictureRepository;
import hr.tis.hackaton.sightseeingapp.repository.ReviewRepository;
import hr.tis.hackaton.sightseeingapp.service.PictureService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
public class PictureServiceImpl implements PictureService {

    private static final String IMAGE_DIR  = System.getProperty("user.dir") + "/image"+ "/" ;



    private final PictureRepository  pictureRepository;

    public PictureServiceImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }


    @Override
    public void getPicturePath(String path) throws IOException {
        return;
    }

    @Override
    public byte[] getPictureByLocationAndAttraction(String location, String attractionUrlName, Long id) throws IOException {
        Picture picture = pictureRepository
                .findByAttractionNameAndLocation(attractionUrlName, location, id).orElseThrow(() -> new NoPictureFoundException("Picture does not exist"));
        String path =  PictureServiceImpl.IMAGE_DIR + picture.getPicturePath();

        return Files.readAllBytes(new File(path).toPath());
    }

    public static void main(String[] args) {
        System.out.println(IMAGE_DIR);
    }

}

