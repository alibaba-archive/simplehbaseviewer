package com.alipay.hbaseviewer.home;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * CommandForm
 * 
 * @author xinzhi.zhang
 * */
public class CommandForm {
    private String  fullCommand;

    private String  command;
    private boolean columnMode;

    public boolean isColumnMode() {
        return columnMode;
    }

    public void setColumnMode(boolean columnMode) {
        this.columnMode = columnMode;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getFullCommand() {
        return fullCommand;
    }

    public void setFullCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
