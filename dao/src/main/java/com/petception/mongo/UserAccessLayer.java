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

    public Document createUser(String username, String password) {
        Document document = new Document("username",username).append("password",password);
        userInfoCollection.insertOne(document);
        if(document.getObjectId("_id")!=null)
        {
            return document;
        }
        return null;
    }
}
