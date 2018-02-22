package hogrebetyg;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		System.out.println("Loading files...");
		String[] paths = new String[1];
		paths[0] = "/Users/marietopphem/Desktop/actors.list";
		//paths[1] = "/Users/marietopphem/Desktop/actresses.list";

		long time = System.currentTimeMillis();
		Graph g = new Graph(paths);
		System.out.println((System.currentTimeMillis() - time) / 1000 + " sekunder");
		//	System.out.println(listedConnections.size());

		SearchGraph sg= new SearchGraph(g);

		Scanner scanner = new Scanner(System.in);

		boolean con = true;
		
		while(con) {
			
			System.out.println("Write actor you want to find BaconValue on (Lastname, Firstname): ");
			String str = scanner.nextLine().trim();
			switch(str) {

			case "quit":

				con = false;
				break;

			default:

				System.out.println("Baconvalue is: " + sg.searchPathWithBFS(g.vertexToConnectionindex.get("Bacon, Kevin (I)"), g.vertexToConnectionindex.get(str)));
				break;

			}
		}
	}
}
