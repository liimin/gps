package com.lj.gps.frame.utils;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author mym
 */
public class FileZip {

    /**
     * @param bList 数组输出流
     * @param out   zip输出流
     */
    public static void zipFiles(List<ByteArrayOutputStream> bList, ZipOutputStream out, String title) {
        byte[] buf = new byte[1024 * 4];
        try {

            for (int i = 0; i < bList.size(); i++) {
                ByteArrayOutputStream baos = bList.get(i);
                ByteArrayInputStream in = new ByteArrayInputStream(baos.toByteArray());
                out.putNextEntry(new ZipEntry(title + "_" + i + ".xlsx"));
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                out.closeEntry();
                in.close();
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}