package cn.date21;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Arrays;

public class demo03 {
    public static void main(String[] args) throws IOException {
        String inPath = "G://job//out//jobout3.csv";
        String outPath = "G://job//resalary//job-salary3.csv";

        //创建csv读对象
        InputStream in = new FileInputStream(new File(inPath));
        CsvReader csvReader = new CsvReader(in, ',', Charset.forName("UTF-8"));

        //创建csv写对象
        OutputStream out = new FileOutputStream(new File(outPath));
        CsvWriter csvWriter = new CsvWriter(out,',',Charset.forName("UTF-8"));

        //读表头
        csvReader.readHeaders();

        String pattern = "(?i)k";
        while (csvReader.readRecord()){
//            System.out.println(csvReader.get(11));
            String[] line = new String[csvReader.getColumnCount()+1];
            String[] split = new String[2];
            if(csvReader.get(11).contains("面议")){
                continue;
            }
            for (int i=0;i<csvReader.getColumnCount();i++){
                if(i==11){

                    split = csvReader.get(i).split("-");
                    split[1] = split[1].replaceAll("万", "");
                    line[i] = split[0]+'0';
                    continue;
                }
                line[i] = csvReader.get(i);
            }
            line[line.length-1] = line[13];
            line[13] = line[12];
            line[12] = split[1]+'0';
            System.out.println(Arrays.asList(line));
            csvWriter.writeRecord(line);
//            flag = true;
        }

        //关闭资源
        csvReader.close();
        csvWriter.close();


    }
}
