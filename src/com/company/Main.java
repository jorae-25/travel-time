package com.company;

import java.util.Objects;

public class Main {
    public static String [] arr = {"null","null","null","null","null","null","null","null","null"};
    public static void main(String[] args) {
         //boolean isSum = nugs(3,7, 11);
        //System.out.print(isSum);
        //String [] things = {null, null, null};
        //System.out.print(memoNugs(5,3,8));
        System.out.print(dNugs(5,3,8));

    }

    public static boolean nugs(int a, int b, int n){
        if(n==0){
            return true;
        }
        if(n<a && n<b){
            return false;
        }
        else{
            return nugs(a,b, n-a) || nugs(a,b,n-b);
        }
    }
    public static String memoNugs(int a, int b, int n){
        return memoNugsPrime(a,b,n);
    }

    public static String memoNugsPrime(int a, int b, int i){
        if(!Objects.equals(arr[i], "null")){
            return arr[i];
        }
        if(i==0){
            arr[i] = "true";
        }
        if(i<a && i<b){
            arr[i] = "false";
        }
        else{
            if(memoNugsPrime(a,b,i-a).equals("true") || memoNugsPrime(a,b,i-b).equals("true")){
                arr[i] = "true";
            }
            else {
                arr[i] = "false";
            }
        }
        return arr[i];
    }
    public static boolean dNugs(int a, int b, int n){
        boolean [] arr = new boolean[n+1];
        for(int i= 0; i<n; i++){
            if(i==0){
                arr[i] = true;
            }
             else if(i<a && i<b){
                arr[i] = false;
            }
            else{
                arr[i] = arr[i-a] || arr[i-b];
            }
        }
        return arr[n+1];
    }
}
