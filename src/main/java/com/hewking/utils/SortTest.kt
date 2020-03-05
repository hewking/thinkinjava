package com.opechain.wallet


/**
 * 类的描述：
 * 创建人员：hewking
 * 创建时间：2018/5/12
 * 修改人员：hewking
 * 修改时间：2018/5/12
 * 修改备注：
 * Version: 1.0.0
 */
object SortTest {

    @JvmStatic
    fun main(args: Array<String>) {
        sortTest()
    }

    fun sortTest() {

        val array = arrayOf(23, 12, 65, 76, 34, 43, 8, 22, 76, 23)
        quickSort(array, 0, array.size - 1)
        for (a in array) {
            System.out.println(a)
        }
    }

    fun quickSort(array: Array<Int>, start: Int, end: Int) {
        if (start < 0) {
            return
        }
        if (start > end) {
            return
        }

        val partion = partion(array, start, end)
        quickSort(array, start, partion - 1)
        quickSort(array, partion + 1, end)
    }

    /**
     * 找出一个数组种 比指定第一个数 比它小的全在左边，比它大的全在右边的 位置
     */
    private fun partion(array: Array<Int>, start: Int, end: Int): Int {
        var target = array[start]
        var left = start
        var right = end

        while (left < right) {
            // 找到第一个比它大的 所以大于等于
            while (left < right && target <= array[right]) {
                right--
            }
            array[left] = array[right]
            while (left < right && target >= array[left]) {
                left++
            }
            array[right] = array[left]
        }
        array[left] = target
        return left
    }


}