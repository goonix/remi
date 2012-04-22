

<%@ page import="remi.Remote" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'remote.label', default: 'Remote')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${remoteInstance}">
            <div class="errors">
                <g:renderErrors bean="${remoteInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${remoteInstance?.id}" />
                <g:hiddenField name="version" value="${remoteInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="fullName"><g:message code="remote.fullName.label" default="Full Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: remoteInstance, field: 'fullName', 'errors')}">
                                    <g:textField name="fullName" value="${remoteInstance?.fullName}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="commands"><g:message code="remote.commands.label" default="Commands" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: remoteInstance, field: 'commands', 'errors')}">
                                    
<ul>
<g:each in="${remoteInstance?.commands?}" var="c">
    <li><g:link controller="command" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="command" action="create" params="['remote.id': remoteInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'command.label', default: 'Command')])}</g:link>

                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="commonName"><g:message code="remote.commonName.label" default="Common Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: remoteInstance, field: 'commonName', 'errors')}">
                                    <g:textField name="commonName" value="${remoteInstance?.commonName}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
