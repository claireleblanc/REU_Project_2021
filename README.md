# REU_Project_2021
Danforth Center Summer 2021 research internship looking at GAUT and GATL genes and proteins

## alignCompare: 
### Purpose: 
Given four groups a genes and a mafft alignment, this program will identify locations where: 
 - Amino acids in groups 1 and 2 are the same, and the conserved amino acid is not present in groups 3 or 4 
 - Amino acids in groups 3 and 4 are the same, and the conserved amino acid is not present in groups 1 or 2 
 - Amino acids in group 1 are the same, and the conserved amino acid is not present in groups 2, 3, or 4 
 - Amino acids in group 2 are the same, and the conserved amino acid is not present in groups 1, 3, or 4 
 - Amino acids in group 3 are the same, and the conserved amino acid is not present in groups 1, 2, or 4 
 - Amino acids in group 4 are the same, and the conserved amino acid is not present in groups 1, 2, or 3 
  
### To use: 
  - On the command line, navigate to the directory containing this file. Also, make sure the alignment is saved in the same directory as the program
  - Run "javac alignCompare.java" on the command line
  - Run "java alignCompare.java NAME_OF_ALIGNMENT_FILE GROUP_2_START_COORD GROUP_3_START_COORD GROUP_4_START_COORD 
  (note: The coordinates are determined by the order of genes in the alignment, with the first gene being at coordinate 0, the second at coordinate 1, etc.)  
  
