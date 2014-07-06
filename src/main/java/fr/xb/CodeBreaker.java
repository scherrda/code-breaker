package fr.xb;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class CodeBreaker {
    private int[] secret;
    private static int SECRET_LENGTH = 4;

    CodeBreaker(String code){
        this.secret = new int[SECRET_LENGTH];
        for(int i =0; i< SECRET_LENGTH; i++){
            this.secret[i] = code.charAt(i) - '0';
        }
    }

    private boolean exactMatch(int pos, String guess){
        return this.secret[pos] == guess.charAt(pos) -'0';
    }

    private boolean digitMatch(int pos, String guess){
        return guess.contains(Integer.toString(this.secret[pos]))
                && (guess.charAt(pos)-'0' != this.secret[pos]) ;
    }


    private boolean exactMatch(int digit, int pos){
        return this.secret[pos] == digit ;
    }

    private boolean digitMatch(int digit, int pos){
        return Arrays.toString(secret).contains(String.valueOf(digit)) && this.secret[pos] != digit ;
    }



    public String match(String guess){
        char[] matches = new char[SECRET_LENGTH];

        for( int pos = 0; pos< SECRET_LENGTH; pos++){
            if(exactMatch(pos, guess)){
                matches[pos] = '+';
            }else if(digitMatch(pos, guess)){
                matches[pos] = '-';
            }
        }
        return String.valueOf(matches).replaceAll("\u0000", "");
    }


    public static void main(String...args){
        CodeBreaker codeBreaker = new CodeBreaker(randomNumber());

        System.out.println("Codebreaker Game --- find the 4 digits secret : ");

        Scanner console = new Scanner(System.in);
        while(console.hasNextLine()){
            String guess = console.nextLine();
            if(guess.length() != 4){
                System.out.println("Enter a 4 digits number: ");
            }else{
                String result = codeBreaker.match(guess);
                System.out.println("CodeBreaker answer : " + result);
                if(result.equals("++++")){
                    System.out.println("You WIN !!! ");
                    break;
                }
            }
        }


    }

    private static String randomNumber() {
        StringBuilder numberBuilder = new StringBuilder();
        Random rand = new Random();
        for (int i = 1; i <= 4; ++i){
            int randomInt = rand.nextInt(10);
            numberBuilder.append(randomInt);
        }
        return numberBuilder.toString();
    }
}
