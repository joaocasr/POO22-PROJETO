import java.util.Scanner;


public class parser {
    
    public static void parser()
    {
        File file = new File("log.txt");
        Scanner sc = new Scanner(file);

        String[] line;
        while (sc.hasNextLine()) {
            line = sc.nextLine().split(",");
        }
    }
}
