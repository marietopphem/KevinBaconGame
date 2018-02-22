package hogrebetyg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class SearchGraph {

	Graph graph ;
	
	public SearchGraph(Graph graph) {
		this.graph = graph;
	}
	
	public int searchPathWithBFS(int source, int destination) {

		int baconValue = 0;

		LinkedList<Integer> queue = new LinkedList<Integer>();

		ArrayList<Integer> sourceChild = new ArrayList<Integer>();
		
		HashMap<Integer, Integer> childAndParent = new HashMap<Integer, Integer>();
		
		queue.add(source);
		
		while(!queue.isEmpty()) {
			
			int index = queue.remove();
			if( index == destination) {
				
				ArrayList<String> path = new ArrayList<String>();

				for(int i = index; i != source; i = childAndParent.get(i)){
					
					path.add(graph.indexconnectionToVertex.get(i));
				}
				
				path.add(graph.indexconnectionToVertex.get(source));
				System.out.println(path.toString());
				baconValue = path.size()/2;

				queue.clear();
				return baconValue;
				
			}else {
				sourceChild = graph.listedConnections.get(index);
				
				for(int child : sourceChild){
					queue.add(child);
					childAndParent.put(child, index);
				}
				
			}
		}
		return 0;
		
	}
}
