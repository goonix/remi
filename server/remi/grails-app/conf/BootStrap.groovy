import remi.Remote
import remi.Command

import com.apple.cocoa.foundation.*;
//import com.apple.cocoa.application.NSApplication;
import com.apple.cocoa.application.*;

import javax.script.*

class BootStrap {

    def init = { servletContext ->
        /* 
        if (!Remote.count()) {
            def remote1 =
                new Remote(fullName: "Harmon Kardon-20120411-203045", commonName: "AVR Tuner").save(failOnError: true)
            def command1 = new Command(remote: remote1, name: "ON").save(failOnError: true)
            */

        //setup script engine
        ScriptEngineManager mgr = new ScriptEngineManager()
        ScriptEngine engine = mgr.getEngineByName("AppleScript")

        //get remotes
        def script = """
        tell application "IR-FBA"
           -- get names of all the known remotes:
           set rems to remotes
           return rems
        end tell"""
        def remotes = engine.eval(script)
        remotes.each() { remoteResult ->
            println remoteResult
            def m = remoteResult.find(/'seld':'utxt'\(.+?\)/) =~ /\("(.+)"\)/
            def remoteName = m[0][1]
            def remote1 =
                new Remote(fullName: remoteName, commonName: "").save(failOnError: true)

            //get commands for remoteName
            script = """
            tell application "IR-FBA"
               -- get the buttons of one remote
               set theButtons to buttons of remote "${remoteName}"
               return theButtons
            end tell
            """
            def commands = engine.eval(script)
            commands.each() { command ->
                m = command.find(/'seld':'utxt'\(.+?\)/) =~ /\("(.+)"\)/
                new Command(remote: remote1, name: m[0][1]).save(failOnError: true)
            }
        }
    }
    def destroy = {
    }
}
