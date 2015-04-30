package coinffeine.model.currency

import java.math.BigInteger

case object Nubit extends Currency {
  val OneNbtInSatoshi = BigDecimal(100000000)
  override val precision = 8
  override val symbol = "NBT"
  override val preferredSymbolPosition = Currency.SymbolSuffixed
  override val toString = symbol

  def fromSatoshi(amount: BigInteger): Nubit.Amount =
    Nubit(BigDecimal(amount) / OneNbtInSatoshi)
  def fromSatoshi(amount: BigInt): Nubit.Amount = Nubit(BigDecimal(amount) / OneNbtInSatoshi)
  def fromSatoshi(amount: Long): Nubit.Amount = fromSatoshi(BigInt(amount))
}

