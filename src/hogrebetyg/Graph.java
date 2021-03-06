package hogrebetyg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class Graph {

	ArrayList<ArrayList<Integer>> listedConnections;
	HashMap<String, Integer> vertexToConnectionindex;
	HashMap<Integer, String> indexconnectionToVertex;

	int LCIndex;

	public Graph(String[] filePath) {

		listedConnections = new ArrayList<ArrayList<Integer>>();
		vertexToConnectionindex = new HashMap<String, Integer>();
		indexconnectionToVertex = new HashMap<Integer, String>();

		LCIndex = 0;
		

		for (String string : filePath) {
		
			fillGraph(string);
		}
	}

	private void fillGraph(String filepath) {

		try {
			// för att läsa in data
			BaconReader br = new BaconReader(filepath);
			BaconReader.Part part;

			// för att addera strings i java
			StringBuilder builder = new StringBuilder();

			int currentActorIndex = 0;

			while((part = br.getNextPart()) != null) {

				switch (part.type) {

				case NAME:

					if (!builder.toString().isEmpty()) {
						addToGraph(currentActorIndex, builder.toString());
						builder = new StringBuilder();
					}

					String name = part.text;
					ArrayList<Integer> titleConnections = new ArrayList<Integer>();
					listedConnections.add(titleConnections);
					vertexToConnectionindex.put(name, LCIndex);
					indexconnectionToVertex.put(LCIndex, name);
					currentActorIndex = LCIndex++;

					break;

				case TITLE:

					if (!builder.toString().isEmpty()) {
						addToGraph(currentActorIndex, builder.toString());
						builder = new StringBuilder(part.text);
					}else {
						builder.append(part.text);

					}
					break;

				case YEAR:
					builder.append(" : ");
					builder.append( part.text);

					break;

				case ID:
					builder = new StringBuilder();
					
					break;

				case INFO:
					break;
				}


			}
			addToGraph(currentActorIndex, builder.toString());


		} catch (IOException e) {
			System.out.println("Something wrong with filepath: " + e);

		}
	}

	private void addToGraph(int currentActorIndex, String title) {

		//if the movie already exists
		if(vertexToConnectionindex.containsKey(title)) { 
			//System.out.println("oh, already put in");
			int index = vertexToConnectionindex.get(title);
			listedConnections.get(currentActorIndex).add(index);
			listedConnections.get(index).add(currentActorIndex);

		}else {//if it doesn't exist

			ArrayList<Integer> actorConnections = new ArrayList<Integer>();
			actorConnections.add(currentActorIndex);
			listedConnections.add(actorConnections);
			listedConnections.get(currentActorIndex).add(LCIndex);
			vertexToConnectionindex.put(title, LCIndex);
			indexconnectionToVertex.put(LCIndex, title);
			LCIndex++;
		}
	}

}