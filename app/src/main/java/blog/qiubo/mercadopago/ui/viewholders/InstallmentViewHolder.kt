package blog.qiubo.mercadopago.ui.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import blog.qiubo.mercadopago.R
import blog.qiubo.mercadopago.ui.adapters.recyclerviews.InstallmentAdapter
import blog.qiubo.mercadopago.ui.viewmodels.InstallmentVM
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.recycler_payment_method_item.*

/**
 * Created by Lawrence Cermeño on 06/08/18.
 */
class InstallmentViewHolder(override val containerView: View, mListener: InstallmentAdapter.IOnClickListener) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    var mItem: InstallmentVM? = null

    init {
        vw_payment_method_item_root.setOnClickListener {
            mItem?.let {
                it.mSelected = !it.mSelected
                mListener.onInstallmentSelected(it)
                setCheckVisibility(it.mSelected)
            }
        }
    }

    fun setValues(vm: InstallmentVM) {
        mItem = vm
        txt_payment_method.text = vm.mRecommendedMessage
        Glide.with(containerView.context).load(R.drawable.ic_payment).into(img_vw_payment_method)
        setCheckVisibility(vm.mSelected)
    }

    private fun setCheckVisibility(isSelected: Boolean) {
        img_vw_payment_method_check.visibility = if (isSelected) View.VISIBLE else View.GONE
    }
}