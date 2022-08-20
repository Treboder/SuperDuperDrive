package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.MyCredential;
import com.udacity.jwdnd.course1.cloudstorage.model.MyNote;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CredentialMapper
{

    @Select("SELECT url FROM CREDENTIALS")
    List<String> getAllUrls();

    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{id}")
    List<MyCredential> getListOfCredentialsFromUser(Integer id);

    @Insert("INSERT INTO CREDENTIALS (url, username, key, password, userId) VALUES(#{url}, #{username}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialid")
    int addNote(MyNote note);

}





