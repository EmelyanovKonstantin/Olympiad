package ru.emelyanovkonstantin.Factorial;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;

/**
 *
 * @author EmelyanovKonstantin
 * @since 2016
 */
public class Factorial {
    private static final BigInteger ZERO = new BigInteger("0");
    private static final BigInteger ONE = new BigInteger("1");
    private static final BigInteger TWO = new BigInteger("2");
    private static final SecureRandom random = new SecureRandom();
    private static final int MIN_LIMIT = 440000000;
    private static final int MAX_LIMIT = 450000000;
    private static ArrayList <BigInteger> dArray;

    public static void main(String[] args) {
        BigInteger result = BigInteger.ZERO;
        for (int i = MIN_LIMIT; i <= MAX_LIMIT; i++) {
            dArray = new ArrayList < > ();
            BigInteger bigI = new BigInteger("" + i);
            System.out.print(i + "  ||->  ");
            factoriz(bigI);
            sort(dArray);
            int sumElem = (dArray.get(dArray.size() - 1)).intValue();
            int element = (dArray.get(dArray.size() - 1)).intValue();
            int maxSum = (dArray.get(dArray.size() - 1)).intValue();
            int maxElem = (dArray.get(dArray.size() - 1)).intValue();
            for (int j = dArray.size() - 2; j >= 0; j--) {
                if ((dArray.get(j)).intValue() == element) {
                    sumElem = sumElem + (dArray.get(j)).intValue();
                } else {
                    if (maxSum < sumElem) {
                        maxSum = sumElem;
                        maxElem = element;
                    }
                    element = (dArray.get(j)).intValue();
                    sumElem = (dArray.get(j)).intValue();
                }
            }
            System.out.print(" " + maxSum + " " + maxElem);
            if ((maxSum - maxElem) != 0) {
                System.out.println("!!!= ");
                BigInteger m = new BigInteger("" + maxElem);
                BigInteger resultSumm = new BigInteger("" + maxElem);
                while (!(getFactorial(resultSumm).mod(bigI).equals(BigInteger.ZERO))) {
                    resultSumm = resultSumm.add(m);
                    if (m.intValue() == maxSum) {
                        System.out.println("Error!");
                        break;
                    }
                }

                System.out.println("|| m = " + resultSumm);
                result = result.add(resultSumm);
            } else {
                System.out.println("|| m = " + maxElem);
                result = result.add(new BigInteger("" + maxElem));
            }
        }
        System.out.println("result = " + result);
    }
    public static BigInteger rho(BigInteger n) {
        BigInteger divisor;
        BigInteger c = new BigInteger(n.bitLength(), random);
        BigInteger x = new BigInteger(n.bitLength(), random);
        BigInteger xx = x;

        // check divisibility by 2
        if (n.mod(TWO).compareTo(ZERO) == 0) return TWO;

        do {
            x = x.multiply(x).mod(n).add(c).mod(n);
            xx = xx.multiply(xx).mod(n).add(c).mod(n);
            xx = xx.multiply(xx).mod(n).add(c).mod(n);
            divisor = x.subtract(xx).gcd(n);
        } while ((divisor.compareTo(ONE)) == 0);

        return divisor;
    }

    public static void factoriz(BigInteger n) {
        if (n.compareTo(ONE) == 0) {
            return;
        }
        if (n.isProbablePrime(20)) {
            dArray.add(n);
            return;
        }
        BigInteger divisor = rho(n);
        factoriz(divisor);
        factoriz(n.divide(divisor));
    }

    public static void sort(ArrayList <BigInteger> arr) {
        for (int i = 0; i < arr.size() - 1; i++)
            for (int j = 0; j < arr.size() - 1 - i; j++) {
                if (arr.get(j).intValue() > arr.get(j + 1).intValue()) {
                    int tmp = arr.get(j).intValue();
                    arr.set(j, arr.get(j + 1));
                    arr.set(j + 1, new BigInteger("" + tmp));
                }
            }
    }

    public static BigInteger getFactorial(BigInteger n) {
        BigInteger result = BigInteger.ONE;
        while (!n.equals(BigInteger.ZERO)) {
            result = result.multiply(n);
            n = n.subtract(BigInteger.ONE);
        }
        return result;
    }
}