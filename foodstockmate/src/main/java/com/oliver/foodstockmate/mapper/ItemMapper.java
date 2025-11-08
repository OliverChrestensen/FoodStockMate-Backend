package com.oliver.foodstockmate.mapper;

import com.oliver.foodstockmate.dto.ItemDto;
import com.oliver.foodstockmate.entity.Item;

public class ItemMapper {

    public static ItemDto mapToItemDto(Item item) {

        return new ItemDto(
                item.getId(),
                item.getName(),
                item.getQuantity(),
                item.getUnit(),
                item.getLocation(),
                item.getPurchaseDate(),
                item.getExpiryDate(),
                item.getNotes(),
                item.getCreatedAt()
        );
    }

    public static Item mapToItem(ItemDto dto) {
        Item item = new Item();
        item.setName(dto.getName());
        item.setQuantity(dto.getQuantity());
        item.setUnit(dto.getUnit());
        item.setLocation(dto.getLocation());
        item.setPurchaseDate(dto.getPurchaseDate());
        item.setExpiryDate(dto.getExpiryDate());
        item.setNotes(dto.getNotes());
        return item;
    }
}
