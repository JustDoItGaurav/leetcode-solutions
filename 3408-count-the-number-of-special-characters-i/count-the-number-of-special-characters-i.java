class Solution {
    public int numberOfSpecialChars(String word) {
        HashSet<Character> setUpper=new HashSet<>();
        HashSet<Character> setLower=new HashSet<>();
        for(int i=0;i<word.length();i++){
            if(Character.isLowerCase(word.charAt(i))){
                setLower.add(word.charAt(i));
            }
        }
        for(int i=0;i<word.length();i++){
            if(Character.isUpperCase(word.charAt(i))){
                setUpper.add(word.charAt(i));
            }
        }
        int result=0;
        for(char x:setLower){
            if(setUpper.contains(Character.toUpperCase(x))){
                result++;
            }
        }
        return result;
    }
}