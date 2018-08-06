package blog.qiubo.mercadopago.presenters

import blog.qiubo.mercadopago.Constants
import blog.qiubo.mercadopago.events.AmountEvent
import blog.qiubo.mercadopago.events.StepEvent
import blog.qiubo.mercadopago.presenters.interfaces.IAmountPresenter
import blog.qiubo.mercadopago.ui.views.IAmountView
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

/**
 * Created by Lawrence Cermeño on 06/08/18.
 */
class AmountPresenter(var mView: IAmountView?) : IAmountPresenter {
    override fun onCreate() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
    }

    override fun onDestroy() {
        EventBus.getDefault().unregister(this)
        mView = null
    }

    @Subscribe
    fun onStepEvent(event: StepEvent) {
        when (event.mCode) {
            Constants.STEP_AMOUNT -> {
                val amountTxt = mView?.getAmount() ?: ""
                val amount: Float = if (amountTxt.isEmpty()) 0f else amountTxt.toFloat()
                val event = AmountEvent().apply { mAmount = amount }
                EventBus.getDefault().post(event)
            }
        }
    }
}