class Solution {
    public int PG_68935(int n) {
        return Integer.parseInt(new StringBuilder(Integer.toString(n, 3)).reverse().toString(),3);
    }
}