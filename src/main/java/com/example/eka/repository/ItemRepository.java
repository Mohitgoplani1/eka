package com.example.eka.repository;

import com.example.eka.model.MenuItem;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ItemRepository extends MongoRepository <MenuItem,String>{
    MenuItem findByName(String name);
    List<MenuItem> findByCategory(String category);
}
