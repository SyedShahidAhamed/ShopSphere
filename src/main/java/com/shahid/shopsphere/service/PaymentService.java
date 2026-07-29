package com.shahid.shopsphere.service;

import com.shahid.shopsphere.dto.payment.PaymentRequest;
import com.shahid.shopsphere.dto.payment.PaymentResponse;

public interface PaymentService {
    PaymentResponse makePayment(PaymentRequest request);
}
