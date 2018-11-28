package caveSearch;
import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

	public static void main(String[] args) 
	{
		String fileName = "";
		ArrayList<Integer> values = new ArrayList<Integer>();	
		
		
		// checks for input from command line
		if(args.length > 0)
		{
			fileName = args[0];
		}
		
		//takes in filename without the .cav file type
		if(!fileName.contains(".cav"))
		{
			fileName = fileName + ".cav";
		}
		
		values = readFromCav(fileName);
		
		int n = values.get(0); //assigns the nth cavern
		int distance = 0;
		Integer[] tempArray = values.toArray(new Integer[values.size()]);
		Integer[][] matrix = new Integer[n][n];
		Integer[][] coOrdMatrix = new Integer[n][2];
		Vertex v[] = new Vertex[n];
		
		//creates all the vertices
		for(int i = 0; i < n; i++)
		{
			v[i] = new Vertex(i+1);
			//System.out.println(v[i].toString());
		}

		matrix = createMatrix(tempArray, n);
		coOrdMatrix = getOrdinates(tempArray, n);
		//printMatrix(matrix);
		//printMatrix(coOrdMatrix);
		
		//checks connection matrix for a path and creates an edge between vertices
		for(int x = 0; x < matrix.length; x++)
		{
			for(int y = 0; y < matrix[x].length; y++)
			{
				if(matrix[x][y] == 1)
				{
					distance = calcDistance(coOrdMatrix[x][0], coOrdMatrix[x][1], coOrdMatrix[y][0], coOrdMatrix[y][1]);
					//System.out.println((x + 1) + " " + (y + 1));
					v[y].addNeighbour(new Edge(distance,v[y],v[x]));
					//System.out.print(v[x].g);
				}
			}
		}
		
		Dijkstras shortestRoute = new Dijkstras();
		shortestRoute.calcShortestRoute(v[0]); //starting vertex
		
		
		System.out.println("===============================");
		System.out.println("Shortest Route From 1 to " + n);
		System.out.println("===============================");
		String finalPath = shortestRoute.getShortestRoute(v[(n - 1)]).toString(); 
		
		fileName = fileName.replaceAll(".cav", ".csn");
		
		saveToFile(finalPath, fileName);	
	}
	
	//creates the connection matrix
	public static Integer[][] createMatrix(Integer[] values, int n)
	{
		Integer[][] matrix = new Integer[n][n];
		int i = (n*2) + 1;
		
		for(int x = 0; x < matrix.length; x++)
		{
			for(int y = 0; y < matrix[x].length; y++)
			{
				matrix[x][y] = values[i];
				i++;
			}
		}
		
		return matrix;
	}
	
	//calculates the weight of the edge between vertices
	public static int calcDistance(Integer x1, Integer y1, Integer x2, Integer y2)
	{
		double temp = 0;
		
		temp = Math.round(Math.sqrt(((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1))), 2);
		
		int dist = (int) temp;
		
		//System.out.println(x1 + "," + y1 + " - " + x2 + "," + y2 + "-----" + dist);
		
		return dist;
	}
	
	
	//creates a co-ordinates matrix which will be used to calculate the distance between the vertices
	public static Integer[][] getOrdinates(Integer[] values, int n)
	{
		Integer[][] coOrdMatrix = new Integer[n][2];
		int i = 1;
		
		for(int x = 0; x < coOrdMatrix.length; x++)
		{
			for(int y = 0; y < coOrdMatrix[x].length; y++)
			{
				if(i <= n*2)
				{
					coOrdMatrix[x][y] = values[i];
					i++;
				}
				else
				{
					break;
				}
			}
		}
		
		return coOrdMatrix;
	}
	
	//prints out the matrix, used for testing
	public static void printMatrix(Integer matrix[][])
	{		
		for (Integer[] x : matrix)
		{
        	System.out.println(Arrays.toString(x));
		}
	}
	
		//reads in file
	public static ArrayList<Integer> readFromCav(String fileName)
	{
		ArrayList<Integer> values = new ArrayList<Integer>();
		BufferedReader in = null;
		String line = "";
		String delim = ",";
		
		try
		{
			in = new BufferedReader(new FileReader(fileName));
			
			while((line = in.readLine()) != null)
			{
				String[] tempString = line.split(delim);
				
				for(int i = 0; i < tempString.length; i++)
				{
					int tempVal = Integer.parseInt(tempString[i]);
					values.add(tempVal);
				}
			}
		}		
		catch(FileNotFoundException e)
		{
			System.out.println("File Not Found");
		}
		
		catch(IOException e)
		{
			e.printStackTrace();
		}		
		
		return values;
	}
	
	//saves path to file
	public static void saveToFile(String finalPath, String fileName)
	{
		String[] tempArray = finalPath.split(",");
		String temp;
		
		if(tempArray.length <= 1)
		{
			temp = "0";
		}
		else
		{
			temp = finalPath.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\s", "").replaceAll(",", " ");
			System.out.println(temp);
		}
				
		try 
		{
			BufferedWriter write = new BufferedWriter(new FileWriter(fileName, false));
			write.write(temp);
			write.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
			
		
	}

}
