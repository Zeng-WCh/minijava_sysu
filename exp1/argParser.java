import java.util.HashMap;
import java.util.Map;

enum argType {
    typeInt,
    typeString,
    typeBoolean,
    typeDouble,
}

class argException extends Exception {
    public argException(String info) {
        super(info);
    }
}


/**
 * Current Support Mode: --[trigger] [value], like --debug true
 * TODO: -[shortTrigger] [value], like -d true
 */
class Args {
    argType type;
    String trigger, description, defaultVal;

    public Args(String trigger, argType type) {
        this.type = type;
        this.trigger = trigger;
        switch (this.type) {
            case typeInt -> {
                this.defaultVal = "0";
                this.description = "An Interger Instance";
            }
            case typeBoolean -> {
                this.defaultVal = "true";
                this.description = "A Boolean Instance";
            }
            case typeDouble -> {
                this.defaultVal = "0.0";
                this.description = "A Double Instance";
            }
            case typeString -> {
                this.defaultVal = "";
                this.description = "A String Instance";
            }
        }
    }

    public Args(String trigger, argType type, String defaultVal) {
        this.defaultVal = defaultVal;
        this.trigger = trigger;
        this.type = type;
        switch (this.type) {
            case typeInt ->
                this.description = "An Interger Instance";
            case typeBoolean ->
                this.description = "A Boolean Instance";
            case typeDouble ->
                this.description = "A Double Instance";
            case typeString ->
                this.description = "A String Instance";
        }
    }

    public Args(String trigger, argType type, String defaultVal, String description) {
        this.defaultVal = defaultVal;
        this.description = description;
        this.type = type;
        this.trigger = trigger;
    }

    /**
     * @return a formatted string, looks like "--[trigger]: [type], default=[defaultVal], [description]
     */
    public String helpInfo() {
        String tp = "";
        switch (this.type) {
            case typeInt ->
                tp = "int";
            case typeBoolean ->
                tp = "boolean";
            case typeDouble ->
                tp = "double";
            case typeString ->
                tp = "string";
        }
        return String.format("--%-10s: %-8s, default=%-20s, %s", this.trigger, tp, this.defaultVal, this.defaultVal);
    }
}

public class argParser {
    public static String getTrigger(String arg) {
        String[] info = arg.split("--");
        if (info.length == 0) {
            return "";
        }
        else {
            return info[0];
        }
    }

    private Map<String, Args> pattern, mp;
    public argParser() {
        this.pattern = new HashMap<>();
        this.mp = new HashMap<>();
    }

    public void addArgs(String trigger, argType type) {
        this.pattern.put(trigger, new Args(trigger, type));
    }

    public void addArgs(String trigger, argType type, String defaultVal) {
        this.pattern.put(trigger, new Args(trigger, type, defaultVal));
    }

    public void addArgs(String trigger, argType type, String defaultVal, String description) {
        this.pattern.put(trigger, new Args(trigger, type, defaultVal, description));
    }

    public void parseArgs(String[] args) throws Exception {

    }
}