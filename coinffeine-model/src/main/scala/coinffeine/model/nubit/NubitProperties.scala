package coinffeine.model.nubit

trait NubitProperties {
  val wallet: WalletProperties
  val network: NetworkProperties
}

class MutableNubitProperties extends NubitProperties {
  override val wallet = new MutableWalletProperties
  override val network = new MutableNetworkProperties
}

object MutableNubitProperties {

  trait Component {
    def nubitProperties: MutableNubitProperties
  }
}
