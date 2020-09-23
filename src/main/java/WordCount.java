import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
public class WordCount {
    public static void main(String[] args) throws Exception {

        //Récupérer la configuration générale du cluster
        Configuration conf = new Configuration();

        //Créer un job
        Job job = Job.getInstance(conf, "word count");

        //Préciser quelles sont les classes Map et Reduce du programme
        job.setJarByClass(WordCount.class);
        job.setMapperClass(TokenizerMapper.class);
        job.setCombinerClass(IntSumReducer.class);
        job.setReducerClass(IntSumReducer.class);

        //Préciser les types de clés et de valeur correspondant à notre problème
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        //Indiquer où sont les données d'entrée et de sortie dans HDFS
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        //Lancer l'exécution de la tâche
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}