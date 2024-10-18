//package com.htdinh.bookstore.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@CrossOrigin
//@Controller
//@RequestMapping("/api/v1/cart")
//public class CartController {
//
////    @Autowired
////    private CartService cartService;
////
////    @GetMapping(value = "")
////    public ResponseEntity<CartResponse> getAllInfo() {
////        CartResponse cartResponse = cartService.getAllInfo();
////        return ResponseEntity.ok(cartResponse);
////    }
////
////    @PostMapping("")
////    public ResponseEntity<CartItemResponse> addToCart(@RequestBody CartItemRequest request) {
//////        return ResponseEntity.status(HttpStatus.CREATED).body(cartItemService.addItem(request));
////        //TODO;
////        return null;
////    }
////
////    @DeleteMapping("")
////    public ResponseEntity<String> delete(
////            @PathVariable("id")
////            @NotNull(message = "Cart Item Id cannot be null")
////            @Min(value = 1, message = "Cart Item Id value must be greater than 0")
////            Integer id
////    ) {
////        //TODO;
////        return null;
//////        cartItemService.deleteItem(id);
//////        return ResponseEntity.ok("Cart Item with id " + id + " was successfully deleted");
////    }
////
////    @PostMapping("/update")
////    public ResponseEntity<CartItemResponse> update(@RequestBody CartItemRequest request) {
//////        return ResponseEntity.status(HttpStatus.CREATED).body(cartItemService.addItem(request));
////        //TODO;
////        return null;
////    }
//}
