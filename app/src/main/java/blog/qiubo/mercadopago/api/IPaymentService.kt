package blog.qiubo.mercadopago.api

import blog.qiubo.mercadopago.Constants
import blog.qiubo.mercadopago.dtos.BankDTO
import blog.qiubo.mercadopago.dtos.InstallmentDTO
import blog.qiubo.mercadopago.dtos.PaymentMethodDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Lawrence Cermeño on 05/08/18.
 */
interface IPaymentService {

    companion object {
        const val END_POINT = "v1/payment_methods"
    }

    @GET(END_POINT)
    fun requestPayments(): Call<List<PaymentMethodDTO>?>

    @GET("$END_POINT/card_issuers")
    fun requestBanks(@Query(Constants.PAYMENT_METHOD_ID_KEY) paymentMethod: String): Call<List<BankDTO>?>

    @GET("$END_POINT/installments")
    fun requestInstallments(@Query(Constants.AMOUNT_KEY) amount: Float,
                            @Query(Constants.PAYMENT_METHOD_ID_KEY) paymentMethod: String,
                            @Query(Constants.BANK_ID_KEY) bank: String): Call<List<InstallmentDTO>?>
}