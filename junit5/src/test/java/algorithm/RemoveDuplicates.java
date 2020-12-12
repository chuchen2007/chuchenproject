package algorithm;

/**
 * 移除目标数组的重复元素，返回新数组nums的长度
 */
public class RemoveDuplicates {
    public int removeDuplicates(int[] num) {
        if (num.length == 0) {
            return 0;
        }
        int i = 0;
        for (int j = 1; j <= num.length; j++) {
            if (num[i] != num[j]) {
                i++;
                num[i] = num[j];
            }
        }
        return i + 1;
    }
}
