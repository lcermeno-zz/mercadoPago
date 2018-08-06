package blog.qiubo.mercadopago.dtos

import blog.qiubo.mercadopago.Constants
import com.google.gson.annotations.SerializedName

/**
 * Created by Lawrence Cermeño on 05/08/18.
 */
class BankDTO {
    @SerializedName(Constants.ID_KEY)
    var mId: String? = null
    @SerializedName(Constants.NAME_KEY)
    var mName: String? = null
    @SerializedName(Constants.THUMBNAIL_TYPE_KEY)
    var mThumbnail: String? = null
}