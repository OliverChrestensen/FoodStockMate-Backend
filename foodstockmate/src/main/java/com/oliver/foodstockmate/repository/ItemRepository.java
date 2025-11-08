package com.oliver.foodstockmate.repository;

import com.oliver.foodstockmate.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByUserId(Long userId);
    List<Item> findByUserIdAndNameContainingIgnoreCase(Long userId, String keyword);
}
