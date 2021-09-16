/*package com.liao.datascope.utils;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.BasicDBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;*/

/**
 * <p>
 * MongoBD操作类
 * </p>
 *
 * @author LiAo
 * @since 2021/6/2
 */
//@SuppressWarnings(value = {"unchecked", "rawtypes"})
//@Component
/*public class MongoUtil {

    @Autowired
    private MongoTemplate mongoTemplate;


    *//**
     * 插入jsonString
     *
     * @param str JSONString
     * @return
     *//*
    public String saveJSONString(String str) {

        return saveJSONString(str, "col");
    }

    *//**
     * 插入jsonString
     *
     * @param str            JSONString
     * @param collectionName 表名称
     * @return
     *//*
    public String saveJSONString(String str, String collectionName) {
        BasicDBObject parse = BasicDBObject.parse(str);

        BasicDBObject col = mongoTemplate.save(parse, collectionName);

        return col.get("_id").toString();
    }

    *//**
     * 根据id查询结果
     *
     * @param id id
     * @return json
     *//*
    public JSONObject findOneById(String id) {
        return findOneById(id, "col");
    }

    *//**
     * 根据id查询结果
     *
     * @param id             id
     * @param collectionName 表名称
     * @return json
     *//*
    public JSONObject findOneById(String id, String collectionName) {
        return mongoTemplate.findOne(Query.query(Criteria.where("_id").is(id)).limit(1), JSONObject.class, collectionName);
    }

    *//**
     * 根据 ID 更新文档
     *
     * @param id             id
     * @param document       文档
     * @param collectionName 表名
     * @return 结果
     *//*
    public String updateMulti(String id, String document, String collectionName) {

        // 执行删除
        removeById(id, collectionName);

        // 执行插入
        return saveJSONString(document, collectionName);
    }

    *//**
     * 根据id 删除文档
     *
     * @param id             id
     * @param collectionName 表名称
     *//*
    public void removeById(String id, String collectionName) {
        mongoTemplate.remove(Query.query(Criteria.where("_id").is(id)), collectionName);
    }

    *//**
     * 根据id 删除文档
     *
     * @param id id
     *//*
    public void removeById(String id) {
        removeById(id, "col");
    }
}*/
