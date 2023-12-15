package com.example.apigateway.config;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

@Service
public class RouterValidator {

    public static final List<String> openEndpoints = List.of(
            "/api/v1/auth/register",
            "/api/v1/auth/sign_in",
            "/api/v1/users/get_user",
            "/api/v1/users/change_user",
            "/api/v1/users/user_add_items",
            "/api/v1/items/get_item_by_id",
            "/api/v1/items/get_items_by_username",
            "/api/v1/items/create_item",
            "/api/v1/items/update_item"


    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openEndpoints.stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));
}