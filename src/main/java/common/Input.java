package common;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;
@Slf4j
public class Input {
    private final Scanner scanner = new Scanner(System.in);

    public String enterCommand() {
        return scanner.nextLine();


    }
}




