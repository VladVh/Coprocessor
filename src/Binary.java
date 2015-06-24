public class Binary
{
    final int character = 9;//за умовою
    final int mant = 30;
    final int BIAS = 255;
    char[] characters;
    char[] mantissa;
    int sign;
    boolean isNaN = false;
    boolean isInfinite = false;

    Binary()
    {
        characters = new char[character];
        mantissa = new char[mant];
        sign = 0;
        isNaN = false;
        isInfinite = false;
    }

    Binary(double num)
    {
        characters = new char[character];
        mantissa = new char[mant];
        makeBinary(num);
    }

    Binary(char[] chars, char[] mans, int sign, boolean Nan, boolean Inf)
    {
        characters = new char[character];
        mantissa = new char[mant];
        for(int i = 0; i < character; i++)
            characters[i] = chars[i];
        for(int i = 0; i < mant; i++)
            mantissa[i] = mans[i];
        this.sign = sign;
        this.isInfinite = Inf;
        this.isNaN = Nan;
    }

    void makeBinary(double number)
    {
        setSign(number);
        if(number == 0)
        {
            for(int i = 0; i < character; i++)
                characters[i] = '0';
            for(int i = 0; i < mant; i++)
                mantissa[i] = '0';
        }
        else if(Double.isInfinite(number)) // 0|1 111111111 000000000000000000000
        {
            for(int i = 0; i < character; i++)
                characters[i] = '1';
            for(int i = 0; i < mant; i++)
                mantissa[i] = '0';
            isInfinite = true;
            isNaN = false;
        }
        else if(Double.isNaN(number)) // 0|1 111111111 100000000000000000000
        {
            for(int i = 0; i < character; i++)
                characters[i] = '1';
            for(int i = 0; i < mant; i++)
                mantissa[i] = '0';
            mantissa[0] = '1';
            isNaN = true;
            isInfinite = false;
        }
        else
        {
            isNaN = false;
            isInfinite = false;
            double fraction;
            double num = Math.abs(number);
            int exp = 0;
            if (Math.abs(number) > 2)//приведення до формату 1,46548
            {
                while (num >= 2)
                {
                    num /= 2;
                    exp++;
                }
                fraction = num - 1;
            }
            else
            {
                while (num < 1)
                {
                    num *= 2;
                    exp--;
                }
                fraction = num - 1;
            }
            expPart(exp);
            fractionPart(fraction);
            //output();
        }
    }

    void setSign(double num)
    {
        if(num >= 0)
            sign = 0;
        else
            sign = 1;
    }

    void expPart(int num)// задаємо характеристику,
    {
        int exp = BIAS + num;
        int i = 0;
        int remnant;
        String line = Integer.toBinaryString(exp);
        remnant = character - line.length();///дописування потрібних нулів спочатку
        while(remnant-- > 0)
            characters[i++] = '0';
        for(int j = 0 ; i < character; i++, j++)
            characters[i] = line.charAt(j);
    }

    void fractionPart(double fraction)//визначення двійкової мантиси *,5448
    {
        int cnt = 0;
        while(cnt < mant)
        {
            fraction*=2;
            if(fraction < 1)
                mantissa[cnt++] = '0';
            else
            {
                mantissa[cnt++] = '1';
                fraction -= 1;
            }

        }
    }

    void output()
    {
        System.out.print("[" + sign + "]" + " ");
        System.out.print('[');
        for(int i = 0; i < character; i++)
        {
            System.out.print(characters[i]);
            if((i+1)%4 == 0)
                System.out.print(" ");
        }
        System.out.print(']' + " " + '[');
        for(int i = 0; i < mant; i++)
        {
            System.out.print(mantissa[i]);
            if((i+1)%4 == 0)
                System.out.print(" ");
        }
        System.out.println(']');
    }

    double binToDouble()
    {
        if(isInfinite)
        {
            return (-1)*(2*sign-1)*(1.0/0);
        }
        else if(isNaN)
        {
            return (0.0/0);
        }
        String num;
        int power = Integer.parseInt(String.valueOf(characters), 2) - BIAS;
        if(power == -255)
            return 0.0;
        else
        {
            if (power >= 0)
                num = "1";
            else
                num = "0";
            for (int i = 0; i < power; i++)
            {
                if (i < mant)
                    num += mantissa[i];
                else
                    num += "0";
            }
            //int number = Integer.parseInt(num, 2);
            double number = 0;
            int power2 = power;
            for (int i = 0; i < num.length(); i++)
            {
                if (num.charAt(i) == '1')
                    number += Math.pow(2.0, (double) power2);
                power2--;
            }
            double value = 0;
            if (power < mant)
            {
                int p = -1;
                for (int i = power; i < mant; i++)
                {
                    if (mantissa[i] == '1')
                        value += Math.pow(2.0, (double) p);
                    p--;
                }
            }
            if (sign == 0)
                value = value + number;
            else
                value = (-1) * (value + number);
            return value;
        }
    }

    public Binary clone() throws CloneNotSupportedException
    {
        Binary clone = (Binary)super.clone();
        clone.characters = (char[])characters.clone();
        clone.mantissa = (char[])mantissa.clone();
        return clone;
    }

    public Binary copy()
    {
        Binary clone = new Binary(this.characters, this.mantissa, this.sign, this.isNaN, this.isInfinite);
        return clone;
    }
}
