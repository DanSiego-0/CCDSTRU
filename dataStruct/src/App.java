public class App {

    public static void PrintBoard(int x[][]) {
        for (int y = 0; y <= x.length-1;y++) {
            System.out.print("(");
            for (int z = 0; z <= x[y].length-1;z++) {
                System.out.print(x[y][z]);
                if (z != 1) System.out.print(",");
            }
            System.out.print(") "); 
        }
    }

    public static 


    public static void main(String[] args) throws Exception {
        int[] R = {1,2,3,4,5,6,7}; // Dynamic array
        int[] C = {1,2,3,4,5}; // Dynamic array
        int[][] S = {
            {1,1},
            {1,3},
            {1,5},
            {2,2},
            {2,4},
            {3,1},
            {3,3},
            {3,5},
            {4,2},
            {4,4},
            {5,1},
            {5,3},
            {5,5},
            {6,2},
            {6,4},
            {7,1},
            {7,3},
            {7,5}
        };
        boolean over = false;
        boolean ok = false;
        boolean aTurn = true;

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





        PrintBoard(S);

    }
}
