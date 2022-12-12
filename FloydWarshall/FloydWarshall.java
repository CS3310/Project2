import java.util.*;
import java.io.*;

class FloydWarshalls
{
    private int numOfVertices;
    private int graph[][];
   
    public FloydWarshalls(int numOfVertices)
    {
        graph = new int[numOfVertices + 1][numOfVertices + 1];
        this.numOfVertices = numOfVertices;
    }
 
    public void floydAlgo(int adjMatrix[][])
    {
        for(int i = 1; i <= numOfVertices; i++)
        {
            for(int j = 1; j <= numOfVertices; j++)
            {
                graph[i][j] = adjMatrix[i][j];
            }
        }
 
        for(int x = 1; x <= numOfVertices; x++)
        {
            for(int i = 1; i <= numOfVertices; i++)
            {
                for(int j = 1; j <= numOfVertices; j++)
                {
                    if(graph[i][x] + graph[x][j] < graph[i][j])
                    {
                        graph[i][j] = graph[i][x] + graph[x][j];
                    }
                }
            }
        }
    }

    //Display Graph
    public void printGraph(int adjMatrix[][])
    {
        for(int i = 1; i <= numOfVertices; i++)
        {
            System.out.print("\t" + i);
        }
        System.out.println("");
        for(int i = 1; i <= numOfVertices; i++)
        {
            System.out.print(i + "\t");
            for (int j = 1; j <= numOfVertices; j++)
            {
                System.out.print(graph[i][j] + "\t");
            }
            System.out.println("");
        }
    }
}
class ch{
	public static final int INFINITY = 999;

    public static void main(String... arg)
    {
        int runs = 2048;
        int currentRuns = 1;
        int numOfVertices = 0;
        Random rand = new Random();
        int adjMatrix[][];
        long startTime;
        long endTime;
        long totalTime = 0;

        for(int x = 0; x < runs; x++)
        {
            numOfVertices += 10;
            adjMatrix = new int[numOfVertices + 1][numOfVertices + 1];
			for(int y = 0; y < 1; y++)
            {
                for(int i = 1; i <= numOfVertices; i++)
                {
                    for(int j = 1; j <= numOfVertices; j++)
                    {
                        adjMatrix[i][j] = rand.nextInt(10);
                        if(i == j)
                        {
                            adjMatrix[i][j] = 0;
                            continue;
                        }
                        if(adjMatrix[i][j] == 0)
                        {
                            adjMatrix[i][j] = INFINITY;
                        }
                    }
                }
                //Show the Weighted Graph
                /*
                System.out.println("Weighted Graph:");
                for(int i = 1; i <= numOfVertices; i++)
                {
                    for(int j = 1; j <= numOfVertices; j++)
                    {
                        System.out.print(adjMatrix[i][j]);
                        System.out.print(" ");
                    }
                    System.out.println("");
                } */
                startTime = System.nanoTime();
                FloydWarshalls run = new FloydWarshalls(numOfVertices);
                run.floydAlgo(adjMatrix);
                //run.printGraph(adjMatrix);
                endTime = System.nanoTime();
                totalTime += endTime - startTime;
			}
            
            totalTime = totalTime / currentRuns;
            currentRuns++;

            System.out.println("Output:");
            System.out.println("n = " + (numOfVertices));
			System.out.println("Floyd-Warshall: " + totalTime + " nanoseconds");
		}
    }
}