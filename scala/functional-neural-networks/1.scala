/**
 * Version 1:
 * For loops, mutating state, oh no!
 */

case class Layer(
  weightMatrix: DenseMatrix[Double],
  biasMatrix: DenseMatrix[Double],
  activation: Activation
) 

def forwardpropagation(trainX: DenseMatrix[Double], layers: Seq[Layers]): DenseMatrix[Double] = {
  var activation: DenseMatrix[Double] = trainX
  for (layer <- layers) {
    val z = (activation * layer.weightMatrix) + layer.biasMatrix
    activation = activation.transform(z)       
  }
  activation
}

def backpropagation(outputSigma: DenseMatrix[Double], layers: Seq[Layer], trainY: DenseMatrix[Double]): Seq[Layers] = {
  val layers = layers.reverse
  val outputLayer = layers.head
  val hiddenLayers = layers.tail

  val outputDelta = optimizer.cost.delta(outputLayer, outputSigma, trainY)
  val nablaOutputLayer = updateLayer(outputLayer, outputDelta)

  var layers = layers

  // Traverse layers (in reverse)
  for ((i,layer) <- zipWithIndex(layers)) {
    val newDelta = (layer.weightMatrix.t * delta) * layer.activation.transformPrime(layer.z)
    val nablaLayer = updateLayer(layer, newDelta)
    layers(i) = nablaLayer
  }

  layers
}
