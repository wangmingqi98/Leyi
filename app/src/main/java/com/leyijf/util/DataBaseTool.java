package com.leyijf.util;

import com.leyijf.App;
import com.leyijf.bean.BindedCardBean;
import com.leyijf.bean.UserBean;
import com.leyijf.greendao.BindedCardBeanDao;
import com.leyijf.greendao.UserBeanDao;

import java.util.List;

/**
 * Created by wmq on 2018/4/18.
 * 数据库的增删改查工具类
 *
 */

public class DataBaseTool {
    private static UserBeanDao userBeanDao = App.getInstance().getDaoSession().getUserBeanDao();
    private static BindedCardBeanDao bindedCardBeanDao = App.getInstance().getDaoSession().getBindedCardBeanDao();
    /**
     * 增加绑定银行卡信息
     */
    public static long insertSportInfo(UserBean userInfo, BindedCardBean bindedCardBean)
    {
        bindedCardBean.setUserId(userInfo.getId());//
        return bindedCardBeanDao.insertOrReplace(bindedCardBean);
    }
    /**
     * 增加用户信息
     */
    public static long insertUserInfo(UserBean info)
    {
        return userBeanDao.insertOrReplace(info);
    }

    /**
     * 查绑定银行卡信息
     */
    public static List<BindedCardBean> SearchSportInfo(UserBean userInfo)
    {
        return userInfo.getBinded_card();//查找绑定银行卡表则需要通过用户表来获取绑定银行卡表的集合然后再遍历找到所需银行卡表
    }

    /**
     * 查用户信息
     */
    public static List<UserBean> SearchUserInfo()
    {
        //惰性加载
        List<UserBean> list = userBeanDao.queryBuilder().listLazy();
        return list;
    }

    /**
     * 删除某条用户信息
     * @param i 删除数据的id
     */
    public static void deleteUserInfo(long i)
    {
        userBeanDao.deleteByKey(i);
        //当然Greendao还提供了其他的删除方法，只是传值不同而已
    }

    /**
     *修改某条绑定银行卡的信息
     */
    public static void correctSportInfo(BindedCardBean info)
    {
        bindedCardBeanDao.update(info);
    }

    /**
     *修改某条用户信息
     */
    public static void correctUserInfo(UserBean info)
    {
        userBeanDao.update(info);
    }
}
