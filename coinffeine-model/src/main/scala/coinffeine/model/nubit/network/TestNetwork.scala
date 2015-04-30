package coinffeine.model.nubit.network

import com.matthewmitchell.nubitsj.params.RegTestParams

import coinffeine.model.nubit.NetworkComponent
import coinffeine.model.network.NetworkEndpoint

trait TestNetwork extends RegTestParams with NetworkComponent.SeedPeers {
  trait Component extends NetworkComponent {
    override lazy val network = TestNetwork.this
  }
}

object IntegrationTestNetwork extends TestNetwork {
  dnsSeeds = Array.empty
  override val seedPeers = Seq(NetworkEndpoint("104.245.36.10", 18000))
}

object PublicTestNetwork extends TestNetwork {
  dnsSeeds = Array.empty
  override val seedPeers =
    for (port <- 18000 to 18004) yield NetworkEndpoint("104.245.36.10", port)
}

