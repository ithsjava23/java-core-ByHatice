package org.example.warehouse;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Category {
    private final String name;
    private static final Map<String, Category> categoryList = new HashMap<>();
    private Category(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public static Category of(String name) {
        if (name == null)
            throw new IllegalArgumentException("Category name can't be null");
         String formattedName = name.substring(0, 1).toUpperCase() + name.substring(1);
        return categoryList.computeIfAbsent(formattedName, Category::new);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        return Objects.equals(name, category.name);
    }
    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
