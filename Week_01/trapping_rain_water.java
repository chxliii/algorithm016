import java.util.ArrayDeque;
import java.util.Deque;

public class trapping_rain_water {

    public static void main(String[] args) {
        int[] nums = new int[] {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};

        System.out.println(trapping(nums));
    }

    public static int trapping(int[] nums) {

        int ans = 0;
//        //法1：两重暴力，对每个位置，左右扫描其最大值，然后就可以知道当前位置 可以接的雨水
//
//        for (int i = 1; i < nums.length - 1; i++) {
//            int k = i, l = i;
//            int left_max = 0, right_max = 0;
//            while (k > 0){
//                left_max = Math.max(left_max, nums[k--]);
//            }
//
//            while (l < nums.length) {
//                right_max = Math.max(right_max, nums[l++]);
//            }
//
//            ans += Math.min(left_max, right_max) - nums[i];
//
//        }
//
//        return ans;


        //法2：由于该问题具备最近相关性的特征，因此可以采用单调栈的方法
        //基本思想大概是，如果入栈的元素每次大于栈顶元素，则可以进行出栈操作

//        int ans = 0;
//
        Deque<Integer> deque =  new ArrayDeque<>();

        for (int i = 0; i < nums.length; i++) {

            while (!deque.isEmpty() && nums[i] > nums[deque.peekFirst()]) {

                //Note: 此地方很重要，当遇到大于当前栈顶元素的索引时，需要把当前栈顶元素索引位置记录下来，然后才进行出栈操作。
                int topIndex = deque.peekFirst();//0, 0
                deque.removeFirst();//null, [1]

                if (deque.isEmpty()) {
                    break;
                }
                System.out.println("nums[deque.peekFirst()] :" + nums[deque.peekFirst()]);
                int boundedH = Math.min(nums[i], nums[deque.peekFirst()]) - nums[topIndex];
                int area = boundedH * (i - deque.peekFirst() - 1);

                System.out.println("新增面积:"+area);
                ans += area;
            }

            //1,0
            deque.addFirst(i);
        }
        return ans;


        //法3：双指针
        //思想：定义两个指针，在一层循环中扫描完所有的位置可以接的雨水，需要明确的是
        //由于左右指针分别从两侧扫描，对于从左扫描到右的当前位置left而言，它知道它左边的最大值，
        //右边的最大值一定是大于等于当前的right_max，这时候如果left_max < right_max
        //此时可以确定当前left位置的雨水量: 即left_max - height[left]
//        int i = 0, j = nums.length - 1;
//        int left_max = 0, right_max = 0;
//        int ans = 0;
//        while (i <= j) {
//            if (left_max < right_max) {
//                ans += Math.max(0, left_max - nums[i]);
//                left_max = Math.max(left_max, nums[i]);
//                i++;
//            } else {
//                ans += Math.max(0, right_max - nums[j]);
//                right_max = Math.max(right_max, nums[j]);
//                j--;
//            }
//        }
//        return ans;
    }
}
