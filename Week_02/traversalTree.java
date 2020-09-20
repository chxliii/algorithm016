import java.util.*;

class TreeNode {
    int val;

    TreeNode left;

    TreeNode right;

    public TreeNode (int val) {
        this.val = val;

        left = null;

        right = null;
    }

}

public class traversalTree {

    static List<Integer> ans = new LinkedList<>();

    static Deque<TreeNode> deque = new ArrayDeque<>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String s = input.nextLine();

        String[] ss = s.split(" ");

        int[] nums = new int[ss.length];

        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.valueOf(ss[i]);
        }

        TreeNode root = createBT(nums, 0);

        System.out.println("先序遍历.");

        preOrder(root);

        System.out.println();

        System.out.println("中序遍历.");

        inOrder(root);

        System.out.println();

        System.out.println("后序遍历.");

        postOrder(root);

        System.out.println();




        System.out.println("此处进行迭代方式的中序遍历.");

        for (Integer i : inOrderIterations(root)) {
            System.out.print(i + " ");
        }

        ans.clear();

        System.out.println();

        System.out.println("此处进行迭代方式的前序遍历");

        for (Integer i : preOrderIterations(root)) {
            System.out.print(i + " ");
        }

        ans.clear();

        System.out.println();

        System.out.println("此处进行迭代方式的后续遍历");

        for (Integer i : postOrderIterations(root)) {
            System.out.print(i +  " ");
        }
    }


    public static TreeNode createBT(int[] nums, int i) {
        TreeNode root = null;

        if (i >= nums.length) {
            return null;
        }

        root = new TreeNode(nums[i]);

        root.left = createBT(nums, 2 * i + 1);
        root.right = createBT(nums, 2 * i + 2);

        return root;
    }


    public static void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }

        System.out.print(root.val + " ");
        preOrder(root.left);

        preOrder(root.right);

    }


    public static void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }

        inOrder(root.left);

        System.out.print(root.val + " ");

        inOrder(root.right);
    }


    public static void postOrder(TreeNode root) {
        if (root == null) {
            return;
        }

        postOrder(root.left);

        postOrder(root.right);

        System.out.print(root.val + " ");
    }

    public static List<Integer> inOrderIterations(TreeNode root){
        //迭代方式进行中序遍历：
        //中序遍历时：左子树，根, 右子树


        while (root != null || !deque.isEmpty()) {
            //栈先进后出，进去的时候根左，出来的时候左，根..
            while (root != null) {
                deque.addFirst(root);
                root = root.left;
            }

            root = deque.removeFirst();

            //访问中间节点
            ans.add(root.val);

            root = root.right;
        }

        return ans;
    }


    public static List<Integer> preOrderIterations(TreeNode root){

        if (root == null) {
            return ans;
        }
        //迭代方式进行先序遍历
        //先序遍历：根，左，右
        //可以考虑使用栈进行根，右，左，此时出栈的时候可以满足根，左，右

        deque.addFirst(root);
        while (!deque.isEmpty()) {
            TreeNode tmp = deque.removeFirst();

            ans.add(tmp.val);

            if (tmp.right != null) {
                deque.addFirst(tmp.right);
            }

            if (tmp.left != null) {
                deque.addFirst(tmp.left);
            }

        }

        return ans;
    }


    public static List<Integer> postOrderIterations(TreeNode root){
        //记住：逆后序遍历顺序是前序遍历进行左右子树交换的结果
        //然后再反转，就可以得到后序遍历的结果

        //根，右 。。左
        //右，根。。左
        //左，根。。右

        if (root == null) {
            return ans;
        }

        deque.addFirst(root);
        while (!deque.isEmpty()) {
            TreeNode tmp = deque.removeFirst();

            ans.add(tmp.val);

            if (tmp.left != null) {
                deque.addFirst(tmp.left);
            }

            if (tmp.right != null) {
                deque.addFirst(tmp.right);
            }
        }
        Collections.reverse(ans);

        return ans;
    }
}
