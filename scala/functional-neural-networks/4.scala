/**
 * Version 4:
 * Downpour SGD
 */

class FeedForwardNetworkMonoid extends Monoid[FeedForwardNetwork] {
  import FeedForwardNetwork._

  def zero: FeedForwardNetwork = FeedForwardNetwork(Seq.empty[Layer])

  def plus(l: FeedForwardNetwork, r: FeedForwardNetwork): FeedForwardNetwork = {
    val cLayers = l.layers
      .zip(r.layers)
      .map { case (l1, l2) => combineLayers(l1, l2) }
    FeedForwardNetwork(cLayers)
  }
}

val master: FeedForwardNetwork = FeedForwardNetworkMonoid.empty

// This is async
partitions.foreach { partition =>
  val network: FeedForwardNetwork = train(partition)
  update(network)
}

// Fetches current state of master and returns new master
def update(updatedNetwork: FeedForwardNetwork): FeedForwardNetwork = {
  val ffnMonoid = new FeedForwardNetworkMonoid
  ffnMonoid.plus(master, updatedNetwork)
}