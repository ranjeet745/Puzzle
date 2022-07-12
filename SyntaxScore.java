import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class SyntaxScore {

    static String syntax = "[({(<(())[]>[[{[]{<()<>>\n" +
	    "[(()[<>])]({[<{<<[]>>(\n" +
            "{([(<{}[<>[]}>{[]{[(<()>\n" +
            "(((({<>}<{<{<>}{[]{[]{}\n" +
            "[[<[([]))<([[{}[[()]]]\n" +
            "[{[{({}]{}}([{[{{{}}([]\n" +
            "{<[[]]>}<{[{[{[]{()[[[]\n" +
            "[<(<(<(<{}))><([]([]()\n" +
            "<{([([[(<>()){}]>(<<{{\n" +
            "<{([{{}}[<[[[<>{}]]]>[]]\n";

public static void main(String[] args) { 
   int points=0;
   Map<Character, Integer> scoreMap = new HashMap<>();
   scoreMap.put(')', 3);
   scoreMap.put(']', 57);
   scoreMap.put('}', 1197);
   scoreMap.put('>', 251371);


   Stack<Character> stack = new Stack<>();
   boolean isLineCorrupt = false;
   for(int i=0; i<syntax.length(); i++) {
       char c = syntax.charAt(i);
       if(c == '\n') {
           isLineCorrupt = false;
           stack.clear();
       } else {
           if(isLineCorrupt) {
               continue;
           }
           if(c == '[' || c == '<' || c == '{' || c == '(') {
               stack.push(c);
           } else {
               char lastChar = stack.pop();
               if ((lastChar == '[' && c != ']') 
               || (lastChar == '(' && c != ')')
               || (lastChar == '{' && c != '}') 
               || (lastChar == '<' && c != '>')) {
                   System.out.println("corrupted: "+ c);
                   points += scoreMap.get(c);
                   isLineCorrupt = true;
               }
            }
        }
     }

      System.out.println("Total Points: "+ points);
   }
}
