<#include "parts/security.ftlh">
<#import "parts/common.ftlh" as c>


<@c.page>
<div class="form-row">
    <div class="form-group col-md-6">
        <form method="get" action="/main" class="form-inline">
            <input type="text" name="filter" class="form-control" value="${filter?ifExists}" placeholder="Search by text">
            <button type="submit" class="btn btn-primary ml-2">Search</button>
        </form>
    </div>
</div>

    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
        Add new Message
    </a>

<div class="collapse" id="collapseExample">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input type="text" class="form-control" name="text" placeholder="Введите сообщение" />
            </div>
            <div class="form-group">
                <input type="text" class="form-control" name="tag" placeholder="Тэг">
            </div>

            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Add</button>
            </div>
        </form>
    </div>
</div>

<div class="card-columns">
    <#list messages as message>
    <div class="card my-3">
        <#if message.filename??>
        <img src="/img/${message.filename}" class="card-img-top">
        </#if>
        <div class="m-2">
            <span>${message.text}</span>
            <i>${message.tag}</i>
        </div>
        <div class="card-footer text-muted">
            ${message.authorName}
        </div>
    </div>
    <#else>
    No message
    </#list>



</div>
    <div>
        <#if isClient>
            <div>
            You are CLIENT
                </div>
            List of Volunteers:
            <table>
                <thead>
                <tr>

                    <th></th>
                </tr>
                </thead>
                <tbody>
                <#list users_v as user>
                    <tr>
                        <td>${user.username}</td>

                        <td><#list user.roles as role>${role}<#sep>, </#list></td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </#if>
        <#if isVol>
            You are VOLUNTEER
            List of Clients:
            <table>
                <thead>
                <tr>

                    <th></th>
                </tr>
                </thead>
                <tbody>
                <#list users_c as user>
                    <tr>
                        <td>${user.username}</td>
                        <td><#list user.roles as role>${role}<#sep>, </#list></td>
                        <td><a href="/main/${user.id}/${current_user.id}">+</a></td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </#if>
    </div>




<div class="card-columns">
    <#list messages as message>
        <div class="card my-3">
            <div class="m-2">
                <span>    <div>
        Volunteer and client couple:
                        </div>
</span>
            </div>
            <div class="card-footer text-muted">
                <div>
                    <table>
                        <thead>
                        </thead>
                        <tbody>
                        <#list users_to_vol as user>
                            <tr>
                                <td>${user.username}</td>
                                <td><#list user.roles as role>${role}<#sep>, </#list></td>
                                <td>${user.phone}</td>
                                <td><a href="/main/${reqID}">Cancel</a></td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    <#else>
        No message
    </#list>

</@c.page>
