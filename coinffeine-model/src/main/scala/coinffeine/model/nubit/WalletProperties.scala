package coinffeine.model.nubit

import coinffeine.model.currency.NubitBalance
import coinffeine.model.properties.{MutableProperty, Property}

trait WalletProperties {
  val balance: Property[Option[NubitBalance]]
  val primaryAddress: Property[Option[Address]]
  val activity: Property[WalletActivity]
}

class MutableWalletProperties extends WalletProperties {

  override val balance: MutableProperty[Option[NubitBalance]] =
    new MutableProperty(None)

  override val primaryAddress: MutableProperty[Option[Address]] =
    new MutableProperty(None)

  override val activity: MutableProperty[WalletActivity] =
    new MutableProperty(WalletActivity())
}
