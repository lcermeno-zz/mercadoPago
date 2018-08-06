package blog.qiubo.mercadopago.presenters

import blog.qiubo.mercadopago.Constants
import blog.qiubo.mercadopago.dtos.BankDTO
import blog.qiubo.mercadopago.dtos.InstallmentDTO
import blog.qiubo.mercadopago.dtos.PaymentMethodDTO
import blog.qiubo.mercadopago.events.*
import blog.qiubo.mercadopago.interactors.GetInstalmentsUseCase
import blog.qiubo.mercadopago.interactors.interfaces.IUseCaseCallback
import blog.qiubo.mercadopago.presenters.interfaces.IInstallmentsPresenter
import blog.qiubo.mercadopago.ui.viewmodels.InstallmentVM
import blog.qiubo.mercadopago.ui.views.IInstallmentsView
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

/**
 * Created by Lawrence Cermeño on 06/08/18.
 */
class InstallmentsPresenter(var mView: IInstallmentsView?) : IInstallmentsPresenter {

    private var mInstallmentSelected: InstallmentVM? = null
    private var mVmCounter: Long = 0
    private var mAmount: Float? = null
    private var mPaymentMethodDTO: PaymentMethodDTO? = null
    private var mBankDTO: BankDTO? = null
    private var mInstallmentDTO: InstallmentDTO? = null

    override fun onCreate() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
    }

    override fun onDestroy() {
        EventBus.getDefault().unregister(this)
        mView = null
    }

    override fun requestInstallments() {
        mView?.showProgress(true)
        val getInstalmentsUseCase = GetInstalmentsUseCase()
        getInstalmentsUseCase.mCallback = object : IUseCaseCallback<InstallmentEvent> {
            override fun onSuccess(event: InstallmentEvent) {
                val vms: List<InstallmentVM>? = buildVms(event.mInstallmentDTOs)
                mView?.setItems(vms)
                mView?.showProgress(false)
            }

            override fun onError(event: InstallmentEvent) {
                mView?.showProgress(false)
                event.mMessage?.let { mView?.showMessage(it) }
            }
        }

        if (mAmount != null && mPaymentMethodDTO != null && mBankDTO != null) {
            getInstalmentsUseCase.execute(mAmount!!, mPaymentMethodDTO!!.mId!!, mBankDTO!!.mId!!)
        }
    }

    private fun buildVms(installmentDTOs: List<InstallmentDTO>?): List<InstallmentVM>? {
        var vms: MutableList<InstallmentVM>? = null
        installmentDTOs?.let { installmentDTOs ->
            vms = mutableListOf()
            for (dto in installmentDTOs) {
                dto.mPayerCosts?.let { payerCosts ->
                    for (payerCost in payerCosts) {
                        mVmCounter++
                        val item = InstallmentVM()
                                .apply {
                                    mId = mVmCounter
                                    mRecommendedMessage = payerCost.mRecommendedMessage
                                }
                        vms!!.add(item)
                    }
                }
            }
        }
        return vms
    }

    override fun onInstallmentSelected(installmentVM: InstallmentVM, items: List<InstallmentVM>?) {
        items?.let { items ->
            mInstallmentSelected?.let {
                if (it.mId != installmentVM.mId) {
                    it.mSelected = false
                    val index = items.indexOf(it)
                    mView?.update(index)
                }
            }
            mInstallmentSelected = installmentVM
        }
    }

    @Subscribe
    fun onAmountEvent(event: AmountEvent) {
        mAmount = event.mAmount
    }

    @Subscribe
    fun onPaymentMethodEvent(event: PaymentEvent) {
        mPaymentMethodDTO = event.mPaymentMethodDTO
    }

    @Subscribe
    fun onBankEvent(event: BankEvent) {
        mBankDTO = event.mBankDTO
        requestInstallments()
    }

    @Subscribe
    fun onStepEvent(event: StepEvent) {
        when (event.mCode) {
            Constants.STEP_INSTALLMENTS -> {
                mInstallmentSelected?.mInstallmentDTO?.let {
                    val event = InstallmentEvent().apply { mInstallmentDTO = it }
                    EventBus.getDefault().post(event)
                }
            }
        }
    }
}