public class Algebra {
    
    // =======================================================================
    // פונקציית עזר לשבירת הרקורסיה (מחשבת ערך מוחלט ללא קריאה ל-minus או plus)
    // =======================================================================
    private static int internalAbs(int a) {
        if (a >= 0) {
            return a;
        }
        // אם a שלילי, הופכים אותו לחיובי על ידי חיסור חוזר מ-0 (בשימוש ב-++)
        int result = 0;
        int current = a;
        while (current < 0) {
            current++; 
            result++; 
        }
        return result;
    }

    public static void main(String args[]) {
        // ... (הבדיקות נשארות כפי שהן) ...
    }  

    // =======================================================================
    // 1. PLUS (הבסיס לחיבור)
    // =======================================================================
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

    // =======================================================================
    // 2. MINUS (הבסיס לחיסור - פתרון StackOverflowError)
    // =======================================================================
    public static int minus(int x1, int x2) {
        // מבצעים את הפעולה x1 + (-x2)
        int absX2 = internalAbs(x2);
        boolean isNegative = x2 < 0;

        for(int i = 0; i < absX2; i++) {
            if (isNegative) { // אם x2 שלילי, אנו מחברים (x1 - (-x2))
                x1++; 
            } else { // אם x2 חיובי, אנו מחסרים
                x1--; 
            }
        }
        return x1;
    }


    // =======================================================================
    // 3. TIMES (כפל - משתמש ב-plus)
    // =======================================================================
    public static int times(int x1, int x2) {
        if (x1 == 0 || x2 == 0) return 0;
        
        int result = 0;
        int absX1 = internalAbs(x1);
        int absX2 = internalAbs(x2);
        
        boolean isNegativeResult = (x1 < 0 && x2 >= 0) || (x1 >= 0 && x2 < 0);
        
        for(int i = 0; i < absX2; i++) { 
            result = plus(result, absX1);
        }
        
        if (isNegativeResult) {
            return minus(0, result);
        } else {
            return result;
        }
    }

    // =======================================================================
    // 4. POW (חזקה - משתמש ב-times)
    // =======================================================================
    public static int pow(int x, int n) {
        if (n < 0) return 0; 
        if (n == 0) return 1; 

        int result = 1; 
        
        for(int i = 0; i < n; i++) {
            result = times(result, x); 
        }
        return result;
    }
    
    // =======================================================================
    // 5. DIV (חילוק שלמים - משתמש ב-minus, times)
    // =======================================================================
    public static int div(int x1, int x2) {
        if (x2 == 0) return 0;
        
        int result = 0;
        
        int absX1 = internalAbs(x1);
        int absX2 = internalAbs(x2);

        boolean isNegativeResult = (x1 < 0 && x2 >= 0) || (x1 >= 0 && x2 < 0);
        
        // לולאת חיסור חוזר
        while (absX1 >= absX2) {
            absX1 = minus(absX1, absX2); 
            result = plus(result, 1);       
        }
        
        if (isNegativeResult) {
            return minus(0, result); 
        } else {
            return result;
        }
    }

    // =======================================================================
    // 6. MOD (שארית - משתמש ב-div, times, minus)
    // =======================================================================
    public static int mod(int x1, int x2) {
        if(x2 == 0) return 0;
        
        int quotient = div(x1, x2);
        int product = times(quotient, x2);
        int remainder = minus(x1, product);
        
        return remainder;
    }   

    // =======================================================================
    // 7. SQRT (שורש - משתמש ב-times, plus, minus)
    // =======================================================================
    public static int sqrt(int x) 
    {
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