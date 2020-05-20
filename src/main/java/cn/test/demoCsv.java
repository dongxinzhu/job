package cn.test;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import org.mortbay.io.WriterOutputStream;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.Arrays;

public class demoCsv {
    public static void main(String[] args) throws IOException {
        String filePath = "G://job//jobs6.csv";
        String outPath = "G://job//job6.csv";

//        Writer out = new OutputStreamWriter(new FileOutputStream(outPath), "utf-8");
//        CsvWriter cw = new CsvWriter(out,',');
        OutputStream out = new FileOutputStream(outPath);
        CsvWriter cw = new CsvWriter(out,',', Charset.forName("UTF-8"));//UTF-8

        InputStream in = new FileInputStream(new File(filePath));
        // 创建CSV读对象
        CsvReader csvReader = new CsvReader(in, ',', Charset.forName("UTF-8"));//GBK
        // 读表头
        csvReader.readHeaders();
        String[] header = csvReader.getHeaders();
        System.out.println(Arrays.asList(header));
        cw.writeRecord(header, true);
        int count = 0;
        int lcount = 0;
        while (csvReader.readRecord()){

//            if(lcount>=1000){
//                break;
//            }

//            String re = csvReader.getRawRecord();
//            System.out.println(re);
            // 读一整行
//            if("company_financing_stage".startsWith(re) ){
            if("company_financing_stage".equals(csvReader.get(0))){
                count++;
                continue;
            }else {
                lcount++;
                String[] str = new String[csvReader.getColumnCount()];
                for(int i=0;i<csvReader.getColumnCount();i++){
                    String item = csvReader.get(i);
//                    item = item.replace("\n", "**");//替换列中的回车 tab等字符
//                    item = item.replace("\t", "**");
//                    item = item.replace("\r", "**");
                    item = item.replace("\n", "");//替换列中的回车 tab等字符
                    item = item.replace("\t", "");
                    item = item.replace("\r", "");
                    if(i==7){
                        item = item.replace("学历：","");
                    }
                    if(i==8){
                        item = item.replace("经验","");
                    }

                    str[i] = item;
                }
//                    System.out.println(str);
                cw.writeRecord(str, true);

//                System.out.println(str);
//                String line = csvReader.getRawRecord();
//                System.out.println(line);
//                System.out.println(csvReader.getRawRecord());
            }

        }
        System.out.println(count);
        System.out.println(lcount);
        cw.flush();
        cw.close();

        csvReader.close();
    }
}
