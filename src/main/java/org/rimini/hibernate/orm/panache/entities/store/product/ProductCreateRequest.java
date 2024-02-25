package org.rimini.hibernate.orm.panache.entities.store.product;

public record ProductCreateRequest(
        String name,
        String description,
        String variantCode,
        String color,
        Double price,
        String categoryName,
        String subcategoryName,
        Integer quantity
) { }
