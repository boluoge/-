package com.chenway.ssm.domain;

import com.chenway.ssm.util.CallLogUtil;

/**
 * CallLog
 */
public class CallLog {
    private String caller;
    private String callee;
    private String callTime;
    private String callDuration;
    private boolean flag;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getCaller() {
        return caller;
    }

    public void setCaller(String caller) {
        this.caller = caller;
    }

    public String getCallee() {
        return callee;
    }

    public void setCallee(String callee) {
        this.callee = callee;
    }

    public String getCallTime() {
        if (callTime != null) {
            return CallLogUtil.formatDate(callTime);
        }
        return null;
    }

    public void setCallTime(String callTime) {
        this.callTime = callTime;
    }

    public String getCallDuration() {
        return callDuration;
    }

    public void setCallDuration(String callDuration) {
        this.callDuration = callDuration;
    }
}
