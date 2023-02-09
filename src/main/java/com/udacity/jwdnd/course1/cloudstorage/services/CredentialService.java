package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CredentialService {

    private final UserMapper userMapper;
    private final CredentialMapper credentialMapper;

    public CredentialService(UserMapper userMapper, CredentialMapper credentialMapper) {
        this.userMapper = userMapper;
        this.credentialMapper = credentialMapper;
    }

    public List<Credential> getListOfCredentialsFromUser(String user) {
        Integer userId = userMapper.getUser(user).getUserId();
        List<Credential> credentials = credentialMapper.getListOfCredentialsFromUser(userId);
        return credentials;
    }

    public void addCredential(String url, String userName, String key, String password, String actingUser) {
        Integer userId = userMapper.getUser(actingUser).getUserId();
        Credential credential = new Credential(0, url, userName, key, password, userId.intValue());
        credentialMapper.addCredential(credential);
    }

    public void editCredential(String id, String url, String userName, String key, String password, String actingUser) {
        Integer userId = userMapper.getUser(actingUser).getUserId();
        Credential credential = new Credential(Integer.valueOf(id), url, userName, key, password, userId.intValue());
        credentialMapper.editCredential(credential);
    }

    public void deleteCredential(Integer credentialId) {
        credentialMapper.deleteCredential(credentialId);
    }


}
