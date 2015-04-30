package coinffeine.model.nubit

import coinffeine.model.currency.Nubit

trait NubitFeeCalculator {
  def defaultTransactionFee: Nubit.Amount
}
