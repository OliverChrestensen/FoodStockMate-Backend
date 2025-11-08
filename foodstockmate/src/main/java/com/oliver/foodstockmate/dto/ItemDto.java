package com.oliver.foodstockmate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {

    private Long id;

    @NotBlank(message = "Navn må ikke være tomt")
    private String name;

    @NotNull(message = "Mængde skal angives")
    @Min(value = 0, message = "Mængde skal være 0 eller større")
    private Integer quantity;

    private String unit;
    private String location;

    private LocalDate purchaseDate;

    @FutureOrPresent(message = "Udløbsdato kan ikke være i fortiden")
    private LocalDate expiryDate;

    private String notes;

    private LocalDateTime createdAt;
}