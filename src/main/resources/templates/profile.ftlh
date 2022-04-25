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
    <#if bank_accounts?size==0>
        <div class="basic">
            <p>No Bank Accounts Available</p>
        </div>
    <#else>
        <div class="basic">
            <table class="table">
                <thead>
                <tr>
                    <th>Bank Account Number</th>
                    <th>Nal</th>
                    <th>Points</th>
                </tr>
                </thead>
                <tbody>
                <#list bank_accounts as bank_acccount>
                    <tr>
                        <td>${bank_acccount.getId()}</td>
                        <td>${bank_acccount.getCash()!""}</td>
                        <td>${bank_acccount.getPoints()!""}</td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </#if>
</div>
</body>
</html>