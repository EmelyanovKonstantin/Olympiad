package ru.emelyanovkonstantin.Palindrom;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 *
 * @author EmelyanovKonstantin
 * @version 1.0
 * @since 2016
 */
public class Palindrom {
    public static void main(String[] args) {
        int result = 0;
        int h = 0;
        for (int i = 1; i < 12888; i++) {
            BigInteger sum = reverseThenAdd(new BigInteger("" + i));
            System.out.println(i + " ");
            if (isPalindrom(sum)) {
                result++;
                System.out.println("sum=" + sum);
            } else {
                int index = 1;
                while (index < 50) {
                    sum = reverseThenAdd(sum);
                    index++;
                    System.out.println(sum + " " + index);
                    if (isPalindrom(sum)) {
                        result++;
                        System.out.println("sum=" + sum + " index=" + index);
                        break;
                    }
                }
            }
            h++;
        }
        System.out.println("result=" + (h - result));
    }

    public static boolean isPalindrom(BigInteger bigI) {
        ArrayList<Integer> dArray = new ArrayList<>();
        while (!(bigI.equals(BigInteger.ZERO))) {
            int number = (bigI.mod(BigInteger.TEN)).intValue();
            dArray.add(0, number);
            bigI = bigI.divide(BigInteger.TEN);
        }
        boolean isP = true;
        for (int i = 0; i < dArray.size(); i++) {
            if (!(dArray.get(i)).equals(dArray.get(dArray.size() - i - 1))) {
                isP = false;
                break;
            }
        }
        return isP;
    }

    public static BigInteger reverseThenAdd(BigInteger n) {
        BigInteger nSave = n;
        ArrayList<Integer> dArray = new ArrayList<>();
        while (!(n.equals(BigInteger.ZERO))) {
            int number = (n.mod(BigInteger.TEN)).intValue();
            dArray.add(0, number);
            n = n.divide(BigInteger.TEN);
        }
        BigInteger flipN = BigInteger.ZERO;
        for (int l = (dArray.size() - 1); l >= 0; l--) {
            BigInteger kf = (BigInteger.TEN).pow(l);
            if (kf.equals(BigInteger.ZERO)) {
                kf = BigInteger.ONE;
            }
            BigInteger tmp = new BigInteger("" + dArray.get(l));
            tmp = tmp.multiply(kf);
            flipN = flipN.add(tmp);
        }

        return (flipN.add(nSave));
    }
}