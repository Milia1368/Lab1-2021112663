package test1;



import java.util.Arrays;
import java.util.List;

public class tt {
    public List<String> extractWords(String text) {
        //空格、制表符、换行符、回车符、换页符、以及标点符号作为分隔符
        String[] words = text.split("[ \\t\\n\\x0B\\f\\r\\p{P}]+");
        return Arrays.asList(words);
    }
}