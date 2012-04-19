package remi

class Remote {
    String fullName //used in the IR panel
    String commonName //displayed to the user
    
    //commands this remote can send (must be stored in IR panel as well under the same name)
    static hasMany = [commands: Command]

    static constraints = {
        fullName nullable: false
        fullName empty: false
    }

    //does "displayName" need to be label transient?
    public String getDisplayName() {
        return ((null != commonName) ? commonName : fullName)
    }
}
