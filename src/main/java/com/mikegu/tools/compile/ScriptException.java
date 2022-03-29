package com.mikegu.tools.compile;

/**
 * 脚本异常
 */
public class ScriptException extends Exception {

    /** 序列化ID */
    private static final long serialVersionUID = 1L;

    /**
     * @param message
     * @param cause
     */
    public ScriptException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public ScriptException(String message) {
        this(message, null);
    }
}
