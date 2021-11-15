package common;

import common.manager.input.impl.IOManagerImpl;
import common.manager.PlannerManager;
import common.manager.impl.CommandManagerImpl;
import common.manager.impl.CommandResolverImpl;
import common.manager.impl.PlannerManagerImpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Application {

    public static void main(String[] args) {
        final PlannerManager manager = new PlannerManagerImpl(
            new CommandManagerImpl(new IOManagerImpl(new BufferedReader(new InputStreamReader(System.in)))),
            new CommandResolverImpl(),
            new IOManagerImpl(new BufferedReader(new InputStreamReader(System.in)))
        );
        manager.run();
    }
}