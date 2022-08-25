package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.FileFormBackingObject;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteFormBackingObject;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.ListIterator;

@Controller("file")
@RequestMapping("file")
@ControllerAdvice
public class FileController {

    FileService fileService; // initialized by Spring via constructor below

    // Spring initializes the object along with @Controller annotation
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("upload")
    public String uploadFile(Authentication authentication,
                             @ModelAttribute("fileForm") FileFormBackingObject newFile,
                             Model model) throws IOException {
        String userName = authentication.getName();
        MultipartFile multipartFile = newFile.getFile();

        // ToDo: Do not create a record without a file specified
        if(newFile.getFile().isEmpty()) {
            model.addAttribute("changeSuccess", false);
            model.addAttribute("errorMessage", "No file chosen. ");
            return "result";
        }
        else if(fileExists(multipartFile, userName)) {
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

    @GetMapping(
            value = "/get/{fileName}",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public @ResponseBody byte[] getFile(@PathVariable String fileName, Authentication authentication) {
        String userName = authentication.getName();
        return fileService.getFile(fileName, userName).getFileData();
    }

    private Boolean fileExists(MultipartFile multipartFile, String userName) {
        ListIterator<File> iterator = fileService.getListOfFilesFromUser(userName).listIterator();
        while(iterator.hasNext())
            if(multipartFile.getOriginalFilename().equals(iterator.next().getFileName()))
                return true;
        // return false if file does not exist
        return false;
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String handleFileUploadError(RedirectAttributes ra, Model model){
        model.addAttribute("changeSuccess", false);
        model.addAttribute("errorMessage", "File too big. ");
        return "result";
        // Following two lines in application.properties enable large file uploads, so that this exception handler is an extra safety net
        // spring.servlet.multipart.max-file-size=-1
        // #spring.servlet.multipart.max-request-size=-1
        // ... on the other hand, removing these two files in application.properties will call the exception handler
    }

}



