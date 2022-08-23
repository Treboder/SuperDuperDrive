package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {

    private final UserMapper userMapper;
    private final FileMapper fileMapper;
    // mapper initialized by Spring via constructor below

    // Spring initializes the object along with @Service annotation
    public FileService(UserMapper userMapper, FileMapper fileMapper) {
        this.userMapper = userMapper;
        this.fileMapper = fileMapper;
    }

    public List<File> getListOfFilesFromUser(String userName) {
        Integer userId = userMapper.getUser(userName).getUserId();
        List<File> existingFiles = fileMapper.getListOfFilesFromUser(userId);
        return existingFiles;
    }

    public void addFile(MultipartFile multipartFile, String userName) throws IOException  {
        String fileName = multipartFile.getOriginalFilename();
        String contentType = multipartFile.getContentType();
        String fileSize = String.valueOf(multipartFile.getSize());
        Integer userId = userMapper.getUser(userName).getUserId();
        byte[] fileData = getFileData(multipartFile);
        File file = new File(0, fileName, contentType, fileSize, userId, fileData);
        fileMapper.addFile(file);
    }

    public void deleteFile(Integer fileId) {
        fileMapper.deleteFile(fileId);
    }

    public File getFile(String fileName) {
        return fileMapper.getFile(fileName);
    }

    private byte[] getFileData(MultipartFile multipartFile) throws IOException {
        InputStream fis = multipartFile.getInputStream();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[1024];
        while ((nRead = fis.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        buffer.flush();
        byte[] fileData = buffer.toByteArray();
        return fileData;
    }

}
