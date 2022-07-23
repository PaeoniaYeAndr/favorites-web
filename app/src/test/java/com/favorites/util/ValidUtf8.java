package com.favorites.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class  ValidUtf8{
  static int MASK1 = 1 << 7;
  static int MASK2 = (1 << 7) + (1 << 6);

  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      String line = br.readLine();
      while (line != null) {
        line = line.replace("[", "").replace("]", "");
        String[] arrys = line.split(",");
        int[] nums = new int[arrys.length];
        for (int i = 0; i <arrys.length; i++) {
          nums[i] = Integer.parseInt(arrys[i]);
        }

        boolean s1 = validUtf8(nums);
        System.out.println(s1);
        line = br.readLine();
      }
    }
  }

  public static boolean validUtf8(int[] data) {
    int n = data.length;
    // 数组遍历的索引
    int idx = 0;
    while (idx < n) {
      // 获取编码首字节
      int num = data[idx];
      // 获取编码长度
      int len = getLen(num);
      // 若长度不合格，直接返回 false
      if (len < 0 || len + idx > n) {
        return false;
      }
      // 长度大于 1，则对后续字节进行验证
      for (int i = idx + 1; i < idx + len; i++) {
        if (!isValid(data[i])) {
          return false;
        }
      }
      // 更新索引
      idx += len;
    }
    return true;
  }

  // 获取编码长度
  public static int getLen(int num) {
    // 与 MASK1 与操作结果为 0，表示长度为 1
    if ((num & MASK1) == 0) {
      return 1;
    }
    int n = 0;
    int mask = MASK1;
    // 获取高位连续的 1 的个数
    while ((num & mask) != 0) {
      n++;
      // 若长度大于 4，不合格
      if (n > 4) {
        return -1;
      }
      mask >>= 1;
    }
    // 若高位的 1 只有一个，不合格
    return n >= 2 ? n : -1;
  }

  // 验证长度大于 1 的编码后续字节是否合格
  public static boolean isValid(int num) {
    return (num & MASK2) == MASK1;
  }
}