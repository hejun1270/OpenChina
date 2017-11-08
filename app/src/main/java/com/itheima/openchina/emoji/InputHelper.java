
package com.itheima.openchina.emoji;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.view.KeyEvent;
import android.widget.EditText;

import com.itheima.openchina.utils.TextDevice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 表情输入帮助类
 */
public class InputHelper {
    public static void backspace(EditText editText) {
        if (editText == null) {
            return;
        }
        KeyEvent event = new KeyEvent(0, 0, 0, KeyEvent.KEYCODE_DEL, 0, 0, 0,
                0, KeyEvent.KEYCODE_ENDCALL);
        editText.dispatchKeyEvent(event);
    }

    /**
     * 获取name对应的资源
     */
    public static int getEmojiResId(String name) {
        Integer res = EmojiRules.getMapAll().get(name);
        if (res != null) {
            return res;
        } else {
            return -1;
        }
    }

    /**
     * Support OSChina Client，due to the need to support both 2 Format<br>
     * (I'm drunk, I go home)
     */
    public static Spannable displayEmoji(Resources res, CharSequence s) {
        return displayEmoji(res, new SpannableString(s));
    }

    public static Spannable displayEmoji(Resources res, Spannable spannable) {
        String str = spannable.toString();

        if (!str.contains(":") && !str.contains("[")) {
            return spannable;
        }

        Pattern pattern = Pattern.compile("(\\[[^\\[\\]:\\s\\n]+\\])|(:[^:\\[\\]\\s\\n]+:)");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            String emojiStr = matcher.group();
            if (TextUtils.isEmpty(emojiStr)) continue;
            int resId = getEmojiResId(emojiStr);
            if (resId <= 0) continue;
            Drawable drawable = res.getDrawable(resId);
            if (drawable == null) continue;
            drawable.setBounds(0, 0, (int) TextDevice.dpToPixel(25), (int) TextDevice.dpToPixel
                    (25));
            ImageSpan span = new ImageSpan(drawable, ImageSpan.ALIGN_BOTTOM);
            spannable.setSpan(span, matcher.start(), matcher.end(), Spannable
                    .SPAN_INCLUSIVE_EXCLUSIVE);
        }

        return spannable;
    }

    public static void input2OSC(EditText editText, EmojiIcon emojicon) {
        if (editText == null || emojicon == null) {
            return;
        }
        int start = editText.getSelectionStart();
        int end = editText.getSelectionEnd();
        if (start < 0) {
            // 没有多选时，直接在当前光标处添加
            editText.append(displayEmoji(editText.getResources(),
                    emojicon.getRemote()));
        } else {
            // 将已选中的部分替换为表情(当长按文字时会多选刷中很多文字)
            Spannable str = displayEmoji(editText.getResources(),
                    emojicon.getRemote());
            editText.getText().replace(Math.min(start, end),
                    Math.max(start, end), str, 0, str.length());
        }
    }
}
