
<%@ page import="remi.Remote" %>
<html>
    <head>
        <g:javascript library="prototype" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'remote.label', default: 'Remote')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
        <script>
        /*
        function loadHotels(){
            var url = "${createLink(controller:'hotel', action:'near')}"
                url += "?lat=" + airportMarkers[1].getLatLng().lat()
                url += "&lng=" + airportMarkers[1].getLatLng().lng()
            new Ajax.Request(url,{
                    onSuccess: function(req) { showHotels(req) },
                    onFailure: function(req) { displayError(req) }
                })
        }
        */
        function executeCommand(command){
            var url = "${createLink(controller:'remote', action:'execute')}"
                url += "?id=" + "${remoteInstance.id}"
                url += "&command=" + command;
            new Ajax.Request(url,{
                /*
                onSuccess: function(req) { alert("SUCCESS") },
                onFailure: function(req) { alert("DENIED") }
                */
                onSuccess: function(req) { },
                onFailure: function(req) { }
            })

            return false;
        }
        </script>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="remote.fullName.label" default="Full Name" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: remoteInstance, field: "fullName")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="remote.commands.label" default="Commands" /></td>
                            
                            <td valign="top" style="text-align: left;" class="value">
                                <table class="commandtable">
                                <g:each in="${remoteInstance.commands}" var="c">
                                    <tr><td><g:link onclick="return executeCommand('${c.name}');" class="commandbutton">${c?.encodeAsHTML()}</g:link></td></tr>
                                </g:each>
                                </table>
                            </td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="remote.commonName.label" default="Common Name" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: remoteInstance, field: "commonName")}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${remoteInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
