package com.petception.mongo;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * Created by manjtsingh on 6/5/2016.
 */
@Repository
public class BaseRepository {

    @Autowired
    @Qualifier("petInfoCollection")
    protected MongoCollection<Document> petInfoCollection;

    @Autowired
    @Qualifier("userInfoCollection")
    protected MongoCollection<Document> userInfoCollection;

}
