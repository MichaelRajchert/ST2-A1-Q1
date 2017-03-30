package canberra.edu.au;

import java.util.Arrays;

public class Main {

    //DEBUG
    public static boolean printOutput = false;
    public static boolean printBegins = false;
    public static boolean printSetBegins = false;
    public static boolean printInitialArrays = false;
    //-----

    public static int array_dimensions = 100000;
    public static int[] array_ascend  = new int[array_dimensions];
    public static int[] array_descend = new int[array_dimensions];
    public static int[] array_random  = new int[array_dimensions];
    public static int[] array_holder  = new int[array_dimensions]; //Algorithms ONLY sort this array, this is to avoid modifying the other 3 arrays

    public static void initializeArray(){
        System.out.println(">Array Dimensions     : " + array_dimensions);
        System.out.println(">Print array output   : " + printOutput);
        System.out.println(">Print array initials : " + printInitialArrays);
        System.out.println(">Print set begins     : " + printSetBegins);
        System.out.println(">Print array begins   : " + printBegins + "\n");

        //initialize array_ascend
        for (int i = 1; i != array_dimensions+1; i++){
            array_ascend[i-1] = i;
        }
        System.out.println(">array_ascend initialized");
        //initialize array_descend
        int temp = array_dimensions;
        for (int i = 1; i != array_dimensions+1; i++){
            array_descend[i-1] = temp;
            temp--;
        }
        System.out.println(">array_descend initialized");
        //initialize array_random
        for(int i = 0; i < array_random.length; i++){
            array_random[i] = array_ascend[i];
        }
        shuffleArray(array_random);
        System.out.println(">array_random initialized \n");

        //if printArrays
        if (printInitialArrays == true){
            System.out.println(">array_ascend  : " + (Arrays.toString(array_ascend)));
            System.out.println(">array_descend : " + (Arrays.toString(array_descend)));
            System.out.println(">array_random  : " + (Arrays.toString(array_random)) + "\n");
        }
    }

