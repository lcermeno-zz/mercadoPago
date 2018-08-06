package blog.qiubo.mercadopago.dtos

import blog.qiubo.mercadopago.Constants
import com.google.gson.annotations.SerializedName

/**
 * Created by Lawrence Cermeño on 06/08/18.
 */
class InstallmentDTO {
    @SerializedName(Constants.PAYER_COSTS_KEY)
    var mPayerCosts: List<PayerCostDTO>? = null
}