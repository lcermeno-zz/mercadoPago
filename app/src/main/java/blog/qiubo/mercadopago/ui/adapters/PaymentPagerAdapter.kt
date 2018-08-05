package blog.qiubo.mercadopago.ui.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import blog.qiubo.mercadopago.ui.fragments.AmountFragment
import blog.qiubo.mercadopago.ui.fragments.BankSelectionFragment
import blog.qiubo.mercadopago.ui.fragments.InstallmentsFragment
import blog.qiubo.mercadopago.ui.fragments.PaymentMethodFragment
import blog.qiubo.mercadopago.ui.views.IPaymentDetailView

/**
 * Created by Lawrence Cermeño on 04/08/18.
 */
class PaymentPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    private val mItems: MutableList<IPaymentDetailView> = mutableListOf()

    init {
        mItems.add(AmountFragment.newInstance())
        mItems.add(PaymentMethodFragment.newInstance())
        mItems.add(BankSelectionFragment.newInstance())
        mItems.add(InstallmentsFragment.newInstance())
    }

    override fun getItem(position: Int): Fragment = mItems[position] as Fragment

    override fun getCount(): Int = mItems.size

    override fun getPageTitle(position: Int): CharSequence? = mItems[position].getPageTitle(position)
}