package com.example.wood121.viewdemos.algorithm;

import java.util.Scanner;

/**
 * Created by wood121 on 2018/3/12.
 */

public class AlgorithmPractise {

    public static void main(String[] args) {
        String dataFromConsole = readDataFromConsole("Please input string:");

        //1 two sum
        //2 add two numbers
        //3 longest substring without repeating characters
        //4 median of two sorted arrays
        //5 longest palindromic substring
        longestPalindroimc(dataFromConsole);
    }

    private static void longestPalindroimc(String dataFromConsole) {
        System.out.println("The information from console: " + dataFromConsole);


    }

    private static String readDataFromConsole(String s) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(s);
        return scanner.nextLine();
    }
}
