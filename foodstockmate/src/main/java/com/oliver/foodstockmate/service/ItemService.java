package com.oliver.foodstockmate.service;

import com.oliver.foodstockmate.dto.ItemDto;

import java.util.List;


public interface ItemService {

    ItemDto create(ItemDto itemDto);

    ItemDto getItemById(Long itemId);

    List<ItemDto> getAllItems();

    List<ItemDto> getItemsForCurrentUser();

    ItemDto updateItem(Long itemId, ItemDto updatedItem);

    void deleteItem(Long itemId);

    List<ItemDto> searchItem(String keyword);
}
