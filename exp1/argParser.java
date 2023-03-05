import java.util.HashMap;
import java.util.Map;

enum argType {
    argString,
    argInt,
    argDouble,
    argBoolean
}

class args {
    String trigger;
    argType type;
    String description;
    String defaultVal;

    public args() {
        this.trigger = "";
        this.description = "";
        this.defaultVal = "";
    }

    public args(String trigger, argType type) {
        this.type = type;
        this.trigger = trigger;
        switch (type) {
            case argInt -> {
                this.defaultVal = "0";
                this.description = "An Integer instance";
            }
            case argBoolean -> {
                this.defaultVal = "true";
                this.description = "An Boolean instance";
            }
            case argDouble -> {
                this.defaultVal = "0.0";
                this.description = "An Double instance";
            }
            case argString -> {
                this.defaultVal = "";
                this.description = "An String instance";
            }
        }
    }

    public args(String trigger, argType type, String defaultVal) {
        this.trigger = trigger;
        this.type = type;
        this.defaultVal = defaultVal;
        switch (type) {
            case argInt ->
                this.description = "An Integer instance";
            case argBoolean ->
                this.description = "An Boolean instance";
            case argDouble ->
                this.description = "An Double instance";
            case argString ->
                this.description = "An String instance";
        }
    }

    public args(String trigger, argType type, String defaultVal, String description) {
        this.trigger = trigger;
        this.type = type;
        this.description = description;
        this.defaultVal = defaultVal;
    }

    /**
     *
     * @return a formatted String instance, looks like:
     * -i [argType], [defaultVal] [description]
     */
    public String helpInfo() {
        String type = "";
        switch (this.type) {
            case argInt -> type = "int";
            case argBoolean -> type = "boolean";
            case argDouble -> type = "double";
            case argString -> type = "string";
        }
        return String.format("%-5s %-8s, %-5s\t%-40s", this.trigger, type, this.defaultVal, this.description);
    }
}

public class argParser {
    private Map<String, String> shortMp;
    private Map<String, String> longMp;

    public argParser() {
        this.shortMp = new HashMap<>();
        this.longMp = new HashMap<>();
    }

    public void addArgs(String command, argType type, String description, String defaultVal) {

    }

}
