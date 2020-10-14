package ua.kram.tolm.web.command;

import org.apache.log4j.Logger;
import ua.kram.tolm.web.command.admin.ManagerCommand;
import ua.kram.tolm.web.command.admin.RemoveUserCommand;
import ua.kram.tolm.web.command.admin.ShowUpdateUserCommand;
import ua.kram.tolm.web.command.admin.UpdateUserCommand;

import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {
    private static final Logger LOG = Logger.getLogger(CommandContainer.class);

    private static final Map<String, Command> actionMap = new TreeMap<>();

    static {
        actionMap.put("register", new RegisterCommand());
        actionMap.put("show", new ShowCommand());
        actionMap.put("removeUser", new RemoveUserCommand());
        actionMap.put("showUpdateUser", new ShowUpdateUserCommand());
        actionMap.put("updateUser", new UpdateUserCommand());
        actionMap.put("login", new LoginCommand());
        actionMap.put("showBooks", new ShowBooksCommand());
        actionMap.put("logout", new LogoutCommand());
        actionMap.put("manager", new ManagerCommand());
        actionMap.put("lang", new LangCommand());
    }

    public static Command getCommand (String request){
        LOG.info("CommandContainer #getCommand  request --> " + request);

        return actionMap.get(request);
    }

    private CommandContainer() {
        throw new IllegalStateException("Utility class");
    }
}
