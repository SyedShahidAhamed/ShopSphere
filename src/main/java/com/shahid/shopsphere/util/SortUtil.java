package com.shahid.shopsphere.util;

import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class SortUtil {
    //sort validation
    public static final Set<String> ALLOWED_SORT_FIELDS = Set.of(
        "id",
        "name",
        "price",
        "stock",
        "createdAt"
);
//direction validation
public static final Set<String> ALLOWED_DIRECTIONS = Set.of(
        "asc",
        "desc"
);
}
