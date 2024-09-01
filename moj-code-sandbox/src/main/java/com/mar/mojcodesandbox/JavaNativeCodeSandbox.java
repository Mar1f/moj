package com.mar.mojcodesandbox;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import com.mar.mojcodesandbox.model.ExecuteCodeRequest;
import com.mar.mojcodesandbox.model.ExecuteCodeResponse;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @description；
 * @author:mar1
 * @data:2024/08/31
 **/
public class JavaNativeCodeSandbox implements CodeSandbox {

    private static final String GLOBAL_CODE_DIR_NAME = "tmpCode";

    private static final String GLOBAL_JAVA_CLASS_NAME = "Main.java";

    public static void main(String[] args) {
        JavaNativeCodeSandbox javaNativeCodeSandbox = new JavaNativeCodeSandbox();
        ExecuteCodeRequest executeCodeRequest = new ExecuteCodeRequest();
        executeCodeRequest.setInputList(Arrays.asList("1 2","1 3"));
        String code = ResourceUtil.readStr("testCode/simpleComputeArgs/Main.java", StandardCharsets.UTF_8);
        executeCodeRequest.setCode(code);
        executeCodeRequest.setLanguage("java");
        ExecuteCodeResponse executeCodeResponse = javaNativeCodeSandbox.executeCode(executeCodeRequest);
        System.out.println(executeCodeResponse);
    }

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        List<String> inputList = executeCodeRequest.getInputList();
        String code = executeCodeRequest.getCode();
        String language = executeCodeRequest.getLanguage();
        String userDir = System.getProperty("user.dir");
        String globalCodePathName = userDir + File .separator + GLOBAL_CODE_DIR_NAME;
        //判断全局代码目录是否存在
        if(FileUtil.exist(globalCodePathName)){
            FileUtil.mkdir(globalCodePathName);
        }
        // 把用户的代码隔离存放
        String userCodeParentPath = globalCodePathName + File.separator + UUID.randomUUID();
        String userCodePath = userCodeParentPath + File.separator + GLOBAL_JAVA_CLASS_NAME;
        File userCodeFile = FileUtil.writeString(code, userCodePath, StandardCharsets.UTF_8);

        // 编译代码，得到class文件
        String compileCmd = String.format("javac -encoding utf-8 %s", userCodeFile.getAbsolutePath());
        try {
            Process compileProcess = Runtime.getRuntime().exec(compileCmd);
            int exitValue = compileProcess.waitFor();
            if(exitValue == 0){
                //正常退出
                System.out.println("编译成功");
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(compileProcess.getInputStream()));
                // 拿到控制台的输入流
                String compileOutputLine;
                while((compileOutputLine = bufferedReader.readLine()) != null){
                    System.out.println(compileOutputLine);
                }
            }
            else {
                // 异常退出
                System.out.println("编译失败，错误码" + exitValue);
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(compileProcess.getInputStream()));
                // 拿到控制台的输入流（正常输出）
                String compileOutputLine;
                while((compileOutputLine = bufferedReader.readLine()) != null) {
                    System.out.println(compileOutputLine);
                }
                // 分批获取进程的输出
                BufferedReader errorBufferedReader=new BufferedReader(new InputStreamReader(compileProcess.getErrorStream()));
                // 拿到控制台的输入流
                String errorCompileOutputLine;
                while((errorCompileOutputLine = errorBufferedReader.readLine()) != null) {
                    System.out.println(errorCompileOutputLine);
                }
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
