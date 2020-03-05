package com.hewking.utils;

public class QuickSort {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
//        int[] arr = {49, 38, 65, 97, 76, 13, 27};
        int[] arr = new int[]{3, 26, 42, 75, 34, 65, 23};

        quickSort(0, arr.length - 1, arr);
//        System.out.println("ssss");
        for (int i : arr) {
            System.out.println(i);
        }

//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        System.out.println(format.format(System.currentTimeMillis()));
    }

    public static void quickSort(int left, int right, int[] arr) {
        if (left > right) {
            return;
        }

        int part = partition(left, right, arr);
        quickSort(left, part - 1, arr);
        quickSort(part + 1, right, arr);
    }

    public static int partition(int left, int right, int[] arr) {
        //悲伤的股市 不知道为啥我要这样写 还看不懂代码 。。我说怎么这么奇怪
//        int k = left;
//        while(left < right){
//            while(left < right && arr[right] >= arr[k])
//                right --;
//                arr[left] = arr[right];
//            while(left < right && arr[left] <= arr[k])
//                left ++;
//                arr[right] = arr[left];
//        }
//        arr[left] = arr[k];
//        return left;


        int result = arr[left];
        while (left < right) {
            while (left < right && arr[right] >= result)
                right--;
            arr[left] = arr[right];
            while (left < right && arr[left] <= result)
                left++;
            arr[right] = arr[left];
        }
        arr[left] = result;
        return left;
    }

}
