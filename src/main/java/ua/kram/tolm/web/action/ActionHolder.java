package ua.kram.tolm.web.action;

import org.apache.log4j.Logger;

import java.util.Map;
import java.util.TreeMap;

public class ActionHolder {
    private static Logger LOG = Logger.getLogger(ActionHolder.class);

    private static Map<String, Action> actionMap = new TreeMap<>();

    static {
        actionMap.put("register", new RegisterAction());
        actionMap.put("show", new ShowAction());
        actionMap.put("removeUser", new RemoveUserAction());
        actionMap.put("updateUser", new UpdateUserAction());
    }

    public static Action getCommand (String request){
        LOG.info("ActionHolder #getCommand");

        return actionMap.get(request);
    }
}
