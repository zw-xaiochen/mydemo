package com.cjy.util.ffmpeg;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by tonydeng on 15/4/17.
 */
public class FileUtils {

    private static final String EXTEND_MP4 = ".mp4";

    private static final String EXTEND_MP3 = ".mp3";

    private static final String EXTEND_WAV = ".wav";

    private FileUtils() {
    }

    /**
     * 获得文件大小
     */
    public static long getFineSize(File input) {
        if (input != null && input.exists()) {
            try (InputStream in = Files.newInputStream(input.toPath())) {
                return in.available();
            } catch (IOException e) {
                /*if (log.isErrorEnabled()) {
                    log.error("getFineSize error:'{}'", e.getMessage());
                }
                log.error("getFineSize", e);*/
                System.out.println("getFineSize");
            }
        }
        return 0;
    }

    /**
     * 根据视频文件获得生成的mp4文件地址
     */
    public static File getMp4OutputByInput(File input) {
        String videoName = getFileName(input);
        if (null == videoName) {
            videoName = "";
        }
        return new File(input.getParent() + videoName + EXTEND_MP4);
    }

    /**
     * 根据视频文件获得生成的mp3文件地址
     */
    public static File getMp3OutputByInput(File input) {
        String videoName = getFileName(input);
        if (StringUtils.isNotEmpty(videoName) && !"mp3".equals(getFileExtend(input))) {
            return new File(input.getParent() + File.separator + videoName + EXTEND_MP3);
        }
        return null;
    }

    /**
     * 根据视频文件获得生成的mav文件地址
     */
    public static File getWAVOutputByInput(File input) {
        String videoName = getFileName(input);
        if (StringUtils.isNotEmpty(videoName) && !"wav".equals(getFileExtend(input))) {
            return new File(input.getParent() + File.separator + videoName + EXTEND_WAV);
        }
        return null;
    }

    /**
     * 创建目录
     */
    public static boolean createDirectory(String path) {
        if (StringUtils.isNotEmpty(path)) {
            return createDirectory(new File(path));
        }
        return false;
    }

    /**
     * 创建目录
     */
    public static boolean createDirectory(File path) {
        if (path.exists()) {
            return path.isDirectory();
        } else {
            return path.mkdirs();
        }
    }

    /**
     * 获得文件名
     */
    public static String getFileName(File file) {
        if (file != null && file.exists() && file.isFile()) {
            if (file.getName().lastIndexOf(".") > 0) {
                return file.getName().substring(0, file.getName().lastIndexOf(".")).toLowerCase();
            } else {
                return file.getName();
            }

        }
        return null;
    }

    /**
     * 获得文件扩展名
     */
    public static String getFileExtend(File file) {
        if (file != null && file.exists() && file.isFile() && file.getName().lastIndexOf(".") > 0) {
            return file.getName().substring(file.getName().lastIndexOf(".") + 1).toLowerCase();
        }
        return null;
    }

    /**
     * 获取文件的大小,转换为M
     */
    public static String bytes2Mb(long bytes) {
        BigDecimal filesize = new BigDecimal(bytes);
        BigDecimal megabyte = new BigDecimal(1024 * 1024);
        float returnValue = filesize.divide(megabyte, 2, RoundingMode.UP).floatValue();
        return returnValue + "";
    }

    public static void writeFile(String srcPath, OutputStream destination) {
        byte[] buff = new byte[1024];
        int len;
        try (BufferedInputStream bis = new BufferedInputStream(Files.newInputStream(Paths.get(srcPath)));
            BufferedOutputStream bos = new BufferedOutputStream(destination)) {
            while ((len = bis.read(buff)) != -1) {
                bos.write(buff, 0, len);
            }
        } catch (Exception e) {
            //log.error("写文件失败！");
            System.out.println("写文件失败");

        }
    }

    public static void writeFile(InputStream src, OutputStream destination) {
        byte[] buff = new byte[1024];
        int len;
        try (BufferedInputStream bis = new BufferedInputStream(src);
            BufferedOutputStream bos = new BufferedOutputStream(destination)) {
            while ((len = bis.read(buff)) != -1) {
                bos.write(buff, 0, len);
            }
        } catch (Exception e) {
            //log.error("写文件失败！");
            System.out.println("写文件失败");
        }
    }

    public static void writeFile(byte[] data, String outPath) {
        try (BufferedOutputStream bos = new BufferedOutputStream(Files.newOutputStream(Paths.get(outPath)))) {
            bos.write(data);
        } catch (Exception e) {
            System.out.println("写文件失败");
            //log.error("写文件失败！");
        }
    }

}
