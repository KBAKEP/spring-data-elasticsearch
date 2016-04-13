package com.epam;

import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName="entity")
public class SampleEntity {
    private String id;
    private String message;
    
    public String getId() {
    
        return id;
    }
    
    public void setId(String id) {
    
        this.id = id;
    }
    
    public String getMessage() {
    
        return message;
    }
    
    public void setMessage(String message) {
    
        this.message = message;
    }
    
    

}
