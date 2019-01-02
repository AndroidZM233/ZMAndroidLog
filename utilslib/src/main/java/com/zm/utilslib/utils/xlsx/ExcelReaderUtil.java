package com.zm.utilslib.utils.xlsx;

import java.util.List;

/**
 * Created by 张明_ on 2018/12/29.
 * Email 741183142@qq.com
 */

public class ExcelReaderUtil {
    //excel2003扩展名
    public static final String EXCEL03_EXTENSION = ".xls";
    //excel2007扩展名
    public static final String EXCEL07_EXTENSION = ".xlsx";

    /**
     * 每获取一条记录，即打印
     * 在flume里每获取一条记录即发送，而不必缓存起来，可以大大减少内存的消耗，这里主要是针对flume读取大数据量excel来说的
     *
     * @param sheetName
     * @param sheetIndex
     * @param curRow
     * @param cellList
     */
    public static void sendRows(String filePath, String sheetName, int sheetIndex, int curRow, List<String> cellList) {

    }

    public static void readExcel(String fileName) throws Exception {
        int totalRows = 0;
        if (fileName.endsWith(EXCEL03_EXTENSION)) { //处理excel2003文件
            ExcelXlsReader excelXls = new ExcelXlsReader();
            totalRows = excelXls.process(fileName);
        } else if (fileName.endsWith(EXCEL07_EXTENSION)) {//处理excel2007文件
            ExcelXlsxReaderWithDefaultHandler excelXlsxReader = new ExcelXlsxReaderWithDefaultHandler();
            totalRows = excelXlsxReader.process(fileName);
        } else {
            throw new Exception("文件格式错误，fileName的扩展名只能是xls或xlsx。");
        }
        System.out.println("发送的总行数：" + totalRows);
    }
}
