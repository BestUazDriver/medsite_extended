<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/css/table.css">
    <link rel="stylesheet" href="/css/home.css">
    <link rel="stylesheet" href="/css/profile.css">
    <title>Welcome back, ${name!"Unknown"}</title>
</head>
<body>
<div class="logout-container">
    <form class="submit" action="/logout">
        <button class="logout-button">Logout</button>
    </form>
</div>
<div class="parent">
    <#if illness?size==0>
        <div class="basic">
            <p>No Illness</p>
        </div>
    <#else>
        <div class="basic">
            <table class="table">
                <thead>
                <tr>
                    <th>Ill</th>
                </tr>
                </thead>
                <tbody>
                <#list illness as ill>
                    <tr>
                        <td>${ill.getName()}</td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </#if>
</div>
<div class="window-button">
    <#if role=="USER">
        <a href="/offer_note">Order note for doctor</a>
    <#else>
        <a href="/doc_notes">Available notes</a>
        <br>
        <a href="/assigned_notes">My notes</a>
    </#if>
</div>
</body>
</html>