package com.example.wood121.viewdemos.util;

import com.example.wood121.viewdemos.sdk_part.bean.MoneyPersonBean;
import com.example.wood121.viewdemos.frame_part.bean.Tab0206Bean;
import com.example.wood121.viewdemos.frame_part.bean.Tab0208Bean;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * Created by wood121 on 2017/12/28.
 * 读取原始对照表、tab0206补贴表、tab0208补贴表
 * 写tab0206Copy,tab0208copy
 * 验证比对 结果
 */

public class ExceUtilHelper {

    public static List<MoneyPersonBean> getOriginList(String tabPath) {
        ArrayList<MoneyPersonBean> originList = new ArrayList<>();
        File file = new File(tabPath);
        try {
            Workbook workbook = Workbook.getWorkbook(file);
            int sheetNum = workbook.getNumberOfSheets();

            Sheet sheet = workbook.getSheet(0);
            int sheetRows = sheet.getRows();
            int sheetColumns = sheet.getColumns();
            for (int i = 0; i < sheetRows; i++) {
                MoneyPersonBean moneyPersonBean = new MoneyPersonBean();

                moneyPersonBean.setId(sheet.getCell(0, i).getContents());

                String agencyId = sheet.getCell(1, i).getContents();
                if (agencyId.contains("'")) {
                    String substring = agencyId.substring(1, agencyId.length());
                    moneyPersonBean.setAgencyId(substring);
                } else {
                    moneyPersonBean.setAgencyId(agencyId);
                }

                moneyPersonBean.setName(sheet.getCell(2, i).getContents());

                String cardId = sheet.getCell(3, i).getContents();
                if (cardId.contains("'")) {
                    String substring = cardId.substring(1, cardId.length());
                    moneyPersonBean.setCardId(substring);
                } else {
                    moneyPersonBean.setCardId(cardId);
                }

                moneyPersonBean.setIsCardId(sheet.getCell(4, i).getContents());

                String account = sheet.getCell(5, i).getContents();
                if (account.contains("'")) {
                    String substring = account.substring(1, account.length());
                    moneyPersonBean.setAccount(substring);
                } else {
                    moneyPersonBean.setAccount(account);
                }

                moneyPersonBean.setIsAccount(sheet.getCell(6, i).getContents());
                originList.add(moneyPersonBean);
            }
            workbook.close();

        } catch (Exception e) {

        }
        return originList;
    }

    public static List<Tab0206Bean> getTab0206(String tabPath) {
        ArrayList<Tab0206Bean> tab0206List = new ArrayList<>();
        File file = new File(tabPath);
        try {
            Workbook workbook = Workbook.getWorkbook(file);
            int sheetNum = workbook.getNumberOfSheets();

            Sheet sheet = workbook.getSheet(0);
            int sheetRows = sheet.getRows();
            int sheetColumns = sheet.getColumns();

            for (int i = 0; i < sheetRows; i++) {
                Tab0206Bean tab0206Bean = new Tab0206Bean();

                String id = sheet.getCell(0, i).getContents();
                String agencyId = sheet.getCell(1, i).getContents();
                String name = sheet.getCell(2, i).getContents();
                String cardId = sheet.getCell(3, i).getContents();
                String address = sheet.getCell(4, i).getContents();
                String money = sheet.getCell(5, i).getContents();
                String account = sheet.getCell(6, i).getContents();
                String date = sheet.getCell(7, i).getContents();

                tab0206Bean.setId(id);

                if (agencyId.contains("'")) {
                    String substring = agencyId.substring(1, agencyId.length());
                    tab0206Bean.setAgencyId(substring);
                } else {
                    tab0206Bean.setAgencyId(agencyId);
                }

                tab0206Bean.setName(name);

                if (cardId.contains("'")) {
                    String substring = cardId.substring(1, cardId.length());
                    tab0206Bean.setCardId(substring);
                } else {
                    tab0206Bean.setCardId(cardId);
                }

                if (address == null) {
                    tab0206Bean.setAddress("");
                } else {
                    tab0206Bean.setAddress(address);
                }

                tab0206Bean.setMoney(money);

                if (account.contains("'")) {
                    String substring = account.substring(1, account.length());
                    tab0206Bean.setAccount(substring);
                } else {
                    tab0206Bean.setAccount(account);
                }

                tab0206Bean.setDate(date);

                tab0206List.add(tab0206Bean);
            }
            workbook.close();

        } catch (Exception e) {

        }
        return tab0206List;
    }

