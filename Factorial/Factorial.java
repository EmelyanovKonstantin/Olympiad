/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    private final static BigInteger ZERO = new BigInteger("0");
    private final static BigInteger ONE  = new BigInteger("1");
    private final static BigInteger TWO  = new BigInteger("2");
    private final static SecureRandom random = new SecureRandom();
    private static ArrayList<BigInteger> dArray;

    public static void main(String[] args) {
         int a = 440000000;
         int b = 450000000; 
         BigInteger result = BigInteger.ZERO;
         for(int i=a; i<=b ; i++){               
             dArray = new ArrayList<>();
             BigInteger bigI = new BigInteger(""+i);
             System.out.print(i+"  ||->  ");
             factoriz(bigI);
             sort(dArray);             
             int sumElem = (dArray.get(dArray.size()-1)).intValue();
             int element = (dArray.get(dArray.size()-1)).intValue();
             int maxSum= (dArray.get(dArray.size()-1)).intValue();
             int maxElem= (dArray.get(dArray.size()-1)).intValue();
             for(int j=dArray.size()-2; j>= 0;j--){
                 if ((dArray.get(j)).intValue() == element){
                     sumElem= sumElem + (dArray.get(j)).intValue();
                 }else{
                     if(maxSum < sumElem){
                         maxSum = sumElem;
                         maxElem = element;
                     }
                     element = (dArray.get(j)).intValue();
                     sumElem = (dArray.get(j)).intValue();
                 }                 
             }
             System.out.print(" "+ maxSum +" "+ maxElem);
             if ((maxSum -maxElem) != 0){
                 System.out.println("!!!= " );
             BigInteger m = new BigInteger(""+maxElem);
             BigInteger resultSumm = new BigInteger(""+maxElem);
             while(!(getFactorial(resultSumm).mod(bigI).equals(BigInteger.ZERO))){
                 resultSumm = resultSumm.add(m);
                 if(m.intValue() == maxSum){
                     System.out.println("Arror!!!!!!!!!!!!!!!" );
                     break;
                 }
             }
            
             System.out.println("|| m = "+ resultSumm );
             result = result.add(resultSumm);
             } else {
                 System.out.println("|| m = "+ maxElem );
                 result = result.add(new BigInteger(""+maxElem));
             }
         }
         System.out.println("result = "+ result );
    }
    public static BigInteger rho(BigInteger N) {
        BigInteger divisor;
        BigInteger c  = new BigInteger(N.bitLength(), random);
        BigInteger x  = new BigInteger(N.bitLength(), random);
        BigInteger xx = x;

        // check divisibility by 2
        if (N.mod(TWO).compareTo(ZERO) == 0) return TWO;

        do {
            x  =  x.multiply(x).mod(N).add(c).mod(N);
            xx = xx.multiply(xx).mod(N).add(c).mod(N);
            xx = xx.multiply(xx).mod(N).add(c).mod(N);
            divisor = x.subtract(xx).gcd(N);
        } while((divisor.compareTo(ONE)) == 0);

        return divisor;
    }

    public static void factoriz(BigInteger N) {
        if (N.compareTo(ONE) == 0){return;}
        if (N.isProbablePrime(20)) {
            dArray.add(N);
            return;
        }
        BigInteger divisor = rho(N);
        factoriz(divisor);
        factoriz(N.divide(divisor));
    }

    public static void sort( ArrayList<BigInteger> arr ){
        for(int i = 0; i< arr.size()-1; i++)
          for(int j = 0; j< arr.size()-1-i; j++){
               if (arr.get(j).intValue() > arr.get(j+1).intValue()){
                   int tmp = arr.get(j).intValue();
                   arr.set(j, arr.get(j+1));
                   arr.set(j+1, new BigInteger(""+tmp));
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
