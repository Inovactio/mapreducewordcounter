import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TokenizerMapper
        extends Mapper<Object, Text, Text, IntWritable>{
    private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();
    public void map(Object key, Text value, Mapper.Context context
    ) throws IOException, InterruptedException {

        Pattern p=Pattern.compile("[\\p{L}]+");
        Matcher m=p.matcher(value.toString().toLowerCase());
        while (m.find()) {
            word.set(m.group());
            context.write(word, one);
        }
    }
}