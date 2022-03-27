package com.springbootmongodb;

import com.springbootmongodb.model.GroceryItem;
import com.springbootmongodb.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;

/*
 * Our class MdbSpringBootApplication implements the CommandLineRunner interface to run the spring application. ItemRepository is Autowired, allowing Spring to find it automatically. Spring initializes the Application Context using the @SpringBootApplication annotation. We also activate the Mongo Repositories using @EnableMongoRepositories
 * */
@SpringBootApplication
@EnableMongoRepositories
public class SpringBootMongodbApplication implements CommandLineRunner {
    @Autowired
    ItemRepository groceryItemRepo;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMongodbApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }

    //CREATE
    void createGroceryItems() {
        System.out.println("Data creation started...");
        groceryItemRepo.save(new GroceryItem("Whole Wheat Biscuit", "Whole Wheat Biscuit", 5, "snacks"));
        groceryItemRepo.save(new GroceryItem("Kodo Millet", "XYZ Kodo Millet healthy", 2, "millets"));
        groceryItemRepo.save(new GroceryItem("Dried Red Chilli", "Dried Whole Red Chilli", 2, "spices"));
        groceryItemRepo.save(new GroceryItem("Pearl Millet", "Healthy Pearl Millet", 1, "millets"));
        groceryItemRepo.save(new GroceryItem("Cheese Crackers", "Bonny Cheese Crackers Plain", 6, "snacks"));
        System.out.println("Data creation complete...");
    }

    // READ
    // 1. Show all the data
    public void showAllGroceryItems() {
        groceryItemRepo.findAll().forEach(item -> System.out.println(getItemDetails(item)));
    }

    // 2. Get item by name
    public void getGroceryItemByName(String name) {
        System.out.println("Getting item by name: " + name);
        GroceryItem item = groceryItemRepo.findGroceryItemByName(name);
        System.out.println(getItemDetails(item));
    }

    // 3. Get name and quantity of a all items of a particular category
    public void getItemsByCategory(String category) {
        System.out.println("Getting items for the category " + category);
        List<GroceryItem> list = groceryItemRepo.findAll(category);

        list.forEach(item -> System.out.println("Name: " + item.getName() + ", Quantity: " + item.getQuantity()));
    }

    // 4. Get count of documents in the collection
    public void findCountOfGroceryItems() {
        long count = groceryItemRepo.count();
        System.out.println("Number of documents in the collection: " + count);
    }


    /**
     * Suppose we change our mind and like the word “munchies” better in place of “snacks” for our grocery list. We would need to change all the documents where the category is “snacks.”
     */
    public void updateCategoryName(String category) {
        // Change to this new value
        String newCategory = "munchies";

        // Find all the items with the category snacks
        List<GroceryItem> list = groceryItemRepo.findAll(category);

        list.forEach(item -> {
            // Update the category in each document
            item.setCategory(newCategory);
        });

        // Save all the items in database
        List<GroceryItem> itemsUpdated = groceryItemRepo.saveAll(list);

        if (itemsUpdated != null)
            System.out.println("Successfully updated " + itemsUpdated.size() + " items.");
    }

    // Print details in readable form
    public String getItemDetails(GroceryItem item) {
        System.out.println(
                "Item Name: " + item.getName() +
                        ", \nQuantity: " + item.getQuantity() +
                        ", \nItem Category: " + item.getCategory()
        );
        return "";
    }
}
