import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//백준 - 오목
public class B2615 {
    static int[] dirR = { 0, 1, 1, 1 };
    static int[] dirC = { 1, -1, 0, 1 };
    static int[][] board = new int[21][21];
    static boolean[][] six = new boolean[21][21];
    static int winner = 0;
    static int winnerR = -1;
    static int winnerC = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 19; i++) {
            String[] arr = br.readLine().split(" ");
            for (int j = 0; j < arr.length; j++) {
                board[i + 1][j + 1] = Integer.parseInt(arr[j]);
            }
        }

        solution();
        System.out.println(winner);
        if (winner > 0)
            System.out.println(winnerR + " " + winnerC);
    }

    public static void solution() {
        for (int i = 1; i < 20; i++) {
            for (int j = 1; j < 20; j++) {
                if (board[i][j] != 0) {
                    dfs(i, j, board[i][j], 1, -1);
                    if (winner > 0) {
                        if (j <= winnerC) {
                            winnerR = i;
                            winnerC = j;
                        }
                        return;
                    }
                }
            }
        }
    }

    public static boolean isOverFive(int r, int c, int stone, int dir) {
        int nr = r + dirR[dir];
        int nc = c + dirC[dir];
        while (board[nr][nc] == stone) {
            six[nr][nc] = true;
            nr += dirR[dir];
            nc += dirC[dir];
        }

        if (six[r][c] || six[r + dirR[dir]][c + dirC[dir]]) {
            return true;
        }
        return false;
    }

    public static void dfs(int r, int c, int stone, int count, int dir) {
        if (count == 5 && !isOverFive(r, c, stone, dir)) {
            winner = stone;
            winnerR = r;
            winnerC = c;
            return;
        }

        if (dir == -1) {
            for (int i = 0; i < 4; i++) {
                int nr = r + dirR[i];
                int nc = c + dirC[i];
                if (board[nr][nc] == stone) {
                    dfs(nr, nc, stone, count + 1, i);
                }
            }
        } else {
            int nr = r + dirR[dir];
            int nc = c + dirC[dir];
            if (board[nr][nc] == stone) {
                dfs(nr, nc, stone, count + 1, dir);
            }
        }
    }

}
