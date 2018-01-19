package com.scl.bit;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: shengchenglong
 * @Date: 2018/1/17 16:03
 */
public class BinaryUtil {

    public static void main(String[] args) throws UnsupportedEncodingException {
        int num = 19;
        String binaryString = Integer.toBinaryString(num);
        System.out.println(binaryString);
        for (int i = binaryString.getBytes().length - 1; i >= 0; i--) {
            System.out.print("index: " + i + " - " + getIndex(num, i) + "\t");
        }
        System.out.println();
        System.out.println("arr: " + Arrays.toString(getArray(num)));

        List list = new ArrayList();
        list.add(Integer.parseInt("1", 2));
        list.add(Integer.parseInt("10", 2));
        list.add(Integer.parseInt("100", 2));
        list.add(Integer.parseInt("1000", 2));
        list.add(Integer.parseInt("10000", 2));
        list.add(Integer.parseInt("100000", 2));
        list.add(Integer.parseInt("1000000", 2));
        list.add(Integer.parseInt("10000000", 2));
        list.add(Integer.parseInt("100000000", 2));
        list.add(Integer.parseInt("1000000000", 2));
        list.add(Integer.parseInt("10000000000", 2));
        list.add(Integer.parseInt("100000000000", 2));
        list.add(Integer.parseInt("1000000000000", 2));
        list.add(Integer.parseInt("10000000000000", 2));
        list.add(Integer.parseInt("100000000000000", 2));
        list.add(Integer.parseInt("1000000000000000", 2));
        list.add(Integer.parseInt("10000000000000000", 2));
        list.add(Integer.parseInt("100000000000000000", 2));
        list.add(Integer.parseInt("1000000000000000000", 2));
        list.add(Integer.parseInt("10000000000000000000", 2));
        System.out.println(list.toString());

        System.out.println(compare(14, 2));
        System.out.println(Arrays.toString(compareAll(14, 2)));
    }

    /**
     * 比较 目标进度值 和 当前进度值，并返回当前未完成进度节点
     * 注意：从低位开始比较
     *
     * @param dest 目标进度值
     * @param now  当前进度值
     * @return 当前未完成进度节点，返回 -1 表示任务进度已经完成
     */
    public static int compare(int dest, int now) {
        if (dest == now) {
            return -1;
        }
        int[] destArr = getArray(dest);
        int[] nowArr = getArray(now);
        for (int i = 0; i < destArr.length; i++) {
            try {
                if (destArr[i] != nowArr[i]) {
                    return i;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 比较 目标进度值 和 当前进度值，并返回当前未完成的所有进度节点
     *
     * @param dest 目标进度值
     * @param now  当前进度值
     * @return 当前未完成进度节点，返回 null 表示任务进度已经完成
     */
    public static int[] compareAll(int dest, int now) {
        if (dest == now) {
            return null;
        }
        int[] destArr = getArray(dest);
        int[] nowArr = getArray(now);
        List<Integer> progressIndex = new ArrayList<Integer>();
        for (int i = 0; i < destArr.length; i++) {
            try {
                if (destArr[i] != nowArr[i]) {
                    progressIndex.add(i);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                progressIndex.add(i);
            }
        }
        int[] progressArr = new int[progressIndex.size()];
        for (int i = 0; i < progressArr.length; i++) {
            progressArr[i] = progressIndex.get(i);
        }
        return progressArr;
    }

    /**
     * 获取二进制指定位数的值
     *
     * @param num   目标数，十进制
     * @param index 倒数第一位为0（0 <= index <= Integer.toBinaryString(num).getBytes().length - 1）
     * @return 0 或 1
     */
    public static int getIndex(int num, int index) {
        return (num & (0x1 << index)) >> index;
    }

    /**
     * 获取一个十进制数的 二进制数 并拆成 整形数组返回
     * 注意：返回数组顺序从二进制数低位开始
     *
     * @param num 目标数，十进制
     * @return example: num=19, binaryString=10011, return={1, 1, 0, 0, 1}
     */
    public static int[] getArray(int num) {
        int len = Integer.toBinaryString(num).getBytes().length;
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (num & (0x1 << i)) >> i;
        }
        return arr;
    }

}
