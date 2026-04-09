package com.yastodev.app.findIntersection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindIntersection {

    public static String FindIntersectionCheck(String[] strArr) {

        String[] arr1 = strArr[0].split(",");
        String[] arr2 = strArr[1].split(",");

        System.out.println("-" + Arrays.toString(arr1));
        System.out.println("-" + Arrays.toString(arr2));

        int i=0; int j=0;
        List<String> result = new ArrayList<>();

        while (i< arr1.length && j< arr2.length ){

            int num1 = Integer.parseInt(arr1[i].trim());
            int num2 = Integer.parseInt(arr2[j].trim());

            if(num1 == num2) {
                result.add(arr1[i]);
                i++;
                j++;
            }
            else if (num1 > num2){
                j++;
            }
            else {
                i++;
            }

        }

        return result.isEmpty() ? "false" : String.join(",", result);
    }

}
