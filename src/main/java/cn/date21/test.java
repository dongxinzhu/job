package cn.date21;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import java.io.*;
import java.nio.charset.Charset;


public class test {
    public static void main(String[] args) throws IOException {
        String inPath = "G://job//lastsalary//job-salary6.csv";

        //创建csv读对象
        InputStream in = new FileInputStream(new File(inPath));
        CsvReader csvReader = new CsvReader(in, ',', Charset.forName("UTF-8"));

        int count = 0;

        while (csvReader.readRecord()){
            for(int i=0;i<csvReader.getColumnCount();i++){
                if(csvReader.get(i).contains(",")){
                    count++;

                }
            }
        }
        System.out.println(count);
        csvReader.close();

        //关闭资源
        csvReader.close();

    }
}
