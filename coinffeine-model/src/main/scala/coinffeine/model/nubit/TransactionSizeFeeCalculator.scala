package coinffeine.model.nubit

import coinffeine.model.currency._

/** Computes fees based on the size of the transactions. */
object TransactionSizeFeeCalculator extends NubitFeeCalculator {

  override val defaultTransactionFee = 0.01.NBT
}
