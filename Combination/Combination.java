/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.emelyanovkonstantin.Combination;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author EmelyanovKonstantin
 * @since 2016
 */
public class Combination {
    public static void main(String[] args) {
        for(int i=1; i<125874;i++){
            int dig1= i*2;
            int dig2= i*6;
            System.out.print(""+ i+"-> ||  ");
            int[] arr = sort(numerArr(dig1));
            int[] arr2 = sort(numerArr(dig2));
            
            for(int j=0;j<arr.length;j++){
                System.out.print(" "+arr[j]);
            }
            System.out.print(" || ");
            for(int j=0;j<arr2.length;j++){
                System.out.print(" "+arr2[j]);
            }
            if (Arrays.equals(arr, arr2)){
                System.out.println(" YES "+ i);
                break;
            }
            System.out.println(" ");
        }
    }
    
    public static int[] numerArr(int x){
         ArrayList<Integer> dArray = new ArrayList<>();        
         while(x>0){
             int number = x%10;
             dArray.add(0,number);
             x/=10;             
         }
         int[] array = new int[dArray.size()];
         for(int i=0; i< dArray.size(); i++){
             array[i] = dArray.get(i);
         }
         return array;
    }
    public static int[] sort(int[] arr){
        for(int i = 0; i< arr.length-1; i++)
            for(int j = 0; j< arr.length-1-i; j++){
                if (arr[j] > arr[j+1]){
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                }
            }
        return arr;
    }
}
    
