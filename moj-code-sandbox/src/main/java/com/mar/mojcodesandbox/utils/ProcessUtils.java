package com.mar.mojcodesandbox.utils;

import cn.hutool.core.util.StrUtil;
import com.mar.mojcodesandbox.model.ExecuteMessage;
import org.springframework.util.StopWatch;

import java.io.*;

/**
 * @description；继承获取信息
 * @author:mar1
 * @data:2024/09/02
 **/
public class ProcessUtils {
    /**
     *
     * @param runProcess
     * @param opName
     * @return
     */
    public static ExecuteMessage runProcessAndGetMessage(Process runProcess, String opName){
        ExecuteMessage executeMessage = new ExecuteMessage();

        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            int exitValue = runProcess.waitFor();
            executeMessage.setExitValue(exitValue);
            if(exitValue == 0){
                //正常退出
                System.out.println(opName + "成功");
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(runProcess.getInputStream()));
                StringBuilder compileOutputStringBuilder = new StringBuilder();
                // 拿到控制台的输入流
                String compileOutputLine;
                while((compileOutputLine = bufferedReader.readLine()) != null){
                    compileOutputStringBuilder.append(compileOutputLine);
                }
                executeMessage.setMessage(compileOutputStringBuilder.toString());
            }
            else {
                // 异常退出
                System.out.println( opName + "失败，错误码" + exitValue);
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(runProcess.getInputStream()));
                StringBuilder compileOutputStringBuilder = new StringBuilder();
                // 拿到控制台的输入流（正常输出）
                String compileOutputLine;
                while((compileOutputLine = bufferedReader.readLine()) != null) {
                    compileOutputStringBuilder.append(compileOutputLine);
                }
                executeMessage.setMessage(compileOutputStringBuilder.toString());

                // 分批获取进程的输出
                BufferedReader errorBufferedReader=new BufferedReader(new InputStreamReader(runProcess.getErrorStream()));
                StringBuilder errorCompileOutputStringBuilder = new StringBuilder();

                // 拿到控制台的输入流
                String errorCompileOutputLine;
                while((errorCompileOutputLine = errorBufferedReader.readLine()) != null) {
                    errorCompileOutputStringBuilder.append(errorCompileOutputLine);
                }
                executeMessage.setErrorMessage(compileOutputStringBuilder.toString());
            }

            stopWatch.stop();
            executeMessage.setTime(stopWatch.getLastTaskTimeMillis());
        }catch (Exception e){
            e.printStackTrace();
        }
        return executeMessage;
    }

    /**
     * 交互式进程
     * @param runProcess
     * @param args
     * @return
     */
    public static ExecuteMessage runInterProcessAndGetMessage(Process runProcess,String args){
        ExecuteMessage executeMessage = new ExecuteMessage();
        try {
            OutputStream outputStream = runProcess.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            String[] s = args.split(" ");
            String join = StrUtil.join("\n",s)+ "\n";
            outputStreamWriter.write(join);
            outputStreamWriter.flush();
            InputStream inputStream = runProcess.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder compileOutputStringBuilder = new StringBuilder();
            // 拿到控制台的输入流
            String compileOutputLine;
            while((compileOutputLine = bufferedReader.readLine()) != null){
                compileOutputStringBuilder.append(compileOutputLine);
            }
            executeMessage.setMessage(compileOutputStringBuilder.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return executeMessage;
    }
}
