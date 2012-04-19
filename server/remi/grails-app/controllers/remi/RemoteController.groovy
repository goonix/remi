package remi

class RemoteController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
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
