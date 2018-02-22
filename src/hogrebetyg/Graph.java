package hogrebetyg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;


public class Graph {

	ArrayList<ArrayList<Integer>> listedConnections;
	HashMap<String, Integer> vertexToConnectionindex;
	HashMap<Integer, String> indexconnectionToVertex;

	int LCIndex;
	int titlecounter;

	public Graph(String[] filePath) {

		listedConnections = new ArrayList<ArrayList<Integer>>();
		vertexToConnectionindex = new HashMap<String, Integer>();
		indexconnectionToVertex = new HashMap<Integer, String>();

		LCIndex = 0;
		titlecounter = 0;

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
			int actorcounter = 0;


			while((part = br.getNextPart()) != null) {


				switch (part.type) {

				case NAME:


					if (/*LCIndex > 0 &&*/ !builder.toString().isEmpty()) {
						addToGraph(currentActorIndex, builder.toString());
						builder = new StringBuilder();
					}

					String name = part.text;
					ArrayList<Integer> titleConnections = new ArrayList<Integer>();
					listedConnections.add(titleConnections);
					vertexToConnectionindex.put(name, LCIndex);
					indexconnectionToVertex.put(LCIndex, name);
					currentActorIndex = LCIndex++;
					actorcounter++;


					break;

				case TITLE:

					if (!builder.toString().isEmpty()) {
						addToGraph(currentActorIndex, builder.toString());
						builder = new StringBuilder(part.text);
					}else {
						builder.append(part.text);
						//builder.append();

					}
					break;


				case YEAR:
					builder.append(" : ");
					builder.append( part.text);

					break;

				case ID:

//					builder.append(" : ");
//					builder.append( part.text);

					builder = new StringBuilder();
					

					break;




				case INFO:
					break;
				}


			}
			addToGraph(currentActorIndex, builder.toString());

			System.out.println(actorcounter + " actors");
			System.out.println(titlecounter + " Movies");
			System.out.println(indexconnectionToVertex.get(listedConnections.size()-1));
			System.out.println(indexconnectionToVertex.get(listedConnections.size()-2));
			System.out.println(indexconnectionToVertex.get(listedConnections.size()-3));
			System.out.println(indexconnectionToVertex.get(listedConnections.size()-4));
			System.out.println(indexconnectionToVertex.get(0));
			System.out.println(indexconnectionToVertex.get(1));
			System.out.println(indexconnectionToVertex.get(2));
			System.out.println(indexconnectionToVertex.get(3));

			System.out.println(indexconnectionToVertex.get((indexconnectionToVertex.size())-1));


		


			

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
			titlecounter++;
		}

	}


}