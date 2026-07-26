package com.shahid.shopsphere.service.imp1;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shahid.shopsphere.dto.cart.AddToCartRequest;
import com.shahid.shopsphere.dto.cart.CartItemResponse;
import com.shahid.shopsphere.dto.cart.CartResponse;
import com.shahid.shopsphere.dto.cart.UpdateCartItemRequest;
import com.shahid.shopsphere.entity.Cart;
import com.shahid.shopsphere.entity.CartItem;
import com.shahid.shopsphere.entity.Product;
import com.shahid.shopsphere.entity.User;
import com.shahid.shopsphere.exception.ResourceNotFoundException;
import com.shahid.shopsphere.repository.CartItemsRepository;
import com.shahid.shopsphere.repository.CartRepository;
import com.shahid.shopsphere.repository.ProductRepository;
import com.shahid.shopsphere.repository.UserRepository;
import com.shahid.shopsphere.service.CartService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemsRepository cartItemsRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
     
     private CartResponse mapToCartResponse(Cart cart) {

        List<CartItemResponse> items = cart.getCartItems()
                .stream()
                .map(item -> new CartItemResponse(
                        item.getProduct().getId(),
                        item.getProduct().getName(),
                        item.getUnitPrice(),
                        item.getQuantity(),
                        item.getUnitPrice()
                                .multiply(BigDecimal.valueOf(item.getQuantity()))
                ))
                .toList();

        return new CartResponse(
                cart.getId(),
                items,
                cart.getTotalAmount()
        );
    }
    @Override
    public CartResponse addToCart(AddToCartRequest request) {

        // 1. Find Product
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found"));

        // 2. Get User (Temporary)
        User user = userRepository.findById(1L)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        // 3. Find or Create Cart
        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseGet(() -> {
                    Cart newCart = Cart.builder()
                            .user(user)
                            .build();
                    return cartRepository.save(newCart);
                });

        // 4. Check if Product Already Exists
        CartItem cartItem = cartItemsRepository
                .findByCartIdAndProductId(cart.getId(), product.getId())
                .orElse(null);

        if (cartItem != null) {

            // Increase Quantity
            cartItem.setQuantity(cartItem.getQuantity() + request.getQuantity());

        } else {

            // Create New Cart Item
            cartItem = CartItem.builder()
                    .cart(cart)
                    .product(product)
                    .quantity(request.getQuantity())
                    .unitPrice(product.getPrice())
                    .build();
        }

        // 5. Save Cart Item
        cartItemsRepository.save(cartItem);

        // 6. Calculate Total Amount
        BigDecimal total = cartItemsRepository.findByCartId(cart.getId())
                .stream()
                .map(item ->
                        item.getUnitPrice()
                                .multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 7. Update Cart Total
        cart.setTotalAmount(total);
        cartRepository.save(cart);

        // 8. Return Response
        return mapToCartResponse(cart);
    }

   


    public CartResponse getCart(){
        //fetch user
        User user = userRepository.findById(1L).orElseThrow(() -> new ResourceNotFoundException("user Not Found."));

        //fetch cart of user
        Cart cart = cartRepository.findByUserId(user.getId()).orElseThrow(()-> new ResourceNotFoundException("cart not Found."));

        return mapToCartResponse(cart);
    }

    //update cart item
    public CartResponse updateCartItem(Long Itemid,UpdateCartItemRequest request)
    {          //find that item id incart
             CartItem cartItem = cartItemsRepository.findById(Itemid).orElseThrow(() -> new ResourceNotFoundException("Cart Item Not Found."));
             //update quantity
             cartItem.setQuantity(request.getQuantity());
             //save 
             cartItemsRepository.save(cartItem);
             //getCart
             Cart cart = cartItem.getCart();
             //toatl amount
             BigDecimal total = cartItemsRepository.findByCartId(cart.getId())
                                                    .stream()
                                                     .map(item -> item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                                                     .reduce(BigDecimal.ZERO,BigDecimal::add);
                                                     
          //save total amount
          cart.setTotalAmount(total);
          cartRepository.save(cart);
          return mapToCartResponse(cart);

    }

    @Override
public CartResponse removeCartItem(Long itemId) 
{         //find cartitem
        CartItem cartItem = cartItemsRepository.findById(itemId).orElseThrow(() -> new ResourceNotFoundException("Cart Not Found."));
           //remove
           cartItemsRepository.delete(cartItem);

           //get cart
           Cart cart =cartItem.getCart();
           //update total amount

           BigDecimal total = cartItemsRepository.findByCartId(cart.getId())
                                                  .stream()
                                                 .map(item -> item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                                                .reduce(BigDecimal.ZERO,BigDecimal::add);

           cart.setTotalAmount(total);
           cartRepository.save(cart);
           return mapToCartResponse(cart);

}
public void clearCart(){

        User user = userRepository.findById(1L).orElseThrow(() -> new ResourceNotFoundException("User Not Found."));
        Cart cart = cartRepository.findByUserId(user.getId()).orElseThrow(() -> new ResourceNotFoundException("Cart Not Found."));

        cartItemsRepository.deleteAll(cart.getCartItems());

        cart.setTotalAmount(BigDecimal.ZERO);
        cartRepository.save(cart);




}
}