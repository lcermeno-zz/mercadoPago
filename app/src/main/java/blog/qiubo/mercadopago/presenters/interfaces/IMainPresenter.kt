package blog.qiubo.mercadopago.presenters.interfaces

/**
 * Created by Lawrence Cermeño on 05/08/18.
 */
interface IMainPresenter: IOnDestroy {
    fun checkUIByPosition(position: Int, size: Int)
    fun notifyNext(nextStep: Int)
}