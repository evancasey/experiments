/**
 * Version 3:
 * The training interface
 */

val trainingData = (trainX, trainY)

val placeholders = Seq(
  Placeholder(weightMatrix = w1, biasMatrix = b1, SigmoidActivation),
  Placeholder(weightMatrix = w2, biasMatrix = b2, SigmoidActivation) // output layer
)

val optimizer = GradientDescentOptimizer(L2Cost, 1.0)
val trainer = Trainer(optimizer)
val output = trainer.train(placeholders, trainingData)