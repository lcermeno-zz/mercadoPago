package blog.qiubo.mercadopago.ui.widgets

import android.content.Context
import android.content.res.TypedArray
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

import blog.qiubo.mercadopago.R

/**
 * Created by Lawrence Cermeño on 01/08/18.
 */
class QiuboViewPager : ViewPager {
    private var mSwipeEnabled = true

    constructor(context: Context) : super(context, null)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        if (attrs != null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.QiuboViewPager)
            mSwipeEnabled = a.getBoolean(R.styleable.QiuboViewPager_swipe, true)
            a.recycle()
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return mSwipeEnabled && super.onTouchEvent(event)
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        return mSwipeEnabled && super.onInterceptTouchEvent(event)
    }

    override fun canScrollHorizontally(direction: Int): Boolean {
        return mSwipeEnabled
    }
}
