package blog.qiubo.mercadopago.ui.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import blog.qiubo.mercadopago.ui.adapters.recyclerviews.PaymentMethodAdapter
import blog.qiubo.mercadopago.ui.viewmodels.PaymentMethodVM
import com.bumptech.glide.Glide

import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.recycler_payment_method_item.*

/**
 * Created by Lawrence Cermeño on 05/08/18.
 */
class PaymentMethodViewHolder(override val containerView: View, mListener: PaymentMethodAdapter.IOnClickListener) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    var mItem: PaymentMethodVM? = null

    init {
        vw_payment_method_item_root.setOnClickListener {
            mItem?.let {
                it.mSelected = !it.mSelected
                mListener.onPaymentMethodSelected(it)
                setCheckVisibility(it.mSelected)
            }
        }
    }

    fun setValues(vm: PaymentMethodVM) {
        mItem = vm
        txt_payment_method.text = vm.mPaymentDTO?.mName
        vm.mPaymentDTO?.mThumbnail?.let {
            Glide.with(containerView.context).load(it).into(img_vw_payment_method)
        }
        setCheckVisibility(vm.mSelected)
    }

    private fun setCheckVisibility(isSelected: Boolean) {
        img_vw_payment_method_check.visibility = if (isSelected) View.VISIBLE else View.GONE
    }
}