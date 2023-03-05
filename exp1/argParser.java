import java.util.Map;
import java.util.HashMap;

public class argparser {
    public enum argType {
        argString,
        argInt,
        argDouble,
        argBoolean
    }

    private String[] args;
    private Map<String, String> mp;

    /**
     *
     * @param shortCommand, like "-i"
     * @param longCommand, like "--interactive"
     * @param type, an "argType" instance
     * @param description, info that will be displayed when type --help
     * @param defaultVal, if not given by the command line, then how it will be
     */
    public void addArgs(String shortCommand, String longCommand, argType type, String description, String defaultVal){
    }

}
