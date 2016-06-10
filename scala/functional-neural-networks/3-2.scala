/**
 * Version 3:
 * Putting it altogether with MapAccum. Note we wrap layers in FeedForwardNetwork
 */

case class FeedForwardNetwork(layers: Vector[Layer])

def forwardpropagate(placeholders: Seq[Placeholder], trainX: DenseMatrix[Double]): (DenseMatrix[Double], FeedForwardNetwork) = {
  def step(input: DenseMatrix[Double], placeholder: Placeholder): (DenseMatrix[Double], Layer) = {
    val z = placeholder.z(input)

    val layer = Layer(
      weightMatrix = placeholder.weightMatrix,
      biasMatrix = placeholder.biasMatrix,
      activation = placeholder.activation,
      z = z,
      sigma = input
    )

    val sigma = placeholder.activation.transform(z)
    (sigma: DenseMatrix[Double], layer: Layer)
  }

  val (outputSigma, layers) = mapAccumLeft(placeholders)(trainX, step)
  (outputSigma, FeedForwardNetwork(layers))
}

def backpropagate(forward: (DenseMatrix[Double], FeedForwardNetwork), trainY: DenseMatrix[Double]): FeedForwardNetwork = {
  val (outputSigma, network) = forward
  val (hiddenLayers, outputLayer) = (network.layers.dropRight(1), network.layers.last)

  val outputDelta = optimizer.cost.delta(outputLayer, outputSigma, trainY)
  val nablaOutputLayer = updateLayer(outputLayer, outputDelta)

  def step(delta: DenseMatrix[Double], layer: Layer): (DenseMatrix[Double], Layer) = {
    val newDelta = (layer.weightMatrix.t * delta) * layer.activation.transformPrime(layer.z)
    val nablaLayer = updateLayer(layer, newDelta)

    (newDelta: DenseMatrix[Double], nablaLayer: Layer)
  }

  val (_, nablaHiddenLayers) = mapAccumRight(hiddenLayers)(outputDelta, step)
  FeedForwardNetwork(nablaHiddenLayers :+ nablaOutputLayer)
}