import javax.script.*
 
def takeScreenshot = """\
open location \"http://www.bitwalker.nl\"
delay 5
do shell script \"screencapture screenshot.pdf\"
"""
 
ScriptEngineManager mgr = new ScriptEngineManager()
ScriptEngine engine = mgr.getEngineByName("AppleScript")
engine.eval(takeScreenshot)
engine.eval("say \"Your screenshot is ready\"")
