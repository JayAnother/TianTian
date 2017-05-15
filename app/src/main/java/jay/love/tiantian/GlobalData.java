package jay.love.tiantian;

/**
 * Created by jay on 2017/5/5.
 */

public class GlobalData {
    public static String serviceRequestUrl;
    public static boolean DEBUG=true;
    public static final String SP_NAME = "puzzle_sp";
    public static final String SP_LEVEL = "level";
    public static final String TURING_URL = "http://www.tuling123.com/";
    public static  final String TURING_KEY = "abccedfcf53e4c9a933f82b27c1f5b78";
    public static class TuringCode {
        public static final int TEXT = 100000;//文本类数据
        public static final int URL = 200000;//网址类数据
        public static final int NEWS = 302000;//新闻
        public static final int APP = 304000;//应用、软件、下载
        public static final int TRAIN = 305000;//列车
        public static final int PLANE = 306000;//航班
        public static final int VIDEO = 308000;//菜谱、视频、小说
        public static final int HOTEL = 309000;//酒店
        public static final int PLACE = 311000;//价格

        public static final int ERRORKEY_LEN = 40001;//key的长度错误（32位）
        public static final int ERROR_TEXT_NULL = 40002;//请求内容为空
        public static final int ERROR_KEY_ERROR = 40003;//key错误或帐号未激活
        public static final int ERROR_OUT_OF_TIMES = 40004;//当天请求次数已用完
        public static final int ERROR_NOT_SUPPORT = 40005;//暂不支持该功能
        public static final int ERROR_SERVER_UPDATE = 40006;//服务器升级中
        public static final int ERROR_DATA_FORMAT = 40007;//服务器数据格式异常
    }
}
