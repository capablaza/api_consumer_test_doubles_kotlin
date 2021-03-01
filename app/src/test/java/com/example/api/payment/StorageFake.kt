package com.example.api.payment

class StorageFake : Storage {

    var paymentRequests: ArrayList<PaymentRequest> = ArrayList()


    override fun save(request: PaymentRequest) {
        paymentRequests.add(request!!)
    }

    override fun find(sale: Sale): PaymentRequest {
        for (request in paymentRequests) {
            if (request.sale() == sale) return request
        }
        return PaymentRequest(Sale(), CreditCard("", 0))
    }

    override fun remove(request: PaymentRequest) {}
}