package org.rimini.hibernate.orm.panache.entities.store.product;

public record ProductUpdateRequest(
        String name,
        String description,
        String variantCode,
        String color,
        Double price,
        Integer quantity
) { }
