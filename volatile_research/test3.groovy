import javax.script.*

/*
ScriptEngineManager mgr = new ScriptEngineManager()
ScriptEngine engine = mgr.getEngineByName("AppleScript")
engine.eval("say \"Groovy way to call Applescript!\"")
*/
/*
public class EvalFile {
    public static void main(String[] args) throws Exception {
        // create a script engine manager
        ScriptEngineManager factory = new ScriptEngineManager();
        // create JavaScript engine
        ScriptEngine engine = factory.getEngineByName("JavaScript");
        // evaluate JavaScript code from given file - specified by first argument
        engine.eval(new java.io.FileReader(args[0]));
    }
}
*/

//soooo
def SCRIPT_LOCATION = "."
def SCRIPT_EXTENSION = ".as"
def command = args[0]
def commandScript = "${SCRIPT_LOCATION}/${command}${SCRIPT_EXTENSION}"
println commandScript

ScriptEngineManager mgr = new ScriptEngineManager()
ScriptEngine engine = mgr.getEngineByName("AppleScript")
engine.eval(new java.io.FileReader(commandScript));
