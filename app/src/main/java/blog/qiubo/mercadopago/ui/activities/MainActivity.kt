package blog.qiubo.mercadopago.ui.activities

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import blog.qiubo.mercadopago.Constants
import blog.qiubo.mercadopago.R
import blog.qiubo.mercadopago.presenters.MainPresenter
import blog.qiubo.mercadopago.ui.adapters.viewpagers.PaymentPagerAdapter
import blog.qiubo.mercadopago.ui.views.IMainView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), IMainView {

    private val mPresenter = MainPresenter(this)
    private val mAdapter = PaymentPagerAdapter(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupModule()
    }

    override fun onDestroy() {
        mPresenter.onDestroy()
        super.onDestroy()
    }

    private fun setupModule() {
        setupViewPager()
        setupButtons()
    }

    private fun setupButtons() {
        btn_next.setOnClickListener {
            val index = vw_pager.currentItem + 1
            if (index < mAdapter.count) {
                vw_pager.currentItem = index
                mPresenter.notifyNext(index)
            }
        }

        btn_prev.setOnClickListener {
            val index = vw_pager.currentItem - 1
            if (index >= 0) {
                vw_pager.currentItem = index
            }
        }
    }

    private fun setupViewPager() {
        vw_pager.offscreenPageLimit = Constants.PAYMENT_STEPS
        vw_pager.adapter = mAdapter
        vw_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
                // nothing here
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                // nothing here
            }

            override fun onPageSelected(position: Int) {
                mPresenter.checkUIByPosition(position, mAdapter.count)
            }
        })
    }

    override fun setPrevButtonVisibility(visibility: Int) {
        btn_prev.visibility = visibility
    }

    override fun setNextLabel(label: Int) {
        btn_next.setText(label)
    }
}
