package blog.qiubo.mercadopago.dtos

import blog.qiubo.mercadopago.Constants
import com.google.gson.annotations.SerializedName

/**
 * Created by Lawrence Cermeño on 05/08/18.
 */
class PaymentMethodDTO {
    @SerializedName(Constants.ID_KEY)
    var mId: String? = null
    @SerializedName(Constants.NAME_KEY)
    var mName: String? = null
    @SerializedName(Constants.PAYMENT_TYPE_KEY)
    var mPaymentType: String? = null
    @SerializedName(Constants.THUMBNAIL_TYPE_KEY)
    var mThumbnail: String? = null
    @SerializedName(Constants.MAX_ALLOWED_AMOUNT_KEY)
    var mMaxAllowedAmount: Float = 0f
    @SerializedName(Constants.MIN_ALLOWED_AMOUNT_KEY)
    var mMinAllowedAmount: Float = 0f
}