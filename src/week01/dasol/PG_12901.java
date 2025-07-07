class Solution {
    public String solution(int a, int b) {
        //a=1; b=1;
        String answer = "";
        int[] months = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        String[] weeks = {"FRI","SAT","SUN","MON","TUE","WED","THU"};
        int days = b-1;
        for (int i=1 ; i<a ; i++) {
            days+=months[i];
        }
        
        return weeks[days%7];
    }
}