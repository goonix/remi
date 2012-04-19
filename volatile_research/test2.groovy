import javax.script.*
 
ScriptEngineManager mgr = new ScriptEngineManager()
ScriptEngine engine = mgr.getEngineByName("AppleScript")
engine.eval("say \"Groovy way to call Applescript!\"")
