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
    public static int Search(int [][]Alpha, int []prev) {
        for (int x = 0; x <= Alpha.length-1; x++) {
            for (int y = 0; y<=Alpha[x].length-1;y++) {
                  if (Alpha[x][y] == prev[0] && Alpha[x][y+1] == prev[1]) return x; 
            }
          }
          return -1;
    }

    public static void Copy(int A[][], int b[][]) {
        for (int x = 0; x < A.length; x++) {
            for (int y = 0; y < 2; y++) {
                b[x][y] = A[x][y];
            }
        }
    }

    public static void DifferenceOfSets(int [][]Alpha, int [][]prev, int [][]next) {
        // A −B = {x |x ∈A ∧x 6∈B }
        System.out.println(Alpha.length);
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
    public static void NextPlayerMove(int []prev, int []next, boolean aTurn, int [][]Alpha, int [][]Beta, int [][]Free ,int [][]P, boolean ok, int [][]S, int [] cardinalities) {

        Scanner s = new Scanner(System.in);
        if (!aTurn) System.out.println("Alpha's Turn!");
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

        if (isAnElement(Alpha, prev) && aTurn && prev[0] == next[0]+1 && (next[1] == prev[1] && next[1] == prev[1] + 1 && prev[1] == next[1] + 1)) ok = !ok;
        else if (isAnElement(Beta, prev) && !aTurn && next[0] == prev[0]+1 && (next[1] == prev[1] && next[1] == prev[1] + 1 && prev[1] == next[1] + 1)) ok = !ok;
        else ok = true;

        if (ok && aTurn && isAnElement(Free, next)) {
            Replace(Alpha, prev, next);
            aTurn = false;
            ok = !ok;
        }
        if (ok && !aTurn && isAnElement(Free, next)) {
            Replace(Beta, prev, next);
            aTurn = true;
            ok = !ok;
        }

         if (ok && aTurn && isAnElement(Beta, next) && !isAnElement(S, prev)){
             ok = !ok;
             System.out.println("Shesh6");
        }else if (ok && aTurn && isAnElement(Beta, next) && isAnElement(S, prev)) {
            Delete(Beta, next);
            Replace(Alpha,prev,next);
            aTurn = !aTurn;
            ok = !ok;
            cardinalities[1]--;
            System.out.println("Shesh3");
        } 

        if (ok && !aTurn && !isAnElement(Alpha, prev)) {
            ok = !ok;
            System.out.println("Shesh5");
        }else if (ok && !aTurn && isAnElement(Alpha, prev)) {
            Delete(Alpha, next);
            Replace(Beta,prev,next);
            aTurn = !aTurn;
            ok = !ok;
            cardinalities[0]--;
            System.out.println("Shesh4");
        }
        

    }


    public static void PutPiece(int R, int C, int prev[]) {
        for (int row = 0; row < 5; row++) {
            for (int column = 0; column < 7 ; column++) {
                if (row == prev[0] && column == prev[1]) {
                    System.out.print("X\t");
                }else System.out.print("*\t");
            }
            System.out.println();
        }
        System.out.println(prev[0] + " " + prev[1]);
    }

    public static boolean Checker(int [][]P, int [][]Union, int check) {
        int checker = 0;
        boolean go = false;
        while (true && checker < 35) {
            try {
                if (P[checker][0] == Union[check][0]) {
                    if (P[checker][1] == Union[check][1]) {
                        go = true;
                        // System.out.println("P = {" + P[checker][0] + "," + P[checker][1] +"}" + "," + "U = {" + Union[check][0] + "," + Union[check][1] + "}");
                        checker = 0;
                        break;
                    }
                }
            }catch (Exception e) {
                if (P[checker][0] == Union[9][0]) {
                    if (P[checker][1] == Union[9][1]) {
                        go = true;
                        // System.out.println("P = {" + P[checker][0] + "," + P[checker][1] +"}" + "," + "U = {" + Union[9][0] + "," + Union[9][1] + "}");
                        checker = 0;
                        break;
                    }
                }
            }
            if (check >= 10) break;
            checker++;
        }
        return go;
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
        int alphaCounter = 0, betaCounter = 0; 
        

        SetUnion(B, A, Union);

        for (int row = 0,newLine = 0; row < 35; row++, newLine++) {
            // System.out.println("{"+P[row][0] + "," + P[row][1] + "}");
            if (newLine == 5) {
                System.out.println();
                newLine = 0;
            }
            if (CheckerV2(P, Union, check, row)) {
                if (betaCounter < cardinalities[1]) System.out.print("B\t");
                else System.out.print("A\t");
                check++;
                betaCounter++;
            }else System.out.print("*\t");
            if (check >= 10) check = 9;
            
        }
        System.out.println();
    

    }

    public static void GameOver(){
        
    }
    public static void main(String[] args) throws Exception {
        int []cardinalities = {5,5};
        int R = 7; // Dynamic array
        int C = 5; // Dynamic array
        int prev[] = new int[2];
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
        int [][] Free = {
            {1,2},{1,4},{2,1},{2,3},{2,5},{3,1},{3,2},{3,3},{3,4},{3,5},{4,1},{4,2},{4,3},{4,4},{4,5},{5,1},{5,2},{5,3},{5,4},{5,5},{6,1},{6,3},{6,5},{7,2},{7,4}
        };
        int alphaBetaU[][] = {
            {6,2},{6,4},{7,1},{7,3},{7,5},{1,1},{1,3},{1,5},{2,2},{2,4}
        };




        boolean over = false;
        boolean ok = false;
        boolean aTurn = true;
        int []move = new int[2];
        int []pieceToMove = new int[2];
        move[0] = 5;
        move[1] = 2; 

        pieceToMove[0] = 6;
        pieceToMove[1] = 2;
        
        Welcome();
        ViewBoard(Alpha, Beta, cardinalities, P);
        Replace(Alpha, pieceToMove, move); // Kaya iba output dahil dito dito oo tignan mo ung FindplayerNextMove sa taas dun gaganapin lahat
        System.out.println();
        ViewSet(Alpha);// bat wala inaask sa user pag nirun ko? di pa ba na iimplement yung user inp
    
        ViewBoard(Alpha, Beta, cardinalities, P);
        
        // Checker(Alpha,P);
        // while (true) {
        //     ViewBoard(Alpha, Beta, cardinalities);
        //     NextPlayerMove(move, pieceToMove, aTurn, Alpha, Beta, Free, P, ok, S, cardinalities);
        // }
        

        

        

    }
}
