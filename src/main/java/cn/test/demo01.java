package cn.test;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Arrays;

public class demo01 {
    public static void main(String[] args) throws IOException {
        String inPath = "G://job//job1.csv";
        String outPath = "G://job//out//jobout1.csv";

        //创建csv读对象
        InputStream in = new FileInputStream(new File(inPath));
        CsvReader csvReader = new CsvReader(in, ',', Charset.forName("UTF-8"));

        //创建csv写对象
        OutputStream out = new FileOutputStream(new File(outPath));
        CsvWriter csvWriter = new CsvWriter(out,',',Charset.forName("UTF-8"));

        //读表头
        csvReader.readHeaders();
        String[] header = csvReader.getHeaders();
        System.out.println(Arrays.asList(header));

        csvWriter.writeRecord(header, true);
        //关闭资源
        csvReader.close();
        csvWriter.close();
    }

}
