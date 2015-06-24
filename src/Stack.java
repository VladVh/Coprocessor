/**
 * Created by Vlad on 09.05.2015.
 */
public class Stack
{
    Binary[] binaryNumbers;
    int count = 0;
    Stack()
    {
        binaryNumbers = new Binary[8];// 8 binary numbers
        for(int i = 0; i < 8; i++)
            binaryNumbers[i] = new Binary();
    }

    void fild(double number)//add a new number
    {
        for(int i = count; i > 0; i--)
        {
                binaryNumbers[i] = binaryNumbers[i-1].copy();//в комірці міститься об*єкт класу
        }
        binaryNumbers[0].makeBinary(number);
        count++;
    }

    void dub()//create a copy and move the others
    {
        for(int i = count; i > 0; i--)
        {
            binaryNumbers[i] = binaryNumbers[i-1].copy();
        }
        binaryNumbers[1] = binaryNumbers[0].copy();
        count++;
    }

    void add()//add two numbers and shift the others
    {
        count--;
        double number1 = binaryNumbers[0].binToDouble();
        double number2 = binaryNumbers[1].binToDouble();
        number1 = number2 + number1;
        binaryNumbers[0].makeBinary(number1);
        pullUp();
    }

    void substract()
    {
        count--;
        double number1 = binaryNumbers[0].binToDouble();
        double number2 = binaryNumbers[1].binToDouble();
        number1 -= number2;
        binaryNumbers[0].makeBinary(number1);
        pullUp();
    }

    void multiply()
    {
        count--;
        double number1 = binaryNumbers[0].binToDouble();
        double number2 = binaryNumbers[1].binToDouble();
        number1 *= number2;
        binaryNumbers[0].makeBinary(number1);
        pullUp();
    }

    void division()
    {
        count--;
        double number1 = binaryNumbers[0].binToDouble();
        double number2 = binaryNumbers[1].binToDouble();
        number1 /= number2;
        binaryNumbers[0].makeBinary(number1);
        pullUp();
    }
    double fist()
    {
        return binaryNumbers[0].binToDouble();
    }

    double fistp()//return the first number and shift the others
    {
        count--;
        double value = binaryNumbers[0].binToDouble();
        for(int i = 0; i < count; i++)
        {
            binaryNumbers[i] = binaryNumbers[i+1].copy();
        }
        binaryNumbers[count] = new Binary();
        return value;
    }

    void swap()
    {
        Binary additional = new Binary(binaryNumbers[0].characters, binaryNumbers[0].mantissa, binaryNumbers[0].sign, binaryNumbers[0].isNaN, binaryNumbers[0].isInfinite);
        binaryNumbers[0] = binaryNumbers[1].copy();
        binaryNumbers[1] = additional.copy();
    }
    private void pullUp()
    {
        for(int i = 1; i < count; i++)
        {
            binaryNumbers[i] = binaryNumbers[i+1].copy();
        }
        binaryNumbers[count] = new Binary();
    }

    void display()
    {
        for(int i = 0; i < 8; i++)
        {
            System.out.println("Stack " + i + " :");
            binaryNumbers[i].output();
        }
        System.out.println();
    }
}
