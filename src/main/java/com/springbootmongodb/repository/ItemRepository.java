package com.springbootmongodb.repository;

import com.springbootmongodb.model.GroceryItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
/*
* The first method, findItemByName, requires a parameter for the query, i.e., the field to filter the query by. We specify this with the annotation @Query. The second method uses the category field to get all the items of a particular category. We only want to project the field’s name and quantity in the query response, so we set those fields to 1. We reuse the method count() as it is.
* */
public interface ItemRepository extends MongoRepository<GroceryItem, String> {
    @Query("{name:'?0'}")
    GroceryItem findGroceryItemByName(String name);

    @Query(value = "{category:'?0'}", fields = "{'name':1, 'quantity':1}")
    List<GroceryItem> findAll(String category);

    public long count();
}
