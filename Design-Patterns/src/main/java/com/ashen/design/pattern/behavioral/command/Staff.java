package com.ashen.design.pattern.behavioral.command;

import java.util.ArrayList;
import java.util.List;

/**
 * 命令接收者，员工类
 */
public class Staff {
    private List<Command> commandList = new ArrayList<>();

    // 接收命令方法
    public void addCommand(Command command) {
        commandList.add(command);
    }

    // 执行命令方法
    public void executeCommands() {
        for (Command command : commandList) {
            command.execute();
        }
        commandList.clear();
    }
}
