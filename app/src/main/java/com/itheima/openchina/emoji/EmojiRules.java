package com.itheima.openchina.emoji;

import com.itheima.openchina.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User:DoctorHe <p/>
 * Date: 2017/11/8 <p/>
 * Time: 11:29 <p/>
 * Project:OpenChina <p/>
 * Package:com.itheima.openchina.emoji <p/>
 * Desc:emoji 表情匹配规则
 */

public enum EmojiRules {
    // 注意：value不能从0开始，因为0会被库自动设置为删除按钮
    // int type, int value, int resId, String cls
    /*************************************qq表情***********************************/
    KJEMOJI0(0, 1, R.drawable.smiley_0, "[微笑]", "[0]"),
    KJEMOJI1(0, 1, R.drawable.smiley_1, "[撇嘴]", "[1]"),
    KJEMOJI2(0, 1, R.drawable.smiley_2, "[色]", "[2]"),
    KJEMOJI3(0, 1, R.drawable.smiley_3, "[发呆]", "[3]"),
    KJEMOJI4(0, 1, R.drawable.smiley_4, "[得意]", "[4]"),
    KJEMOJI5(0, 1, R.drawable.smiley_5, "[流泪]", "[5]"),
    KJEMOJI6(0, 1, R.drawable.smiley_6, "[害羞]", "[6]"),
    KJEMOJI7(0, 1, R.drawable.smiley_7, "[闭嘴]", "[7]"),
    KJEMOJI8(0, 1, R.drawable.smiley_8, "[睡]", "[8]"),
    KJEMOJI9(0, 1, R.drawable.smiley_9, "[大哭]", "[9]"),
    KJEMOJI10(0, 1, R.drawable.smiley_10, "[尴尬]", "[10]"),
    KJEMOJI11(0, 1, R.drawable.smiley_11, "[发怒]", "[11]"),
    KJEMOJI12(0, 1, R.drawable.smiley_12, "[调皮]", "[12]"),
    KJEMOJI13(0, 1, R.drawable.smiley_13, "[呲牙]", "[13]"),
    KJEMOJI14(0, 1, R.drawable.smiley_14, "[惊讶]", "[14]"),
    KJEMOJI15(0, 1, R.drawable.smiley_15, "[难过]", "[15]"),
    KJEMOJI16(0, 1, R.drawable.smiley_16, "[酷]", "[16]"),
    KJEMOJI17(0, 1, R.drawable.smiley_17, "[冷汗]", "[17]"),
    KJEMOJI18(0, 1, R.drawable.smiley_18, "[抓狂]", "[18]"),
    KJEMOJI19(0, 1, R.drawable.smiley_19, "[吐]", "[19]"),
    KJEMOJI20(0, 1, R.drawable.smiley_20, "[偷笑]", "[20]"),
    KJEMOJI21(0, 1, R.drawable.smiley_21, "[可爱]", "[21]"),
    KJEMOJI22(0, 1, R.drawable.smiley_22, "[白眼]", "[22]"),
    KJEMOJI23(0, 1, R.drawable.smiley_23, "[傲慢]", "[23]"),
    KJEMOJI24(0, 1, R.drawable.smiley_24, "[饥饿]", "[24]"),
    KJEMOJI25(0, 1, R.drawable.smiley_25, "[困]", "[25]"),
    KJEMOJI26(0, 1, R.drawable.smiley_26, "[惊恐]", "[26]"),
    KJEMOJI27(0, 1, R.drawable.smiley_27, "[流汗]", "[27]"),
    KJEMOJI28(0, 1, R.drawable.smiley_28, "[憨笑]", "[28]"),
    KJEMOJI29(0, 1, R.drawable.smiley_29, "[大兵]", "[29]"),
    KJEMOJI30(0, 1, R.drawable.smiley_30, "[奋斗]", "[30]"),
    KJEMOJI31(0, 1, R.drawable.smiley_31, "[咒骂]", "[31]"),
    KJEMOJI32(0, 1, R.drawable.smiley_32, "[疑问]", "[32]"),
    KJEMOJI33(0, 1, R.drawable.smiley_33, "[嘘]", "[33]"),
    KJEMOJI34(0, 1, R.drawable.smiley_34, "[晕]", "[34]"),
    KJEMOJI35(0, 1, R.drawable.smiley_35, "[折磨]", "[35]"),
    KJEMOJI36(0, 1, R.drawable.smiley_36, "[衰]", "[36]"),
    KJEMOJI37(0, 1, R.drawable.smiley_37, "[骷髅]", "[37]"),
    KJEMOJI38(0, 1, R.drawable.smiley_38, "[敲打]", "[38]"),
    KJEMOJI39(0, 1, R.drawable.smiley_39, "[再见]", "[39]"),
    KJEMOJI40(0, 1, R.drawable.smiley_40, "[擦汗]", "[40]"),
    KJEMOJI41(0, 1, R.drawable.smiley_41, "[抠鼻]", "[41]"),
    KJEMOJI42(0, 1, R.drawable.smiley_42, "[鼓掌]", "[42]"),
    KJEMOJI43(0, 1, R.drawable.smiley_43, "[糗大了]", "[43]"),
    KJEMOJI44(0, 1, R.drawable.smiley_44, "[坏笑]", "[44]"),
    KJEMOJI45(0, 1, R.drawable.smiley_45, "[左哼哼]", "[45]"),
    KJEMOJI46(0, 1, R.drawable.smiley_46, "[右哼哼]", "[46]"),
    KJEMOJI47(0, 1, R.drawable.smiley_47, "[哈欠]", "[47]"),
    KJEMOJI48(0, 1, R.drawable.smiley_48, "[鄙视]", "[48]"),
    KJEMOJI49(0, 1, R.drawable.smiley_49, "[委屈]", "[49]"),
    KJEMOJI50(0, 1, R.drawable.smiley_50, "[快哭了]", "[50]"),
    KJEMOJI51(0, 1, R.drawable.smiley_51, "[阴险]", "[51]"),
    KJEMOJI52(0, 1, R.drawable.smiley_52, "[亲亲]", "[52]"),
    KJEMOJI53(0, 1, R.drawable.smiley_53, "[吓]", "[53]"),
    KJEMOJI54(0, 1, R.drawable.smiley_54, "[可怜]", "[54]"),
    KJEMOJI55(0, 1, R.drawable.smiley_55, "[菜刀]", "[55]"),
    KJEMOJI56(0, 1, R.drawable.smiley_56, "[西瓜]", "[56]"),
    KJEMOJI57(0, 1, R.drawable.smiley_57, "[啤酒]", "[57]"),
    KJEMOJI58(0, 1, R.drawable.smiley_58, "[篮球]", "[58]"),
    KJEMOJI59(0, 1, R.drawable.smiley_59, "[乒乓]", "[59]"),
    KJEMOJI60(0, 1, R.drawable.smiley_60, "[咖啡]", "[60]"),
    KJEMOJI61(0, 1, R.drawable.smiley_61, "[饭]", "[61]"),
    KJEMOJI62(0, 1, R.drawable.smiley_62, "[猪头]", "[62]"),
    KJEMOJI63(0, 1, R.drawable.smiley_63, "[玫瑰]", "[63]"),
    KJEMOJI64(0, 1, R.drawable.smiley_64, "[凋谢]", "[64]"),
    KJEMOJI65(0, 1, R.drawable.smiley_65, "[嘴唇]", "[65]"),
    KJEMOJI66(0, 1, R.drawable.smiley_66, "[爱心]", "[66]"),
    KJEMOJI67(0, 1, R.drawable.smiley_67, "[心碎]", "[67]"),
    KJEMOJI68(0, 1, R.drawable.smiley_68, "[蛋糕]", "[68]"),
    KJEMOJI69(0, 1, R.drawable.smiley_69, "[闪电]", "[69]"),
    KJEMOJI70(0, 1, R.drawable.smiley_70, "[炸弹]", "[70]"),
    KJEMOJI71(0, 1, R.drawable.smiley_71, "[刀]", "[71]"),
    KJEMOJI72(0, 1, R.drawable.smiley_72, "[足球]", "[72]"),
    KJEMOJI73(0, 1, R.drawable.smiley_73, "[瓢虫]", "[73]"),
    KJEMOJI74(0, 1, R.drawable.smiley_74, "[便便]", "[74]"),
    KJEMOJI75(0, 1, R.drawable.smiley_75, "[月亮]", "[75]"),
    KJEMOJI76(0, 1, R.drawable.smiley_76, "[太阳]", "[76]"),
    KJEMOJI77(0, 1, R.drawable.smiley_77, "[礼物]", "[77]"),
    KJEMOJI78(0, 1, R.drawable.smiley_78, "[拥抱]", "[78]"),
    KJEMOJI79(0, 1, R.drawable.smiley_79, "[强]", "[79]"),
    KJEMOJI80(0, 1, R.drawable.smiley_80, "[弱]", "[80]"),
    KJEMOJI81(0, 1, R.drawable.smiley_81, "[握手]", "[81]"),
    KJEMOJI82(0, 1, R.drawable.smiley_82, "[胜利]", "[82]"),
    KJEMOJI83(0, 1, R.drawable.smiley_83, "[抱拳]", "[83]"),
    KJEMOJI84(0, 1, R.drawable.smiley_84, "[勾引]", "[84]"),
    KJEMOJI85(0, 1, R.drawable.smiley_85, "[拳头]", "[85]"),
    KJEMOJI86(0, 1, R.drawable.smiley_86, "[差劲]", "[86]"),
    KJEMOJI87(0, 1, R.drawable.smiley_87, "[爱你]", "[87]"),
    KJEMOJI88(0, 1, R.drawable.smiley_88, "[NO]", "[88]"),
    KJEMOJI89(0, 1, R.drawable.smiley_89, "[OK]", "[89]"),
    KJEMOJI90(0, 1, R.drawable.smiley_90, "[爱情]", "[90]"),
    KJEMOJI91(0, 1, R.drawable.smiley_91, "[飞吻]", "[91]"),
    KJEMOJI92(0, 1, R.drawable.smiley_92, "[跳跳]", "[92]"),
    KJEMOJI93(0, 1, R.drawable.smiley_93, "[发抖]", "[93]"),
    KJEMOJI94(0, 1, R.drawable.smiley_94, "[怄火]", "[94]"),
    KJEMOJI95(0, 1, R.drawable.smiley_95, "[转圈]", "[95]"),
    KJEMOJI96(0, 1, R.drawable.smiley_96, "[磕头]", "[96]"),
    KJEMOJI97(0, 1, R.drawable.smiley_97, "[回头]", "[97]"),
    KJEMOJI98(0, 1, R.drawable.smiley_98, "[跳绳]", "[98]"),
    KJEMOJI99(0, 1, R.drawable.smiley_99, "[投降]", "[99]"),
    KJEMOJI100(0, 1, R.drawable.smiley_100, "[激动]", "[100]"),
    KJEMOJI101(0, 1, R.drawable.smiley_101, "[乱舞]", "[101]"),
    KJEMOJI102(0, 1, R.drawable.smiley_102, "[献吻]", "[102]"),
    KJEMOJI103(0, 1, R.drawable.smiley_103, "[左太极]", "[103]"),
    KJEMOJI104(0, 1, R.drawable.smiley_104, "[右太极]", "[104]"),

