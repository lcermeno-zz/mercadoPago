package blog.qiubo.mercadopago.dtos

import blog.qiubo.mercadopago.Constants
import com.google.gson.annotations.SerializedName

/**
 * Created by Lawrence Cermeño on 06/08/18.
 */
class PayerCostDTO {
    @SerializedName(Constants.RECOMMENDED_MESSAGE_KEY)
    var mRecommendedMessage: String? = null
}