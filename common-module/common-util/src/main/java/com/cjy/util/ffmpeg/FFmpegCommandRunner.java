package com.cjy.util.ffmpeg;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class FFmpegCommandRunner {

    public static void main(String[] args) {
        File file = new File("D:/ffmpeg/CH7-20230814073936-20230814074019.avi");
        System.out.println(copyToMp4(file));
    }
    /**
     * 复制到mp4文件
     */
    public static VideoFile copyToMp4(File input) {
        VideoFile vf = null;
        if (checkContentType(input.getPath()) == 0) {
            vf = new VideoFile(input, FileUtils.getMp4OutputByInput(input));
            if (vf.getTarget() != null && !vf.getTarget().exists()) {
                vf.setInputInfo(getVideoInfo(input));
                if (vf.getInputInfo().getSize() > 0) {
                    List<String> commands = Lists.newArrayList(BaseCommandOption.getFFmpegBinary());

                    commands.addAll(BaseCommandOption
                            .copyMP4CmdArrays(input.getAbsolutePath(), vf.getTarget().getAbsolutePath(),
                                    vf.getInputInfo()));

                    if (StringUtils.isNotEmpty(runProcess(commands))) {
                        vf.setTargetInfo(getVideoInfo(vf.getTarget()));
                        vf.setSuccess(true);
                        return vf;
                    }
                }

            }
        }
        return vf;
    }

    /**
     * 获取视频信息
     */
    public static VideoInfo getVideoInfo(File input) {
        VideoInfo vi = new VideoInfo();
        if (input != null && input.exists()) {
            List<String> commands = Lists.newArrayList(BaseCommandOption.getFFprobeBinary());
            commands.add(input.getAbsolutePath());
            vi.setSize(FileUtils.getFineSize(input));
            if (vi.getSize() > 0) {
                return FFmpegUtils.regInfo(runProcess(commands), vi);
            }
        } else {
            System.out.println("\"video '{}' is not fount! \", input.getAbsolutePath()");
        }

        return vi;
    }

    private static int checkContentType(String path) {
        String type = path.substring(path.lastIndexOf(".") + 1).toLowerCase();
        // ffmpeg能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv, ts等）
        // 对ffmpeg无法解析的文件格式(wmv9，rm，rmvb等),
        // 可以先用别的工具（mencoder）转换为avi(ffmpeg能解析的)格式.
        switch (type) {
            case "avi":
            case "mpg":
            case "wmv":
            case "3gp":
            case "h264":
            case "264":
            case "dat":
            case "flv":
            case "asx":
            case "asf":
            case "mp4":
            case "mov":
            case "ts":
                return 0;
            case "wmv9":
            case "rmvb":
            case "rm":
                return 1;
            default:
                return 9;
        }
    }

    /**
     * 执行命令
     */
    public static String runProcess(List<String> commands) {
        long start = System.currentTimeMillis();
        ProcessBuilder pb = new ProcessBuilder(commands);

        pb.redirectErrorStream(true);

        String result = null;
        Process process = null;
        try {
            process = pb.start();
            result = getExecuteResult(process);
            int flag = process.waitFor();
            if (flag != 0) {
                return null;
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            //log.error("errorStream:", e);
            System.out.println("errorStream");
        } finally {
            if (null != process) {
                try {
                    process.getInputStream().close();
                    process.getOutputStream().close();
                    process.getErrorStream().close();
                    process.destroy();
                } catch (IOException e) {
                    System.out.println("Close stream error");
                    //log.error("Close stream error:{}", result, e);
                }
            }
            /*if (log.isInfoEnabled()) {
                log.info("ffmpeg run cost {} milliseconds", System.currentTimeMillis() - start);
            }*/
        }
        return result;
    }
    private static String getExecuteResult(Process process) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = input.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

}
