package com.mikegu.tools.compile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaFileObject;

/**
 * 编译结果
 */
public class CompileResult {

    /** 存放编译过程中的错误信息 */
    private final DiagnosticCollector<JavaFileObject> diagnostics;

    /** 编译结果 */
    private final boolean                             compileSuccess;

    /**
     * 构造函数
     *
     * @param compileSuccess    -存放编译过程中的错误信息
     * @param diagnostics   -编译结果
     */
    public CompileResult(boolean compileSuccess, DiagnosticCollector<JavaFileObject> diagnostics) {
        this.compileSuccess = compileSuccess;
        this.diagnostics = diagnostics;
    }

    /**
     * 默认构造函数
     */
    public CompileResult() {
        this(true, null);
    }

    /**
     * 默认构造函数
     * @param compileSuccess
     */
    public CompileResult(boolean compileSuccess) {
        this(compileSuccess, null);
    }

    /**
     * Getter method for property <tt>diagnostics</tt>.
     *
     * @return property value of diagnostics
     */
    public DiagnosticCollector<JavaFileObject> getDiagnostics() {
        return diagnostics;
    }

    /**
     * Getter method for property <tt>compileSuccess</tt>.
     *
     * @return property value of compileSuccess
     */
    public boolean isCompileSuccess() {
        return compileSuccess;
    }

    /**
     * 取得编译失败的java文件名称<pre/>
     * 其中java文件名称，包含绝对路径
     *
     * @return
     */
    public List<File> getCompileFailJavaFileName() {
        List<Diagnostic<? extends JavaFileObject>> errors = this.getDiagnostics().getDiagnostics();
        List<File> fileList = new ArrayList<File>();
        for (Diagnostic<? extends JavaFileObject> error : errors) {
            fileList.add(new File(String.valueOf(error.getSource())));
        }
        return fileList;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {

        //编译成功
        if (compileSuccess) {
            return "compileSuccess:" + compileSuccess;
        }
        //编译失败
        else {
            StringBuffer sb = new StringBuffer(100);
            sb.append("compileSuccess:" + compileSuccess + "\n");
            for (Diagnostic<?> diagnostic : diagnostics.getDiagnostics()) {
                sb.append(diagnostic.getMessage(null));
                sb.append("\n");
            }
            return sb.toString();
        }
    }

}
