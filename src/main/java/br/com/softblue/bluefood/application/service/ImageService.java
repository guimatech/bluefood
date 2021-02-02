package br.com.softblue.bluefood.application.service;

import br.com.softblue.bluefood.application.exception.ApplicationServiceException;
import br.com.softblue.bluefood.util.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageService {

    @Value("${bluefood.files.logotipo}")
    private String logotipoDir;

    public void uploadLogotipo(MultipartFile multipartFile, String filename) {
        try {
            IOUtils.copy(multipartFile.getInputStream(), filename, logotipoDir);
        } catch (IOException e) {
            throw new ApplicationServiceException(e);
        }
    }
}
