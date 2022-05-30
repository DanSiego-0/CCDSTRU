import java.util.Scanner;

public class App {
    public static void Welcome() {
        System.out.println("\t\t  || Welcome ||");
    }

    public static void PrintBoard(int x[], int g[]) {
        for (int y = 0; y <= g.length-1;y++) {
            for (int k = 0; k <= x.length-1; k++) {
                System.out.print("*\t");
            }
            System.out.println();
        }
    }
    public static void SetUnion(int x[][], int y[][], int z[][]) {
        int lastIndexdim = 0;
        for (int i = 0; i <= x.length-1; i++,lastIndexdim++) {
            z[i] = x[i];
            for (int j = 0; j<=x[i].length-1; j++) {
                z[i][j] = x[i][j];
            }
        }
        for (int g = 0; g <= y.length-1; g++,lastIndexdim++) {
            z[lastIndexdim] = y[g];
            for (int h = 0; h <= y[g].length-1; h++) {
               z[lastIndexdim][h] = y[g][h];
            }
        }
   
    }

    public static Boolean isAnElement(int [][]Alpha, int [][]prev) {
        boolean isFound = false;
        for (int x = 0; x <= Alpha.length; x++) {
          if (Alpha[x] == prev[x]) {
              for (int y = 0; y<=Alpha[x].length;y++) {
                  if (Alpha[x][y] == prev[x][y]) isFound = true;
              }
          }
        }
        return isFound;
    }

    public static void DifferenceOfSets(int [][]Alpha, int [][]prev, int [][]next) {
        // A −B = {x |x ∈A ∧x 6∈B }
        
    }

    public static void NextPlayerMove(int [][]prev, int [][]next, boolean aTurn, int [][]Alpha, int [][]Beta, int [][]Free ,int [][]P, boolean ok) {
        int []pointAB = {prev[0][0],prev[0][1]};
        int []pointCD = {next[0][0],next[0][1]};

        if (aTurn && isAnElement(Alpha, prev) && pointAB[0] == (pointAB[1] + 1) && ((pointCD[0] == pointAB[1]) || (pointCD[1] == pointAB[1]+1) || (pointAB[1] == pointCD[1]+1))) 
            ok = !ok;
        else if (!aTurn && isAnElement(Beta, prev) && pointCD[0] == (pointAB[0]+1) && ((pointCD[0] == pointAB[1]) || (pointCD[1] == pointAB[1]+1) || (pointAB[1] == pointCD[1]+1))) 
            ok = !ok;
        
        if (ok && aTurn && isAnElement(Alpha, prev)) {

        }

    }



    public static void main(String[] args) throws Exception {
        int[] R = {1,2,3,4,5,6,7}; // Dynamic array
        int[] C = {1,2,3,4,5}; // Dynamic array
        Scanner sc = new Scanner(System.in);
        int[][]P = {
            {1,1},{1,2},{1,3},{1,4},{1,5},
            {2,1},{2,2},{2,3},{2,4},{2,5},
            {3,1},{3,2},{3,3},{3,4},{3,5},
            {4,1},{4,2},{4,3},{4,4},{4,5},
            {5,1},{5,2},{5,3},{5,4},{5,5},
            {6,1},{6,2},{6,3},{6,4},{6,5},
            {7,1},{7,2},{7,3},{7,4},{7,5}
        };
        int[][] S = {
            {1,1},{1,3},{1,5},{2,2},{2,4},{3,1},{3,3},{3,5},{4,2},{4,4},{5,1},{5,3},{5,5},{6,2},{6,4},{7,1},{7,3},{7,5}
        };

        int[][] Alpha = {
            {6,2},
            {6,4},
            {7,1},
            {7,3},
            {7,5}
        };
    
        int [][] Beta = {
            {1,1},
            {1,3},
            {1,5},
            {2,2},
            {2,4}
        };
        int [][] Free = P.clone();

        boolean over = false;
        boolean ok = false;
        boolean aTurn = true;

        // SetUnion(Alpha, Beta, UnionOfSetAandB);
        Welcome();
        PrintBoard(R,C);

    }
}
