package ch.ubs.m295.demo.Aufgaben;


import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class MathCalcTest {

      @Test
      void primesUnitlN1() {
            List<Integer> listOfPrimeNumbersUntilN = MathCalc.primesUntilN(2);

            assertThat(listOfPrimeNumbersUntilN).containsExactly(2);
      }

      @Test
      void primesUnitlN2() {
            List<Integer> listOfPrimeNumbersUntilN = MathCalc.primesUntilN(4);

            assertThat(listOfPrimeNumbersUntilN).containsExactly(2,3);
      }

      @Test
      void primesUnitlN3() {
            List<Integer> listOfPrimeNumbersUntilN = MathCalc.primesUntilN(8);

            assertThat(listOfPrimeNumbersUntilN).containsExactly(2,3,5,7);
      }

      @Test
      void primesUnitlN4() {
            List<Integer> listOfPrimeNumbersUntilN = MathCalc.primesUntilN(16);

            assertThat(listOfPrimeNumbersUntilN).containsExactly(2,3,5,7,11,13);
      }

      @Test
      void primesUnitlN5() {
            List<Integer> listOfPrimeNumbersUntilN = MathCalc.primesUntilN(32);

            assertThat(listOfPrimeNumbersUntilN).containsExactly(2,3,5,7,11,13,17,19,23,29,31);
      }

      @Test
      void primesUnitlN6() {
            List<Integer> listOfPrimeNumbersUntilN = MathCalc.primesUntilN(64);

            assertThat(listOfPrimeNumbersUntilN).containsExactly(2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61);
      }
}
