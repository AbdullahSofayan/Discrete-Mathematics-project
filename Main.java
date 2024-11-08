class Main{

public static void main(String[] args) {

    factorization(8051);

}

public static void factorization(int n) {
    int c =1;
    int x =2, y=2;
    int d =1;
    int f; 
    int f2;
    int i =1;
    while( d!=1 || d!= n) {
        f = (x*x + c) % n;
        x = f;

        f = (y*y + c) % n;
        f2= (f*f + c ) % n;
        y= f2;

        System.out.println("x"+ i + "is" + x + "and y" + i + "is" + y );

        d = gcd(Math.abs(x) - Math.abs(y), n);  

        


    }
    
}

    // Recursive Euclidean algorithm
    public static int gcd(int a, int b) {
        if (b == 0) 
            return a;
        
        return gcd(b, a % b);
    }
}