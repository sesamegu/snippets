package com.mikegu.tools.compile;

import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;
public class FileOperateUtil {

    /**
     * ����sourceDirĿ¼�����е�java�ļ�
     * 
     * @param sourceDir -Դ�ļ���,��Ҫ������
     *                      <li>�ļ���Ŀ¼�������</li>
     *                      <li>����Ϊ�ļ���·��,�����ļ�·��</li>
     *                       
     * @return   -����java�ļ��б�
     *            <li>-if sourceDir path is not exist ,throw IllegalArgumentException</li>
     *            <li>-if sourceDir path is not directory,throw IllegalArgumentException</li>
     */
    public static List<File> findSourceFiles(String sourceDir) {

        File sourceFile = new File(sourceDir);

        //�ļ���·��������
        if (!sourceFile.exists()) {
            throw new IllegalArgumentException(sourceDir + " path is not exist!");
        }
        //���ļ���Ŀ¼
        if (!sourceFile.isDirectory()) {
            throw new IllegalArgumentException(sourceDir + " path is not directory!");
        }

        //����java�ļ�
        File[] childrenFiles = sourceFile.listFiles(new FileFilter() {
            /**
             * @see java.io.FileFilter#accept(java.io.File)
             */
            public boolean accept(File pathname) {
                if (pathname.isDirectory()) {
                    return false;
                } else {
                    String name = pathname.getName();
                    return name.endsWith(".java") ? true : false;
                }
            }
        });

        return Arrays.asList(childrenFiles);
    }

    /**
     * ��ȡfilePathsĿ¼�������ļ����ļ���
     * @param filePaths �ļ�·���б�
     * @param fileNames �����ļ�����
     */
    public static void getFilesWithFilter(String[] filePaths, List<String> fileNames) {
        for (String filePath : filePaths) {
            File file = findFile(filePath);
            if (!file.exists()) {
                continue;
            }
            if (file.isDirectory()) {
                List<String> subFiles = new ArrayList<String>();
                for (String subFile : file.list()) {
                    subFiles.add(filePath + File.separator + subFile);
                }
                getFilesWithFilter(subFiles.toArray(new String[subFiles.size()]), fileNames);
            }
            File[] subFiles = file.listFiles(new FileFilter() {
                /**
                 * @see java.io.FileFilter#accept(java.io.File)
                 */
                public boolean accept(File pathname) {
                    if (!StringUtils.endsWith(pathname.getAbsolutePath(),
 ".java")
                        || !pathname.isFile()) {
                        return false;
                    }
                    return true;
                }
            });
            if (subFiles == null) {
                continue;
            }
            for (File subFile : subFiles) {
                if (subFile.isFile()) {
                    fileNames.add(subFile.getAbsolutePath());
                }
            }
        }
    }

    //֧�־���·�������·�����ļ�����
    private static File findFile(String filePath) {

        if (isBlank(filePath)) {
            return null;
        }
        if (filePath.startsWith(File.separator)) {
            return new File(filePath);
        } else {
            URL url = FileOperateUtil.class.getClassLoader().getResource(filePath);
            if (url == null)
                return null;

            return new File(url.getFile());
        }

    }

    /**
     * �����ļ�
     * 
     * @param filePath  �ļ��ľ���·��
     * @param text  д�ļ�������
     * @throws IOException 
     */
    public static void writeFile(String filePath, String text) throws IOException {
        FileWriter fw = null;
        try {
            fw = new FileWriter(filePath);
            fw.write(text);
        } catch (IOException e) {
            System.err.println("soucrceFilesNotDirectory error" + e);
        } finally {
            if (fw != null) {
                fw.close();
            }
        }
    }

    /**
     * ��ȡfilePathĿ¼�������ļ����ļ���
     * 
     * @param filePath
     * @return
     */
    public static List<String> getFiles(String filePath) {
        List<String> fileNames = new ArrayList<String>();
        File file = new File(filePath);
        if (file.exists()) {
            File[] subFiles = file.listFiles();
            for (File subFile : subFiles) {
                if (subFile.isFile()) {
                    fileNames.add(subFile.getAbsolutePath());
                }
            }
        }
        return fileNames;
    }

    /**
     * �����ļ���
     * 
     * @param path  -�ļ�·��
     * @return
     */
    public static boolean mkdirs(String filePath) {
        File sourceFile = new File(filePath);
        if (sourceFile.exists()) {
            return true;
        } else {
            return sourceFile.mkdirs();
        }
    }

    /**
     * ɾ��ָ���ļ��������ļ��������ļ����ļ��У�
     * 
     * @param file
     * @return
     */
    public static boolean rmdirs(File file) {
        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {
                File files[] = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    rmdirs(files[i]);
                }
            }
            file.delete();

        }
        return true;
    }

    /**
     * ����ɾ���ļ�
     * 
     * @param files
     * @return
     */
    public static boolean rmFiles(List<File> files) {
        boolean result = true;
        if (CollectionUtils.isEmpty(files)) {
            return result;
        }
        for (File file : files) {
            if (!rmdirs(file)) {
                return false;
            }
        }
        return result;
    }

    /**
     * ɾ��ָ���ļ��������ļ��������ļ����ļ��У�
     * 
     * @param filePath  �ļ���·��
     * @return
     */
    public static boolean rmdirs(String filePath) {
        return rmdirs(new File(filePath));
    }


    private static boolean isBlank(String abc) {
        return abc == null ? true : abc.trim().equals("");
    }
}
