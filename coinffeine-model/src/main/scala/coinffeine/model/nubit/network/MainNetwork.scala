package coinffeine.model.nubit.network

import com.matthewmitchell.nubitsj.params.MainNetParams

import coinffeine.model.nubit.NetworkComponent

object MainNetwork extends NetworkComponent {
  override lazy val network = new MainNetParams with NetworkComponent.SeedPeers {
    /** No handpicked peers, use DNS bootstrap */
    override val seedPeers = Seq.empty
  }
}
