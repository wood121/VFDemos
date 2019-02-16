package com.example.wood121.viewdemos.util;

import com.example.wood121.viewdemos.sdk_thirdparty.bean.MoneyPersonBean;
import com.example.wood121.viewdemos.frames.bean.Tab0206Bean;
import com.example.wood121.viewdemos.frames.bean.Tab0208Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wood121 on 2017/12/27.
 * 输入标准表格A、需要比对表格B
 * 拿b和A进行对比，梳理出正确的B
 * 输出正确的B表
 */

public class ExcelUtil {

    public static void main(String[] args) {
        getLists();
    }

    /**
     * 获取excel数据
     */
    private static void getLists() {
        String originPath = "./app/src/main/assets/tab1.xls";
        String tab026Path = "./app/src/main/assets/tab0206.xls";
        String tab0208Path = "./app/src/main/assets/tab0208.xls";
        String tab0206PathCopy = "./app/src/main/assets/tab0206coy.xls";
        String tab0208PathCopy = "./app/src/main/assets/tab0208coy.xls";

        //原始对比表格
//        List<MoneyPersonBean> originList = ExceUtilHelper.getOriginList(originPath);


        //比对tab0206表格
//        List<Tab0206Bean> tab0206List = ExceUtilHelper.getTab0206(tab026Path);
//        ExceUtilHelper.writeTab0206Copy(compareTab0206(originList, tab0206List), 0);
//        ExceUtilHelper.writeTab0206Copy(tab0206List, 0);
        //比对tab0208表格
        List<Tab0208Bean> tab0208List = ExceUtilHelper.getTab0208(tab0208Path);
//        ExceUtilHelper.writeTab0208Copy(compareTab0208(originList, tab0208List), 0);

        //验证tab0206Copy
//        List<Tab0206Bean> tab0206ListCopy = ExceUtilHelper.getTab0206(tab0206PathCopy);
//        ExceUtilHelper.writeTab0206Copy(verifyTab0206(tab0206List, tab0206ListCopy), 1);
//        System.out.printf(tab0206ListCopy.size() + ":::tab0206ListCopy");
//        ExceUtilHelper.writeTab0206Copy(tab0206ListCopy, 1);
        //验证tab0208Copy
        List<Tab0208Bean> tab0208ListCopy = ExceUtilHelper.getTab0208(tab0208PathCopy);
        ExceUtilHelper.writeTab0208Copy(verifyTab0208(tab0208List, tab0208ListCopy), 1);
    }

    /**
     * 验证tab0206的结果
     * 经过修改了的内容：agencyId,name,cardId,account
     *
     * @param tab0206List
     * @param tab0206ListCopy
     * @return
     */
    private static ArrayList<Tab0206Bean> verifyTab0206(List<Tab0206Bean> tab0206List, List<Tab0206Bean> tab0206ListCopy) {
        ArrayList<Tab0206Bean> tabList = new ArrayList<>();

        for (int i = 0; i < tab0206ListCopy.size(); i++) {
            Tab0206Bean tab0206BeanCopy = tab0206ListCopy.get(i);
            String agencyIdCopy = tab0206BeanCopy.getAgencyId();
            String nameCopy = tab0206BeanCopy.getName();
            String cardIdCopy = tab0206BeanCopy.getCardId();
            String accountCopy = tab0206BeanCopy.getAccount();
            boolean flag = false;

            for (int j = 0; j < tab0206List.size(); j++) {
                Tab0206Bean tab0206Bean = tab0206List.get(j);

                String agencyId = tab0206Bean.getAgencyId();
                if (agencyId.contains("'")) {
                    agencyId = agencyId.substring(1, agencyId.length());
                }
                String name = tab0206Bean.getName();
                String cardId = tab0206Bean.getCardId();
                if (cardId.contains("'")) {
                    cardId = cardId.substring(1, cardId.length());
                }
                String account = tab0206Bean.getAccount();
                if (account.contains("'")) {
                    account = account.substring(1, account.length());
                }

                if (agencyIdCopy.equals(agencyId)
                        && nameCopy.equals(name)
                        && cardIdCopy.equals(cardId)
                        && accountCopy.equals(account)) {
                    tab0206BeanCopy.setName("***我们都一样***");
                    tabList.add(tab0206BeanCopy);
                    flag = true;
                }
            }

            if (!flag) {
                tabList.add(tab0206BeanCopy);
            }

        }
        return tabList;
    }

