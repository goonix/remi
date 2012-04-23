
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
            <h1>${fieldValue(bean: remoteInstance, field: "fullName")}</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:each in="${remoteInstance.commands}" var="c">
                <span class="button">
                    <g:link onclick="return executeCommand('${c.name}');" class="commandbutton">${c?.encodeAsHTML()}</g:link>
                </span>
            </g:each>
        </div>
    </body>
</html>
