package util;

public class Utile {
    public static void printTab(int[][] tab){
        for(int i=0;i<tab.length;i++){
            for(int j=0;j<tab[i].length;j++){
                System.out.print(tab[i][j]);
            }
            System.out.println("");
        }
    }
}
