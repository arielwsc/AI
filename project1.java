import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

class project1{

    public static void main(String[] args) throws FileNotFoundException{
        int[][] map = new int[10][10];
        
        mapIO(args[0], map, 'I');
        mapIO(args[0], map, 'O');
    }

    private static void mapIO(String file, int[][] map, char mode) throws FileNotFoundException{
        Scanner inFile = new Scanner(new FileInputStream(file));

        if(mode == 'I'){
            for(int i=0; i<10; i++){
                for(int j=0; j<10; j++){
                    map[j][i] = inFile.nextInt();
                }
                if (!inFile.hasNext()) break;
                inFile.nextLine();
            }
            inFile.close();
        }
        else if(mode == 'O'){
            for(int i=0; i<10; i++){
                for(int j=0; j<10; j++){
                    System.out.print(map[j][i] + " ");
                }
                System.out.print("\n");
            }
        }
    }

}