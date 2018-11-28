package caveSearch;

public class Edge {
	
	//creates an edge between two vertices and assigns a weight
	
	private int weight;
	private Vertex startVertex;
	private Vertex destVertex;
	
	public Edge(int weight, Vertex startVertex, Vertex destVertex)
	{
		this.weight = weight;
		this.startVertex = startVertex;
		this.destVertex = destVertex;
		//System.out.println("Start Cavern = " + startVertex.getCavernNum() + " Dest Cavern = " + destVertex.getCavernNum() + " Weight = " + this.weight);
	}
	
	
	//getters and setters
	public void setWeight(int weight)
	{
		this.weight = weight;
	}
	
	public void setStartVertex(Vertex startVertex)
	{
		this.startVertex = startVertex;
	}
	
	public int getWeight()
	{
		return weight;
	}
	
	public Vertex getStartVertex()
	{
		return startVertex;
	}

	public Vertex getdestVertex()
	{
		return destVertex;
	}

	public void setdestVertex(Vertex destVertex) 
	{
		this.destVertex = destVertex;
	}

}
