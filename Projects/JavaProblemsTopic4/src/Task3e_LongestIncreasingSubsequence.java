
import java.util.Scanner;

public class Task3e_LongestIncreasingSubsequence {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a digit: ");
        int number = sc.nextInt();
        int arr[] = new int[number];
        System.out.printf("Please enter %d digits: %n ", number);
        for (int i = 0; i < number; i++) {
            arr[i] = sc.nextInt();
        }
        boolean isIncreasing = false;
        int countOfIncrSubseq = 1;
        int maxCountOfIncrSubseq = 0;
        for (int i = 1; i < arr.length - 1; i++) {
            if (arr[i] > arr[i - 1]) {
                isIncreasing = true;
                countOfIncrSubseq++;
            } else {
                if (maxCountOfIncrSubseq < countOfIncrSubseq) {
                    maxCountOfIncrSubseq = countOfIncrSubseq;
                }
                countOfIncrSubseq = 1;
            }

        }
        //System.out.println(maxCountOfIncrSubseq);
        countOfIncrSubseq = 1;
        if (!isIncreasing) {
            System.out.println("There is no increasing subsequence");
            return;
        } else {
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] > arr[i - 1]) {
                    countOfIncrSubseq++;
                    if (countOfIncrSubseq == maxCountOfIncrSubseq) {
                        System.out.println("");
                        for (int j = i - (maxCountOfIncrSubseq - 1); j <= i; j++) {
                            System.out.print(arr[j] + " ");
                        }
                    }
                } else {
                    countOfIncrSubseq = 1;
                }

            }
        }

    }

}
