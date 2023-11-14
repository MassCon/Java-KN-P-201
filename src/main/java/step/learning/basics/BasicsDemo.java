package step.learning.basics;

import java.util.*;

public class BasicsDemo {
    public void run(){

        //region Types and values
        System.out.println( "BasicDemo" );
        System.out.printf("interpolated '%s' value%n", "hello");
        // ...
        byte b = 10;
        short s =100;
        int i =10000;
        long l = 1000000000;

        Byte rb = 10;
        Short rs =100;
        Integer ri =10000;
        Long rl = 1000000000L;

        float f = 1e-3f;
        double d = 2e-7;

        boolean bool  = true;
        char c = 'A';

        String str1 = "Hello";
        String str2 = "Hello";
        String str3 = new String ("Hello");
        if(str1 == str2){
            System.out.println("str1 == str2"); // similar because pooling
        }
        else {
            System.out.println("str1 != str2");
        }

        if(str1 == str3){
            System.out.println("str1 == str3");
        }
        else {
            System.out.println("str1 != str3");
        }

        if(str1.equals(str3)){
            System.out.println("str1.equals str3");
        }
        else {
            System.out.println("str1 !equals str3");
        }
        // endregion

        arrDemo();
    }

    private void arrDemo(){

        int[] arr1 = {5,4,3,2,1};
        int[] arr2 = new int[]{5,4,3,2,1};

        for (int i = 0; i < arr1.length; i++) {
            System.out.print( arr1[i] + " ");
        }
        System.out.println();

        for (int x: arr2) {
            System.out.print( x + " ");
        }
        System.out.println();

        int [][] arr2d ={
                {1,2,3},
                {4,5,6, 4},
                {7,8,9, 1, 2}
        };

        for( int[] x: arr2d){
            for (int y : x ) {
                System.out.print(y+ " ");
            }
            System.out.println();
        }

        //collection
        List<Integer> list1 = new ArrayList<>();
        list1.add( 10 );
        list1.add( 20 );
        list1.add( 30 );
        list1.add( 40 );

        for (Integer x: list1 ) {
            System.out.print( x + " ");
        }
        System.out.println();

        //Assoc
        Map<String, String> headers = new LinkedHashMap<>();
        headers.put("Host", "localhost");
        headers.put("Connection", "close");
        headers.put("Connection-Type", "text/html");

        for (String key: headers.keySet()) {
            System.out.println(
                    String.format(
                            "%s: %s",
                            key, headers.get(key)
                    )
            );
        }
    }
}
