import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// https://school.programmers.co.kr/learn/courses/30/lessons/160586
// 프로그래머스 키패드 누르기

class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        Map<Character, Integer> map = new HashMap<>();
        
        for (int i = 0; i < keymap.length; i++) {
            for (int j = 0; j < keymap[i].length(); j++) {
                //최소 횟수 버튼값 추가
                Character button = keymap[i].charAt(j);
                int count = j + 1;
                map.merge(button, count, (v1, v2) -> Math.min(v1, v2));
            }
        }
        // System.out.println(map);
        
        return Arrays.stream(targets)
            .mapToInt(target -> targetToCount(map, target))
            .toArray();
    }
    
    private int targetToCount(Map<Character, Integer> map, String target) {
        int sum = 0;
        
        for (int i = 0; i < target.length(); i++) {
            char ch = target.charAt(i);
            if (!map.containsKey(ch)) {
                return -1;
            } else {
                sum += map.get(ch);
            }
        }
        
        return sum;
    }
}