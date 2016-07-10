package com.petception.dao;

import com.petception.mongo.UserAccessLayer;
import com.petception.user.User;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by manjtsingh on 6/5/2016.
 */
@Component
public class UserDao {

    @Autowired
    UserAccessLayer userAccessLayer;

    public User isUserRegistered(String username, String password)
    {
        Document user = userAccessLayer.findUser(username, password);
        if(user ==null)
        {
            return null;
        }
        return mapDocumentToUser(user);
    }

    private User mapDocumentToUser(Document document)
    {
        User user = new User();
        user.setUsername(document.getString("username"));
        user.setId(document.getObjectId("_id").toString());
        return user;
    }

    public void registerNewUser(String username, String password) throws Exception {
        Document user = userAccessLayer.createUser(username,password);
    }
}
