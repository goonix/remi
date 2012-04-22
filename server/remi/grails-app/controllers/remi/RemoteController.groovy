package remi

import javax.script.*

class RemoteController {

    def grailsApplication

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    //execute a command
    def execute = {
        //def remoteInstance = Remote.get(params.id)
        //if (remoteInstance?

        def remoteInstance = Remote.get(1)
        println remoteInstance
        println remoteInstance.commands
        println "PROPS:"
        println remoteInstance.properties
        println "END PROPS"
        println "PROPS:"
        println remoteInstance.properties.commands
        println "END PROPS"
        println remoteInstance.commands.each() { println it.name }
        /*
        if (remoteInstance?[params.
        */
        /*
        //applicationContext.getResource('WEB-INF/ReportingService.wsdl')
        println remoteInstance.commands.class
        def commands = remoteInstance.commands
        println commands.get(0).name
        */
        /*
        def script = grailsApplication.mainContext.getResource("/WEB-INF/commandScripts/test.as")
        def file = new java.io.FileReader(script.getFile())
        */
        /*
        List<String> l = new ArrayList<String>();
        l.add("Java");
        l.add(testString);
        */
        List<String> scriptParams = new ArrayList<String>()
        scriptParams.add("I love you!");

        /*
        def scriptFile = new java.io.FileReader(
                grailsApplication.mainContext.getResource("/WEB-INF/commandScripts/test.as").getFile())
        ScriptEngineManager mgr = new ScriptEngineManager()
        ScriptEngine engine = mgr.getEngineByName("AppleScript")
        engine.put(javax.script.ScriptEngine.ARGV, scriptParams);
        engine.eval(scriptFile);
        */

        ScriptEngineManager mgr = new ScriptEngineManager()
        ScriptEngine engine = mgr.getEngineByName("AppleScript")
        def foo = "I Love you.\""
        def script = "say \"" + "magoo " + foo
        //engine.eval(scriptFile);
        engine.eval(script);

        script = """
        tell application "IR-FBA"
           -- send the Pause command to the JVC camcorder
           send button "${remoteInstance.commands.toArray()[0]}" of remote "${remoteInstance.fullName}"
        end tell"""
        println script

        redirect(action: "list", params: params)
    }

    def showremote = {
        def remoteInstance = Remote.get(params.id)
        if (!remoteInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'remote.label', default: 'Remote'), params.id])}"
            redirect(action: "list")
        }
        else {
            [remoteInstance: remoteInstance]
        }
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [remoteInstanceList: Remote.list(params), remoteInstanceTotal: Remote.count()]
    }

    def create = {
        def remoteInstance = new Remote()
        remoteInstance.properties = params
        return [remoteInstance: remoteInstance]
    }

    def save = {
        def remoteInstance = new Remote(params)
        if (remoteInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'remote.label', default: 'Remote'), remoteInstance.id])}"
            redirect(action: "show", id: remoteInstance.id)
        }
        else {
            render(view: "create", model: [remoteInstance: remoteInstance])
        }
    }

    def show = {
        def remoteInstance = Remote.get(params.id)
        if (!remoteInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'remote.label', default: 'Remote'), params.id])}"
            redirect(action: "list")
        }
        else {
            [remoteInstance: remoteInstance]
        }
    }

    def edit = {
        def remoteInstance = Remote.get(params.id)
        if (!remoteInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'remote.label', default: 'Remote'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [remoteInstance: remoteInstance]
        }
    }

    def update = {
        def remoteInstance = Remote.get(params.id)
        if (remoteInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (remoteInstance.version > version) {
                    
                    remoteInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'remote.label', default: 'Remote')] as Object[], "Another user has updated this Remote while you were editing")
                    render(view: "edit", model: [remoteInstance: remoteInstance])
                    return
                }
            }
            remoteInstance.properties = params
            if (!remoteInstance.hasErrors() && remoteInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'remote.label', default: 'Remote'), remoteInstance.id])}"
                redirect(action: "show", id: remoteInstance.id)
            }
            else {
                render(view: "edit", model: [remoteInstance: remoteInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'remote.label', default: 'Remote'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def remoteInstance = Remote.get(params.id)
        if (remoteInstance) {
            try {
                remoteInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'remote.label', default: 'Remote'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'remote.label', default: 'Remote'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'remote.label', default: 'Remote'), params.id])}"
            redirect(action: "list")
        }
    }
}
