# 📦 기능 개발 (PG - 42586)

> 프로그래머스 [기능 개발](https://school.programmers.co.kr/learn/courses/30/lessons/42586) 문제 풀이  
> ✅ `solution2`: 일반 반복문 기반 풀이  
> ✅ `solution`: Stack을 활용한 역순 처리 방식

---

## 📌 문제 요약

- 각 기능의 개발 진행률(`progresses[]`)과 개발 속도(`speeds[]`)가 주어질 때,
- 각 기능이 완료되는 날 기준으로 **몇 개의 기능이 함께 배포되는지**를 순서대로 구해야 한다.
- **앞선 기능이 완료되어야 다음 기능도 함께 배포할 수 있음.**

---

## 🧠 풀이 전략

### ✅ 공통 아이디어

- 각 기능마다 `잔여일 = Math.ceil((100 - progress) / speed)`로 계산
- 배포 기준일(`max`)을 기준으로 다음 기능이 `<= max` 이면 함께 배포
- 그렇지 않으면 새로운 배포 단위로 구분하여 리스트에 담음
- 마지막 배포는 루프 밖에서 따로 처리

---

## 🔎 코드 설명

### 1. `solution2` – 단순 루프 기반

- `progresses[]`를 앞에서부터 순회
- `count`, `max`를 활용해 배포 묶음을 구성
- 코드가 **직관적**이고, 추가 자료구조 없이 해결

```java
int max = 잔여일(progresses[0], speeds[0]);
for (i = 1 ~ N) {
    int day = 잔여일(progresses[i], speeds[i]);
    if (day <= max) count++;
    else {
        answer.add(count);
        count = 1;
        max = day;
    }
}
```

### 2. `solution2` – Stack을 활용한 역순 처리

- 뒤에서부터 Stack에 잔여일을 쌓고 pop하면서 순회
- 개념적으로는 동일하지만 역순 처리 + Stack 활용으로 변형된 방식
- Stack 연습용 풀이 또는 후입선출 방식의 문제와 유사한 구조에 적용 가능

```java
Stack<Integer> stack = 잔여일을 역순으로 push
while (!stack.empty()) {
int day = stack.pop();
    if (day <= max) count++;
        else {
        answer.add(count);
count = 1;
max = day;
    }
}
```

### 📈 출력 예시
```aiignore
입력: progresses = [93, 30, 55], speeds = [1, 30, 5]
출력: [2, 1]

입력: progresses = [95, 90, 99, 99, 80, 99], speeds = [1, 1, 1, 1, 1, 1]
출력: [1, 3, 2]
```

### ✨ 주요 포인트
- Math.ceil 사용 시 100.0을 곱해 정확한 실수 연산으로 잔여일 계산
- Integer.valueOf(count) → List<Integer> 를 int[]로 변환 시 stream().mapToInt() 사용
- Stack 풀이와 루프 풀이 모두 활용 가능하나, 면접에서는 간결한 루프 방식이 유리
- solution2와 solution은 로직은 유사하지만 자료구조 선택의 차이가 있음
