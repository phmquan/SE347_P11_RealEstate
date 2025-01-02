package vn.uit.realestate.service;

import jakarta.servlet.ServletContext;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadService {

    private final ServletContext servletContext;

    public UploadService(ServletContext servletContext) {

        this.servletContext = servletContext;
    }

    public String handleSaveUploadSingleFile(MultipartFile file, String targetFolder) {
        String rootPath = this.servletContext.getRealPath("/resources/images");
        String fileName = null;

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                File dir = new File(rootPath + File.separator + targetFolder);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                // Create the file on server
                String finalName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
                File serverFile = new File(dir.getAbsolutePath() + File.separator + finalName);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
                fileName = finalName;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileName;
    }

    public List<String> handleSaveUploadMultipleFile(MultipartFile[] files, String targetFolder) {
        List<String> fileNames = new ArrayList<>();
        String rootPath = this.servletContext.getRealPath("/resources/images");

        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    File dir = new File(rootPath + File.separator + targetFolder);
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    // Create the file on server
                    String finalName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
                    File serverFile = new File(dir.getAbsolutePath() + File.separator + finalName);
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                    stream.write(bytes);
                    stream.close();
                    fileNames.add(finalName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return fileNames;
    }
}
