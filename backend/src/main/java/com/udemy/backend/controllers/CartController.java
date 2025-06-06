package com.udemy.backend.controllers;

import com.udemy.backend.dtos.addtocart.AddToCartResponse;
import com.udemy.backend.dtos.butnow.BuyNowResponse;
import com.udemy.backend.dtos.getcartitems.GetCartItemsResponse;
import com.udemy.backend.dtos.removefromcart.RemoveFromCartResponse;
import com.udemy.backend.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import static com.udemy.backend.constants.Headers.USER_ID;
import static com.udemy.backend.constants.PathVariables.*;

@RestController
@RequestMapping("${app.api.endpoints.cart.base-url}")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("${app.api.endpoints.cart.apis.add-to-cart}")
    public ResponseEntity<AddToCartResponse> addToCart(@AuthenticationPrincipal String userId, @PathVariable(COURSE_ID) String courseId) {
        AddToCartResponse response = cartService.addToCart(userId, courseId);
        return ResponseEntity.status(201).body(response);
    }

    @DeleteMapping("${app.api.endpoints.cart.apis.remove-from-cart}")
    public ResponseEntity<RemoveFromCartResponse> removeFromCart(@PathVariable(ITEM_ID) String itemId) {
        RemoveFromCartResponse response = cartService.removeFromCart(itemId);
        return ResponseEntity.status(200).body(response);
    }


    @GetMapping("${app.api.endpoints.cart.apis.get-cart}")
    public ResponseEntity<GetCartItemsResponse> getCartItemsResponse(@AuthenticationPrincipal String userId) {
        GetCartItemsResponse response = cartService.getCartItems(userId);
        return ResponseEntity.status(200).body(response);
    }

    @PostMapping("${app.api.endpoints.cart.apis.buy-now}")
    public ResponseEntity<BuyNowResponse> buyNow(@AuthenticationPrincipal String userId){
        BuyNowResponse response = cartService.buyNow(userId);
        return ResponseEntity.status(200).body(response);
    }




}
