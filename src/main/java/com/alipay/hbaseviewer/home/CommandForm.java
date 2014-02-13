package com.alipay.hbaseviewer.home;

public class CommandForm {

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
}
