package blog.qiubo.mercadopago.presenters

import blog.qiubo.mercadopago.Constants
import blog.qiubo.mercadopago.dtos.PaymentMethodDTO
import blog.qiubo.mercadopago.events.AmountEvent
import blog.qiubo.mercadopago.events.BaseEvent
import blog.qiubo.mercadopago.events.PaymentEvent
import blog.qiubo.mercadopago.events.StepEvent
import blog.qiubo.mercadopago.interactors.GetPaymentMethodsUseCase
import blog.qiubo.mercadopago.interactors.interfaces.IUseCaseCallback
import blog.qiubo.mercadopago.presenters.interfaces.IPaymentMethodsPresenter
import blog.qiubo.mercadopago.ui.viewmodels.PaymentMethodVM
import blog.qiubo.mercadopago.ui.views.IPaymentMethodView
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

/**
 * Created by Lawrence Cermeño on 05/08/18.
 */
class PaymentMethodPresenter(var mView: IPaymentMethodView?) : IPaymentMethodsPresenter {

    private var mPaymentMethodSelected: PaymentMethodVM? = null
    private var mVmCounter: Long = 0

    override fun onCreate() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
        requestPaymentMethods()
    }

    override fun onDestroy() {
        EventBus.getDefault().unregister(this)
        mView = null
    }

    override fun requestPaymentMethods() {
        mView?.showProgress(true)
        val getPaymentMethodsUseCase = GetPaymentMethodsUseCase()
        getPaymentMethodsUseCase.mCallback = object : IUseCaseCallback<PaymentEvent> {
            override fun onSuccess(event: PaymentEvent) {
                mView?.showProgress(false)
                val vms: List<PaymentMethodVM>? = buildVms(event.mPaymentMethods)
                mView?.setItems(vms)
            }

            override fun onError(event: PaymentEvent) {
                event.mMessage?.let { mView?.showMessage(it) }
                mView?.showProgress(false)
            }
        }
        getPaymentMethodsUseCase.execute()
    }

    private fun buildVms(paymentMethods: List<PaymentMethodDTO>?): List<PaymentMethodVM>? {
        var vms: MutableList<PaymentMethodVM>? = null
        paymentMethods?.let {
            val collection = it.filter { it.mPaymentType == Constants.CREDIT_CARD_TYPE }
            vms = mutableListOf()
            for (dto in collection) {
                mVmCounter++
                val item = PaymentMethodVM()
                        .apply {
                            mId = mVmCounter
                            mPaymentDTO = dto
                        }
                vms!!.add(item)
            }
        }
        return vms
    }

    override fun onPaymentMethodSelected(paymentMethod: PaymentMethodVM, items: List<PaymentMethodVM>?) {
        items?.let { items ->
            mPaymentMethodSelected?.let {
                if (it.mId != paymentMethod.mId) {
                    it.mSelected = false
                    val index = items.indexOf(it)
                    mView?.update(index)
                }
            }
            mPaymentMethodSelected = paymentMethod
        }
    }

    @Subscribe
    fun onStepEvent(event: StepEvent) {
        when (event.mCode) {
            Constants.STEP_PAYMENT_METHOD -> {
                mPaymentMethodSelected?.mPaymentDTO?.let {
                    val event = PaymentEvent().apply { mPaymentMethodDTO = it }
                    EventBus.getDefault().post(event)
                }
            }
            Constants.STEP_FINISH -> {
                clearCurrentSelection()
            }
        }
    }

    private fun clearCurrentSelection() {
        val currentItems = mView?.getItems()
        currentItems?.let { items ->
            mPaymentMethodSelected?.let {
                it.mSelected = false
                val index = items.indexOf(it)
                mView?.update(index)
            }
            mPaymentMethodSelected = null
        }
    }
}