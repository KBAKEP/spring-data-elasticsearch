package com.epam;

import static org.elasticsearch.index.query.QueryBuilders.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;


public class SpringRunner {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
        System.out.println(applicationContext.getStartupDate());
        ElasticsearchTemplate elasticsearchTemplate = (ElasticsearchTemplate)applicationContext.getBean("elasticsearchTemplate");
        System.out.println(elasticsearchTemplate);
        
        String documentId = "123456";
        SampleEntity sampleEntity = new SampleEntity();
        sampleEntity.setId(documentId);
        sampleEntity.setMessage("some message");
        IndexQuery indexQuery = new IndexQueryBuilder().withId(sampleEntity.getId()).withObject(sampleEntity).build();
        elasticsearchTemplate.index(indexQuery);
        
        //search
        
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(queryString(documentId).field("id"))
        .build();
        Page<SampleEntity> sampleEntities = elasticsearchTemplate.queryForPage(searchQuery,SampleEntity.class);
        
        System.out.println(sampleEntities);
    }

}