    /*************************************Emoji表情***********************************/

    ;
    /**
     * qq emoji
     */
    public static final int QQ_TYPE = 0;

    /**
     * emoji类型
     */
    public static final int EMOJI_TYPE = 2;
    /*********************************
     * 操作
     **************************************/
    /**
     * 表情描述
     */
    private String emojiStr;
    /**
     * 网络传输匹配规则
     */
    private String remote;
    /**
     * 值
     */
    private int value;
    /**
     * 表情资源id
     */
    private int resId;
    /**
     * 表情类型
     */
    private int type;

    /**
     * 表情意义对应的resId
     */
    private static Map<String, Integer> sEmojiMap;

    EmojiRules(int type, int value, int resId, String emojiStr, String remote) {
        this.emojiStr = emojiStr;
        this.remote = remote;
        this.value = value;
        this.resId = resId;
        this.type = type;
    }

    public String getEmojiStr() {
        return emojiStr;
    }

    public String getRemote() {
        return remote;
    }

    public int getValue() {
        return value;
    }

    public int getResId() {
        return resId;
    }

    public int getType() {
        return type;
    }

    /**
     * 得到emoji信息
     *
     * @param data
     * @return
     */
    private static EmojiIcon getEmojiFromEnum(EmojiRules data) {
        return new EmojiIcon(data.getResId(), data.getValue(),
                data.getEmojiStr(), data.getRemote());
    }

