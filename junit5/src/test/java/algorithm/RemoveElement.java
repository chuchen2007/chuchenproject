package algorithm;
/**
移除目标数组nums等于Val值的元素，返回新数组的长度
 **/
public class RemoveElement {
    public int removeElement(int [] nums,int val){
        if (nums == null ||nums.length ==0) {
            return 0;
        }
        int j = 0;
        for (int i = 0; i <nums.length ; i++) {
            if (nums[i] != val){
                nums[j++] = nums[i];
            }
        }
        return j;
        }
    public static void main(String [] args){
        int [] num = {2,3,4,5,6,3,1,2,3,4,3};
        int vals = 3;
        RemoveElement removeElement = new RemoveElement();
        System.out.println(removeElement.removeElement(num,vals));

    }
}
