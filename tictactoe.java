public class tictactoe {
    public static void main(String[] args) {
        int[][]arr={
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
        };
        grid(arr);
    }
    public static void grid(int arr[][]){
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr.length;j++){
                System.out.print(" "+arr[i][j]+" ");
                if(j+1<arr.length){
                    System.out.print("|");
                }
                
            }
            System.out.println();
            if(i+1<arr.length){
                System.out.print("___________");
            }
            System.out.println();
        }

    }
}
