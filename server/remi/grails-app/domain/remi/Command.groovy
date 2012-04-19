package remi

class Command {

    String name

    static belongsTo = [remote: Remote]

    static constraints = {
        name nullable: false
        name empty: false
    }

    public String toString() {
        return name
    }
}
