package remi

class CommandController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [commandInstanceList: Command.list(params), commandInstanceTotal: Command.count()]
    }

    def create = {
        def commandInstance = new Command()
        commandInstance.properties = params
        return [commandInstance: commandInstance]
    }

    def save = {
        def commandInstance = new Command(params)
        if (commandInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'command.label', default: 'Command'), commandInstance.id])}"
            redirect(action: "show", id: commandInstance.id)
        }
        else {
            render(view: "create", model: [commandInstance: commandInstance])
        }
    }

    def show = {
        def commandInstance = Command.get(params.id)
        if (!commandInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'command.label', default: 'Command'), params.id])}"
            redirect(action: "list")
        }
        else {
            [commandInstance: commandInstance]
        }
    }

    def edit = {
        def commandInstance = Command.get(params.id)
        if (!commandInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'command.label', default: 'Command'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [commandInstance: commandInstance]
        }
    }

    def update = {
        def commandInstance = Command.get(params.id)
        if (commandInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (commandInstance.version > version) {
                    
                    commandInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'command.label', default: 'Command')] as Object[], "Another user has updated this Command while you were editing")
                    render(view: "edit", model: [commandInstance: commandInstance])
                    return
                }
            }
            commandInstance.properties = params
            if (!commandInstance.hasErrors() && commandInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'command.label', default: 'Command'), commandInstance.id])}"
                redirect(action: "show", id: commandInstance.id)
            }
            else {
                render(view: "edit", model: [commandInstance: commandInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'command.label', default: 'Command'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def commandInstance = Command.get(params.id)
        if (commandInstance) {
            try {
                commandInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'command.label', default: 'Command'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'command.label', default: 'Command'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'command.label', default: 'Command'), params.id])}"
            redirect(action: "list")
        }
    }
}
