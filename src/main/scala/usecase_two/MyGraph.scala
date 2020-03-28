package usecase_two

import org.apache.hadoop.yarn.state.Graph
import scala.collection.mutable.ArrayBuffer

object MyGraph {
  def main(args: Array[String]) {
    def vertices: Array[String] = Array("Societe_Generale", "Credit Agricole", "UBS",
        "HSBC", "BNP Paribas", "RBS", "Santander", "Boursorama", 
        "Deutsche")

    def edges: ArrayBuffer[(Int, Int)] = ArrayBuffer(
        (0, 1), (0, 2), 
        (1, 3), (1, 4), (1, 7), 
        (2, 5), 
        (3, 6),
        (4, 7), 
        (5, 8)
        )
        val graph = new UseCaseTwoGraph(vertices, edges)
        println("number of vertices in graph: " + graph.size)
        println("the vertex with index 1 is: " + graph.vertex(1))
        println("the index for HSBC is: " + graph.index("HSBC"))
        println("the edges for graph: ")
        println("adjacency list for graph: ")
        println(graph.printAdjacencyList)
        println("adjacency matrix for graph: ")
        println(graph.printAdjacencyMatrix)    
   }
}