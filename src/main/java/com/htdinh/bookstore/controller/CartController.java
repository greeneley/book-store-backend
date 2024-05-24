package com.htdinh.bookstore.controller;

import com.htdinh.bookstore.dto.request.CartRequest;
import com.htdinh.bookstore.dto.response.CartResponse;
import com.htdinh.bookstore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1//cart")
public class CartController {
    
    @Autowired
    private CartService cartService;
    
    @GetMapping(value = "")
    public ResponseEntity<CartResponse> getAllInfo() {
        CartResponse cartResponse = cartService.getAllInfo();
        return ResponseEntity.ok(cartResponse);
    }
    
//	private final BookService bookService;
//	private final ShoppingCartService shoppingCartService;
//
//	public CartController(BookService bookService, ShoppingCartService shoppingCartService) {
//		this.bookService = bookService;
//		this.shoppingCartService = shoppingCartService;
//	}
//
//	@GetMapping(value = { "", "/" })
//	public String shoppingCart(Model model) {
//		model.addAttribute("cart", shoppingCartService.getCart());
//		return "cart";
//	}
//
//	@GetMapping("/add/{id}")
//	public String addToCart(@PathVariable("id") Long id, RedirectAttributes redirect) {
//		List<Book> cart = shoppingCartService.getCart();
//		Book book = bookService.findBookById(id).get();
//		if (book != null) {
//			cart.add(book);
//		}
//		shoppingCartService.getSession().setAttribute("cart", cart);
//		redirect.addFlashAttribute("successMessage", "Added book successfully!");
//		return "redirect:/cart";
//	}
//
//	@GetMapping("/remove/{id}")
//	public String removeFromCart(@PathVariable("id") Long id, RedirectAttributes redirect) {
//		Book book = bookService.findBookById(id).get();
//		if (book != null) {
//			shoppingCartService.deleteProductWithId(id);
//		}
//		redirect.addFlashAttribute("successMessage", "Removed book successfully!");
//		return "redirect:/cart";
//	}
//
//	@GetMapping("/remove/all")
//	public String removeAllFromCart() {
//		List<Book> cart = shoppingCartService.getCart();
//		cart.removeAll(cart);
//		return "redirect:/cart";
//	}

}