    public static List<Tab0208Bean> getTab0208(String tabPath) {
        ArrayList<Tab0208Bean> tab0208List = new ArrayList<>();
        File file = new File(tabPath);
        try {
            Workbook workbook = Workbook.getWorkbook(file);
            int sheetNum = workbook.getNumberOfSheets();

            Sheet sheet = workbook.getSheet(0);
            int sheetRows = sheet.getRows();
            int sheetColumns = sheet.getColumns();
            for (int i = 0; i < sheetRows; i++) {
                Tab0208Bean tab0208Bean = new Tab0208Bean();

                String id = sheet.getCell(0, i).getContents();
                String typeName = sheet.getCell(1, i).getContents();
                String agencyId = sheet.getCell(2, i).getContents();
                String name = sheet.getCell(3, i).getContents();
                String cardId = sheet.getCell(4, i).getContents();
                String address = sheet.getCell(5, i).getContents();
                String money_standard = sheet.getCell(6, i).getContents();
                String money_count = sheet.getCell(7, i).getContents();
                String money = sheet.getCell(8, i).getContents();
                String account = sheet.getCell(9, i).getContents();
                String date = sheet.getCell(10, i).getContents();
                String bank_name = sheet.getCell(11, i).getContents();
                String back_up = sheet.getCell(12, i).getContents();
                String ws = sheet.getCell(13, i).getContents();

                tab0208Bean.setId(id);
                tab0208Bean.setTypeName(typeName);
                if (agencyId.contains("'")) {
                    String substring = agencyId.substring(1, agencyId.length());
                    tab0208Bean.setAgencyId(substring);
                } else {
                    tab0208Bean.setAgencyId(agencyId);
                }
                tab0208Bean.setName(name);
                if (cardId.contains("'")) {
                    String substring = cardId.substring(1, agencyId.length());
                    tab0208Bean.setCardId(substring);
                } else {
                    tab0208Bean.setCardId(cardId);
                }
                tab0208Bean.setAddress(address);
                if (money_standard == null) {
                    tab0208Bean.setMoney_standard("");
                } else {
                    tab0208Bean.setMoney_standard(money_standard);
                }
                tab0208Bean.setMoney_count(money_count);
                tab0208Bean.setMoney(money);
                if (account.contains("'")) {
                    String substring = account.substring(1, agencyId.length());
                    tab0208Bean.setAccount(substring);
                } else {
                    tab0208Bean.setAccount(account);
                }
                tab0208Bean.setDate(date);
                tab0208Bean.setBank_name(bank_name);
                if (back_up == null) {
                    tab0208Bean.setBack_up("");
                } else {
                    tab0208Bean.setBack_up(back_up);
                }
                tab0208Bean.setWs(ws);

                tab0208List.add(tab0208Bean);
            }
            workbook.close();
        } catch (Exception e) {
        }
        return tab0208List;
    }

