package com.shahid.shopsphere.service.imp1;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shahid.shopsphere.dto.cart.AddToCartRequest;
import com.shahid.shopsphere.dto.cart.CartResponse;
import com.shahid.shopsphere.dto.cart.UpdateCartItemRequest;
import com.shahid.shopsphere.entity.Cart;
import com.shahid.shopsphere.entity.CartItem;
import com.shahid.shopsphere.entity.Product;
import com.shahid.shopsphere.entity.User;
import com.shahid.shopsphere.exception.ForbiddenException;
import com.shahid.shopsphere.exception.ResourceNotFoundException;
import com.shahid.shopsphere.mapper.CartMapper;
import com.shahid.shopsphere.repository.CartItemsRepository;
import com.shahid.shopsphere.repository.CartRepository;
import com.shahid.shopsphere.repository.ProductRepository;
import com.shahid.shopsphere.service.AuthenticationFacade;
import com.shahid.shopsphere.service.CartService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemsRepository cartItemsRepository;
    private final ProductRepository productRepository;
    private final AuthenticationFacade authenticationFacade;
    private final CartMapper cartMapper;
      /**
     * Calculates total amount of the cart.
     */
    private BigDecimal calculateCartTotal(Long cartId) {

        return cartItemsRepository.findByCartId(cartId)
                .stream()
                .map(item ->
                        item.getUnitPrice()
                                .multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public CartResponse addToCart(AddToCartRequest request) {

        // 1. Find Product
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found"));

        // 2. Get User (Temporary)
        User user =authenticationFacade.getCurrentUser();
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
        BigDecimal total = calculateCartTotal(cart.getId());
        // 7. Update Cart Total
        cart.setTotalAmount(total);
        cartRepository.save(cart);
        //reload
          cart = cartRepository.findById(cart.getId())
        .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
        // 8. Return Response
        return cartMapper.toCartResponse(cart);
    }

   


    public CartResponse getCart(){
        //fetch user
        User user = authenticationFacade.getCurrentUser();

        //fetch cart of user
        Cart cart = cartRepository.findByUserId(user.getId()).orElseThrow(()-> new ResourceNotFoundException("cart not Found."));

        return cartMapper.toCartResponse(cart);
    }

    //update cart item
    public CartResponse updateCartItem(Long Itemid,UpdateCartItemRequest request)
    {          //find that item id incart
             CartItem cartItem = cartItemsRepository.findById(Itemid).orElseThrow(() -> new ResourceNotFoundException("Cart Item Not Found."));
             User currentUser = authenticationFacade.getCurrentUser();

if (!cartItem.getCart().getUser().getId().equals(currentUser.getId())) {
    throw new ForbiddenException("You cannot modify another user's cart.");
}
             //update quantity
             cartItem.setQuantity(request.getQuantity());
             //save 
             cartItemsRepository.save(cartItem);
             //getCart
             Cart cart = cartItem.getCart();
             //toatl amount
             BigDecimal total = calculateCartTotal(cart.getId());
          //save total amount
          cart.setTotalAmount(total);
          cartRepository.save(cart);
          return cartMapper.toCartResponse(cart);

    }

    @Override
public CartResponse removeCartItem(Long itemId) 
{         //find cartitem
        CartItem cartItem = cartItemsRepository.findById(itemId).orElseThrow(() -> new ResourceNotFoundException("Cart Not Found."));
          User currentUser = authenticationFacade.getCurrentUser();

if (!cartItem.getCart().getUser().getId().equals(currentUser.getId())) {
    throw new ForbiddenException("You cannot modify another user's cart.");
}  
        //remove
           cartItemsRepository.delete(cartItem);

           //get cart
           Cart cart =cartItem.getCart();
           //update total amount

           BigDecimal total = calculateCartTotal(cart.getId());

           cart.setTotalAmount(total);
           cartRepository.save(cart);
           return cartMapper.toCartResponse(cart);

}
public void clearCart(){

        User user = authenticationFacade.getCurrentUser();
        Cart cart = cartRepository.findByUserId(user.getId()).orElseThrow(() -> new ResourceNotFoundException("Cart Not Found."));
        cartItemsRepository.deleteAll(cart.getCartItems());
        cart.getCartItems().clear();
        cart.setTotalAmount(BigDecimal.ZERO);
        cartRepository.save(cart);




}
}