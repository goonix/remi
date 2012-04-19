package remi

class Command {

    String name

    static belongsTo = [author: Author]

    static constraints = {
        name nullable: false
        name empty: false
    }
}
