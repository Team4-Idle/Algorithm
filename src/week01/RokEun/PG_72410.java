/* 아이디 규칙
    1. 3~ 15
    2. 알파벳 소문자/숫자/-/_/.  만 사용가능
    3. . 는 처음과 끝에 사용할수 없으며 연속 사용 불가
    아이디 검사
    1. 대문자 -> 소문자 변환 2. 사용 가능한 문자 제외하고 나머지 문자 제거 -> 3. .가 연속인경우 하나로 변환 ->
    4. .가 처음이가 끝일 경우 제거 -> 5.빈 문자열 일경우 a로 대입 -> 
    6. 아이디 길이가 16자 이상이면 첫 15자 까지만 남김 -> 
    7. 2글자 이하일경우 마지막 문자를 3이 될떄까지 반복해서 3자릿수로 만듬
*/
class Solution {
    public String solution(String new_id) {
        String answer = "";
        answer = checkId(new_id);
        
        return answer;
    }
    
    private String firstStep(String new_id) {
        
        return new_id.toLowerCase();
    }
    
    private String secondStep(String new_id) {
        return new_id.replaceAll("[^[a-z0-9._-]]", "");
    }
    
    private String thirdStep(String new_id) {
        while(new_id.contains("..")) {
            new_id = new_id.replaceAll("\\.\\.", ".");
        }
        return new_id;
    }
    
    private String fourthStep(String new_id) {
        if(new_id.startsWith(".")) {
            new_id = new_id.substring(1);
        }
        
        if(new_id.endsWith(".")) {
           new_id =  new_id.substring(0, new_id.length()-1);
        }
        return new_id;
    }
    private String fifthStep(String new_id) {
        if(new_id.isEmpty()) {
            new_id = "aaa";
        }
        return new_id;
    }
    
    private String sixthStep(String new_id) {
        if(new_id.length() >= 16) {
            new_id = new_id.substring(0, 15);
            new_id = fourthStep(new_id);
        }
        return new_id;
    }
    
    private String seventhStep(String new_id) {
        for(; new_id.length() <= 2; ) {
            char c = new_id.charAt(new_id.length()-1);
            new_id = new_id + c;
        }
        return new_id;
    }
    
    private String checkId(String new_id) {
        new_id = firstStep(new_id);
        new_id = secondStep(new_id);
        new_id = thirdStep (new_id);
        new_id = fourthStep(new_id);
        new_id = fifthStep(new_id);
        new_id = sixthStep(new_id);
        new_id = seventhStep(new_id);
        return new_id;
    }
}
