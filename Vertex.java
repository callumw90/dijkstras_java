package caveSearch;

import java.util.ArrayList;
import java.util.List;

public class Vertex implements Comparable<Vertex> {
	
	
	
	private int cavernNum;
	private List<Edge> neighbours;
	private boolean visited;
	private Vertex previous;
	private int distance = Integer.MAX_VALUE;
	
	
	public Vertex(int cavernNum)
	{
		this.cavernNum = cavernNum;
		this.neighbours = new ArrayList<>();
		//this.previous = null;
		//System.out.println(this.cavernNum);
	}
	
	public int getCavernNum() 
	{
		return cavernNum;
	}

	public void setCavernNum(int cavernNum) 
	{
		this.cavernNum = cavernNum;
	}
	
	public void addNeighbour(Edge edge)
	{
		this.neighbours.add(edge);
	}
	
	public List<Edge> getList()
	{
		return neighbours;
	}
	
	public void setList(List<Edge> list)
	{
		this.neighbours = list;
	}
	
	public void setVisited(boolean visited)
	{
		this.visited = visited;
	}
	
	public boolean getVisited()
	{
		return visited;
	}
	
	public int getDistance()
	{
		return distance;
	}
	
	public void setDistance(int distance)
	{
		this.distance = distance;
	}
	

	public Vertex getPrevious() 
	{
		return previous;
	}

	public void setPrevious(Vertex previous) 
	{
		this.previous = previous;
	}

	public String toString()
	{
		String cavern;
		
		cavern = String.valueOf(this.cavernNum);
		
		return cavern;	
	}
	
	//compares the weight of the vertices
	@Override
	public int compareTo(Vertex other)
	{
		return Integer.compare(this.distance, other.getDistance());
	}
}
