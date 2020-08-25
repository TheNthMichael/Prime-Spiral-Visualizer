/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primespiral;

import java.util.*;
import java.math.*;

/**
 *
 * @author Michael's pc
 */
public class PrimeSpiral {

   
    public static ArrayList<BigInteger> getPrimes(int n) {
        ArrayList<BigInteger> primes = new ArrayList<>();
        BigInteger b;
        String last_prime = "";
        for(int i=0; i<n; i++) {
            b = new BigInteger(String.valueOf(i)); 
            if( !last_prime.equals(b.nextProbablePrime().toString()) ) {
                primes.add(b.nextProbablePrime());
            }
            last_prime = b.nextProbablePrime().toString();
        }
        return primes;
    }
    
    public static void main(String[] args) {
        int n = 100000;
        ArrayList<BigInteger> primes = getPrimes(n);
        Canvas canvas = new Canvas(1920, 1080, primes);
        Frame frame = new Frame(1920, 1080, canvas);
    }
    
}
