import java.util.*;

public class top_k_frequent_ele {


    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        String s = input.nextLine();

        String[] ss = s.split(" ");

        int[] nums = new int[ss.length];


        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.valueOf(ss[i]);
        }
        int k = input.nextInt();

        topKFrequent(nums, k);
    }


    public static void topKFrequent(int[] nums, int k) {


        Map<Integer, Integer> map = new HashMap<>();


        //记录频数
        for (int i:
             nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        //定义大顶堆
        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap = new PriorityQueue<>((o1, o2) ->
                (o2.getValue() - o1.getValue()));

        for (Map.Entry<Integer, Integer> ele:
             map.entrySet()) {
            maxHeap.offer(ele);
        }

        List<Integer> ans = new ArrayList<>();

        while (ans.size() < k) {
            ans.add(maxHeap.poll().getKey());
        }

        System.out.println(ans);

    }

}
