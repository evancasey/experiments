/**
 * Version 2:
 * Folds are powerful!
 */


def forwardpropagation(trainX: DenseMatrix[Double]): DenseMatrix[Double] = {
  layers.foldLeft(trainX) { (input, layer) =>
    val zw = (input * layer.weightMatrix) + layer.biasMatrix 
    activation.transform(zw)
  }
}

def backpropagation(outputSigma: DenseMatrix[Double], layers: Seq[Layer], trainY: DenseMatrix[Double]): DenseMatrix[Delta] = {
  val layers = layers.reverse
  val outputLayer = layers.head
  val hiddenLayers = layers.tail


  val outputDelta = optimizer.cost.delta(outputLayer, outputSigma, trainY)
  val nablaOutputLayer = updateLayer(outputLayer, outputDelta)


  // Traverse layers (in reverse)
  hiddenLayers.foldLeft(outputSigma) { (delta, layer) =>
    val newDelta = (layer.weightMatrix.t * delta) * layer.activation.transformPrime(layer.z)
    val nablaLayer = updateLayer(layer, newDelta) 
    newDelta
  }

  // What about our layer updates???
}
