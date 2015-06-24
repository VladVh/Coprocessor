import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Created by Vlad on 23.04.2015.
 */
public class Main
{
    public static void main(String[] args)
    {
        Coprocessor coprocessor = new Coprocessor();
        try {
            Scanner in = new Scanner(new FileReader("src\\file.txt"));
            while(in.hasNextLine())
            {
                String line = in.nextLine();
                coprocessor.doWork(line);
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}

