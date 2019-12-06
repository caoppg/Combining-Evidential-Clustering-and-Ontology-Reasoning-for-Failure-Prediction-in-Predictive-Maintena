To implement the code:
1. add the folder "ECM results 5.1" into matlab path.
2. import the data in file "time+support" into matlab. The data are going to be clustered into 3 clusters. 
3. run the function "ECSQARU.m".


Resluts explaination:
- The credal partition was implemented with default values of parameters in ECM (e.g., alpha, beta, delta, init).
- The file "ECMInput" containts the input data to the evidence clustering approach (time to failures + estimated maintenance cost + chronicle support). 
- The folder "ECMOutput" containts the output of evidence clustering. 
  There are two files: "ECMResults" containts the pignistic probabilities of the three clusters (sum to 1).
                       "FinalCriticality" stands for the final decision, within which the first column is the max pignistic probability, and the second column is the cluster that the max pignistic probability belongs to.



