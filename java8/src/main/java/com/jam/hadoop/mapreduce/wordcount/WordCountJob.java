package com.jam.hadoop.mapreduce.wordcount;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;

/**
 * @program: SpringCloudStudy
 * @description: 字数统计
 * @author: Mr.Pu
 * @create: 2022-06-01 22:24
 **/

//此处是使用官方的写法，并且只能打成jar包上传linux执行，命令为yarn jar jar包名 com.jam.hadoop.mapreduce.wordcount.WordCountJob
public class WordCountJob extends Configured implements Tool {

    public static void main(String[] args) throws Exception {
        //执行job作业任务类的对象是哪个
        ToolRunner.run(new WordCountJob(), args);
    }

    //执行job作业
    @Override
    public int run(String[] args) throws Exception {
        //设置job对象
        Job job = Job.getInstance(getConf());
        job.setJarByClass(WordCountJob.class);
        //1.设置inputFormat
        job.setInputFormatClass(TextInputFormat.class);
        //如果只写目录则会把目录下的所有文件都分别做计算
        TextInputFormat.addInputPath(job, new Path("/wordcount/word.log"));
        //2.设置map
        job.setMapperClass(WordCountMap.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        //3.设置shuffle 自行处理，无需代码
        //4.设置mapreduce
        job.setReducerClass(WordCountReduce.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        //5.设置outputFormat
        job.setOutputFormatClass(TextOutputFormat.class);
        //保证outputFormat输出结果目录不存在
        TextOutputFormat.setOutputPath(job, new Path("/wordcount/result/"));
        //6.提交job作业
        //job.submit();
        boolean status = job.waitForCompletion(true);
        System.out.println("status:" + status);
        return 0;
    }


    //map阶段，对于不同类型都做了包装
    //Long-->LongWritable Integer-->IntWritable  Double-->DoubleWritable  Float-->FloatWritable
    //String-->Text
    public static class WordCountMap extends Mapper<LongWritable, Text, Text, IntWritable> {
        //inputFormat输出一次调用一次这个方法
        //参数1：本地这行行首偏移量，参数2：当前这行的值
        @Override
        protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context) throws IOException, InterruptedException {
            //对value 的row进行分割
            String[] words = value.toString().split(" ");
            for (String word : words) {
                context.write(new Text(word), new IntWritable(1));
            }
        }
    }

    //reduce阶段，进行汇总计算
    public static class WordCountReduce extends Reducer<Text, IntWritable, Text, IntWritable> {
        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
            //输入的key也就是输出的key
            int sum = 0;
            for (IntWritable value : values) {
                sum += value.get();
            }
            //输出阶段
            context.write(key, new IntWritable(sum));
        }
    }
}
