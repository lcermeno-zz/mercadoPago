package blog.qiubo.mercadopago.ui.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import blog.qiubo.mercadopago.ui.adapters.recyclerviews.BankAdapter
import blog.qiubo.mercadopago.ui.viewmodels.BankVM
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.recycler_payment_method_item.*

/**
 * Created by Lawrence Cermeño on 05/08/18.
 */
class BankViewHolder (override val containerView: View, mListener: BankAdapter.IOnClickListener) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    var mItem: BankVM? = null

    init {
        vw_payment_method_item_root.setOnClickListener {
            mItem?.let {
                it.mSelected = !it.mSelected
                mListener.onBankSelected(it)
                setCheckVisibility(it.mSelected)
            }
        }
    }

    fun setValues(vm: BankVM) {
        mItem = vm
        txt_payment_method.text = vm.mBankDTO?.mName
        vm.mBankDTO?.mThumbnail?.let {
            Glide.with(containerView.context).load(it).into(img_vw_payment_method)
        }
        setCheckVisibility(vm.mSelected)
    }

    private fun setCheckVisibility(isSelected: Boolean) {
        img_vw_payment_method_check.visibility = if (isSelected) View.VISIBLE else View.GONE
    }
}