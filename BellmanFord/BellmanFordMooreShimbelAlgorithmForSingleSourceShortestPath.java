// Advanced Algorithms Lab - 16SCS16
// 2. Implement the Bellman-ford algorithm and determine its performance. Give its application.

// Shortest paths. An edge-weighted digraph is a digraph where we associate weights or costs with each edge. 
// A shortest path from vertex s to vertex t is a directed path from s to t with the property that no other such path has a lower weight. 


// The Bellman-Ford algorithm solves the single-source shortest-paths problem in the general case in which edge weights may be negative.
// Where as Dijkastra Algorithm fails in this case.

// A tempting solution is to add a constant to each edge cost, 
// thus removing negative edges, calculate a shortest path on the new graph, 
// and then use that result on the original.
// The naive implementation of this strategy does not work because paths with many edges
// become more weighty than paths with few edges.

// Why use Bellman Ford Algorithm to solve Single source shortest path - 
// When a faster algortihm Dijkastra Algorithm is available

// Dijkastra algorithm does not handle negative weights but Bellman Ford algorithm does.
// The problem is that once a vertex u is declared known, it is possible that from some other, unknown vertex v there is a path back to u that is very negative. 
// In such a case, taking a path from s to v back to u is better than going from s to u without using v.

// Negative edge weights are found in various applications of graphs, hence the usefulness of Bellman-Ford algorithm
// Bellman–Ford algorithm can also detect negative cycles and report their existence

// Bellman Ford Algorithm Single source shortest paths in a DAG - Directed Acyclic Graph
// Yes the requirement is DAG , but if input is not DAG , the same can be informed

// Dijkstra's algorithm uses a priority queue to greedily select the closest vertex that has not yet been processed, and performs this relaxation process on all of its outgoing edges; 
// By contrast, the Bellman–Ford algorithm simply relaxes all the edges, and does this | V | − 1 times, 
//    where | V | is the number of vertices in the graph

// In each of these repetitions, the number of vertices with correctly calculated distances grows, from which it follows that eventually all vertices will have their correct distances. 

// Performance 

// And Bellman–Ford runs in O ( | V | ⋅ | E | ) time, 
//     where | V | and | E | are the number of vertices and edges respectively.
// Run time - is it based on data structure used in implememtation
// O ( | V | ⋅ | E | ) time is for List based graph representation implementation

// The algorithm was first proposed by Shimbel in 1955, ( but )
// but is instead named after Richard Bellman and Lester Ford, Jr., who published it in 1958 and 1956, respectively.
// Edward F. Moore also published the same algorithm in 1957, and for this reason it is also sometimes called the Bellman–Ford–Moore algorithm.

// Data Structure - Matrix , Linked List

// For Prim's , Kruskal's minimum spannig tree , what would be the suggested data structure

// How about Dijksrta Shortest Path , Priority queue - pick minimum in log n time

// Now Bellman Ford Moore Shimbel Algorithm

// BellmanFord 
// Input - list of vertices, list of edges, source vertex 
//         ( We save as Adjacency Matrix )

// The algorithm returns TRUE if and only if the graph contains no negative-weight cycles that are reachable from the source  
// Could have said TRUE is returned if Graph does not contain negative-weight cycles that are reachable from the source
// Return distance[] and predecessor[] matrix
// distance[] - Cost to reach the present vertex and 
// predecessor[] - The parent / one stop before - when traversing or travelling from source vertex to present vertex

   // This implementation takes in a graph, represented as
   // lists of vertices and edges, and fills two arrays
   // (distance and predecessor) with shortest-path
   // (less cost/distance/metric) information

 /*
   // Step 1: initialize graph
   for each vertex v in vertices:
       distance[v] := inf             // At the beginning , all vertices have a weight of infinity
       predecessor[v] := null         // And a null predecessor
   
   distance[source] := 0              // Except for the Source, where the Weight is zero 
   
   // Step 2: relax edges repeatedly
   for i from 1 to size(vertices)-1:
       for each edge (u, v) with weight w in edges:
           if distance[u] + w < distance[v]:
               distance[v] := distance[u] + w
               predecessor[v] := u

   // Step 3: check for negative-weight cycles
   for each edge (u, v) with weight w in edges:
       if distance[u] + w < distance[v]:
           error "Graph contains a negative-weight cycle"
   return distance[], predecessor[]

 */

