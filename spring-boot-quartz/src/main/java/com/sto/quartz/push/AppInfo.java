package com.sto.quartz.push;

import com.gexin.rp.sdk.http.IGtPush;

/**
 * AppInfo 相关信息
 *
 * @author zhangwf
 * @see
 * @since 2019-07-09
 */
public class AppInfo {
    public static final String APPID = "vxw0zGY6OHAZmekTstOz5A";
    public static final String APPKEY = "4seFcVvReY6fVUrLG6ykl6";
    public static final String MASTERSECRET = "ejxe3BPHufAQhnowkTJvV9";
    // public static final String APPID = "xvslVXqclB8X1xR64o2o9";
    // public static final String APPKEY = "6k2JUHot4V5g2JxvwLmae";
    // public static final String MASTERSECRET = "u5Xi1EpbGcAcraEMkzRLk9";

    public static final String CID = "69b287ce1477f082f7e32da031877c40";
    public static final String CID_2 = "8c1f751db0c3104829a9768dc6de7d77";

    public static final String DEVICETOKEN = "";

    public static final String ALIAS = "alias1";
    public static final String ALIAS_2 = "alias2";

    public static final String TAG = "tag1";
    public static final String TAG_2 = "tag2";

    public static final String PNMD5 = "xxxx";

    public static IGtPush push = new IGtPush(APPKEY, MASTERSECRET);
}
