package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.FileFormBackingObject;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteFormBackingObject;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.ListIterator;

@Controller("file")
@RequestMapping("file")
public class FileController {

    FileService fileService; // initialized by Spring via constructor below

    // Spring initializes the object along with @Controller annotation
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("upload")
    public String uploadFile(Authentication authentication,
                             @ModelAttribute("newFile") FileFormBackingObject newFile,
                             Model model) throws IOException {
        String userName = authentication.getName();
        MultipartFile multipartFile = newFile.getFile();
        if(fileExists(multipartFile, userName)) {
            model.addAttribute("changeSuccess", false);
            model.addAttribute("errorMessage", "File already exists. ");
            return "result";
        }
        else {
            fileService.addFile(multipartFile, authentication.getName());
            model.addAttribute("changeSuccess", true);
            return "result";
        }
    }

    @GetMapping("delete")
    public ModelAndView deleteFile(@RequestParam(value = "fileid", required = false) String fileid) {
        // pass the fileid as parameter via http://localhost:8080/file/delete?fileid=xyz
        ModelAndView model = new ModelAndView();
        if (fileid != null)
            fileService.deleteFile(Integer.parseInt(fileid));

        // always show success message, but should depend on fileService response
        model.addObject("changeSuccess", true);
        model.setViewName("result");
        return model; // alternatively use "Model model" as input parameter and return String (with "result") instead
    }

    private Boolean fileExists(MultipartFile multipartFile, String userName) {
        ListIterator<File> iterator = fileService.getListOfFilesFromUser(userName).listIterator();
        while(iterator.hasNext())
            if(multipartFile.getOriginalFilename().equals(iterator.next().getFileName()))
                return true;
        // return false if file does not exist
        return false;
    }


}



