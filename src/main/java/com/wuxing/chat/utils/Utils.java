package com.wuxing.chat.utils;

import com.wuxing.chat.pojo.Dirty;

import java.util.List;

/**
 * Created by zx on 2017/6/19.
 */
public class Utils {

    public static String checkDirty(String msg, List<Dirty> dirties) {
        for (int o = 0 ;o < dirties.size(); o++) {
            if(msg.contains(dirties.get(o).getWord())) {
                int size = dirties.get(o).getWord().length();
                int i = msg.indexOf(dirties.get(o).getWord());
                StringBuffer star = new StringBuffer();
                for (int j = 0; j < size; j++) {
                    star.append("*");
                }
                String substring = msg.substring(0, i);
                String substring1 = msg.substring(i + size, msg.length());
                msg = substring + star.toString() + substring1;
                o--;
            }
        }
        return msg;
    }
}
