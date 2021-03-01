package com.example.api.payment

class PaymentService {
    var logger: Logger
    var emailSender: EmailSender
    var storage: Storage
    val error: Int = 500

    constructor(logger: Logger, emailSender: EmailSender, storage: Storage) {
        this.logger = logger
        this.emailSender = emailSender
        this.storage = storage
    }

    fun createPaymentRequest(sale: Sale, creditCard: CreditCard): PaymentRequest {
        logger.append("Creating payment for sale $sale")
        return PaymentRequest(sale, creditCard)
    }

    fun sendPayment(request: PaymentRequest, restClient: PaymentRestClient): PaymentResponse {
        logger.append("Sending payment .... ")
        val paymentResponse = restClient.sendPayment(request)
        logger.append("Payment response : " + paymentResponse.code())
        if (paymentResponse.code() == error ) {
            emailSender.sendEmail(request)
            storage.save(request)
        }
        return paymentResponse
    }
}