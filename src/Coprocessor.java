/**
 * Created by Vlad on 09.05.2015.
 */
public class Coprocessor
{
    Stack stack;

    Coprocessor()
    {
        stack = new Stack();
    }
    //111
    int getCommand(String comm)
    {
        int k = -1;
        if(comm.equals("Fild"))
            k = 0;
        else if(comm.equals("Dub"))
            k = 1;
        else if(comm.equals("Add"))
            k = 2;
        else if(comm.equals("Substract"))
            k = 3;
        else if(comm.equals("Multiply"))
            k = 4;
        else if(comm.equals("Division"))
            k = 5;
        else if(comm.equals("Fist"))
            k = 6;
        else if(comm.equals("Fistp"))
            k = 7;
        else if(comm.equals("Swap"))
            k = 8;
        return k;
    }
    void doWork(String line)
    {
        String[] attributes = line.split(" ");
        int command = getCommand(attributes[0]);
        double number = 0;
        double num;
        if(attributes.length > 1)
        {
            number = Double.valueOf(attributes[1]);
            System.out.println("Command : " + attributes[0] + " , attribute = " + attributes[1]);
        }
        else
            System.out.println("Command : " + attributes[0]);
        switch (command) {
            case 0:
                if(stack.count < 8)
                    stack.fild(number);
                else
                    System.out.println("Операцію було скасовано через переповнення стеку");
                break;
            case 1:
                if(stack.count < 8)
                    stack.dub();
                else
                    System.out.println("Операцію було скасовано через переповнення стеку");
                break;
            case 2:
                if(stack.count > 1)
                    stack.add();
                else
                    System.out.println("Недостатньо значень в стеці");
                break;
            case 3:
                if(stack.count > 1)
                    stack.substract();
                else
                    System.out.println("Недостатньо значень в стеці");
                break;
            case 4:
                if(stack.count > 1)
                    stack.multiply();
                else
                    System.out.println("Недостатньо значень в стеці");
                break;
            case 5:
                if(stack.count > 1)
                    stack.division();
                else
                    System.out.println("Недостатньо значень в стеці");
                break;
            case 6:
                if(stack.count > 0)
                {
                    num = stack.fist();
                    System.out.println(num);
                }
                else
                    System.out.println("Недостатньо значень в стеці");
                break;
            case 7:
                if(stack.count > 0)
                {
                    num = stack.fistp();
                    System.out.println(num);
                }
                else
                    System.out.println("Недостатньо значень в стеці");
                break;
            case 8:
                if(stack.count > 1)
                    stack.swap();
                else
                    System.out.println("Недостатньо значень в стеці");
                break;
        }
        stack.display();
    }
}
