package com.mikegu.tools.compile;

/**
 * �ű��쳣
 */
public class ScriptException extends Exception {

    /** ���л�ID */
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
