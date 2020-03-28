package usecase_two

import scala.collection.mutable.ArrayBuffer

class UseCaseTwoGraph(val vertex: IndexedSeq[String], edges: Seq[(Int, Int)]) {
    def size: Int = vertex.length
    val index: Map[String, Int] = vertex.zipWithIndex.toMap
    val adjacent = edges groupBy (_._1) mapValues (_ map (_._2))
    def adjacencyMatrix = adjacent mapValues (_.toSet) mapValues (0 to size map _)
    def printAdjacencyList: String = adjacent mapValues (_ mkString ", ") mkString "\n"
    def printAdjacencyMatrix: String = adjacencyMatrix mapValues(_ mkString ", ") mkString "\n"
  }