package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.*;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("credential")
public class CredentialController {

    CredentialService credentialService; // initialized by Spring via constructor below

    // Spring initializes the object along with @Controller annotation
    public CredentialController(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @PostMapping("addOrEdit")
    public String addOrEditNote(
            Authentication authentication,
            @ModelAttribute("credentialForm") CredentialFormBackingObject credentialForm,
            Model model) {

        String actingUser = authentication.getName();
        String id =  credentialForm.getCredentialId();
        String url = credentialForm.getUrl() ;
        String userName = credentialForm.getUsername();
        String password = credentialForm.getPassword();

        // todo: encrypt password and handle filed "key"
        if(id.isEmpty())
            credentialService.addCredential(url, userName, "key", password, actingUser);
        else
            credentialService.editCredential(id, url, userName, "key", password, actingUser);

        // always show success message, but should depend on noteService response
        model.addAttribute("changeSuccess", true);
        return "result";
    }

    @GetMapping("delete")
    public ModelAndView deleteCredential(@RequestParam(value = "credentialid", required = false) String credentialid) {
        // pass the noteid as parameter via http://localhost:8080/credential/delete?credentialid=xyz
        ModelAndView model = new ModelAndView();
        if (credentialid != null)
            credentialService.deleteCredential(Integer.parseInt(credentialid));

        // always show success message, but should depend on credentialService response
        model.addObject("changeSuccess", true);
        model.setViewName("result");
        return model; // alternatively use "Model model" as input parameter and return String (with "result") instead
    }




}
