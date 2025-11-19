// Implements algebraic operations and the square root function without using 
// the Java operations a + b, a - b, a * b, a / b, a % b, and without calling 
// Math.sqrt. All the functions in this class operate on int values and
// return int values.

public class Algebra {
    
    private static int abs(int a) {
        if (a < 0) {
            return minus(0, a);
        }
        return a;
    }
    public static void main(String args[]) {
        // Tests some of the operations
        System.out.println(plus(2,3));   
        System.out.println(minus(7,2));  
        System.out.println(minus(2,7));  
        System.out.println(times(3,4));  
        System.out.println(plus(2,times(4,2))); 
        System.out.println(pow(5,3));      
        System.out.println(pow(3,5));      
        System.out.println(div(12,3));   
        System.out.println(div(5,5));    
        System.out.println(div(25,7));   
        System.out.println(mod(25,7));   
        System.out.println(mod(120,6));      
        System.out.println(sqrt(36));
        System.out.println(sqrt(263169));
        System.out.println(sqrt(76123));
        System.out.println(times(-3, 4));
        System.out.println(div(-25, 7));
    }  

    // Returns x1 + x2
    public static int plus(int x1, int x2) {
        int absX2 = abs(x2);
        boolean isNegative = x2 < 0;

        for(int i = 0; i < absX2; i++) {
            if (isNegative) {
                x1--; 
            } else {
                x1++; 
            }
        }
        return x1;
    }

    // Returns x1 - x2
    public static int minus(int x1, int x2) {
        int absX2 = abs(x2);
                for(int i = 0; i < absX2; i++)
        {
             if (x2 < 0) { 
                 x1++; 
             } else {
                 x1--; 
             }
        }
        return x1;
    }


    
    public static int times(int x1, int x2) {
        if (x1 == 0 || x2 == 0) return 0;
        
        int result = 0;
        int absX2 = abs(x2);
        
        boolean isNegativeResult = (x1 < 0 && x2 > 0) || (x1 > 0 && x2 < 0);
        

        for(int i = 0; i < absX2; i++)
        { 
 
            result = plus(result, abs(x1));
        }
        
      
        if (isNegativeResult) {
            return minus(0, result);
        } else {
            return result;
        }
    }


    public static int pow(int x, int n) {
        if (n < 0) return 0; 
        if (n == 0) return 1; 

        int result = 1; 
        
        for(int i = 0; i < n; i++) {
            result = times(result, x); 
        }
        return result;
    }
        public static int div(int x1, int x2) {
        if (x2 == 0) return 0;
        
        int result = 0;
        int absX1 = abs(x1);
        int absX2 = abs(x2);

        boolean isNegativeResult = (x1 < 0 && x2 > 0) || (x1 > 0 && x2 < 0);
        
       
        while (absX1 >= absX2) {
            absX1 = minus(absX1, absX2); 
            result = plus(result, 1);
        
     
        if (isNegativeResult) {
            return minus(0, result); 
        } else {
            return result;
        }
    }

    // Returns x1 % x2
    public static int mod(int x1, int x2) {
        if(x2 == 0) {
            return 0;
        }
        
    
        int quotient = div(x1, x2);
        int product = times(quotient, x2);
        int remainder = minus(x1, product);
        
        return remainder;
    }   

    public static int sqrt(int x) {
        if (x < 0) return 0;
        if (x == 0) return 0;

        int g = 1;
        
   
        while (times(g, g) <= x) 
        { 
            g = plus(g, 1); 
        }


        return minus(g, 1); 
    }
}