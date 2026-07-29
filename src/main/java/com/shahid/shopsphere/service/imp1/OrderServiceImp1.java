package com.shahid.shopsphere.service.imp1;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.shahid.shopsphere.dto.order.CheckoutRequest;

import com.shahid.shopsphere.dto.order.OrderResponse;
import com.shahid.shopsphere.entity.Cart;
import com.shahid.shopsphere.entity.CartItem;
import com.shahid.shopsphere.entity.Order;
import com.shahid.shopsphere.entity.OrderItem;
import com.shahid.shopsphere.entity.OrderStatus;
import com.shahid.shopsphere.entity.User;
import com.shahid.shopsphere.exception.ForbiddenException;
import com.shahid.shopsphere.exception.ResourceNotFoundException;
import com.shahid.shopsphere.mapper.OrderMapper;
import com.shahid.shopsphere.repository.CartItemsRepository;
import com.shahid.shopsphere.repository.CartRepository;

import com.shahid.shopsphere.repository.OrderRepository;

import com.shahid.shopsphere.service.AuthenticationFacade;
import com.shahid.shopsphere.service.OrderService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImp1 implements OrderService{
 
    private final OrderRepository orderRepository;
    
      private final CartRepository cartRepository;
      private final CartItemsRepository cartItemsRepository;
    private final AuthenticationFacade authenticationFacade;
   private final OrderMapper orderMapper;
    
   @Override
@Transactional
public OrderResponse checkOut(CheckoutRequest request) {

    // 1. Get logged-in user
    User user = authenticationFacade.getCurrentUser();

    // 2. Get user's cart
    Cart cart = cartRepository.findByUserId(user.getId())
            .orElseThrow(() ->
                    new ResourceNotFoundException("Cart not found."));

    // 3. Validate cart
    if (cart.getCartItems().isEmpty()) {
        throw new ResourceNotFoundException("Cart is empty.");
    }

    // 4. Create Order
    Order order = Order.builder()
            .user(user)
            .shippingAddress(request.getShippingAddress())
            .status(OrderStatus.PENDING)
            .build();

    BigDecimal totalAmount = BigDecimal.ZERO;
    List<OrderItem> orderItems = new ArrayList<>();

    // 5. Convert CartItems -> OrderItems
    for (CartItem cartItem : cart.getCartItems()) {

        OrderItem orderItem = OrderItem.builder()
                .order(order)
                .product(cartItem.getProduct())
                .quantity(cartItem.getQuantity())
                .priceAtPurchase(cartItem.getUnitPrice()) // change if needed
                .build();

        orderItems.add(orderItem);

        totalAmount = totalAmount.add(
                cartItem.getUnitPrice()
                        .multiply(BigDecimal.valueOf(cartItem.getQuantity()))
        );
    }

    // 6. Set Order details
    order.setOrderItems(orderItems);
    order.setTotalAmount(totalAmount);

    // 7. Save Order
    Order savedOrder = orderRepository.save(order);

    // 8. Clear Cart
    cartItemsRepository.deleteAll(cart.getCartItems());
    cart.getCartItems().clear();
    cart.setTotalAmount(BigDecimal.ZERO);

    cartRepository.save(cart);

    // 9. Return Response
    return orderMapper.toOrderResponse(savedOrder);
}

 @Override
  public  List<OrderResponse> getMyOrders(){
       

    User user = authenticationFacade.getCurrentUser();
    List<Order> orders = orderRepository.findByUserIdOrderByCreatedAtDesc(user.getId());
    
    return orders.stream().map(orderMapper :: toOrderResponse).toList();
  }

   public OrderResponse getOrder(Long orderId){
    Order order = orderRepository.findById(orderId).orElseThrow(()-> new ResourceNotFoundException("Order Not Found With This Id:"+orderId));
    User user = authenticationFacade.getCurrentUser();

    if(!order.getUser().getId().equals(user.getId()))
    {
         throw new ForbiddenException("You are not authorized to view this order.");
    }

     return orderMapper.toOrderResponse(order);
   }

   @Override
public void cancelOrder(Long orderId) {

    User user = authenticationFacade.getCurrentUser();

    Order order = orderRepository.findById(orderId)
            .orElseThrow(() ->
                    new ResourceNotFoundException("Order not found"));

    if (!order.getUser().getId().equals(user.getId())) {
        throw new RuntimeException("You are not authorized to cancel this order.");
    }

    if (order.getStatus() != OrderStatus.PENDING) {
        throw new ForbiddenException(
                "Only pending orders can be cancelled.");
    }

    order.setStatus(OrderStatus.CANCELLED);

    orderRepository.save(order);
}
}
