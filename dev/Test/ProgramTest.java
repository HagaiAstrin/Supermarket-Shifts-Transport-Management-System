package Test;

import Domain.IO_Data;
import Presentation.Program;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;

public class ProgramTest {

    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;
    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;
    private Program program;

    @Before
    public void setUp() {
        program = new Program();
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    @After
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
        Program.scanner = new Scanner(testIn);
    }

    private String getOutput() {
        return testOut.toString();
    }

    @Test
    public void testSelectBranch() {
        List<String> branches = new ArrayList<>();
        branches.add("Be'er Sheva");
        branches.add("Haifa");
        branches.add("Test_Data");

        // Assuming IO_Data.listFoldersInDirectory() returns these branches
        IO_Data.branch = branches.get(0);

        provideInput("0\n");

        Program.SelectBranch();

        String output = getOutput();
        assertTrue(output.contains("Please select branch:"));
        assertTrue(output.contains("0. Be'er Sheva"));
        assertTrue(output.contains("1. Haifa"));
        assertTrue(output.contains("2. Test_Data"));
        assertEquals("Be'er Sheva", IO_Data.branch);
    }


    @Test
    public void testInvalidLogin() throws IOException, InterruptedException {
        // Provide invalid login credentials
        provideInput("1\nadmin\nwrongpass\nadmin\nadminpass\n123\n");

        IO_Data.branch = "Test_Data";
        try {
            program.login();
        }
        catch (Exception e) {

        }

        String output = getOutput();
        assertTrue(output.contains("Wrong input, please try again: "));
    }
}