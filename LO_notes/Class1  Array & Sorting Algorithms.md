Selection sort:

Given an array of integers, sort the elements in the array in ascending order. The selection sort algorithm should be used to solve this problem.

Examples

* {1} is sorted to {1}
* {1, 2, 3} is sorted to {1, 2, 3}
* {3, 2, 1} is sorted to {1, 2, 3}
* {4, 2, -3, 6, 1} is sorted to {-3, 1, 2, 4, 6}

Corner Cases

* What if the given array is null? In this case, we do not need to do anything.
* What if the given array is of length zero? In this case, we do not need to do anything.

```java
public class SelectionSort {
    public int[] selectionSort(int[] array) {
        // check null before any other things
        // check other conditions -empty array etc
        if (array == null || array.length <= 1) {
            return array;
        }
        for (int i = 0; i < array.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] < array[min]) {
                    min = j;
                }
            }
            swap(array, i, min);
        }
        return array;
    }
    public void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }
}
```

Merge Sort:

Given an array of integers, sort the elements in the array in ascending order. The merge sort algorithm should be used to solve this problem.

Examples

* {1} is sorted to {1}
* {1, 2, 3} is sorted to {1, 2, 3}
* {3, 2, 1} is sorted to {1, 2, 3}
* {4, 2, -3, 6, 1} is sorted to {-3, 1, 2, 4, 6}

Corner Cases

* What if the given array is null? In this case, we do not need to do anything.
* What if the given array is of length zero? In this case, we do not need to do anything.

```java
public class MergeSort {
    public int[] mergeSort(int[] array) {
        // check null array first
        if (array == null) {
            return array;
        }
        // allocate helper array to help merge step, so that
        // we guarantee no more than O(n) space is used
        int[] helper = new int[array.length];
        mergeSort(array, helper, 0, array.length - 1);
        return array;
    }
    private void mergeSort(int[] array, int[] helper, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(array, helper, left, mid);
        mergeSort(array, helper, mid + 1, right);
        merge(array, helper, left, mid, right);
    }
    private void merge(int[] array, int[] helper, int left, int mid, int right) {
        // copy the content to helper array and we will merge from the helper array
        for (int i = left; i <= right; i++) {
            helper[i] = array[i];
        }
        int leftPointer = left;
        int rightPointer = mid + 1;
        while (leftPointer <= mid && rightPointer <= right) {
            if (helper[leftPointer] <= helper[rightPointer]) {
                array[left++] = helper[leftPointer++];
            } else {
                array[left++] = helper[rightPointer++];
            }
        }
        // if we still have some elements at left side, we need to copy them
        while (leftPointer <= mid) {
            array[left++] = helper[leftPointer++];
        }
        // if there are some elements at right side, we do not need to copy them
        // because they are already in their position
    }
}
```

Quick Sort:

Given an array of integers, sort the elements in the array in ascending order. The quick sort algorithm should be used to solve this problem.

Examples

* {1} is sorted to {1}
* {1, 2, 3} is sorted to {1, 2, 3}
* {3, 2, 1} is sorted to {1, 2, 3}
* {4, 2, -3, 6, 1} is sorted to {-3, 1, 2, 4, 6}

Corner Cases

* What if the given array is null? In this case, we do not need to do anything.
* What if the given array is of length zero? In this case, we do not need to do anything.

```java
public class QuickSort {
    public int[] quickSort(int[] array) {
        // check null first
        if (array == null) {
            return array;
        }
        quickSort(array, 0, array.length - 1);
        return array;
    }
    private void quickSort(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        // define a pivot and use the pivot to partition the array.
        int pivotPos = partition(array, left, right);
        // pivot is already at its position, when we do the recursive call on
        // the two partitions, pivot should not be included in any of them.
        quickSort(array, left, pivotPos - 1);
        quickSort(array, pivotPos + 1, right);
    }
    private int partition(int[] array, int left, int right) {
        int pivotIndex = pivotIndex(left, right);
        int pivot = array[pivotIndex];
        swap(array, pivotIndex, right);
        int leftBound = left;
        int rightBound = right - 1;
        while (leftBound <= rightBound) {
            if (array[leftBound] < pivot) {
                leftBound++;
            } else if (array[rightBound] >= pivot) {
                rightBound++;
            } else {
                swap(array, leftBound++, rightBound++);
            }
        }
        // swap back the pivot element
        swap(array, leftBound, right);
        return leftBound;
    }
    private int pivotIndex(int left, int right) {
        return left + (int) (Math.random() * (right - left + 1));
    }
    private void swap(int[] array, int one, int two) {
        int tmp = array[one];
        array[one] = array[two];
        array[two] = tmp;
    }
}
```

Rainbow Sort:

Given an array of balls, where the color of the balls can only be Red, Green or Blue, sort the balls such that all the Red balls are grouped on the left side, all the Green balls are grouped in the middle and all the Blue balls are grouped on the right side. (Red is denoted by -1, Green is denoted by 0, and Blue is denoted by 1).

Examples

* {0} is sorted to {0}
* {1, 0} is sorted to {0, 1}
* {1, 0, 1, -1, 0} is sorted to {-1, 0, 0, 1, 1}

Assumptions

* The input array is not null.

Corner Cases

* What if the input array is of length zero? In this case, we should not do anything as well.

```java
/**
 * Assumption:
 * we have three colors denoted as -1, 0, 1 and all the elements in the array are valid.
 */
 public class RainbowSort {
     public int[] rainbowSort(int[] array) {
         if (array == null || array.length <= 1) {
             return array;
         }
         // three bounds:
         // 1. the left side of neg is -1 (exclusive of neg)
         // 2. the right side of one is 1 (exclusive of one)
         // 3. the part between neg and zero is 0 (exclusive of zero)
         // 4. between zero and one is to be discovered (inclusive of both)
         int neg = 0;
         int one = array.length - 1;
         int zero = 0;
         while (zero <= one) {
             if (array[zero] == -1) {
                 swap(array, neg++, zero++);
             } else if (array[zero] == 0) {
                 zero++;
             } else {
                 swap(array, zero++, one--);
             }
         }
     }
     private void swap(int[] array, int one, int two) {
        int tmp = array[one];
        array[one] = array[two];
        array[two] = tmp;
    }
 }
```

Move 0s to the end I :

Given an array of integers, move all the 0s to the right end of the array.

The relative order of the elements in the original array does not need to be maintained.

Assumptions:

* The given array is not null.

Examples:

* {1} --\> {1}
* {1, 0, 3, 0, 1} --\> {1, 3, 1, 0, 0} or {1, 1, 3, 0, 0} or {3, 1, 1, 0, 0}

```java
public class MoveZeroI {
    public int[] moveZero(int[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            if (array[left] != 0) {
                left++;
            } else if (array[right] == 0) {
                right--;
            } else {
                swap(array, left++, right--);
            }
        }
        return array;
    }
    private void swap(int[] array, int one, int two) {
        int tmp = array[one];
        array[one] = array[two];
        array[two] = tmp;
    }
}
```

