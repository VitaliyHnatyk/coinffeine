package coinffeine.model.currency

import scala.language.implicitConversions

import org.bitcoinj.core.Coin
import com.matthewmitchell.nubitsj.core.Coin

trait Implicits {

  implicit val bitcoinIsNumeric = Bitcoin.numeric
  implicit val nubitIsNumeric = Nubit.numeric
  implicit val euroIsNumeric = Euro.numeric
  implicit val usDollarIsNumeric = UsDollar.numeric

  implicit class BitcoinSatoshiConverter(btc: Bitcoin.Amount) {
    def asSatoshi = (btc.value * Bitcoin.OneBtcInSatoshi).toBigIntExact().get.underlying()
  }

  implicit class NubitSatoshiConverter(nbt: Nubit.Amount) {
    def asSatoshi = (nbt.value * Nubit.OneNbtInSatoshi).toBigIntExact().get.underlying()
  }

  implicit def pimpMyDouble(i: Double) = new Implicits.Units(i)
  implicit def pimpMyDecimal(i: BigDecimal) = new Implicits.Units(i)
  implicit def pimpMyInt(i: Int) = new Implicits.Units(i)

  implicit def convertToBitcoinjCoin(amount: Bitcoin.Amount): Coin = Coin.valueOf(amount.units)
  implicit def convertToBitcoinAmount(amount: Coin): Bitcoin.Amount =
    CurrencyAmount(amount.value, Bitcoin)

  implicit def convertToNubitsjCoin(amount: Nubit.Amount): Coin = Coin.valueOf(amount.units)
  implicit def convertToNubitAmount(amount: Coin): Nubit.Amount =
    CurrencyAmount(amount.value, Nubit)
}

object Implicits {
  class Units(val i: BigDecimal) extends AnyVal {
    def BTC: Bitcoin.Amount = Bitcoin(i)
    def NBT: Nubit.Amount = Nubit(i)
    def EUR: Euro.Amount = Euro(i)
    def USD: UsDollar.Amount = UsDollar(i)
  }
}
