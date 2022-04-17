package com.calebjianhui.duke.parser;

import com.calebjianhui.duke.commands.AddCommand;
import com.calebjianhui.duke.commands.Command;
import com.calebjianhui.duke.commands.ExitCommand;
import com.calebjianhui.duke.commands.InvalidCommand;
import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class InputParserTest {

    private Command provideInput(String data) {
        return new InputParser(new Scanner(new ByteArrayInputStream(data.getBytes()))).parseCommand();
    }

    @Test
    void parseCommand_validExitCommands_success() {
        Command parsedCommand = provideInput("bye");
        assertTrue(parsedCommand.getClass().isAssignableFrom(ExitCommand.class));
    }

    @Test
    void parseCommand_validAddCommands_success() {
        // [AddCommand]
        // - Add ToDos
        Command parsedCommand = provideInput("todo Relax");
        assertTrue(parsedCommand.getClass().isAssignableFrom(AddCommand.class));
        // - Add Deadline
        Command parsedCommand2 = provideInput("deadline Submit Report /by 17/04/2022 23:59");
        assertTrue(parsedCommand2.getClass().isAssignableFrom(AddCommand.class));
        // - Add Event
        Command parsedCommand3 = provideInput("event Exam /at 13/04/2022 12pm");
        assertTrue(parsedCommand3.getClass().isAssignableFrom(AddCommand.class));
        // - Add Fixed Duration Task
        Command parsedCommand4 = provideInput("fixed Intensive studying /needs 2hour");
        assertTrue(parsedCommand4.getClass().isAssignableFrom(AddCommand.class));
    }

    @Test
    void parseCommand_invalidCommands_fail() {
        // [AddCommand]
        // - ToDo with no spacing
        Command parsedCommand = provideInput("todoRelax");
        assertTrue(parsedCommand.getClass().isAssignableFrom(InvalidCommand.class));
        // [List Command, Invalid Parameters]
        Command parsedCommand2 = provideInput("list -t");
        assertTrue(parsedCommand2.getClass().isAssignableFrom(InvalidCommand.class));
    }
}