package com.oliver.foodstockmate.service.impl;

import com.oliver.foodstockmate.dto.ItemDto;
import com.oliver.foodstockmate.entity.Item;
import com.oliver.foodstockmate.entity.User;
import com.oliver.foodstockmate.exception.ResourceNotFoundException;
import com.oliver.foodstockmate.mapper.ItemMapper;
import com.oliver.foodstockmate.repository.ItemRepository;
import com.oliver.foodstockmate.repository.UserRepository;
import com.oliver.foodstockmate.service.AuthenticationFacade;
import com.oliver.foodstockmate.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {
    private ItemRepository itemRepository;
    private UserRepository userRepository;
    private final AuthenticationFacade authFacade;

    @Override
    public ItemDto create(ItemDto itemDto) {
        // Mapper DTO til entity
        Item item = ItemMapper.mapToItem(itemDto);

        // Sæt den aktuelle bruger som owner
        User user = userRepository.findById(authFacade.getCurrentUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        item.setUser(user);

        // Gem item i databasen
        Item savedItem = itemRepository.save(item);

        // Returner DTO til frontend
        return ItemMapper.mapToItemDto(savedItem);
    }


    @Override
    public ItemDto getItemById(Long itemId) {
        Item employee =  itemRepository.findById(itemId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Item does not exists with the given id : " + itemId));
        return ItemMapper.mapToItemDto(employee);
    }

    @Override
    public List<ItemDto> getAllItems() {
        List<Item> items = itemRepository.findAll();
        return items.stream().map((item) -> ItemMapper.mapToItemDto(item)).collect(Collectors.toList());
    }

    @Override
    public List<ItemDto> getItemsForCurrentUser() {
        Long userId = authFacade.getCurrentUserId();
        List<Item> items = itemRepository.findByUserId(userId);
        return items.stream().map(ItemMapper::mapToItemDto).collect(Collectors.toList());
    }

    @Override
    public ItemDto updateItem(Long itemId, ItemDto updatedItem) {
        Item item = itemRepository.findById(itemId).orElseThrow(
                () -> new ResourceNotFoundException("Item does not exists with given id: " + itemId)
        );
        item.setName(updatedItem.getName());
        item.setQuantity(updatedItem.getQuantity());
        item.setUnit(updatedItem.getUnit());
        item.setLocation(updatedItem.getLocation());
        item.setPurchaseDate(updatedItem.getPurchaseDate());
        item.setExpiryDate(updatedItem.getExpiryDate());
        item.setNotes(updatedItem.getNotes());

        Item updatedItemObj = itemRepository.save(item);
        return ItemMapper.mapToItemDto(updatedItemObj);
    }

    @Override
    public void deleteItem(Long itemId) {
        Item item = itemRepository.findById(itemId).orElseThrow(
                () -> new ResourceNotFoundException("Item does not exists with given id: " + itemId)
        );
        itemRepository.deleteById(itemId);
    }

    @Override
    public List<ItemDto> searchItem(String keyword){
        Long userId = authFacade.getCurrentUserId();
        List<Item> items = itemRepository.findByUserIdAndNameContainingIgnoreCase(userId,keyword);
        return items.stream().map(ItemMapper::mapToItemDto).collect(Collectors.toList());
    }
}
