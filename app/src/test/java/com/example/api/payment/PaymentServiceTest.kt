package com.example.api.payment

import org.junit.Assert
import org.junit.Before
import org.junit.Test


class PaymentServiceTest {

    private var creditCard: CreditCard? = null
    private var logger: Logger? = null
    private var storage: Storage? = null

    val error: Int = 500
    val ok: Int = 200

    @Before
    fun setUp() {
        creditCard = CreditCard("money_card", 123435543)
        logger = LoggerDummy()
        storage = StorageDummy()
    }

    @Test
    fun givenSaleAndCreditCardWhenBothAreCorrectThenPaymentRequestIsFill() {
        val emailSender: EmailSender = EmailSenderDummy()
        val sale = Sale()
        val paymentService = PaymentService(logger!!, emailSender, storage!!)
        val paymentRequest = paymentService.createPaymentRequest(sale, creditCard!!)
        Assert.assertTrue(paymentRequest.isFill())
    }

    @Test
    fun givenPaymentRequestWhenIsValidThenResponseCodeIs200() {
        val emailSender: EmailSender = EmailSenderDummy()
        val sale = Sale()
        val paymentService = PaymentService(logger!!, emailSender, storage!!)
        val paymentRequest = paymentService.createPaymentRequest(sale, creditCard!!)
        val restClient: PaymentRestClient = PaymentRestClientStub(200)
        val paymentResponse = paymentService.sendPayment(paymentRequest, restClient)
        Assert.assertEquals(paymentResponse.code(), ok)
    }

    @Test
    fun givenPaymentRequestWhenResponseIsErrorThenSendEmail() {
        val emailSender = EmailSenderMock()
        val sale = Sale()
        val paymentService = PaymentService(logger!!, emailSender, storage!!)
        val paymentRequest = paymentService.createPaymentRequest(sale, creditCard!!)
        val restClient: PaymentRestClient = PaymentRestClientStub(500)
        val paymentResponse = paymentService.sendPayment(paymentRequest, restClient)

        //contexto
        Assert.assertEquals(paymentResponse.code(), error)
        //flujo
        emailSender.expected(paymentRequest)
        emailSender.verify()
    }

    @Test
    fun givenPaymentRequestWhenResponseIsErrorThenSendEmailOnce() {
        val emailSender = EmailSenderSpy()
        val sale = Sale()
        val paymentService = PaymentService(logger!!, emailSender, storage!!)
        val paymentRequest = paymentService.createPaymentRequest(sale, creditCard!!)
        val restClient: PaymentRestClient = PaymentRestClientStub(500)
        val paymentResponse = paymentService.sendPayment(paymentRequest, restClient)
        Assert.assertEquals(paymentResponse.code(), error)
        Assert.assertEquals(1, emailSender.timesCalled())
    }

    @Test
    fun givenPaymentRequestWhenIsWrongThenStorageHaveRequestSaved() {
        val emailSender: EmailSender = EmailSenderDummy()
        val storage: Storage = StorageFake()
        val sale = Sale()
        val paymentService = PaymentService(logger!!, emailSender, storage)
        val paymentRequest = paymentService.createPaymentRequest(sale, creditCard!!)
        val restClient: PaymentRestClient = PaymentRestClientStub(500)
        val paymentResponse = paymentService.sendPayment(paymentRequest, restClient)
        Assert.assertEquals(paymentResponse.code(), error)
        val requestFromStorage = storage.find(sale)
        Assert.assertEquals(requestFromStorage, paymentRequest)
    }
}