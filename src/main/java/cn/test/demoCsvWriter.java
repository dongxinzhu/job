package cn.test;

import com.csvreader.CsvWriter;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Iterator;

public class demoCsvWriter {
    public static void csvWriterHead(String[] csvHeaders) throws IOException {
        // 定义一个CSV路径
        String csvFilePath = "output/job1.csv";
            // 创建CSV写对象 例如:CsvWriter(文件路径，分隔符，编码格式);
            CsvWriter csvWriter = new CsvWriter(csvFilePath, ',', Charset.forName("GBK"));
            // 写表头
            csvWriter.writeRecord(csvHeaders);

            csvWriter.close();
            System.out.println("--------CSV文件已经写入--------");

    }

    public static void csvWriterItem(String item) throws IOException {
        // 定义一个CSV路径
        String csvFilePath = "output//job1.csv";
        // 创建CSV写对象 例如:CsvWriter(文件路径，分隔符，编码格式);
        CsvWriter csvWriter = new CsvWriter(csvFilePath, ',', Charset.forName("GBK"));
        // 写内容
        String[] csvContent = item.split("-*-");
        csvWriter.writeRecord(csvContent);
        System.out.println("--------CSV文件已经写入--------");
        csvWriter.close();

    }
}
