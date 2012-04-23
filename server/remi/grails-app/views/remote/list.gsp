
<%@ page import="remi.Remote" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'remote.label', default: 'Remote')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'remote.id.label', default: 'Id')}" />
                            <th>Remote</th>
                        
                            <g:sortableColumn property="fullName" title="${message(code: 'remote.fullName.label', default: 'Full Name')}" />
                        
                            <g:sortableColumn property="commonName" title="${message(code: 'remote.commonName.label', default: 'Common Name')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${remoteInstanceList}" status="i" var="remoteInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${remoteInstance.id}">${fieldValue(bean: remoteInstance, field: "id")}</g:link></td>
                            <td><g:link action="showremote" id="${remoteInstance.id}">REM</g:link></td>
                        
                            <td>${fieldValue(bean: remoteInstance, field: "fullName")}</td>
                        
                            <td>${fieldValue(bean: remoteInstance, field: "commonName")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${remoteInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
