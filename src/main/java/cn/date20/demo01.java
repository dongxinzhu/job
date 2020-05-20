package cn.date20;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * 将缺少关键数据字段值（数据为空或值为null）的数据过滤掉，关键数据字段为：job_info，job_name。
 * 无效数据过滤，将job_name中包含“实习”的数据过滤掉。
 */
public class demo01 {
    public static void main(String[] args) throws IOException {
        {
            String inPath = "G://job//job6.csv";
            String outPath = "G://job//out//jobout6.csv";

            //创建csv读对象
            InputStream in = new FileInputStream(new File(inPath));
            CsvReader csvReader = new CsvReader(in, ',', Charset.forName("UTF-8"));

            //创建csv写对象
            OutputStream out = new FileOutputStream(new File(outPath));
            CsvWriter csvWriter = new CsvWriter(out,',',Charset.forName("UTF-8"));

            //读表头
            csvReader.readHeaders();
            String[] header = csvReader.getHeaders();

            int count = 0;
            int lcount = 0;
            int flag = 0;
//            int c = 0;
//            for(String s : header){
//                System.out.println(s+"\n"+c++);
//            }

            //将表头写入到csv文件中
            csvWriter.writeRecord(header, true);



            //读取csv表中每行的数据
            while (csvReader.readRecord()){
                flag++;
//                if(flag >=1000){
//                    break;
//                }
                //创建一个数组保存读取到的数据
                String[] line = new String[csvReader.getColumnCount()];

//                System.out.println(csvReader.get(9)+"\n"+"***********************");//+csvReader.get(10)
                //关键字段job_info，job_name为空时跳过
                if("".equals(csvReader.get(9)) || "".equals(csvReader.get(10)) ){
//                    System.out.println(csvReader.get(9));
                    lcount++;
                    continue;
                }
                if(csvReader.get(10).contains("实习")){
                    lcount++;
                    System.out.println(csvReader.get(10));
                    continue;
                }


                count++;
                //将每行数据保存到数组line中
                for(int i=0;i<csvReader.getColumnCount();i++){
                    line[i] = csvReader.get(i);
                }


                //将数组中的元素写入csv文件
                csvWriter.writeRecord(line);
            }
            System.out.println(flag);
            System.out.println(lcount);
            System.out.println(count);

            //关闭资源
            csvReader.close();
            csvWriter.close();
        }
    }
}
