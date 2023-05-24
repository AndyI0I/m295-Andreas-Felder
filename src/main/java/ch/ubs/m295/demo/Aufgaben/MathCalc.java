package ch.ubs.m295.demo.Aufgaben;

import java.util.ArrayList;
import java.util.List;

public class MathCalc {

      static List<Integer> primesUntilN(int n) {
            List<Integer> primes = new ArrayList<>();
            for (int i = 2; i <= n; i++) {
                  if (isPrime(i)) {
                        primes.add(i);
                  }
            }
            return primes;
      }

       private static boolean isPrime(int number){
            for (int i = 2; i < number; i++) {
                  if (number % i == 0) {
                        return false;
                  }
            }
            return true;
      }

}
