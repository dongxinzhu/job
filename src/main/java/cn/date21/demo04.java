package cn.date21;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Arrays;

public class demo04 {
    public static void main(String[] args) throws IOException {
        String inPath = "G://job//out//jobout4.csv";
        String outPath = "G://job//resalary//job-salary4.csv";

        //创建csv读对象
        InputStream in = new FileInputStream(new File(inPath));
        CsvReader csvReader = new CsvReader(in, ',', Charset.forName("UTF-8"));

        //创建csv写对象
        OutputStream out = new FileOutputStream(new File(outPath));
        CsvWriter csvWriter = new CsvWriter(out,',',Charset.forName("UTF-8"));

        //读表头
        csvReader.readHeaders();

//        boolean flag = true;

        while (csvReader.readRecord()){
            String[] line = new String[csvReader.getColumnCount()+1];
            String[] split = new String[2];
            if(csvReader.get(11).contains("以上")){
                continue;
            }
            if(csvReader.get(11).contains("以下")){
                continue;
            }
            for (int i=0;i<csvReader.getColumnCount();i++){
                if(i==11 && csvReader.get(11).contains("千")){
                    split = csvReader.get(i).split("-");
                    split[1] = split[1].replaceAll("千/月", "");
                    line[i] = split[0];
                    continue;
                }
                if(i==11 && csvReader.get(11).contains("万/月")){
                    split = csvReader.get(i).split("-");
                    split[1] = split[1].replaceAll("万/月", "");
                    System.out.println(split[1]);
                    line[i] = String.valueOf(Double.valueOf(split[0])*10);
                    split[1] = String.valueOf(Double.valueOf(split[1])*10);
                    System.out.println(split[1]);
                    continue;
                }

                if(i==11 && csvReader.get(11).contains("万/年")){
                    split = csvReader.get(i).split("-");
                    split[1] = split[1].replaceAll("万/年", "");
                    System.out.println(split[1]);
                    line[i] = String.valueOf(Double.valueOf(split[0])*10/12.0);
                    split[1] = String.valueOf(Double.valueOf(split[1])*10/12.0);
                    System.out.println(split[1]);
                    continue;
                }
                line[i] = csvReader.get(i);
            }
            line[line.length-1] = line[13];
            line[13] = line[12];
            line[12] = split[1];
//            System.out.println(Arrays.asList(line));
            csvWriter.writeRecord(line);
//            flag = true;
        }

        //关闭资源
        csvReader.close();
        csvWriter.close();


    }
}
