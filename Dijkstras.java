package caveSearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class Dijkstras {
		
	public void calcShortestRoute(Vertex startVertex)
	{
		startVertex.setDistance(0); //initialise start vertex distance to 0
		PriorityQueue<Vertex> queue = new PriorityQueue<>();
		queue.add(startVertex);
		startVertex.setVisited(true);
		
		while(!queue.isEmpty())
		{
			Vertex currentVertex = queue.poll();
			
			for(Edge edge : currentVertex.getList())
			{
				Vertex v = edge.getdestVertex();
								
				if(!v.getVisited())
				{
					int tempDist = currentVertex.getDistance() + edge.getWeight();
										
					if(tempDist < v.getDistance())
					{
						queue.remove(v);
						v.setDistance(tempDist);
						v.setPrevious(currentVertex);
						queue.add(v);
						//System.out.println(currentVertex.getCavernNum());
					}
				}
			}
			currentVertex.setVisited(true);
		}
	}	
	public List<Vertex> getShortestRoute(Vertex destVertex)
	{
		List<Vertex> path = new ArrayList<>();
		
		for(Vertex vertex = destVertex; vertex!= null; vertex = vertex.getPrevious())
		{
			path.add(vertex);
		}
		
		Collections.reverse(path);
		//System.out.println(Math.round(path.get(path.size()-1).getDistance()));		
		return path;		
	}
}
