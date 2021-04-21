package com.pet.project.logging;

import org.testng.Reporter;

import java.io.OutputStream;
import java.io.PrintStream;

public class RestAssuredLogger {
    private PrintStream myPrintStream;
    private final String name;

    public RestAssuredLogger(String name) {
        this.name = name;
    }

    /**
     * @return printStream
     */
    public PrintStream getPrintStream() {
        if (myPrintStream == null) {
            OutputStream output = new OutputStream() {
                private StringBuilder myStringBuilder = new StringBuilder();


                @Override
                public void write(int b) {
                    this.myStringBuilder.append((char) b);
                }

                /**
                 * @see OutputStream#flush()
                 */
                @Override
                public void flush() {
                    String msg = this.myStringBuilder.toString();
                    if (!msg.isEmpty() && !msg.equals("\r\n") && !msg.equals("\n")) {
                        Logger.log().info(name + ":");
                        Logger.log().info(msg);
                        Reporter.log(String.format("<h4>%s: </h4>", name));
                        Reporter.log(String.format("<pre>%s</pre", msg));
                        Reporter.log("<hr align=\"center\" width=\"100%\" size=\"2\"/>");
                    }
                    myStringBuilder = new StringBuilder();
                }
            };

            myPrintStream = new PrintStream(output, true);  // true: autoflush must be set!
        }

        return myPrintStream;
    }
}

