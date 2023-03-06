import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

enum argType {
    typeInt,
    typeString,
    typeBoolean,
    typeDouble,
}

class ArgException extends Exception {
    private final String info;

    public ArgException(String info) {
        super();
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}

/**
 * Current Support Mode: --[trigger] [value], like --debug true
 * TODO: -[shortTrigger] [value], like -d true
 */
class Args {
    private final argType type;
    private final String trigger, description, defaultVal;
    private final boolean isDefault;
    private String value;

    public Args(String trigger, argType type) {
        this.type = type;
        this.trigger = trigger;
        this.isDefault = false;
        this.value = null;
        switch (this.type) {
            case typeInt: {
                this.defaultVal = "0";
                this.description = "An Interger Instance";
                break;
            }
            case typeBoolean: {
                this.defaultVal = "true";
                this.description = "A Boolean Instance";
                break;
            }
            case typeDouble: {
                this.defaultVal = "0.0";
                this.description = "A Double Instance";
                break;
            }
            case typeString: {
                this.defaultVal = "";
                this.description = "A String Instance";
                break;
            }
            default: {
                this.defaultVal = null;
                this.description = null;
                break;
            }
        }
    }

    public Args(String trigger, argType type, String description) {
        this.description = description;
        this.trigger = trigger;
        this.type = type;
        this.isDefault = false;
        this.value = null;
        switch (this.type) {
            case typeInt: {
                this.defaultVal = "0";
                break;
            }
            case typeBoolean: {
                this.defaultVal = "true";
                break;
            }
            case typeDouble: {
                this.defaultVal = "0.0";
                break;
            }
            case typeString: {
                this.defaultVal = "";
                break;
            }
            default: {
                this.defaultVal = null;
                break;
            }
        }
    }

    public Args(String trigger, argType type, String description, String defaultVal) {
        this.defaultVal = defaultVal;
        this.description = description;
        this.type = type;
        this.trigger = trigger;
        this.isDefault = true;
        this.value = null;
    }

    /**
     * @return a formatted string, looks like "--[trigger]: [type], default=[defaultVal], [description], for example --interactive: boolean, default=true, Whather to enable interactive mode
     */
    public String helpInfo() {
        if (this.isDefault)
            return String.format("--%-13s: %7s instance, default=%s, %s", this.trigger, this.getType(), this.defaultVal, this.description);
        else
            return String.format("--%-13s: %7s instance, %s", this.trigger, this.getType(), this.description);
    }

    public boolean checkValid(String value) {
        switch (this.type) {
            case typeInt: {
                Pattern p = Pattern.compile("^(-?)[0-9]*$");
                Matcher m = p.matcher(value);
                return m.find();
            }
            case typeBoolean: {
                Pattern p = Pattern.compile("^(?!)(true|false)$");
                Matcher m = p.matcher(value);
                return m.find();
            }
            case typeDouble: {
                Pattern p = Pattern.compile("^(-?\\d+)(\\.\\d+)?$");
                Matcher m = p.matcher(value);
                return m.find();
            }
            case typeString: {
                Pattern p = Pattern.compile("^(\\w|.)+$");
                Matcher m = p.matcher(value);
                return m.find();
            }
            default: {
                return false;
            }
        }
    }

    public argType typeInfo() {
        return this.type;
    }

    public String getType() {
        switch (this.type) {
            case typeInt: {
                return "int";
            }
            case typeString: {
                return "string";
            }
            case typeDouble: {
                return "double";
            }
            case typeBoolean: {
                return "boolean";
            }
            default: {
                return "";
            }
        }
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() throws ArgException {
        if (!this.isDefault && this.value == null) {
            throw new ArgException(String.format("No Value is assigned to %s", this.trigger));
        }
        if (this.value == null) {
            return this.defaultVal;
        } else {
            return this.value;
        }
    }

    public boolean isDefault() {
        return isDefault;
    }
}

public class argParser {
    /**
     * @param arg, the argument passed by command line, looks like "--debug"
     * @return a string that throw away "--", for example "--debug" and return "debug"
     */
    private String getTrigger(String arg) {
        if (arg.length() == 0) {
            return "";
        }
        String[] info = arg.split("--");
        if (info.length == 0) {
            return "";
        } else {
            return info[1];
        }
    }

    private Map<String, Args> pattern;
    private String info;

    public argParser() {
        this.pattern = new HashMap<>();
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
        if (this.info.length() != 0)
            System.out.println(this.info);
        if (this.pattern.isEmpty()) {
            return;
        }
        System.out.println("Support Arguments:");
        for (String key : this.pattern.keySet()) {
            Args a = this.pattern.get(key);
            System.out.println("\t" + a.helpInfo());
        }
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean hasArgs() {
        for (String key : this.pattern.keySet()) {
            Args a = this.pattern.get(key);
            if (!a.isDefault()) {
                return false;
            }
        }
        return true;
    }

    public void parseArgs(String[] args) throws ArgException {
        if ((args == null || args.length == 0) && !hasArgs()) {
            throw new ArgException("No Args Passed Through");
        } else {
            for (int i = 0; i < args.length; ++i) {
                String arg = getTrigger(args[i]);
                if (arg.equals("help")) {
                    this.helpInfo();
                    System.exit(1);
                }
                Args a = this.pattern.get(arg);
                if (a == null) {
                    throw new ArgException(String.format("No Argument %s can be found", arg));
                }
                ++i;
                String val = args[i];
                boolean f = a.checkValid(val);
                if (!f) {
                    throw new ArgException(String.format("Argument: --%s require type: %s, but get: %s", arg, a.getType(), val));
                } else {
                    this.pattern.get(arg).setValue(val);
                }
            }
        }
    }

    public String get(String trigger) throws ArgException {
        String e = null;
        String val = null;
        Args a = this.pattern.get(trigger);
        if (a == null) {
            throw new ArgException(String.format("%s is not set", trigger));
        }
        try {
            val = a.getValue();
        } catch (ArgException ae) {
            e = ae.getInfo();
            throw ae;
        }
        return val;
    }

    public argType getType(String trigger) throws ArgException {
        Args a = this.pattern.get(trigger);
        if (a == null) {
            throw new ArgException(String.format("%s is not set", trigger));
        }
        return a.typeInfo();
    }
}