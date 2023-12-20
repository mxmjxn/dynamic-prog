import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        HashMap<Integer, Long> memo1 = new HashMap<>();
        HashMap<String, Long> memo2 = new HashMap<>();
        HashMap<Integer, Boolean> memo3 = new HashMap<>();

        //Fibonacci Problem test
        System.out.println(fib(6, memo1));  //8
        System.out.println(fib(7, memo1));  //13
        System.out.println(fib(8, memo1));  //21
        System.out.println(fib(50, memo1)); //12586269025

        //Grid Traveler problem test
        System.out.println(gridTraveler(1, 1, memo2)); //1
        System.out.println(gridTraveler(2, 3, memo2)); //3
        System.out.println(gridTraveler(3, 2, memo2)); //3
        System.out.println(gridTraveler(3, 3, memo2)); //6
        System.out.println(gridTraveler(30, 30, memo2)); //2333606220

        //Can Sum problem test
//        System.out.println(canSum(7, new int[]{2, 3}, new HashMap<>()));         //true
//        System.out.println(canSum(7, new int[]{5, 3, 4, 7}, new HashMap<>()));   //true
//        System.out.println(canSum(7, new int[]{2, 4}, new HashMap<>()));         //false
//        System.out.println(canSum(8, new int[]{2, 3, 5}, new HashMap<>()));      //true
//        System.out.println(canSum(300, new int[]{7, 14}, new HashMap<>()));      //false

        //How Sum problem test
        //System.out.println(howSum(7, new int[]{5, 3, 4, 7}));
    }

    public static int[] howSum(int targetSum, int[] numbers) {
        if (targetSum == 0) return new int[]{};
        if (targetSum < 0) return null;

        for (int number : numbers) {
            int remainder = targetSum - number;
            int[] remainderResult = howSum(remainder, numbers);
            if (remainderResult != null) {
                //return [ ...remainderResult, number];
            }
        }
        return new int[]{};
    }


    public static long fibo(int num, HashMap<Integer, Long> memo) {
        if (memo.containsKey(num)) return memo.get(num);
        if (num <= 2) return 1;
        long fibon = fibo(num - 1, memo) + fibo(num - 2, memo);
        memo.put(num, fibon);

        return fibon;
    }






    public static boolean canSum(int targetSum, int[] numbers, HashMap<Integer, Boolean> memo) {
        if (memo.containsKey(targetSum)) return memo.get(targetSum);
        if (targetSum == 0) return true;
        if (targetSum < 0) return false;

        for (int number : numbers) {
            int remainder = targetSum - number;
            if (canSum(remainder, numbers, memo)) {
                memo.put(targetSum, true);
                return true;
            }
        }
        memo.put(targetSum, false);
        return false;
    }

    //memoization for grid Traveler problem
    //  java object (HashMap [the java equivalent of a dictionary in python]
    //  HashMap initializing a String => our key which consists of our M (rows) and N (columns)
    //  concatenated with a comma separating the unique identifiers
    //  Long => the recursively calculated position
    //  this memoization solution is very similar to the fibonacci solution
    public static long gridTraveler(int m, int n, HashMap<String, Long> memo){
        String key = m + "," + n;
        if (memo.containsKey(key)) return memo.get(key);
        // are the args in the memo
        if (m == 0 || n == 0) return 0;
        if (m == 1 && n == 1) return 1;
        long gT = gridTraveler(m - 1, n, memo) + gridTraveler(m, n - 1, memo);
        memo.put(key, gT);
        return gT;
    }



    //memoization for fibonacci problem
    //  java object. keys will be arg to fn. value will be the return value
    //  using a HashMap here since we have to have KeyValue Pairs,
    //      the position of the fibonacci number and the actual computed number
    public static long fib(int n, HashMap<Integer, Long> memo){
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        if (n <= 2) {
            return 1;
        }
        long fibo = fib(n - 1, memo) + fib(n - 2, memo);
        memo.put(n, fibo);

        return fibo;
    }
}