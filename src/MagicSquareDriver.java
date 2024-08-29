import java.io.FileNotFoundException;
import java.io.IOException;

public class MagicSquareDriver {
    public static void main(String[] args) {

        if (args.length < 2){
            System.out.println(printUsage1());
            return;
        }

        String filename = args[1];
        String flag = args[0];
        int dimension = Integer.parseInt(args[2]);
        
        if (flag.equals("-check")) {
            if (args.length < 1){
                try {
                    MagicSquare ms = new MagicSquare(filename);
                    System.out.println(ms.toString());
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            else {
                System.out.println(printUsage1());
                return;
            }

        }

        if (flag.equals("-create")){
            if(args.length < 2){
                if(dimension %2 != 0){
                    try {
                        MagicSquare ms = new MagicSquare(filename, dimension);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
            else{
                printUsage2();
                return;
            }

        }

    }

    private static String printUsage1 (){
        String str = "Usage: $ java Driver <-check | -create> filename" + "\n" + "    where -check checks square" + "\n" + "  -create creates new square";
        return str;
    }

    private static String printUsage2(){
        String str = "Usage: $ java Driver <filename><-create><oddInt>";
        return str;
    }


}
