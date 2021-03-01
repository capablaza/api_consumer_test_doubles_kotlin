package com.example.api.payment

import junit.framework.Assert.assertEquals

class EmailSenderMock : EmailSender {

    var paymentRequests: ArrayList<PaymentRequest> = ArrayList()
    var expectedPaymentRequests: ArrayList<PaymentRequest> = ArrayList()


    override fun sendEmail(paymentRequest: PaymentRequest) {
        this.paymentRequests.add(paymentRequest)
    }

    fun expected(paymentRequest: PaymentRequest) {
        expectedPaymentRequests.add(paymentRequest)
    }

    fun verify() {
        assertEquals(paymentRequests, expectedPaymentRequests)
    }
}