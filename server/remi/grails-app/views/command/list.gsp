
<%@ page import="remi.Command" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'command.label', default: 'Command')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'command.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="name" title="${message(code: 'command.name.label', default: 'Name')}" />
                        
                            <th><g:message code="command.remote.label" default="Remote" /></th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${commandInstanceList}" status="i" var="commandInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${commandInstance.id}">${fieldValue(bean: commandInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: commandInstance, field: "name")}</td>
                        
                            <td>${fieldValue(bean: commandInstance, field: "remote")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${commandInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
