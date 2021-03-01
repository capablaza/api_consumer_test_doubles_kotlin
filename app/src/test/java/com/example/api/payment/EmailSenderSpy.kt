package com.example.api.payment

class EmailSenderSpy : EmailSender {

    var paymentRequests: ArrayList<PaymentRequest> = ArrayList()

    override fun sendEmail(paymentRequest: PaymentRequest) {
        this.paymentRequests.add(paymentRequest)
    }
    fun timesCalled(): Int {
        return paymentRequests.size
    }
}