    /**
     * 验证tab0208的结果
     *
     * @return
     */
    private static ArrayList<Tab0208Bean> verifyTab0208(List<Tab0208Bean> tab0208List, List<Tab0208Bean> tab0208ListCopy) {
        ArrayList<Tab0208Bean> tabList = new ArrayList<>();

        for (int i = 0; i < tab0208ListCopy.size(); i++) {
            Tab0208Bean tab0208BeanCopy = tab0208ListCopy.get(i);
            String agencyIdCopy = tab0208BeanCopy.getAgencyId();
            String nameCopy = tab0208BeanCopy.getName();
            String cardIdCopy = tab0208BeanCopy.getCardId();
            String accountCopy = tab0208BeanCopy.getAccount();
            boolean flag = false;

            for (int j = 0; j < tab0208List.size(); j++) {
                Tab0208Bean tab0208Bean = tab0208List.get(j);

                String agencyId = tab0208Bean.getAgencyId();
                if (agencyId.contains("'")) {
                    agencyId = agencyId.substring(1, agencyId.length());
                }
                String name = tab0208Bean.getName();
                String cardId = tab0208Bean.getCardId();
                if (cardId.contains("'")) {
                    cardId = cardId.substring(1, cardId.length());
                }
                String account = tab0208Bean.getAccount();
                if (account.contains("'")) {
                    account = account.substring(1, account.length());
                }

                if (agencyIdCopy.equals(agencyId)
                        && nameCopy.equals(name)
                        && cardIdCopy.equals(cardId)
                        && accountCopy.equals(account)) {
                    tab0208BeanCopy.setName("***我们都一样***");
                    tabList.add(tab0208BeanCopy);
                    flag = true;
                }
            }

            if (!flag) {
                tabList.add(tab0208BeanCopy);
            }
        }
        return tabList;
    }

    /**
     * 比较tab0206
     *
     * @param listOrigin
     * @param listToCompare
     * @return
     */
    private static ArrayList<Tab0206Bean> compareTab0206(List<MoneyPersonBean> listOrigin, List<Tab0206Bean> listToCompare) {
        ArrayList<Tab0206Bean> tabList = new ArrayList<>();
        for (int i = 0; i < listToCompare.size(); i++) {
            //需要比对的数据
            Tab0206Bean tab0206Bean = listToCompare.get(i);
            String cardId = tab0206Bean.getCardId();
            String agencyId = tab0206Bean.getAgencyId();
            boolean flag = false;
            //进行梳理的新表格
            for (int j = 0; j < listOrigin.size(); j++) {
                MoneyPersonBean originPerson = listOrigin.get(j);
                if (cardId.equals(originPerson.getCardId()) || agencyId.equals(originPerson.getAgencyId())) {
                    tab0206Bean.setAgencyId(originPerson.getAgencyId());
                    tab0206Bean.setCardId(originPerson.getCardId());
                    tab0206Bean.setName(originPerson.getName());
                    tab0206Bean.setAccount(originPerson.getAccount());
                    flag = true;
                    System.out.printf(tab0206Bean.toString() + "\n");
                }
            }
            if (!flag) {
                tab0206Bean.setName("*******");
            }
            tabList.add(tab0206Bean);
        }
        return tabList;
    }

    /**
     * 比较tab0208
     *
     * @param listOrigin
     * @param listToCompare
     * @return
     */
    private static ArrayList<Tab0208Bean> compareTab0208(List<MoneyPersonBean> listOrigin, List<Tab0208Bean> listToCompare) {
        ArrayList<Tab0208Bean> tabList = new ArrayList<>();
        for (int i = 0; i < listToCompare.size(); i++) {
            //需要比对的数据
            Tab0208Bean tab0208Bean = listToCompare.get(i);
            String cardId = tab0208Bean.getCardId();
            String agencyId = tab0208Bean.getAgencyId();
            boolean flag = false;
            //进行梳理的新表格
            for (int j = 0; j < listOrigin.size(); j++) {
                MoneyPersonBean originPerson = listOrigin.get(j);
                if (cardId.equals(originPerson.getCardId()) || agencyId.equals(originPerson.getAgencyId())) {
                    tab0208Bean.setAgencyId(originPerson.getAgencyId());
                    tab0208Bean.setCardId(originPerson.getCardId());
                    tab0208Bean.setName(originPerson.getName());
                    tab0208Bean.setAccount(originPerson.getAccount());
                    flag = true;
                }
            }
            if (!flag) {
                tab0208Bean.setName("******");
            }
            tabList.add(tab0208Bean);
        }
        return tabList;
    }
}
