package com.heelenyc.research.misc;

import java.io.UnsupportedEncodingException;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sound.sampled.AudioFormat.Encoding;
import javax.swing.text.html.CSS;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.ObjectUtils.Null;

import com.heelenyc.research.common.mongo.MongoCollectionFactory.hubble;

/**
 * @author yicheng
 * @since 2014年2月25日
 * 
 */
public class TestString {

    /**
     * @param args
     */
    public static void main(String[] args) throws UnsupportedEncodingException {
        // String s = "a";
        // s = s + "b";
        // s.intern();
        // System.out.println(s == "ab");
        //
        // StringBuilder sbd = new StringBuilder(); // 非线程安全的。
        // StringBuffer sbfBuffer = new StringBuffer(); // 线程安全的。

        // String str = "zhangsan,lisi , sdfsdfn, sdfsdf ";
        // String[] array = str.split(",|:|;");
        // for (int i = 0; i < array.length; i++) {
        // array[i] = array[i].trim();
        // }
        // for (int i = 0; i < array.length; i++) {
        // System.out.println(array[i]);
        // }

        // Set<String> set = new HashSet<String>();
        // set.add("1");
        // set.add("2");
        // set.add("3");
        // set.add("4");
        // set.add("5");
        //
        // Object[] array = set.toArray();
        //
        // for (int i = 0; i < array.length; i++) {
        // System.out.println((String)array[i]);
        // }
        // Set<Object> top5 = new HashSet<Object>();
        // for (int i = 0; i < 5; i++) {
        // top5.add(array[i]);
        // }
        // String monitorKeySetStr = StringUtils.join(top5, ',');
        // System.out.println(monitorKeySetStr);

        // Calendar nowDate = Calendar.getInstance();
        // nowDate.set(Calendar.HOUR_OF_DAY, 0);
        // System.out.println(nowDate.getTime());
        // System.out.println(nowDate.get(Calendar.AM));
        // System.out.println(nowDate.get(Calendar.AM_PM));
        // System.out.println(nowDate.get(Calendar.HOUR));
        //
        // nowDate.set(Calendar.HOUR_OF_DAY, 12);
        // System.out.println(nowDate.getTime());
        // System.out.println(nowDate.get(Calendar.AM));
        // System.out.println(nowDate.get(Calendar.AM_PM));
        // System.out.println(nowDate.get(Calendar.HOUR));

        // Map<String, Object> info = new HashMap<String, Object>();
        // info.put("platform", "platform");
        // info.put("operator", "operator");
        // info.put("event", "event");
        // System.out.println(info.toString());
        // String a = null;
        // System.out.println((a+"").equals(""+null));

        // String str = "银行业 تღ测试存款类金融asd$^&fsdABAAA123123123";
        // String reg = "[^\u4e00-\u9fa5|0-9|A-Z|a-z]";
        // Pattern pat = Pattern.compile(reg);
        // Matcher mat = pat.matcher(str);
        // String repickStr = mat.replaceAll("");
        // System.out.println(repickStr);
        // System.out.println(new SimpleDateFormat("MMddHH").format(new
        // Date()));

        // Pattern p = Pattern.compile("^.*");
        // System.out.println(p.matcher("msg").find());
        // Pattern p = Pattern.compile("[ -]");
        // System.out.println(p.matcher(null).replaceAll(""));
        // System.out.println("a".getBytes(Charset.forName("UTF-8")).length);
//         byte[] byteset = "\u2018".getBytes(Charset.forName("UTF-8"));
//         for (int i = 0; i < byteset.length; i++) {
//         System.out.println(byteset[i]);
//         }
//        
//         char[] charSet = "\u2018".toCharArray();
//         for (int i = 0; i < charSet.length; i++) {
//         System.out.println(charSet[i]);
//         }
//        String testString = "\u2018\u2018\u2019\u2019错觉\u2026\u2026[0.35km]: \u2018\u2018\u2019\u2019错觉\u2026\u2026关注了你，你们已经是好友，订阅对方动态成";
//        Charset cs = Charset.forName("UTF-8");
//        char [] charSet = testString.toCharArray();
//        for (char c : charSet) {
//            CharBuffer cb = CharBuffer.allocate(1);
//            cb.put(c);cb.flip();
//            System.out.println(c);
//            System.out.println(cs.encode(cb).array().length);
//            
//        }
//        String testString = "123,23123 2323, 2323 , ";
//        String [] aString =testString.split(",| ");
//        for (String string : aString) {
//            System.out.println(string);
//        }
//        // System.out.println(new Date());
//        HashSet<String> h = new HashSet<String>();
//        h.add("hello");
//        h.add("world");
//        System.out.println(h);
//        String text = "能搜到防sdfsdf守打法";
//        System.out.println(text.substring(0, 5));
    	System.out.println(new SimpleDateFormat("yyyyMMdd").format(new Date()));
    }

    protected static String ensureTextLength(String alert, int maxCount) {
        if (alert != null && alert.getBytes(Charset.forName("UTF-8")).length > maxCount) {
            char[] charArray = alert.toCharArray();
            int len = 0;
            int bytelen = 0;
            for (int i = 0; i < charArray.length; i++) {
                char c = charArray[i];
                if (c > 0 && c < 255) {
                    bytelen += 1;
                } else {
                    bytelen += 3;
                }
                if (bytelen < maxCount) {
                    len++;
                } else {
                    break;
                }
            }
            return alert.substring(0, len);
        }
        return alert;
    }

}
