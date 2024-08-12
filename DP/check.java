//{ Driver Code Starts
// Initial Template for Java

import java.io.*;
import java.lang.*;
import java.util.*;

class GFG {
    public static void main(String args[]) throws IOException {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int N = sc.nextInt();
            int M = sc.nextInt();

            int[][] grid = new int[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    grid[i][j] = sc.nextInt();
                }
            }

            Solution obj = new Solution();
            long res = obj.solve(N, M, grid);
            System.out.println(res);
        }
    }
}
// } Driver Code Ends


// User function Template for Java

class Solution {
    public int solve(int n, int m, int grid[][]) {
        // When both robots stay in the same cell, only one takes the chocolates. yo case chai pass hudaina mero logic mah
        //f(i, j1, j2, n, m, grid)
        int[][][] dp = new int[n][m][m];
        for(int[][] row : dp) {
            for(int[] row2 : row) {
                Arrays.fill(row2, -1);
            }
        }
        return solve(0, 0, m-1, n, m, grid, dp);
    }
    
    public int solve(int i, int j1, int j2, int n, int m, int[][] grid, int[][][] dp) {
        if(j1<0 || j1>=m || j2<0 || j2>=m || i<0 || i>=n) {
            return (int)Math.pow(-10, 9);
        }
        
        if(i == n-1) {
            if(j1==j2) {
                return grid[i][j1];
            } else {
                return grid[i][j1] + grid[i][j2];
            }
        }
        
        if(dp[i][j1][j2] != -1) {
            return dp[i][j1][j2];
        }
        
        int ans = Integer.MIN_VALUE;
        
        for(int d1=-1; d1<=1; d1++) {
            for(int d2=-1; d2<=1; d2++) {
                int val = 0;
                if(j1 == j2) {
                    val = solve(i+1, j1+d1, j2+d2, n, m, grid, dp) + grid[i][j1];
                } else {
                    val = solve(i+1, j1+d1, j2+d2, n, m, grid, dp) + grid[i][j1] + grid[i][j2];
                }
                
                ans = Math.max(ans, val);
                dp[i][j1][j2] = ans;
            }
        }
        
        return dp[i][j1][j2];
    }
}