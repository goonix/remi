import remi.Remote
import remi.Command

class BootStrap {

    def init = { servletContext ->
        if (!Remote.count()) {
            def remote1 =
                new Remote(fullName: "Harmon Kardon-20120411-203045", commonName: "AVR Tuner").save(failOnError: true)
            def command1 = new Command(remote: remote1, name: "ON").save(failOnError: true)
        }
    }
    def destroy = {
    }
}
