package coinffeine.model.nubit

object MultiSigOutput {
  def unapply(output: MutableTransactionOutput): Option[MultiSigInfo] =
    MultiSigInfo.fromScript(output.getScriptPubKey)
}
