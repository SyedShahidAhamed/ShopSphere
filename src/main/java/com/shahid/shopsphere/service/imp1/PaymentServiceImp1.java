package com.shahid.shopsphere.service.imp1;



import org.springframework.stereotype.Service;

import com.shahid.shopsphere.dto.payment.PaymentRequest;
import com.shahid.shopsphere.dto.payment.PaymentResponse;
import com.shahid.shopsphere.entity.Order;
import com.shahid.shopsphere.entity.OrderStatus;
import com.shahid.shopsphere.entity.Payment;
import com.shahid.shopsphere.entity.User;
import com.shahid.shopsphere.exception.BadRequestException;
import com.shahid.shopsphere.exception.ForbiddenException;
import com.shahid.shopsphere.exception.ResourceNotFoundException;
import com.shahid.shopsphere.mapper.PaymentMapper;
import com.shahid.shopsphere.repository.OrderRepository;
import com.shahid.shopsphere.repository.PaymentRepository;
import com.shahid.shopsphere.service.AuthenticationFacade;
import com.shahid.shopsphere.service.PaymentService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentServiceImp1 implements PaymentService {
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final AuthenticationFacade  authenticationFacade;
@Override
public PaymentResponse makePayment(PaymentRequest request){
   

    //order
    Order order = orderRepository.findById(request.getOrderId()).orElseThrow(()-> new ResourceNotFoundException("order not found"));
    //check if paid
      paymentRepository.findByOrderId(order.getId())
            .ifPresent(payment -> {
                throw new BadRequestException("Payment already completed for this order.");
            });
     //if the order is pending  need  not to pay
     if (order.getStatus() != OrderStatus.PENDING) {
    throw new BadRequestException(
            "Only pending orders can be paid.");
}
//verify user
User currentUser = authenticationFacade.getCurrentUser();

if (!order.getUser().getId().equals(currentUser.getId())) {
    throw new ForbiddenException(
            "You are not authorized to pay for this order.");
}
        
    //payment create
    Payment payment = paymentMapper.paymentBuilder(order, request);
    Payment savedPayment=paymentRepository.save(payment);
    order.setStatus(OrderStatus.CONFIRMED);
    orderRepository.save(order);
    return paymentMapper.toPaymentResponse(savedPayment);
}
}
