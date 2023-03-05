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
    boolean isDefault;

    public Args(String trigger, argType type) {
        this.type = type;
        this.trigger = trigger;
        this.isDefault = false;
        switch (this.type) {
            case typeInt : {
                this.defaultVal = "0";
                this.description = "An Interger Instance";
            }
            case typeBoolean : {
                this.defaultVal = "true";
                this.description = "A Boolean Instance";
            }
            case typeDouble : {
                this.defaultVal = "0.0";
                this.description = "A Double Instance";
            }
            case typeString : {
                this.defaultVal = "";
                this.description = "A String Instance";
            }
        }
    }

    public Args(String trigger, argType type, String description) {
        this.description = description;
        this.trigger = trigger;
        this.type = type;
        this.isDefault = false;
        switch (this.type) {
            case typeInt :
                this.defaultVal = "0";
            case typeBoolean :
                this.defaultVal = "true";
            case typeDouble :
                this.defaultVal = "0.0";
            case typeString :
                this.defaultVal = "";
        }
    }

    public Args(String trigger, argType type, String description, String defaultVal) {
        this.defaultVal = defaultVal;
        this.description = description;
        this.type = type;
        this.trigger = trigger;
        this.isDefault = true;
    }

    /**
     * @return a formatted string, looks like "--[trigger]: [type], default=[defaultVal], [description]
     */
    public String helpInfo() {
        String tp = "";
        switch (this.type) {
            case typeInt :
                tp = "int";
            case typeBoolean :
                tp = "boolean";
            case typeDouble :
                tp = "double";
            case typeString :
                tp = "string";
        }
        if (this.isDefault)
            return String.format("--%-10s: %7s instance, default=%-10s, %s", this.trigger, tp, this.defaultVal, this.description);
        else
            return String.format("--%-10s: %7s instance, %s", this.trigger, tp, this.description);
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
    private String info;
    public argParser() {
        this.pattern = new HashMap<>();
        this.mp = new HashMap<>();
        this.info = "";
    }

    public void addArgs(String trigger, argType type) {
        this.pattern.put(trigger, new Args(trigger, type));
    }

    public void addArgs(String trigger, argType type, String descrition) {
        this.pattern.put(trigger, new Args(trigger, type, descrition));
    }

    public void addArgs(String trigger, argType type, String description, String defaultVal) {
        this.pattern.put(trigger, new Args(trigger, type, description, defaultVal));
    }

    public void helpInfo() {
        System.out.println(this.info);
        if (!this.pattern.isEmpty()) {
            System.out.println("Support Arguments:");
        }
        for (String key : this.pattern.keySet()) {
            Args a = this.pattern.get(key);
            System.out.println("\t" + a.helpInfo());
        }
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void parseArgs(String[] args) throws Exception {
        if (args == null || args.length == 0) {
            throw new Exception();
        }
    }
}