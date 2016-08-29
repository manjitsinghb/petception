package com.petception.mongo;

import org.bson.Document;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manjtsingh on 6/5/2016.
 */

@Repository
public class UserAccessLayer extends BaseRepository {

    public Document findUser(String username, String password)
    {
        Document document = new Document("username",username).append("password",password);
        List<Document> result = userInfoCollection.find(document).into(new ArrayList<Document>());
        if(result.size()==0)
        {
            return null;
        }
        return result.get(0);
    }

    public Document createUser(String username, String password) throws Exception {
        Document document = new Document("username",username).append("password",password);
        userInfoCollection.insertOne(document);
        return document;
    }

    public boolean updateToken(String username, String token) {
        Document document = new Document("username",username);
        userInfoCollection.findOneAndUpdate(document,new Document("$set",new Document("token",token)));
        return true;
    }

    public Document isValidUser(String token) {
        Document document = new Document("token",token);
        List<Document> userList = userInfoCollection.find(document).into(new ArrayList<>());
        if(!org.springframework.util.CollectionUtils.isEmpty(userList))
        {
            return userList.get(0);
        }
        return null;
    }
}
