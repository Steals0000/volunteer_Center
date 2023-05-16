<#assign
    known = Session.SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#assign
        current_user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        name = current_user.getUsername()
        isFinding = current_user.isFinding()
        isAdmin = current_user.isAdmin()
        isVol = current_user.isVol()
        isClient = current_user.isClient()
    >
<#else>
    <#assign
        name = "unknown"
        isAdmin = false
    >
</#if>