// Informal proof - by induction   
//      distance matrix , 0 for source , infinity to others , Iteration 0 , holds true for source vertex
//      Assume holds true for k iterations => shortest path found to vertices 
//             with at most k-1 edges  
//      Say in iteration k+1 , path to some vertex v will include at most k edges
//      Earlier asumtion of being true for k ,
//		so till k th iteration correct  shortest path found , now next vertex in shortest path is selected to condition - for so far seen vertices correct shortest path and distance has been found
//	If next iteration unseen vertex is added , then again the distance / cost is check and if new shorthest path found that goes through this vertex , the same is upadted

// Application
// Negative edge weights are found in various applications of graphs, hence the usefulness of Bellman-Ford algorithm
// Bellman–Ford algorithm can also detect negative cycles and report their existence
//  Applications in routing
//  A distributed variant of the Bellman–Ford algorithm is used in distance-vector routing protocols, Routing Information Protocol (RIP)
	
import java.util.Scanner;

class BellmanFordMooreShimbelAlgorithm
 {
   public static void main( String args[] )
   {

   int MAX_VALUE = Integer.MAX_VALUE;
   
   // Lets start with Adjacency Matrix

   int vertices; // Number of vertices
   // We will saving adjacency matrix , and also weight / cost matrix
	   // Why two - Adjacency to inform presence of edge - 
	   // Weight matrix - weight / cost of the edge 

	   // it will be easier than maintaining single matrix - problem what value to indicate absence of edge
	   // As the graph - edge can also have negative weight / cost 

   // Read number of vertices
   System.out.print("\n Enter number of Vertices : ");


   Scanner conin = new Scanner(System.in);
   vertices = conin.nextInt();

   System.out.println("\n Number of Vertices : " + vertices );


   int distance[] = new int[vertices];
   int predecessor[] = new int[vertices];

   // Step 1 : 
   // Lets initialize graph distance and predecessor
   for( int v = 0 ; v < vertices ; v++ )
    {
      //System.out.println("\n Initialization Vertex number : " + v );
      distance[v] = MAX_VALUE;
      // I had mentioned - if 99 or 999 used to represent infinity and if we have a edge with cost 99 or 999
      // Very good doubt here -
      // Now what if , what if cost of edge is MAX_VALUE

      // Solution - like we maintain Adjacency Matrix and Cost Matrix
      // Use two array for diatance 
      //   1. maintains information ( 0 / 1 ) - to mention if not infinity and infinity
      //   2. Now save the distance here
      // Like usage of adjacency matrix first to find presence of edge , then find its weight
      // In Distance - first find if infinity or not , and then find distance  
      predecessor[v] = -1;    // instead of null
    }

   // Let source vertex be 0 , or you can read custom source
   int source = 0;

   distance[source] = 0 ;


   // Adjacency Matrix
   int adjacency[][] = new int[vertices][vertices];
   System.out.println("\n Please enter Adjacency Matrix :");
   System.out.print(" ( 1 if edge is present , 0 is no edge ) ");
   for( int i = 0; i < vertices; i++ )
    {
      for( int j = 0; j < vertices; j++ )
       {
          adjacency[i][j] = conin.nextInt();
       }
    }

   System.out.println("\n Adjacency Matrix : \n");
   for( int i = 0; i < vertices; i++ )
    {
      for( int j = 0; j < vertices; j++ )
       {
          System.out.print("\t" + adjacency[i][j]);
       }

      System.out.println("");
    }

   int weight[][] = new int[vertices][vertices];
   // Read edges / weights
   for( int i = 0; i < vertices; i++ )
    {
      for( int j = 0; j < vertices; j++ )
       {
         if( adjacency[i][j] == 1 ) // Only For all the edges present , read weight / cost
          {
            System.out.print("\n Weight of edge between vertex " + i + " and " + j + " : ");
            weight[i][j] = conin.nextInt();
          }
       }
    }

   System.out.println("\n Edges and Weights are : \n");
   for( int i = 0; i < vertices; i++ )
    {
      for( int j = 0; j < vertices; j++ )
       {
         if( adjacency[i][j] == 1 )
          {
            System.out.print("\n ( " + i + " , " + j + " ) = " + weight[i][j]);
          }
       }
    }

   // Step 2 :
   int w;
   for( int i = 0; i < vertices - 1 ; i++ )
    {
      for( int u = 0; u < vertices; u++ )
       {
         for( int v = 0; v < vertices; v++ )
          {
            if( adjacency[u][v] == 1 )
             {
               w = weight[u][v];
               if ( ( distance[u] + w ) < distance[v] )
                {
                  System.out.print("\n Samller distance found from u=" + u + " to v=" + v );
                  System.out.print("\n Earlier distance = " + distance[v] );
                  System.out.print("\n Smaller distance = " + ( distance[u] + w ) + "\n");

                  distance[v] = distance[u] + w;
                  predecessor[v] = u;
                }
             }
          }         
       }
    }

   // Now run time of above algorithm will be ?
   // Remember we are using Adjacency matrix and not Linked List to represent graph


   System.out.print("\n The predecessor of and distance to matrix \n");
   for( int v =0 ; v < vertices ; v++ )
    {
      System.out.print("\n predecessor[" + v + "] = " );

      if( predecessor[v] == -1 )
       {
         System.out.print("null");
       } 
      else
       {
         System.out.print(predecessor[v]);
       } 

      System.out.print("\t distance[" + v + "] = " + distance[v] );
    }

   // Step 3 :
   // Lets start with assumption graph does not have negative weight cycle
   boolean noNegativeWeightCycle = true;
   // If there is we will change the state

   for( int u = 0; u < vertices; u++ )
    {
      for( int v = 0; v < vertices; v++ )
       {
         if( adjacency[u][v] == 1 )
          {
            w = weight[u][v];

            System.out.print("\n Distance found from u=" + u + " to v=" + v );
            System.out.print("\n Distance ( distance[u] + w ) = " + ( distance[u] + w ) );
            System.out.print("\n Distance[v] = " + distance[v] + "\n	");

            if ( ( distance[u] + w ) < distance[v] )
             {
               System.out.print("\n Graph contains a negative-weight cycle");
               noNegativeWeightCycle = false;
             }
          }
       }         
    }

   if( noNegativeWeightCycle )
    {
      System.out.print("\n Graph does not contains a negative-weight cycle\n");
    } 
   else
    {
      System.out.print("\n Graph contains a negative-weight cycle\n");
    } 

   // Run time if Linked List is used ?
   // Can you implement Linked list based implementation
   // Not Adjacency Matrix but Adjacency Lists
   
   
    // can return noNegativeWeightCycle ; distance[] ; predecessor[];
    return;

    // And other ways to solve , yes indeed !
    // Data structures may differ , but in the end the same algorithm is being implemented.
    //  Basis for performance improvement

    // Can you also implement the same with Object Oriented principles ?
    // Above code though a Java code , it is not utilising the full power of Object Orientation

   }
 }
// References - Text books
// Introduction to the design & analysis of algorithms / Anany Levitin
// Introduction to Algorithms , Thomas H. Cormen , Charles E. Leiserson , Ronald L. Rivest
//                              Clifford Stein[For proofs]
// Data Structures and Algorithm Analysis in Java , Mark Allen Weiss
// Java : The Complete Reference, Herbert Schildt
