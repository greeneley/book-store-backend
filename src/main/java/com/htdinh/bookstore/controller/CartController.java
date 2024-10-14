package com.htdinh.bookstore.controller;

import com.htdinh.bookstore.dto.request.CartItemRequest;
import com.htdinh.bookstore.dto.response.CartItemResponse;
import com.htdinh.bookstore.dto.response.CartResponse;
import com.htdinh.bookstore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@CrossOrigin
@Controller
@RequestMapping("/api/v1/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping(value = "")
    public ResponseEntity<CartResponse> getAllInfo() {
        CartResponse cartResponse = cartService.getAllInfo();
        return ResponseEntity.ok(cartResponse);
    }

    @PostMapping("")
    public ResponseEntity<CartItemResponse> addToCart(@RequestBody CartItemRequest request) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(cartItemService.addItem(request));
        //TODO;
        return null;
    }

    @DeleteMapping("")
    public ResponseEntity<String> delete(
            @PathVariable("id")
            @NotNull(message = "Cart Item Id cannot be null")
            @Min(value = 1, message = "Cart Item Id value must be greater than 0")
            Integer id
    ) {
        //TODO;
        return null;
//        cartItemService.deleteItem(id);
//        return ResponseEntity.ok("Cart Item with id " + id + " was successfully deleted");
    }

    @PostMapping("/update")
    public ResponseEntity<CartItemResponse> update(@RequestBody CartItemRequest request) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(cartItemService.addItem(request));
        //TODO;
        return null;
    }
}


//@CrossOrigin
//@RestController
//@RequestMapping("/api/v1/cart-item")
//public class CartItemController {
//
//    @Autowired
//    private CartItemService cartItemService;
//
//    @PostMapping("/add")
//    public ResponseEntity<CartItemResponse> addItem( @RequestBody CartItemRequest request) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(cartItemService.addItem(request));
//    }
//
//    @PatchMapping(value = "/update")
//    public ResponseEntity<CartItemResponse> updateItem(@Valid @RequestBody CartItemRequest request) {
//        return ResponseEntity.ok(cartItemService.updateItem(request));
//    }
//
//    @GetMapping("/delete/{id}")
//    public ResponseEntity<String> deleteItem(
//            @PathVariable("id")
//            @NotNull(message = "Cart Item Id cannot be null")
//            @Min(value = 1, message = "Cart Item Id value must be greater than 0")
//            Integer id
//    ) {
//        cartItemService.deleteItem(id);
//        return ResponseEntity.ok("Cart Item with id " + id + " was successfully deleted");
//    }
//}