package com.sto.mongo.utils;

import com.mongodb.client.model.geojson.Polygon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJson;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DBDaoTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void save(Polygon polygon){
        mongoTemplate.save(polygon);
    }


    public <T> T findById(Class<T> entityClass, String id) {
        return mongoTemplate.findById(id, entityClass);
    }

 public <T> T findOne(Class<T> entityClass, Query query,String collectionName) {
        return mongoTemplate.findOne(query, entityClass, collectionName);
    }

    public <T> List<T> findAll(Class<T> entityClass, String collection) {
        return mongoTemplate.findAll(entityClass, collection);
    }

    public <T> void remove(T entity) {
        mongoTemplate.remove(entity);
    }

    public <T> void add(T entity) {
        mongoTemplate.insert(entity);
    }
    
    public <T> void addAll(List<T> entity) {
        mongoTemplate.insertAll(entity);
    }

    public <T> void saveOrUpdate(T entity) {
        mongoTemplate.save(entity);
    }

    public <T> T findOne(Class<T> entityClass) {
        return mongoTemplate.findOne(new Query(), entityClass);
    }
    
    public List<Polygon> findIntersective(GeoJson geoJson){
        Query query=new Query(Criteria.where("geometry").intersects(geoJson));
        List<Polygon> list=mongoTemplate.find(query, Polygon.class);
        return list;
    }

}