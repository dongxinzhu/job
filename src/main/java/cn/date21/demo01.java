package cn.date21;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Arrays;

public class demo01 {
    public static void main(String[] args) throws IOException {
        String inPath = "G://job//out//jobout1.csv";
        String outPath = "G://job//resalary//job-salary1.csv";

        //创建csv读对象
        InputStream in = new FileInputStream(new File(inPath));
        CsvReader csvReader = new CsvReader(in, ',', Charset.forName("UTF-8"));

        //创建csv写对象
        OutputStream out = new FileOutputStream(new File(outPath));
        CsvWriter csvWriter = new CsvWriter(out,',',Charset.forName("UTF-8"));

        //读表头
        csvReader.readHeaders();

        //标记
        int count = 0;

        //将salary分开
        while (csvReader.readRecord()){
//            System.out.println(csvReader.get(11));
            String[] line = new String[csvReader.getColumnCount()+1];
            String[] split = new String[2];
            for (int i=0;i<csvReader.getColumnCount();i++){
                if(i==11){
                    split = csvReader.get(i).split("-");
                    split[0] = split[0].replace("K", "");
                    split[1] = split[1].replace("K", "");
                    line[i] = split[0];
                    continue;
                }
                line[i] = csvReader.get(i);
            }
            line[line.length-1] = line[13];
            line[13] = line[12];
            line[12] = split[1];
            System.out.println(Arrays.asList(line));
            csvWriter.writeRecord(line);
        }

        //关闭资源
        csvReader.close();
        csvWriter.close();


    }
}
