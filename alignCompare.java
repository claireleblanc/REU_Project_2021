package genes;
/**
 * Given four groups of genes, this program scans the mafft protein alignment and identifies
 * locations where the amino acid is the same within one or two groups, but not present
 * in the other two groups.
 * 
 * @author Claire LeBlanc
 */
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class alignCompare {


		private File file; 
		private Scanner in; 

		/*
		 * Starting at zero, the genes are numbered based on their place in the alignment.
		 * This takes the start coordinate of the second group, the third group, and the 
		 * fourth group. 
		 * 
		 * Note: Originally used to look at grasses, other commelinids, non-commelinid monocots, and dicots, 
		 * hence, the names of the 4 groups in the code
		 */
		public alignCompare(File file, int commStart, int monoStart, int diStart) {
			this.file = file; 

			try {
				in = new Scanner(file);

				ArrayList<ArrayList<Character>> aa = new ArrayList<ArrayList<Character>>(); 

				int t = 0; 
				String line = in.nextLine(); 
				line = in.nextLine(); 
				
				//only happens for the first sequence, creates a array list of array lists
				// where each arraylist represents a coordinate in the alignment
				while (in.hasNext() && !line.startsWith(">")) {				
					for (int i = 0; i < line.length(); i++) {
						ArrayList<Character> col = new ArrayList<Character>(); 
						col.add(line.charAt(i)); 
						aa.add(col); 
					}
					line = in.nextLine(); 
				}
				
				// happens for all other sequences, for each sequence, adds each residue to
				// the associated array list for that coordinate
				while (in.hasNext()) {
					if (line.startsWith(">")) {
						t = 0; 
					}
					else {
						for (int i = 0; i < line.length(); i++) {
							aa.get(t).add(line.charAt(i)); 
							t++; 
						}
					}
					line = in.nextLine(); 
				} 

				// gets the amino acids for each group at a specific coordinate, then compares all 
				// the amino acids at that coordinate, if there are some interesting similarities/differences,
				// prints out the coordinate and amino acids
				// then repeats for the next coordinate
				for (int j = 0; j < aa.size(); j++) {
					ArrayList<Character> compare = aa.get(j); // gets the amino acids at a single coordinate
					
					ArrayList<Character> grass = new ArrayList<Character>(); 
					ArrayList<Character> comm = new ArrayList<Character>(); 
					ArrayList<Character> mono = new ArrayList<Character>(); 
					ArrayList<Character> dicot = new ArrayList<Character>();

					for (int i = 0; i < commStart; i++) {
						grass.add(compare.get(i)); 
					}
					for (int i = commStart; i < monoStart; i++) {
						comm.add(compare.get(i)); 
					}
					for (int i = monoStart; i < diStart; i++) {
						mono.add(compare.get(i)); 
					}
					for (int i = diStart; i < compare.size(); i++) {
						dicot.add(compare.get(i)); 
					}

					ArrayList<Character> commGrass = (new ArrayList<Character>(grass)); 
					commGrass.addAll(comm); 

					ArrayList<Character> monoDi = (new ArrayList<Character>(mono)); 
					monoDi.addAll(dicot); 

					// Groups 1 and 2 the same as each other and different from Groups 3 and 4
					if (allEqual(commGrass)) {
						if (! dicot.contains(commGrass.get(0)) && !mono.contains(commGrass.get(0))) {
							System.out.println("Groups 1 and 2 are both " + commGrass.get(0) + " at " + j);
							System.out.println("g: " + grass);
							System.out.println("c: " + comm);
							System.out.println("m: " + mono);
							System.out.println("d: " + dicot);
						}
					}
					// Groups 3 and 4 the same as each other and different from Groups 1 and 2
					else if (allEqual(monoDi)) {
						if (! comm.contains(monoDi.get(0)) && !grass.contains(monoDi.get(0))) {
							System.out.println("Other monocots & dicots are both " + monoDi.get(0) + " at " + j);
							System.out.println("1: " + grass);
							System.out.println("2: " + comm);
							System.out.println("3: " + mono);
							System.out.println("4: " + dicot);
						}
					}
					// Everything in group 1 is the same and different from the other groups
					else if (allEqual(grass)) {
						if (!comm.contains(grass.get(0)) && !dicot.contains(grass.get(0)) && !mono.contains(grass.get(0))) {
							System.out.println("Group 1 is the same at " + j);
							System.out.println("1: " + grass);
							System.out.println("2: " + comm);
							System.out.println("3: " + mono);
							System.out.println("4: " + dicot);
						}
					}
					
					// Everything in group 2 is the same and different from the other groups
					else if (allEqual(comm)) {
						if (!grass.contains(comm.get(0)) && !dicot.contains(comm.get(0)) && !mono.contains(comm.get(0))) {
							System.out.println("Group 2 is the same at " + j);
							System.out.println("1: " + grass);
							System.out.println("2: " + comm);
							System.out.println("3: " + mono);
							System.out.println("4: " + dicot);
						}
					}
					
					// Everything in group 3 is the same and different from the other groups
					else if (allEqual(mono)) {
						if (!grass.contains(mono.get(0)) && !dicot.contains(mono.get(0)) && !comm.contains(mono.get(0))) {
							System.out.println("Group 3 is the same at " + j);
							System.out.println("1: " + grass);
							System.out.println("2: " + comm);
							System.out.println("3: " + mono);
							System.out.println("4: " + dicot);
						}
					}

					// Everything in group 4 is the same and different from the other groups
					else if (allEqual(dicot)) {
						if (!comm.contains(dicot.get(0)) && !grass.contains(dicot.get(0)) && !mono.contains(dicot.get(0))) {
							System.out.println("Group 4 is the same at " + j);
							System.out.println("Other monocots & dicots are both " + monoDi.get(0) + " at " + j);
							System.out.println("1: " + grass);
							System.out.println("2: " + comm);
							System.out.println("3: " + mono);
							System.out.println("4: " + dicot);
							System.out.println("--------------------------");
						}
					} 
				}
			}
			catch (IOException e) {
				System.out.println("File not found");	    
			}		
		}

		private boolean allEqual (ArrayList<Character> list) {
			for (char s : list) {
				if (s != list.get(0) && s != '-')
					return false;
			}
			return true;
		}

		public static void main(String args[]) { 
			
			// args = [file, coordinate1, coordinate2, coordinate3]
			
			File testFile = new File(args[0]); 
			int secondStart = Integer.parseInt(args[1]); 
			int thirdStart = Integer.parseInt(args[2]);
			int fourthStart = Integer.parseInt(args[3]); 
			alignCompare test = new alignCompare(testFile, secondStart, thirdStart, fourthStart);
			 

	}

}
