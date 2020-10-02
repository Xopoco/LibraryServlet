package ua.kram.tolm.web.command;

import org.apache.log4j.Logger;

import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {
    private static final Logger LOG = Logger.getLogger(CommandContainer.class);

    private static final Map<String, Command> actionMap = new TreeMap<>();

    static {
        actionMap.put("register", new RegisterCommand());
        actionMap.put("show", new ShowCommand());
        actionMap.put("removeUser", new RemoveUserCommand());
        actionMap.put("updateUser", new UpdateUserCommand());
    }

    public static Command getCommand (String request){
        LOG.info("CommandContainer #getCommand");

        return actionMap.get(request);
    }
}
