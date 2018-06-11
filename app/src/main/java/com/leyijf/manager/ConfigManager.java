package com.leyijf.manager;

import android.content.Context;
import android.util.Log;

import com.leyijf.App;
import com.leyijf.base.Constant;
import com.leyijf.greendao.ConfigDao;
import com.leyijf.utils.CommonUtils;
import com.leyijf.utils.DeviceUtil;
import com.leyijf.utils.StringUtils;

import java.util.HashMap;


public class ConfigManager {

    private static final String TAG;
    private static HashMap<String, Object> map;

    static {
        TAG = ConfigManager.class.getSimpleName();
        map = new HashMap<String, Object>();
    }

    public static Object getConfig(ConfigKeyEnum keyEnum) {
        return map.get(keyEnum.name());
    }

    public static String getConfigAsString(ConfigKeyEnum keyEnum) {
        Object obj = getConfig(keyEnum);
        return obj == null ? null : (String) obj;
    }

    public static int getConfigAsInt(ConfigKeyEnum keyEnum) {
        Object obj = getConfig(keyEnum);
        return obj == null ? 0 : ((Integer) obj).intValue();
    }
    public static float getConfigAsFloat(ConfigKeyEnum keyEnum) {
        Object obj = getConfig(keyEnum);
        return obj == null ? 0 : ((Float) obj).intValue();
    }
    public static boolean getConfigAsBoolean(ConfigKeyEnum keyEnum) {
        Object obj = getConfig(keyEnum);
        return obj == null ? false : ((Boolean) obj).booleanValue();
    }

    public static void init(Context context) {
        ConfigDao configDao = App.getDaoSession().getConfigDao();
        for (Config record : configDao.loadAll()) {
            map.put(record.getKey(), record.getValue());
        }

        Object[] screenInfo = DeviceUtil.getScreenInfo(context);
        String currentAppVersionName = CommonUtils.getAppVersionName(context);
        int currentAppVersionCode = CommonUtils.getAppVersionCode(context);
        String lastAppVersionCode=(String) map.get(ConfigKeyEnum.APP_VERSION_CODE.name());
        map.put(ConfigKeyEnum.DEVICE_ID.name(), DeviceUtil.getMyUUID());
//      map.put(ConfigKeyEnum.IMEI.name(), DeviceUtil.getIMEI(context));
        map.put(ConfigKeyEnum.MOBILE_TYPE.name(), DeviceUtil.getMobileType());
        map.put(ConfigKeyEnum.BRAND.name(), DeviceUtil.getMobileName());
        map.put(ConfigKeyEnum.OSVERSION.name(), DeviceUtil.getOsVersion());
        map.put(ConfigKeyEnum.PIXEL.name(), DeviceUtil.getDensity(context));
        map.put(ConfigKeyEnum.CPU.name(), DeviceUtil.getCpuInfo());
        map.put(ConfigKeyEnum.RAM.name(), DeviceUtil.getRAM(context));
        map.put(ConfigKeyEnum.ROM.name(), DeviceUtil.getROM());

        map.put(ConfigKeyEnum.SCREEN_WIDTH.name(), screenInfo[0]);
        map.put(ConfigKeyEnum.SCREEN_HEIGHT.name(), screenInfo[1]);
        map.put(ConfigKeyEnum.IS_LARGE_SCREEN.name(), screenInfo[2]);
        map.put(ConfigKeyEnum.APP_VERSION_NAME.name(), currentAppVersionName);
        map.put(ConfigKeyEnum.APP_VERSION_CODE.name(), currentAppVersionCode);
        if (StringUtils.isBlank(lastAppVersionCode)||!lastAppVersionCode.equals(currentAppVersionCode+"")) {
            Log.i(TAG, "First Luanch");
            map.put(ConfigKeyEnum.IS_FIRST_LUNCH.name(), Boolean.valueOf(true));
            Log.i(TAG, "更新数据库中的版本号,  记录ID=" + Long.valueOf(configDao.insertOrReplace(new Config(null, ConfigKeyEnum.APP_VERSION_CODE.name(), currentAppVersionCode+""))) + ",版本号=" + currentAppVersionCode);
        } else {
            map.put(ConfigKeyEnum.IS_FIRST_LUNCH.name(), Boolean.valueOf(false));
            Log.i(TAG, "判断为非首次启动");
        }
        map.put(ConfigKeyEnum.SELECTED_CITY_CODE.name(), Constant.LOC.DEFAULT_CITY_CODE);
        map.put(ConfigKeyEnum.SELECTED_CITY_NAME.name(), Constant.LOC.DEFAULT_CITY_NAME);
    }


    public static enum ConfigKeyEnum {
        DEVICE_ID,
        APP_VERSION_NAME,
        APP_VERSION_CODE,
        IMEI,
        MOBILE_TYPE,
        SCREEN_WIDTH,
        SCREEN_HEIGHT,
        IS_LARGE_SCREEN,
        IS_FIRST_LUNCH,
        SELECTED_CITY_CODE,
        SELECTED_CITY_NAME,
        OSVERSION,
        BRAND,
        PIXEL,
        CPU,
        RAM,
        ROM

    }

}