    //SORTING METHODS
    public static void selectionSort(int[] array){
        if (printBegins == true){ System.out.println("selectionSort BEGIN"); }
        int MinimumIndex;
        int pass, j, n = array.length;
        int temp;
        for (pass = 0; pass < n - 1; pass++) {
            MinimumIndex = pass;
            for (j = pass + 1; j < n; j++) {
                if (array[j] < array[MinimumIndex])
                    MinimumIndex = j;
            }
            if (MinimumIndex != pass) {
                temp = array[pass];
                array[pass] = array[MinimumIndex];
                array[MinimumIndex] = temp;
            }
        }
        if (printOutput == true){
            System.out.println("Output: " + (Arrays.toString(array)) + "\n");
        }
    }
    public static void insertionSort(int[] array){
        if (printBegins == true){ System.out.println("insertionSort BEGIN"); }
        int i, j, n = array.length;
        int target;
        for (i = 1; i < n; i++) {
            j = i;
            target = array[i];
            while (j > 0 && target < array[j - 1]) {
                array[j] = array[j - 1];
                j--;
            }
            array[j] = target;
        }
        if (printOutput == true){
            System.out.println("Output: " + (Arrays.toString(array)) + "\n");
        }
    }
    public static void bubbleSort(int[] array){
        if (printBegins == true){ System.out.println("bubbleSort BEGIN"); }
        int j;
        boolean flag = true;
        int temp;
        while (flag) {
            flag = false;
            for(j = 0;  j < array.length - 1;  j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    flag = true;
                }
            }
        }
        if (printOutput == true){
            System.out.println("Output: " + (Arrays.toString(array)) + "\n");
        }
    }
    private static void quickSort (int[] array, int fromIndex, int toIndex) {
        int i = fromIndex;
        int j = toIndex;
        int pivot = array[fromIndex + (toIndex - fromIndex) / 2];
        while (i <= j) {
            while (array[i] < pivot) { i++; }
            while (array[j] > pivot) { j--; }
            if (i <= j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }
        if (fromIndex < j)
            quickSort(array, fromIndex, j);
        if (i < toIndex)
            quickSort(array, i, toIndex);
    }
    public static void mergeSort(int[] array, int fromIndex, int toIndex){
        if (fromIndex < toIndex) {
            int middleIndex = fromIndex + (toIndex - fromIndex) / 2;
            // Sort the left side of the array
            mergeSort(array, fromIndex, middleIndex);
            // Sort the right side of the array
            mergeSort(array, middleIndex + 1, toIndex);
            // Merge both sides
            mergeSides(array, fromIndex, middleIndex, toIndex);
        }
    }
    private static void mergeSides (int[] array, int fromIndex, int middleIndex, int toIndex) {
        int[] tmp = new int[array.length];
        for (int i = fromIndex; i <= toIndex; i++) {
            tmp[i] = array[i];
        }
        int i = fromIndex;
        int j = middleIndex + 1;
        int k = fromIndex;
        while (i <= middleIndex && j <= toIndex) {
            if (tmp[i] <= tmp[j]) {
                array[k] = tmp[i];
                i++;
            } else {
                array[k] = tmp[j];
                j++;
            }
            k++;
        }
        while (i <= middleIndex) {
            array[k] = tmp[i];
            k++;
            i++;
        }
    }
    public static void countingSort(int[] array){
        if (printBegins == true){ System.out.println("countingSort BEGIN"); }
        int[] countingArray = new int[array.length];
        int[] sortedArray = new int[array.length];
        for(int arrayIndex = 0; arrayIndex < (array.length); arrayIndex++){
            countingArray[array[arrayIndex]-1]++; //increments counting array index for what the incoming array value is
        }
        for(int pass = 0; pass < (array.length); pass++){
            if (countingArray[pass] != 0){
                sortedArray[pass] = pass+1;
            }
        }
        copyArray(sortedArray, array);
        if (printOutput == true){
            System.out.println("Output: " + (Arrays.toString(array)) + "\n");
        }
    }
    public static void cocktailShaker(int[] array){
        //Based off http://www.javacodex.com/Sorting/Cocktail-Sort
        if (printBegins == true){ System.out.println("extraSort BEGIN"); }
        boolean swapped;
        do {
            swapped = false;
            for (int i =0; i<=  array.length  - 2;i++) {
                if (array[ i ] > array[ i + 1 ]) {
                    //test whether the two elements are in the wrong order
                    int temp = array[i];
                    array[i] = array[i+1];
                    array[i+1]=temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                //we can exit the outer loop here if no swaps occurred.
                break;
            }
            swapped = false;
            for (int i= array.length - 2;i>=0;i--) {
                if (array[ i ] > array[ i + 1 ]) {
                    int temp = array[i];
                    array[i] = array[i+1];
                    array[i+1]=temp;
                    swapped = true;
                }
            }
            //if no elements have been swapped, then the list is sorted
        } while (swapped);
        if (printOutput == true){
            System.out.println("Output: " + (Arrays.toString(array)) + "\n");
        }
    }

    //UTILITIES
    public static boolean searchArray(int[] array, int key){ //Search in array for key
        for (int i = 0; i < array.length; i++){
            if (array[i] == key){
                return true;
            }
        }
        return false;
    }
    public static void shuffleArray(int[] array) { //Shuffle array
        int n = array.length;
        for (int i = 0; i < array.length; i++) {
            // Get a random index of the array past i.
            int random = i + (int) (Math.random() * (n - i));
            // Swap the random element with the present element.
            int randomElement = array[random];
            array[random] = array[i];
            array[i] = randomElement;
        }
    }
    public static void copyArray(int[] masterArray, int[] newArray){
        for (int i = 0; i < masterArray.length && i < newArray.length; i++){
            newArray[i] = masterArray[i];
        }
    }
    public static void quickSortAssist(int[] array, int fromIndex, int toIndex){ //debugging logs were causing issues, so I had to make a assistant method
        if (printBegins == true){ System.out.println("quickSort BEGIN"); }
        quickSort(array, fromIndex, toIndex);
        if (printOutput == true){
            System.out.println("Output: " + (Arrays.toString(array_holder)) + "\n");
        }
    }
    public static void mergeSortAssist(int[] array, int fromIndex, int toIndex){
        if (printBegins == true){ System.out.println("mergeSort BEGIN"); }
        mergeSort(array, fromIndex, toIndex);
        if (printOutput == true){
            System.out.println("Output: " + (Arrays.toString(array)) + "\n");
        }
    }
    public static float timerProduce(long timerStart, long timerEnd){
        float out = (timerEnd - timerStart);
        return (float) (out * 0.001);
    }

    //SORTING CONTROL

    public static void sortAscendingArray(){
        if (printSetBegins == true){ System.out.println("\n>Sorting array_ascend\n"); }
        long timerStart;
        long timerEnd;

        System.out.print("Ascending set - Selecetion Sort: ");
        timerStart = System.currentTimeMillis();
        copyArray(array_ascend, array_holder);
        selectionSort(array_holder);
        timerEnd = System.currentTimeMillis();
        System.out.println(timerProduce(timerStart, timerEnd) + " seconds.");

        System.out.print("Ascending set - Insertion Sort: ");
        timerStart = System.currentTimeMillis();
        copyArray(array_ascend, array_holder);
        insertionSort(array_holder);
        timerEnd = System.currentTimeMillis();
        System.out.println(timerProduce(timerStart, timerEnd) + " seconds.");

        System.out.print("Ascending set - Bubble Sort: ");
        timerStart = System.currentTimeMillis();
        copyArray(array_ascend, array_holder);
        bubbleSort(array_holder);
        timerEnd = System.currentTimeMillis();
        System.out.println(timerProduce(timerStart, timerEnd) + " seconds.");

        System.out.print("Ascending set - Quick Sort: ");
        timerStart = System.currentTimeMillis();
        copyArray(array_ascend, array_holder);
        quickSortAssist(array_holder, 0, (array_dimensions-1));
        timerEnd = System.currentTimeMillis();
        System.out.println(timerProduce(timerStart, timerEnd) + " seconds.");

        System.out.print("Ascending set - Merge Sort: ");
        timerStart = System.currentTimeMillis();
        copyArray(array_ascend, array_holder);
        mergeSortAssist(array_holder, 0, (array_dimensions-1));
        timerEnd = System.currentTimeMillis();
        System.out.println(timerProduce(timerStart, timerEnd) + " seconds.");

        System.out.print("Ascending set - Counting Sort: ");
        timerStart = System.currentTimeMillis();
        copyArray(array_ascend, array_holder);
        countingSort(array_holder);
        timerEnd = System.currentTimeMillis();
        System.out.println(timerProduce(timerStart, timerEnd) + " seconds.");

        System.out.print("Ascending set - Cocktail Sort: ");
        timerStart = System.currentTimeMillis();
        copyArray(array_ascend, array_holder);
        cocktailShaker(array_holder);
        timerEnd = System.currentTimeMillis();
        System.out.println(timerProduce(timerStart, timerEnd) + " seconds. \n");
    }
    public static void sortDescendingArray(){
        if (printSetBegins == true){ System.out.println("\n>Sorting array_descend\n"); }
        long timerStart;
        long timerEnd;

        System.out.print("Descending set - Selection Sort: ");
        timerStart = System.currentTimeMillis();
        copyArray(array_descend, array_holder);
        selectionSort(array_holder);
        timerEnd = System.currentTimeMillis();
        System.out.println(timerProduce(timerStart, timerEnd) + " seconds.");

        System.out.print("Descending set - Insertion Sort: ");
        timerStart = System.currentTimeMillis();
        copyArray(array_descend, array_holder);
        insertionSort(array_holder);
        timerEnd = System.currentTimeMillis();
        System.out.println(timerProduce(timerStart, timerEnd) + " seconds.");

        System.out.print("Descending set - Bubble Sort: ");
        timerStart = System.currentTimeMillis();
        copyArray(array_descend, array_holder);
        bubbleSort(array_holder);
        timerEnd = System.currentTimeMillis();
        System.out.println(timerProduce(timerStart, timerEnd) + " seconds.");

        System.out.print("Descending set - Quick Sort: ");
        timerStart = System.currentTimeMillis();
        copyArray(array_descend, array_holder);
        quickSortAssist(array_holder, 0, (array_dimensions-1));
        timerEnd = System.currentTimeMillis();
        System.out.println(timerProduce(timerStart, timerEnd) + " seconds.");

        System.out.print("Descending set - Merge Sort: ");
        timerStart = System.currentTimeMillis();
        copyArray(array_descend, array_holder);
        mergeSortAssist(array_holder, 0, (array_dimensions-1));
        timerEnd = System.currentTimeMillis();
        System.out.println(timerProduce(timerStart, timerEnd) + " seconds.");

        System.out.print("Descending set - Counting Sort: ");
        timerStart = System.currentTimeMillis();
        copyArray(array_descend, array_holder);
        countingSort(array_holder);
        timerEnd = System.currentTimeMillis();
        System.out.println(timerProduce(timerStart, timerEnd) + " seconds.");

        System.out.print("Descending sett - Cocktail Shaker Sort: ");
        timerStart = System.currentTimeMillis();
        copyArray(array_descend, array_holder);
        cocktailShaker(array_holder);
        timerEnd = System.currentTimeMillis();
        System.out.println(timerProduce(timerStart, timerEnd) + " seconds. \n");
    }
    public static void sortRandomArray(){
        if (printSetBegins == true){ System.out.println("\n>Sorting array_random\n"); }
        long timerStart;
        long timerEnd;

        System.out.print("Random set - Selection Sort: ");
        timerStart = System.currentTimeMillis();
        copyArray(array_random, array_holder);
        selectionSort(array_holder);
        timerEnd = System.currentTimeMillis();
        System.out.println(timerProduce(timerStart, timerEnd) + " seconds.");

        System.out.print("Random set - Insertion Sort: ");
        timerStart = System.currentTimeMillis();
        copyArray(array_random, array_holder);
        insertionSort(array_holder);
        timerEnd = System.currentTimeMillis();
        System.out.println(timerProduce(timerStart, timerEnd) + " seconds.");

        System.out.print("Random set - Bubble Sort: ");
        timerStart = System.currentTimeMillis();
        copyArray(array_random, array_holder);
        bubbleSort(array_holder);
        timerEnd = System.currentTimeMillis();
        System.out.println(timerProduce(timerStart, timerEnd) + " seconds.");

        System.out.print("Random set - Quick Sort: ");
        timerStart = System.currentTimeMillis();
        copyArray(array_random, array_holder);
        quickSortAssist(array_holder, 0, (array_dimensions-1));
        timerEnd = System.currentTimeMillis();
        System.out.println(timerProduce(timerStart, timerEnd) + " seconds.");

        System.out.print("Random set - Merge Sort: ");
        timerStart = System.currentTimeMillis();
        copyArray(array_random, array_holder);
        mergeSortAssist(array_holder, 0, (array_dimensions-1));
        timerEnd = System.currentTimeMillis();
        System.out.println(timerProduce(timerStart, timerEnd) + " seconds.");

        System.out.print("Random set - Counting Sort: ");
        timerStart = System.currentTimeMillis();
        copyArray(array_random, array_holder);
        countingSort(array_holder);
        timerEnd = System.currentTimeMillis();
        System.out.println(timerProduce(timerStart, timerEnd) + " seconds.");

        System.out.print("Random set - Cocktail Shaker: ");
        timerStart = System.currentTimeMillis();
        copyArray(array_random, array_holder);
        cocktailShaker(array_holder);
        timerEnd = System.currentTimeMillis();
        System.out.println(timerProduce(timerStart, timerEnd) + " seconds. \n");
    }

    public static void main(String[] args){
        System.out.println("***PROGRAM START*** \n");
        initializeArray();

        sortAscendingArray();
        sortDescendingArray();
        sortRandomArray();

        System.out.println("***PROGRAM END***");
    }
}
