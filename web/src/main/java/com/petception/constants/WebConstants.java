package com.petception.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by manjtsingh on 6/30/2016.
 */
@Component
public class WebConstants {

    public String getIndex_header() {
        return index_header;
    }

    @Value("${index.header}")
    public String index_header;


    public String getIndex_header_text() {
        return index_header_text;
    }

    @Value("${index.header.text}")
    public String index_header_text;
}