    /**
     * 通过资源id得到emoji信息
     *
     * @param resId
     * @return
     */
    public static EmojiIcon getEmojiFromRes(int resId) {
        for (EmojiRules data : values()) {
            if (data.getResId() == resId) {
                return getEmojiFromEnum(data);
            }
        }
        return null;
    }

    /**
     * 通过value得到emoji信息
     *
     * @param value
     * @return
     */
    public static EmojiIcon getEmojiFromValue(int value) {
        for (EmojiRules data : values()) {
            if (data.getValue() == value) {
                return getEmojiFromEnum(data);
            }
        }
        return null;
    }

    /**
     * 通过 emojiStr得到表情信息
     *
     * @param emojiStr
     * @return
     */
    public static EmojiIcon getEmojiFromName(String emojiStr) {
        for (EmojiRules data : values()) {
            if (data.getEmojiStr().equals(emojiStr)) {
                return getEmojiFromEnum(data);
            }
        }
        return null;
    }

    /**
     * 获取表情意义对应的资源id
     * 提高效率，忽略线程安全
     */
    public static Map<String, Integer> getMapAll() {
        if (sEmojiMap == null) {
            sEmojiMap = new HashMap<String, Integer>();
            for (EmojiRules data : values()) {
                sEmojiMap.put(data.getEmojiStr(), data.getResId());
                sEmojiMap.put(data.getRemote(), data.getResId());
            }
        }
        return sEmojiMap;
    }

    /**
     * 通过类型获得所有该类型的表情信息
     *
     * @param type
     * @return
     */
    public static List<EmojiIcon> getAllByType(int type) {
        List<EmojiIcon> datas = new ArrayList<EmojiIcon>(values().length);
        for (EmojiRules data : values()) {
            if (data.getType() == type) {
                datas.add(getEmojiFromEnum(data));
            }
        }
        return datas;
    }
}
