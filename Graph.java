// Name:
// Student number:
import java.util.ArrayList;
import java.util.List;
 
public class Graph {
	
	private List<Vertex> verticesList;

	public Graph() {
		this.verticesList = new ArrayList<>();
	}

	public void addVertex(Vertex vertex) {
		this.verticesList.add(vertex);
	}

	public void reset() {
		for(Vertex vertex : verticesList) {
			vertex.setVisited(false);
			vertex.setPredecessor(null);
			vertex.setDistance(Double.MAX_VALUE);
		}
	}

	////// Implement the methods below this line //////

	public Vertex getVertexWithMinimumDistance(List<Vertex> lVertexs){
		Vertex Minimum = null;
		for(int i = 0; i < lVertexs.size(); i++){
			if(lVertexs.get(i).isVisited() == false)
				Minimum = lVertexs.get(i);
		}

		for(int i = 0; i < lVertexs.size(); i++){
			if (Minimum.getDistance() > lVertexs.get(i).getDistance())
				if(lVertexs.get(i).isVisited() == false)
					Minimum = lVertexs.get(i);
		}
		return Minimum;
	}

	public Boolean isAllChecked(List<Vertex> lVertexs){
		Boolean Output = true;
		for(int i = 0; i < lVertexs.size(); i++){
			if(! lVertexs.get(i).isVisited()){
				Output = false;
				break;
			}
		}
		return Output;
	}

	public List<Vertex> getShortestPath(Vertex sourceVertex, Vertex targetVertex) {

		// Your code here
		List<Vertex> Output = new ArrayList<>();
		List<Vertex> OutputReversed = new ArrayList<>();

		sourceVertex.setDistance(0);

		while(!isAllChecked(verticesList)){
			Vertex v = getVertexWithMinimumDistance(verticesList);
			v.setVisited(true);
			for(int i = 0; i < v.getAdjacenciesList().size(); i++){
				Vertex u = v.getAdjacenciesList().get(i).getEndVertex();
				if(u.isVisited()) continue;
				
				if(u.getDistance() > v.getDistance() + v.getAdjacenciesList().get(i).getWeight()){
					u.setDistance(v.getDistance() + v.getAdjacenciesList().get(i).getWeight());
					u.setPredecessor(v);
				}

			}

		}

		Vertex Traverse = targetVertex;
		while(Traverse != sourceVertex && Traverse.getPredecessor() != null){
			Output.add(Traverse);
			Traverse = Traverse.getPredecessor();
		}
		if (! Output.isEmpty())
			Output.add(Traverse);
		
		for (int i = Output.size()-1; i >= 0; i--){
			OutputReversed.add(Output.get(i));
		}

		if(sourceVertex == targetVertex)
			OutputReversed.add(sourceVertex);


		return OutputReversed;

}




		


	public double getShortestPathDistance(Vertex sourceVertex, Vertex targetVertex) {
		double Output = Double.MAX_VALUE;
		List<Vertex> path = getShortestPath(sourceVertex, targetVertex);

		if(path.size() > 0)
			Output = path.get(path.size()-1).getDistance();

		if(sourceVertex == targetVertex)
			Output = 0;

		return Output;
	}

}