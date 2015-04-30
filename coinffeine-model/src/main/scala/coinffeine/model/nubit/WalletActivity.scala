package coinffeine.model.nubit

import org.joda.time.DateTime

import coinffeine.model.currency._

case class WalletActivity(entries: Seq[WalletActivity.Entry] = Seq.empty)

object WalletActivity {

  case class Entry(time: DateTime,
                   tx: ImmutableTransaction,
                   amount: Nubit.Amount)

  def apply(wallet: Wallet, transactions: MutableTransaction*): WalletActivity = WalletActivity(
    transactions.map{ tx =>
      Entry(
        time = new DateTime(tx.getUpdateTime),
        tx = ImmutableTransaction(tx),
        amount = tx.getValue(wallet)
      )
    }
  )
}
