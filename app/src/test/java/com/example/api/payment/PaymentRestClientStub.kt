package com.example.api.payment

class PaymentRestClientStub : PaymentRestClient {

    var code: Int

    constructor(code: Int){
        this.code = code
    }

    override fun sendPayment(request: PaymentRequest): PaymentResponse {
        return PaymentResponse(this.code);
    }
}