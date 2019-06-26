package com.example.wood121.viewdemos.sdk_thirdparty;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.wood121.viewdemos.base.BaseActivity;
import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.sdk_thirdparty.bean.MoneyPersonBean;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExcelContentActivity extends BaseActivity {

    @BindView(R.id.tv_excel)
    TextView tvExcel;
    private ArrayList<MoneyPersonBean> xlsData;

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected int initPageLayoutId() {
        return R.layout.activity_excel_content;
    }

    @Override
    protected void initEvent() {

    }

    @OnClick(R.id.tv_excel)
    public void onViewClicked() {
        final String xlsName = "tab1.xls";
        new Thread(new Runnable() {
            @Override
            public void run() {
                xlsData = getXlsData(xlsName);
            }
        }).start();
    }

    private ArrayList<MoneyPersonBean> getXlsData(String xlsName) {
        ArrayList<MoneyPersonBean> originList = new ArrayList<>();
        //获取文件管理器
        AssetManager manager = getAssets();
        try {
            Workbook workbook = Workbook.getWorkbook(manager.open(xlsName));
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

        for (int i = 0; i < originList.size(); i++) {
            Log.e("person", originList.get(i).toString());
        }

        excelWrite(xlsData);

        return originList;
    }

    public boolean excelWrite(List list) {
        try {
            //文件创建
            File file = new File(getExternalCacheDir(), "tab2.xls");
            FileOutputStream fos = new FileOutputStream(file);
            //创建一个可写的Workbook
            WritableWorkbook wwb = Workbook.createWorkbook(fos);
            //创建一个可写的sheet,第一个参数是名字,第二个参数是第几个sheet
            WritableSheet sheet = wwb.createSheet("第一个sheet", 0);
            for (int i = 0; i < list.size(); i++) {
                MoneyPersonBean moneyPersonBean = (MoneyPersonBean) list.get(i);

                //创建一个Label,第一个参数是x轴,第二个参数是y轴,第三个参数是内容,第四个参数可选,指定类型
                Label label0 = new Label(0, i, moneyPersonBean.getId());
                Label label1 = new Label(1, i, moneyPersonBean.getAgencyId());
                Label label2 = new Label(2, i, moneyPersonBean.getName());
                Label label3 = new Label(3, i, moneyPersonBean.getCardId());
                Label label4 = new Label(4, i, moneyPersonBean.getIsCardId());
                Label label5 = new Label(5, i, moneyPersonBean.getAccount());
                Label label6 = new Label(6, i, moneyPersonBean.getIsAccount());

                //把label加入sheet对象中
                sheet.addCell(label0);
                sheet.addCell(label1);
                sheet.addCell(label2);
                sheet.addCell(label3);
                sheet.addCell(label4);
                sheet.addCell(label5);
                sheet.addCell(label6);
            }
            //保存到Workbook中
            wwb.write();
            //只有执行close时才会写入到文件中,可能在close方法中执行了io操作
            wwb.close();
            Log.e("person", file.getName());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}

