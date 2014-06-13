package com.mikegu.tools.compile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaFileObject;

/**
 * ������
 */
public class CompileResult {

    /** ��ű�������еĴ�����Ϣ */
    private final DiagnosticCollector<JavaFileObject> diagnostics;

    /** ������ */
    private final boolean                             compileSuccess;

    /**
     * ���캯��
     * 
     * @param compileSuccess    -��ű�������еĴ�����Ϣ
     * @param diagnostics   -������
     */
    public CompileResult(boolean compileSuccess, DiagnosticCollector<JavaFileObject> diagnostics) {
        this.compileSuccess = compileSuccess;
        this.diagnostics = diagnostics;
    }

    /**
     * Ĭ�Ϲ��캯��
     */
    public CompileResult() {
        this(true, null);
    }

    /**
     * Ĭ�Ϲ��캯��
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
     * ȡ�ñ���ʧ�ܵ�java�ļ�����<pre/>
     * ����java�ļ����ƣ���������·��
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

        //����ɹ�
        if (compileSuccess) {
            return "compileSuccess:" + compileSuccess;
        }
        //����ʧ��
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