    /**
     * 处理完之后tab0206表格
     *
     * @param list
     */
    public static void writeTab0206Copy(List list, int type) {
        try {
            File file;
            if (type == 0) {
                file = new File("./app/src/main/assets/tab0206coy.xls");
            } else {
                file = new File("./app/src/main/assets/tab0206coyVerify.xls");
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);
            //创建一个可写的Workbook
            WritableWorkbook wwb = Workbook.createWorkbook(fos);
            //创建一个可写的sheet,第一个参数是名字,第二个参数是第几个sheet
            WritableSheet sheet = wwb.createSheet("第一个sheet", 0);
            for (int i = 0; i < list.size(); i++) {
                Tab0206Bean tab0206Bean = (Tab0206Bean) list.get(i);

                //创建一个Label,第一个参数是x轴,第二个参数是y轴,第三个参数是内容,第四个参数可选,指定类型
                Label label0 = new Label(0, i, tab0206Bean.getId());
                Label label1 = new Label(1, i, tab0206Bean.getAgencyId());
                Label label2 = new Label(2, i, tab0206Bean.getName());
                Label label3 = new Label(3, i, tab0206Bean.getCardId());
                Label label4 = new Label(4, i, tab0206Bean.getAddress());
                Label label5 = new Label(5, i, tab0206Bean.getMoney());
                Label label6 = new Label(6, i, tab0206Bean.getAccount());
                Label label7 = new Label(7, i, tab0206Bean.getDate());

                //把label加入sheet对象中
                sheet.addCell(label0);
                sheet.addCell(label1);
                sheet.addCell(label2);
                sheet.addCell(label3);
                sheet.addCell(label4);
                sheet.addCell(label5);
                sheet.addCell(label6);
                sheet.addCell(label7);
            }
            //保存到Workbook中
            wwb.write();
            //只有执行close时才会写入到文件中,可能在close方法中执行了io操作
            wwb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeTab0208Copy(List list, int type) {
        try {
            File file;
            if (type == 0) {
                file = new File("./app/src/main/assets/tab0208coy.xls");
            } else {
                file = new File("./app/src/main/assets/tab0208coyVerify.xls");
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);
            //创建一个可写的Workbook
            WritableWorkbook wwb = Workbook.createWorkbook(fos);
            //创建一个可写的sheet,第一个参数是名字,第二个参数是第几个sheet
            WritableSheet sheet = wwb.createSheet("第一个sheet", 0);
            for (int i = 0; i < list.size(); i++) {
                Tab0208Bean tab0208Bean = (Tab0208Bean) list.get(i);

                //创建一个Label,第一个参数是x轴,第二个参数是y轴,第三个参数是内容,第四个参数可选,指定类型
                Label label0 = new Label(0, i, tab0208Bean.getId());
                Label label1 = new Label(1, i, tab0208Bean.getTypeName());
                Label label2 = new Label(2, i, tab0208Bean.getAgencyId());
                Label label3 = new Label(3, i, tab0208Bean.getName());
                Label label4 = new Label(4, i, tab0208Bean.getCardId());
                Label label5 = new Label(5, i, tab0208Bean.getAddress());
                Label label6 = new Label(6, i, tab0208Bean.getMoney_standard());
                Label label7 = new Label(7, i, tab0208Bean.getMoney_count());
                Label label8 = new Label(8, i, tab0208Bean.getMoney());
                Label label9 = new Label(9, i, tab0208Bean.getAccount());
                Label label10 = new Label(10, i, tab0208Bean.getDate());
                Label label11 = new Label(11, i, tab0208Bean.getBank_name());
                Label label12 = new Label(12, i, tab0208Bean.getBack_up());
                Label label13 = new Label(13, i, tab0208Bean.getWs());

                //把label加入sheet对象中
                sheet.addCell(label0);
                sheet.addCell(label1);
                sheet.addCell(label2);
                sheet.addCell(label3);
                sheet.addCell(label4);
                sheet.addCell(label5);
                sheet.addCell(label6);
                sheet.addCell(label7);
                sheet.addCell(label8);
                sheet.addCell(label9);
                sheet.addCell(label10);
                sheet.addCell(label11);
                sheet.addCell(label12);
                sheet.addCell(label13);
            }
            //保存到Workbook中
            wwb.write();
            //只有执行close时才会写入到文件中,可能在close方法中执行了io操作
            wwb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
