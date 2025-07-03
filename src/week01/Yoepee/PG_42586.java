package week01.Yoepee;

import java.util.*;

public class PG_42586 {
    public int[] solution2(int[] progresses, int[] speeds) {
        // 만들어지는 해결되는 개발 갯수는 동적으로 결정되므로 List로 선언
        List<Integer> answer = new ArrayList<>();
        int count=1;
        int max = (int)Math.ceil((100.0-progresses[0])/speeds[0]);

        // 프로세스 진행률과 속도를 바탕으로 잔여일 배열 반환
        for(int i =1; i<progresses.length; i++){
            // 형 변환을 제대로 안해주면 값이 다르게 나옴
            int day= (int)Math.ceil((100.0-progresses[i])/speeds[i]);
            // 뒤에 있는 기능이 앞에 기능보다 먼저 개발 되는 경우
            if (day<=max) count++;
                // 뒤에 기능이 늦게 개발되는 경우
            else{
                answer.add(Integer.valueOf(count));
                count = 1;
                max = day;
            }
        }

        // 마지막 배포 경우 추가
        answer.add(Integer.valueOf(count));

        // Integer List 형식으로 되어 있는 answer을 int[]으로 변환
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    public static int[] solution(int[] progresses, int[] speeds) {
        // 만들어지는 해결되는 개발 갯수는 동적으로 결정되므로 List로 선언
        List<Integer> answer = new ArrayList<>();
        // 프로세스 해결 잔여일 배열
        Stack<Integer> stack = new Stack<>();

        // 프로세스 진행률과 속도를 바탕으로 잔여일 배열 반환
        for(int i =progresses.length-1; i>=0; i--){
            // 형 변환을 제대로 안해주면 값이 다르게 나옴
            int day= (int)Math.ceil((100.0-progresses[i])/speeds[i]);
            stack.push(Integer.valueOf(day));
        }

        // 프로세스가 완료되는 날짜에 종료되는 개발 갯수
        int count=1;
        int max = stack.pop();
        // 우선순위 높은 완료되는 날짜
        // 잔여일 배열을 순회하면서, 먼저 끝내야 하는 개발보다 잔여일이 적은 개발 카운트
        while(!stack.empty()){
            int day = stack.pop();

            // 뒤에 있는 기능이 앞에 기능보다 먼저 개발 되는 경우
            if (day<=max) count++;
            else{
                answer.add(Integer.valueOf(count));
                count = 1;
                max = day;
            }
        }

        // 마지막 배포 경우 추가
        answer.add(Integer.valueOf(count));

        // Integer List 형식으로 되어 있는 answer을 int[]으로 변환
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        // 정상 결과: [2, 1]
        System.out.println(Arrays.toString(solution(new int[]{93, 30, 55}, new int[]{1, 30, 5})));
        // 정상 결과: [1, 3, 2]
        System.out.println(Arrays.toString(solution(new int[]{95, 90, 99, 99, 80, 99}, new int[]{1, 1, 1, 1, 1, 1})));
        // 정상 결과: [1, 1]
        System.out.println(Arrays.toString(solution(new int[]{90, 90}, new int[]{10, 9})));
    }
}
