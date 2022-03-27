package com.springbootmongodb.repository;

/**
 *  To use the MongoTemplate class for the update, we should create a custom repository where we build the update query.
*/
public interface CustomItemRepository {
    void updateItemQuantity(String name, float newQuantity);
}
