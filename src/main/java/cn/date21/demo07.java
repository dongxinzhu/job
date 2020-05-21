package cn.date21;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import java.io.*;
import java.nio.charset.Charset;

/**
 * 将csv文件中每列的英文逗号改为中文逗号
 */
public class demo07 {
    public static void main(String[] args) throws IOException {
        String inPath = "G://job//resalary//job-salary6.csv";
        String  outPath = "G://job//lastsalary//job-salary6.csv";

        //创建csv读对象
        InputStream in = new FileInputStream(new File(inPath));
        CsvReader csvReader = new CsvReader(in, ',', Charset.forName("UTF-8"));

        //创建csv写对象
        OutputStream out = new FileOutputStream(new File(outPath));
        CsvWriter csvWriter = new CsvWriter(out,',',Charset.forName("UTF-8"));


        while (csvReader.readRecord()){
            String[] line = new String[csvReader.getColumnCount()];
            for(int i=0;i<csvReader.getColumnCount();i++){
                line[i] = csvReader.get(i).replaceAll(",","，");
            }
            csvWriter.writeRecord(line);
        }
        csvReader.close();

        //关闭资源
        csvReader.close();
        csvWriter.close();
    }
}
