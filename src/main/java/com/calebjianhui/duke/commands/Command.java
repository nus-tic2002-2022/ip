package com.calebjianhui.duke.commands;

/**
 * A Command class representing the command given by the user and the
 * relevant actions to be executed with accordance to the command.
 */
public abstract class Command {

    /**
     * Execute the specified command.
     *
     * @return Whether the command made any changes to the task list
     */
    public abstract boolean execute();


}
