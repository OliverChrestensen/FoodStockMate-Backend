package com.oliver.foodstockmate.controller;


import com.oliver.foodstockmate.dto.ItemDto;
import com.oliver.foodstockmate.service.CustomUserDetailsService;
import com.oliver.foodstockmate.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/items")
public class ItemController {
    private ItemService itemService;

    //CREATE ITEM
    @PostMapping
    public ResponseEntity<ItemDto> createItem (@RequestBody ItemDto itemDto){
        ItemDto savedItem = itemService.create(itemDto);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }

    //GET ITEM
    @GetMapping("{id}")
    public ResponseEntity<ItemDto> getItemById(@PathVariable("id") Long itemId){
        ItemDto employeeDto = itemService.getItemById(itemId);
        return ResponseEntity.ok(employeeDto);
    }

    //GET ALL ITEMS
    @GetMapping
    public ResponseEntity<List<ItemDto>> getAllItems(){
        List<ItemDto> items = itemService.getAllItems();
        return ResponseEntity.ok(items);
    }

    //GET ALL ITEMS FOR USER
    @GetMapping("/user")
    public List<ItemDto> getUserItems(Authentication authentication) {
        return itemService.getItemsForCurrentUser();
    }


    //UPDATE ITEM
    @PutMapping("{id}")
    public ResponseEntity<ItemDto> updateItem(@PathVariable("id") Long employeeId, @RequestBody ItemDto updatedItem){
        ItemDto employeeDto = itemService.updateItem(employeeId,updatedItem);
        return ResponseEntity.ok(employeeDto);
    }

    //DELETE ITEM
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteItem(@PathVariable("id")Long itemId){
        itemService.deleteItem(itemId);
        return ResponseEntity.ok("Item deleted succesfully!");
    }

    //GET SEARCHED ITEMS FOR USER
    @GetMapping("/search")
    public List<ItemDto> searchItem (@RequestParam String keyword){
        return itemService.searchItem(keyword);
    }

}

