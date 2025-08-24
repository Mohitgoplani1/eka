package com.example.eka.controllers;

import com.example.eka.model.MenuItem;
import com.example.eka.repository.ItemRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    private final ItemRepository items;

    public MenuController(ItemRepository items) {
        this.items = items;
    }

    @PostMapping("/add")
    public String addItem(@RequestBody MenuItem item){
        if(items.findByName(item.getName())==null){
            items.save(item);
            return "Item Added Successfully !";
        }
        else return "Item Already Exists";
    }

    @GetMapping("/{category}/{id}")
    public MenuItem getById(@PathVariable String category,@PathVariable String id){
        return items.findById(id).orElse(null);
    }
    @GetMapping("/all")
    public List<MenuItem> getItems(){
        return items.findAll();
    }
    @GetMapping("/categories")
    public List<String> getAllCategories(){
        List<MenuItem> all=items.findAll();
        List<String> categories=new ArrayList<>();
        all.stream().forEach(i->categories.add(i.getCategory()));
        return categories;
    }
    @GetMapping("/{category}")
    public List<MenuItem> getByCategory(@PathVariable String category){
        List<MenuItem> itemsInCategory=items.findByCategory(category);
        return itemsInCategory.stream().filter(c->c.isAvailability()==true).toList();
    }
}
