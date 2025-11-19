// Implements algebraic operations and the square root function without using 
// the Java operations a + b, a - b, a * b, a / b, a % b, and without calling 
// Math.sqrt. All the functions in this class operate on int values and
// return int values.

public class Algebra {
	public static void main(String args[]) {
	    // Tests some of the operations
	    System.out.println(plus(2,3));   // 2 + 3
	    System.out.println(minus(7,2));  // 7 - 2
   		System.out.println(minus(2,7));  // 2 - 7
 		System.out.println(times(3,4));  // 3 * 4
   		System.out.println(plus(2,times(4,2)));  // 2 + 4 * 2
   		System.out.println(pow(5,3));      // 5^3
   		System.out.println(pow(3,5));      // 3^5
   		System.out.println(div(12,3));   // 12 / 3    
   		System.out.println(div(5,5));    // 5 / 5  
   		System.out.println(div(25,7));   // 25 / 7
   		System.out.println(mod(25,7));   // 25 % 7
   		System.out.println(mod(120,6));  // 120 % 6    
   		System.out.println(sqrt(36));
		System.out.println(sqrt(263169));
   		System.out.println(sqrt(76123));
	}  

    // פונקציית עזר פנימית לטיפול בערך מוחלט (Abs)
    private static int internalAbs(int a) {
        if (a < 0) {
            // הופך לשלילי-שלילי. קריאה בטוחה: minus(0, a) אינה הבעיה, אלא מימושה!
            return minus(0, a); 
        }
        return a;
    }

    // ... (פונקציית main) ...

    // Returns x1 + x2
    public static int plus(int x1, int x2) {
        int absX2 = internalAbs(x2);
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

    // Returns x1 - x2 (תיקון קריטי)
    public static int minus(int x1, int x2) {
        // הדרך הנכונה: נשתמש בחיבור להופכי החיבורי (x1 + (-x2)). 
        // במקום להשתמש ב-minus(0, x2), נשתמש ב-plus וב-++ / -- בצורה נכונה.

        int absX2 = internalAbs(x2);
        
        // אם x2 חיובי, אנו מחסרים (x1 - x2).
        if (x2 >= 0) {
            for(int i = 0; i < absX2; i++) {
                 x1--; // הפחתה ישירה
            }
            return x1;
        } 
        // אם x2 שלילי (כמו x2=-5), אנו מחברים (x1 - (-5) = x1 + 5).
        else {
             for(int i = 0; i < absX2; i++) {
                 x1++; // חיבור ישיר
            }
            return x1;
        }
    }
    
    // ... שאר הפונקציות נשארות כפי שהן (מכיוון שהן תלויות ב-plus/minus) ...

    // Returns x1 * x2
    public static int times(int x1, int x2) {
        if (x1 == 0 || x2 == 0) return 0;
        
        int result = 0;
        int absX1 = internalAbs(x1);
        int absX2 = internalAbs(x2);
        
        // קובעים את הסימן הסופי
        boolean isNegativeResult = (x1 < 0 && x2 >= 0) || (x1 >= 0 && x2 < 0);
        
        // הלולאה רצה על הערך המוחלט של X2
        for(int i = 0; i < absX2; i++)
        { 
            result = plus(result, absX1);
        }
        
        // החזרת התוצאה עם הסימן הנכון
        if (isNegativeResult) {
            return minus(0, result);
        } else {
            return result;
        }
    }

    // Returns x^n (for n >= 0)
    public static int pow(int x, int n) {
        if (n < 0) return 0; 
        if (n == 0) return 1; 

        int result = 1; 
        
        for(int i = 0; i < n; i++) {
            result = times(result, x); 
        }
        return result;
    }
    
    // Returns the integer part of x1 / x2 
    public static int div(int x1, int x2) {
        if (x2 == 0) return 0;
        
        int result = 0;
        
        int absX1 = internalAbs(x1);
        int absX2 = internalAbs(x2);

        // קביעת הסימן הסופי
        boolean isNegativeResult = (x1 < 0 && x2 >= 0) || (x1 >= 0 && x2 < 0);
        
        // לולאת חיסור חוזר על הערכים המוחלטים
        while (absX1 >= absX2) {
            absX1 = minus(absX1, absX2); 
            result = plus(result, 1);       
        }
        
        // החזרת התוצאה עם הסימן הנכון
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
        
        // נשתמש בזהות: a % b = a - (a / b) * b
        int quotient = div(x1, x2);
        int product = times(quotient, x2);
        int remainder = minus(x1, product);
        
        return remainder;
    }   

    // Returns the integer part of sqrt(x) 
    public static int sqrt(int x) 
	{
        if (x < 0) return 0;
        if (x == 0) return 0;

        int g = 1;
        
        // לולאת חיפוש סדרתי
        while (times(g, g) <= x) 
        { 
            g = plus(g, 1); 
        }

        // מחזירים g-1 כי g*g כבר גדול מ-x
        return minus(g, 1); 
    }

}