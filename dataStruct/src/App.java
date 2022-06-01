import java.util.Scanner;

import javax.swing.text.View;


public class App {
    public static void Welcome() {
        System.out.println("\t  || Welcome ||");
        System.out.println("-----------------------------------------");
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

    public static Boolean isAnElement(int [][]Alpha, int []prev) {
        boolean isFound = false;
        for (int x = 0; x <= Alpha.length-1; x++) {
                if (Alpha[x][0] == prev[0] && Alpha[x][1] == prev[1]) isFound = true; 
          }
          return isFound;
    }

    public static void Copy(int A[][], int b[][]) {
        for (int x = 0; x < A.length; x++) {
            for (int y = 0; y < 2; y++) {
                b[x][y] = A[x][y];
            }
        }
    }

    public static void Replace(int Alpha[][], int prev[], int next[]) {
        for (int x = 0; x <= Alpha.length-1; x++) {
            for (int y = 0; y<=Alpha[x].length-1;y++) {
                  if (Alpha[x][0] == prev[0] && Alpha[x][1] == prev[1]) {
                      Alpha[x][0] = next[0]; 
                      Alpha[x][1] = next[1];
                  }; 
            }
          }
    }

    public static void Delete(int Alpha[][], int prev[]) {
        int size = Alpha.length;
        int [][]newAlpha = new int[size-1][2];


        for (int x = 0,z = 0; x <= Alpha.length-1; x++) {
            for (int y = 0, c = 0; y<Alpha[x].length-1;y++) {
                  if (Alpha[x][y] == prev[0] && Alpha[x][y+1] == prev[1]) {
                  } else {
                    newAlpha[z][c] = Alpha[x][y]; 
                    newAlpha[z][c+1] = Alpha[x][y+1];
                    z++;
                    c++;
                  }
            }
        }
        Copy(newAlpha,Alpha);

    }

    public static void ViewPoints(int []prev) {
        System.out.print("(");
        for (int x = 0; x < prev.length; x++) {
            if (x == 1) System.out.print(prev[x]);
            else System.out.print(prev[x]+ ",");
        }
        System.out.println(")");
    } 

    public static void ViewSet(int [][]p) {
        for (int x = 0; x <= p.length-1; x++) {
            for (int y = 0; y <= 1; y++) {
                System.out.print(p[x][y]);
            }
            System.out.println("\n");
        }
    }
    public static void NextPlayerMove(int []prev, int []next, int [][]Alpha, int [][]Beta, int [][]Free ,int [][]P, int [][]S, int [] cardinalities) {
        boolean ok = false;
        boolean aTurn = true;
        Scanner s = new Scanner(System.in);
        if (aTurn == true) System.out.println("Alpha's Turn!");
        else System.out.println("Beta's Turn!");
        System.out.println("Enter coordinates!");
        System.out.println("Which Piece would you like to move?");
        System.out.println("Enter point A: ");
        prev[0] = s.nextInt();
        System.out.println("Enter point B: ");
        prev[1] = s.nextInt();

        System.out.println("Where would you like to move it?");
        System.out.println("Enter point C: ");
        next[0] = s.nextInt();
        System.out.println("Enter point D:");
        next[1] = s.nextInt();



        System.out.print("Points to be moved: ");
        ViewPoints(prev);
        System.out.print("To be moved at: ");
        ViewPoints(next);
        // Prev[0] = 6 prev[1] = 2 next[0] = 5 next[1] = 2
        //2 == 2 && 5 == 7 && 2 == 3
        if (isAnElement(Alpha, prev) && aTurn && prev[0] == next[0]+1 && (next[1] == prev[1] && next[1] == prev[1] + 1 && prev[1] == next[1] + 1)) ok = !ok;
        else if (isAnElement(Beta, prev) && !aTurn && next[0] == prev[0]+1 && (next[1] == prev[1] && next[1] == prev[1] + 1 && prev[1] == next[1] + 1)) ok = !ok;
    
        if (aTurn && isAnElement(Free, next)) {
            Replace(Alpha, prev, next);
            aTurn = !aTurn;
            System.out.println(aTurn);
        }
    

    }


    public static boolean CheckerV2(int [][]P, int [][]Union, int check, int row) {
        boolean isFound = false;
        if (P[row][0] == Union[check][0]) {
            if (P[row][1] == Union[check][1]){
                isFound = true;
            }
        }
        return isFound;
    }


    public static void ViewBoard(int A[][], int B[][], int [] cardinalities, int P[][]) {
        int [][]Union = new int[cardinalities[0] + cardinalities[1]][2];
        int check = 0;
        int betaCounter = 0; 
        

        SetUnion(B, A, Union);

        for (int row = 0,newLine = 0; row < 35; row++, newLine++) {
            // System.out.println("{"+P[row][0] + "," + P[row][1] + "}");
            if (newLine == 5) {
                System.out.println();
                newLine = 0;
            }
            // if (CheckerV2(P, Union, check, row)) {
            //     if (betaCounter < cardinalities[1]) System.out.print("B\t");
            //     else System.out.print("A\t");
            //     check++;
            //     betaCounter++;
            // }else System.out.print("*\t");
            // if (check >= 10) check = 9;
            if (CheckerV2(P, Union, 2, row)) {
                System.out.print("B\t");
            }else System.out.print("*\t");
            
        }
        System.out.println();
    

    }

    public static void GameOver(){
        
    }

    public static void main(String[] args) throws Exception {
        int []cardinalities = {5,5};
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
            {2,1},
            {1,3},
            {1,5},
            {2,2},
            {2,4}
        };

        int [][] Free = {
            {1,2},{1,4},{2,1},{2,3},{2,5},{3,1},{3,2},{3,3},{3,4},{3,5},{4,1},{4,2},{4,3},{4,4},{4,5},{5,1},{5,2},{5,3},{5,4},{5,5},{6,1},{6,3},{6,5},{7,2},{7,4}
        };




        boolean over = false;
        boolean ok = false;
        boolean aTurn = true;
        int []move = new int[2];
        int []pieceToMove = new int[2];
        
        
        Welcome();
        ViewBoard(Alpha, Beta, cardinalities, P);
        // while(true) {
        //     System.out.println("Cardinalities: " + cardinalities[0] + "-" + cardinalities[1]);
        //     ViewBoard(Alpha, Beta, cardinalities, P);
        //     Scanner s = new Scanner(System.in);
        //     if (aTurn == true) System.out.println("Alpha's Turn!");
        //     else System.out.println("Beta's Turn!");
        //     System.out.println("Enter coordinates!");
        //     System.out.println("Which Piece would you like to move?");
        //     System.out.println("Enter point A: ");
        //     pieceToMove[0] = s.nextInt();
        //     System.out.println("Enter point B: ");
        //     pieceToMove[1] = s.nextInt();
    
        //     System.out.println("Where would you like to move it?");
        //     System.out.println("Enter point C: ");
        //     move[0] = s.nextInt();
        //     System.out.println("Enter point D:");
        //     move[1] = s.nextInt();
    
    
        //     System.out.print("Points to be moved: ");
        //     ViewPoints(pieceToMove);
        //     System.out.print("To be moved at: ");
        //     ViewPoints(move);

        //     if (isAnElement(Alpha, pieceToMove) && aTurn && pieceToMove[0] == move[0]+1 && (move[1] == pieceToMove[1] && move[1] == pieceToMove[1] + 1 && pieceToMove[1] == move[1] + 1)) ok = !ok;
        //     else if (isAnElement(Beta, pieceToMove) && !aTurn && move[0] == pieceToMove[0]+1 && (move[1] == pieceToMove[1] && move[1] == pieceToMove[1] + 1 && pieceToMove[1] == move[1] + 1)) ok = !ok;
        
        //     if (!ok && aTurn && isAnElement(Free, move)) {
        //         Replace(Alpha, pieceToMove, move);
        //         aTurn = !aTurn;
        //         System.out.println(aTurn);
        //         ok = !ok;
        //     }else if (ok && !aTurn && isAnElement(Free, move)) {
        //         Replace(Beta, pieceToMove, move);
        //         aTurn = !aTurn;
        //         System.out.println("HELLLO PUTANGINAMO");
        //         ok = !ok;
        //     }
        // }
        
    }
}
