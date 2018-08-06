package blog.qiubo.mercadopago.repositories

import blog.qiubo.mercadopago.Constants
import blog.qiubo.mercadopago.api.IPaymentService
import blog.qiubo.mercadopago.dtos.BankDTO
import blog.qiubo.mercadopago.dtos.InstallmentDTO
import blog.qiubo.mercadopago.dtos.PaymentMethodDTO
import blog.qiubo.mercadopago.events.BankEvent
import blog.qiubo.mercadopago.events.InstallmentEvent
import blog.qiubo.mercadopago.events.PaymentEvent
import blog.qiubo.mercadopago.managers.RetrofitManager
import blog.qiubo.mercadopago.repositories.interfaces.IPaymentRepository
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

/**
 * Created by Lawrence Cermeño on 05/08/18.
 */
class PaymentRepository : IPaymentRepository {

    override fun requestPaymentMethods(): Observable<PaymentEvent> {
        return Observable.create { e ->
            val service = RetrofitManager.instance.getService(IPaymentService::class.java)
            val call = service.requestPayments()
            call.enqueue(object : Callback<List<PaymentMethodDTO>?> {
                override fun onFailure(call: Call<List<PaymentMethodDTO>?>?, t: Throwable) {
                    e.onError(t)
                }

                override fun onResponse(call: Call<List<PaymentMethodDTO>?>?, response: Response<List<PaymentMethodDTO>?>) {
                    val event = PaymentEvent()
                    when (response.code()) {
                        HttpURLConnection.HTTP_OK -> {
                            event.mCode = Constants.ON_SUCCESS
                            event.mPaymentMethods = response.body()
                        }
                        else -> event.mCode = Constants.ON_ERROR
                    }
                    e.onNext(event)
                }
            })
        }
    }

    override fun requestBanks(paymentMethod: String): Observable<BankEvent> {
        return Observable.create { e ->
            val service = RetrofitManager.instance.getService(IPaymentService::class.java)
            val call = service.requestBanks(paymentMethod)
            call.enqueue(object : Callback<List<BankDTO>?> {
                override fun onFailure(call: Call<List<BankDTO>?>?, t: Throwable) {
                    e.onError(t)
                }

                override fun onResponse(call: Call<List<BankDTO>?>?, response: Response<List<BankDTO>?>) {
                    val event = BankEvent()
                    when (response.code()) {
                        HttpURLConnection.HTTP_OK -> {
                            event.mCode = Constants.ON_SUCCESS
                            event.mBankDTOs = response.body()
                        }
                        else -> event.mCode = Constants.ON_ERROR
                    }
                    e.onNext(event)
                }
            })
        }
    }

    override fun requestInstallments(amount: Float, paymentMethod: String, bankId: String): Observable<InstallmentEvent> {
        return Observable.create { e ->
            val service = RetrofitManager.instance.getService(IPaymentService::class.java)
            val call = service.requestInstallments(amount, paymentMethod, bankId)
            call.enqueue(object : Callback<List<InstallmentDTO>?> {
                override fun onFailure(call: Call<List<InstallmentDTO>?>?, t: Throwable) {
                    e.onError(t)
                }

                override fun onResponse(call: Call<List<InstallmentDTO>?>?, response: Response<List<InstallmentDTO>?>) {
                    val event = InstallmentEvent()
                    when (response.code()) {
                        HttpURLConnection.HTTP_OK -> {
                            event.mCode = Constants.ON_SUCCESS
                            event.mInstallmentDTOs = response.body()
                        }
                        else -> event.mCode = Constants.ON_ERROR
                    }
                    e.onNext(event)
                }
            })
        }
    }
}