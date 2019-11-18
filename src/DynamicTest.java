import java.util.Random;

/**
 * 动态规划求从n件商品中拼凑最接近MAX_Price的组合并输出
 */
public class DynamicTest {
    static int n = 10;//购物车内物品数量
    static int[] goodsValue = randomValue(n);//随机生成购物车清单
    static int MAX_Price = 5000;//可以清除购物车的总价格
    static int[][] dp = new int[n + 1][MAX_Price + 1];

    public static void main(String[] args) {
        print(goodsValue);//打印购物车清单
        System.out.println("#############");
        for (int j = 1; j <= n; j++) {
            for (int i = 1; i <= MAX_Price; i++) {
                if (i < goodsValue[j - 1]) {
                    dp[j][i] = dp[j - 1][i];
                } else {
                    int a = goodsValue[j - 1] + dp[j - 1][i - goodsValue[j - 1]];//选择该商品
                    int b = dp[j - 1][i];//不选择该商品
                    dp[j][i] = Math.max(a, b);
                }
            }
        }
        for (int j = 1; j <= n; j++) {
            for (int i = 1; i <= MAX_Price; i++) {
                System.out.print(dp[j][i]+"\t");
            }
            System.out.println();
        }
        System.out.println("最大可清除金额："+dp[n][MAX_Price]);//打印最终需消除的清单
        printTrace();//打印所选商品价格
    }

    static void printTrace() {
        int last = MAX_Price;
        int sum = 0;
        System.out.println("所选商品：");
        for (int i = n; i > 0; i--) {
            if (dp[i][last] != dp[i - 1][last]) {
                System.out.print(goodsValue[i - 1] + " \t");
                last -= goodsValue[i - 1];
                sum += goodsValue[i - 1];//验算
            }
        }
        System.out.println("\n验算：" + sum);
    }

    static void print(int[] arr) {
        System.out.println("以下是购物车清单");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " \t");
            if ((i + 1) % 5 == 0)
                System.out.println();
        }
    }

    static int[] randomValue(int n) {
        Random ran = new Random();
        int[] goodsValue = new int[n];
        for (int i = 0; i < n; i++) {
            goodsValue[i] = ran.nextInt(2000);
        }
        return goodsValue;
    }
}
