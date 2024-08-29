import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

// TODO Javadocs

public class MagicSquare implements MagicSquareInterface{

    private boolean isValidMagicSquare;
    private int[][] matrix;
    private int dimension;

    public MagicSquare(String filename) throws FileNotFoundException {
        this.matrix = readMatrix(filename);
        this.isValidMagicSquare = isMagicSquare();
    }

    public MagicSquare (String filename, int dimension) throws IOException {
        this.dimension = dimension;
        createMagicSquare(dimension);
        writeMatrix(this.matrix, filename);
        this.isValidMagicSquare = isMagicSquare();
    }

    @Override
    public boolean isMagicSquare() {
        int magicNum = dimension * ((dimension * dimension) + 1) /2;

        //check each row
        for(int i =0; i < dimension; i++){
            int rowNum = 0;
            for(int j=0; j < dimension; j++){
                rowNum += matrix[i][j];
            }
            if(rowNum != magicNum){
                return false;
            }
        }

        //check each col
        for(int j =0; j < dimension; j++){
            int colNum = 0;
            for(int i=0; i < dimension; i++){
                colNum += matrix[i][j];
            }
            if(colNum != magicNum){
                return false;
            }
        }

        //check from top left to bottom right
        int diagNum1 = 0;
        for(int i = 0; i < dimension; i++){
            diagNum1 += matrix[i][i];
        }

        if (diagNum1 != magicNum){
            return false;
        }

        int diagNum2 = 0;
        for(int i = 0; i < dimension; i++){
            diagNum2 += matrix[i][dimension - i - 1];
        }

        if (diagNum2 != magicNum){
            return false;
        }

        return true;
        
    }

    @Override
    public int[][] getMatrix() {
       int[][] matrixCopy = new int[dimension][dimension];
       for (int i = 0; i < dimension; i++) {
        for (int j = 0; j < dimension; j++) {
            matrixCopy[i][j] = matrix[i][j];
        }
    }
    return matrixCopy;
}
    

    private int[][] readMatrix(String filename) throws FileNotFoundException{
        Scanner fs = new Scanner(new File(filename));
        dimension = fs.nextInt(); 
        matrix= new int[dimension][dimension];
        for(int i=0; i < dimension; i++ ) {
            for (int j=0; j < dimension; j++){
                if (fs.hasNextInt()) {
                    matrix[i][j] = fs.nextInt();
                }
            }
        }
        fs.close();
        return matrix;
    }
    
    private void writeMatrix(int[][] matrix, String filename)throws IOException{
        PrintWriter outFile = new PrintWriter(new FileWriter(new File(filename)));
        
        outFile.println(dimension);
        for (int[] row : matrix) {
            for (int num : row) {
             outFile.print(num + " ");
            }
        outFile.println();
    }

        outFile.close();
    }

    private void createMagicSquare(int dimension){
    
        int n = dimension;
        matrix = new int[dimension][dimension];
        int row = n -1;
        int col = n /2;
        int oldRow;
        int oldCol;

        for (int i=1; i < n *n; i++){
            matrix[row][col] = i;
            oldRow = row;
            oldCol= col;
            row++;
            col++;

            if (row == n){
                row = 0;
            }
            if (col == n){
                col = 0;
            }
            if (matrix [row][col] != 0 ){
                row = oldRow;
                col = oldCol;
                row--;
            }
        }

    }
    
    public String toString(){
       
        String str = "The matrix\n";

        for (int[] row : matrix) {
            str += "  ";
            for (int num : row) {
                str += num + " ";
            }
            str += "\n";
        }

        if (isValidMagicSquare == true){
            str += "is a magic square";
        }
        if (isValidMagicSquare == false){
            str += "is not a magic square";
        }
        return str;

    }
    
}
