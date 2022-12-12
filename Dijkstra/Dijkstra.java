import java.util.*;
import java.io.*;

public class Dijkstra
{
    public static final int INFINITY = 999;
    public static void dijkstra(int[][] graph, int source)
    {
        int count = graph.length;
        boolean[] visitedVertex = new boolean[count];
        int[] distance = new int[count];

        for(int i = 0; i < count; i++)
        {
            visitedVertex[i] = false;
            distance[i] = Integer.MAX_VALUE;
        }

        distance[source] = 0;
        for(int i = 0; i < count; i++)
        {
            int u = findMinDistance(distance, visitedVertex);
            visitedVertex[u] = true;

            for(int v = 0; v < count; v++)
            {
                if(!visitedVertex[v] && graph[u][v] != 0 && (distance[u] + graph[u][v] < distance[v]))
                {
                    distance[v] = distance[u] + graph[u][v];
                }
            }
        }
        //Sanity Check
        /*
        for(int i = 0; i < distance.length; i++)
        {
            System.out.println(String.format("Distance from %s to %s is %s", source, i, distance[i]));
        }
        */
    }

    private static int findMinDistance(int[] distance, boolean[] visitedVertex)
    {
        int minDistance = Integer.MAX_VALUE;
        int minDistanceVertex = -1;

        for(int i = 0; i < distance.length; i++)
        {
            if(!visitedVertex[i] && distance[i] < minDistance)
            {
                minDistance = distance[i];
                minDistanceVertex = i;
            }
        }
        return minDistanceVertex;
    }

    public static void main(String[] args)
    {
        int runs = 2048;
        int currentRuns = 1;
        Random rand = new Random();
        int graph[][];
        int numOfVertices = 0;
        long startTime;
        long endTime;
        long totalTime = 0;

        for(int x = 0; x < runs; x++)
        {
            numOfVertices += 10;
            graph = new int[numOfVertices][numOfVertices];
			for(int y = 0; y < 1; y++)
            {
                for(int i = 0; i < numOfVertices; i++)
                {
                    for(int j = 0; j < numOfVertices; j++)
                    {
                        graph[i][j] = rand.nextInt(10);
                        if(i == j)
                        {
                            graph[i][j] = 0;
                            continue;
                        }
                        if(graph[i][j] == 0)
                        {
                            graph[i][j] = INFINITY;
                        }
                    }
                }
                startTime = System.nanoTime();
                Dijkstra T = new Dijkstra();
                T.dijkstra(graph, 0);
                endTime = System.nanoTime();
                totalTime += endTime - startTime;
			}
            
            totalTime = totalTime / currentRuns;
            currentRuns++;

            System.out.println("Output:");
            System.out.println("n = " + (numOfVertices));
			System.out.println("Dijkstra: " + totalTime + " nanoseconds");
		}
    }
}
