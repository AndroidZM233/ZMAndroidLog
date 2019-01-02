package com.zm.utilslib.utils.xlsx;

import android.text.TextUtils;

import com.spd.a73590.smartchip.database.DangAnBean;
import com.spd.a73590.smartchip.database.DaoManager;
import com.spd.a73590.smartchip.database.DeviceBean;
import com.spd.a73590.smartchip.database.PanKuBean;
import com.spd.a73590.smartchip.database.UserBean;

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
        switch (sheetIndex) {
            case 1:
                if (TextUtils.isEmpty(cellList.get(1))) {
                    return;
                }
                PanKuBean panKuBean = new PanKuBean();
                panKuBean.setRSID(cellList.get(0));
                panKuBean.setID(cellList.get(1));
                panKuBean.setCabinetNum(cellList.get(2));
                panKuBean.setFloorNum(cellList.get(3));
                panKuBean.setColumnNum(cellList.get(4));
                panKuBean.setNowFloorNum(cellList.get(5));
                panKuBean.setNowCabinetNum(cellList.get(6));
                panKuBean.setNowColumnNum(cellList.get(7));
                panKuBean.setChipId(cellList.get(8));
                panKuBean.setIsAbnorma(cellList.get(9));
                panKuBean.setChipStatus(cellList.get(10));
                panKuBean.setMZ(cellList.get(11));
                panKuBean.setCSNY(cellList.get(12));
                panKuBean.setJG(cellList.get(13));
                panKuBean.setXM(cellList.get(14));
                panKuBean.setTX(cellList.get(15));
                panKuBean.setNOWTX(cellList.get(16));
                panKuBean.setChipId1(cellList.get(17));
                panKuBean.setPKDataTime(cellList.get(18));
                panKuBean.setAddTime(cellList.get(19));
                panKuBean.setUpdataTime(cellList.get(20));
                panKuBean.setRYBH(cellList.get(21));
                panKuBean.setStatus(cellList.get(22));
                DaoManager.getInstance().getDao().getPanKuBeanDao().insertOrReplace(panKuBean);
                break;
            case 2:
                if (TextUtils.isEmpty(cellList.get(0))) {
                    return;
                }
                UserBean userBean = new UserBean();
                userBean.setUserId(cellList.get(0));
                userBean.setAccount(cellList.get(1));
                userBean.setPassWord(cellList.get(2));
                userBean.setPower1(cellList.get(3));
                userBean.setPower2(cellList.get(4));
                userBean.setPower3(cellList.get(5));
                userBean.setPower4(cellList.get(6));
                userBean.setPower5(cellList.get(7));
                userBean.setPower6(cellList.get(8));
                userBean.setPower7(cellList.get(9));
                userBean.setUsertype(cellList.get(10));
                userBean.setAddTime(cellList.get(11));
                userBean.setUpdateTime(cellList.get(12));
                DaoManager.getInstance().getDao().getUserBeanDao().insertOrReplace(userBean);
                break;
            case 3:
                if (TextUtils.isEmpty(cellList.get(0))) {
                    return;
                }
                DeviceBean deviceBean = new DeviceBean();
                deviceBean.setCabinetID(cellList.get(0));
                deviceBean.setColumnNum(cellList.get(1));
                deviceBean.setFloorNum(cellList.get(2));
                deviceBean.setCom(cellList.get(3));
                deviceBean.setAntenna(cellList.get(4));
                DaoManager.getInstance().getDao().getDeviceBeanDao().insertOrReplace(deviceBean);
                break;
            case 4:
                if (TextUtils.isEmpty(cellList.get(0))) {
                    return;
                }
                DangAnBean dangAnBean = new DangAnBean();
                dangAnBean.setRSID(cellList.get(0));
                dangAnBean.setJG(cellList.get(1));
                dangAnBean.setMZ(cellList.get(2));
                dangAnBean.setRYBH(cellList.get(3));
                dangAnBean.setXB(cellList.get(4));
                dangAnBean.setXM(cellList.get(5));
                dangAnBean.setZZMM(cellList.get(6));
                dangAnBean.setZW(cellList.get(7));
                dangAnBean.setState(cellList.get(8));
                dangAnBean.setSTRXMPY(cellList.get(9));
                dangAnBean.setADDTime(cellList.get(10));
                dangAnBean.setUpdateTime(cellList.get(11));
                DaoManager.getInstance().getDao().getDangAnBeanDao().insertOrReplace(dangAnBean);
                break;
        }
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
