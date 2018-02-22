package hogrebetyg;

public class Main {

	public static void main(String[] args) {
		System.out.println("Laddar in listan...");
		String[] paths = new String[2];
		paths[0] = "/Users/marietopphem/Desktop/actors.list";
		paths[1] = "/Users/marietopphem/Desktop/actresses.list";
		
		long time = System.currentTimeMillis();
		Graph g = new Graph(paths);
		System.out.println((System.currentTimeMillis() - time) / 1000 + " sekunder");
	//	System.out.println(listedConnections.size());
		
		SearchGraph sg= new SearchGraph(g);
		
		//System.out.println(sg.searchPathWithBFS(g.vertexToConnectionindex.get("Bacon, Kevin (I)"), g.vertexToConnectionindex.get("Belushi, John")));
	}
}
