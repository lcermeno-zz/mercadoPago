package blog.qiubo.mercadopago.ui.adapters.recyclerviews

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import blog.qiubo.mercadopago.Constants
import blog.qiubo.mercadopago.R
import blog.qiubo.mercadopago.ui.viewholders.BankViewHolder
import blog.qiubo.mercadopago.ui.viewmodels.BankVM

/**
 * Created by Lawrence Cermeño on 05/08/18.
 */
class BankAdapter (private val mListener: IOnClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var mItems: List<BankVM>? = null

    interface IOnClickListener {
        fun onBankSelected(item: BankVM)
    }

    override fun getItemCount(): Int = mItems?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_payment_method_item, parent, false)
        return BankViewHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val vm = mItems?.get(position)
        val viewHolder = holder as BankViewHolder
        vm?.let { viewHolder.setValues(it) }
    }

    fun update(index: Int) {
        if (index != Constants.INDEX_NOT_FOUND) {
            notifyItemChanged(index)
        }
    }
}