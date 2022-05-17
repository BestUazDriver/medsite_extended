<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Assign illness</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script type="text/javascript" src="../js/home.js"></script>
    <script type="text/javascript" src="../js/delete_ill.js"></script>
    <link rel="stylesheet" href="/css/table.css">
    <link rel="stylesheet" href="/css/home.css">
    <link rel="stylesheet" href="/css/profile.css">
</head>
<body>
<script>
    function search(name) {
        let request = new XMLHttpRequest();
        request.open('GET', '/add_ill/search/name?name=' + name, false);
        request.send();
        console.log(name);
        console.log(request);
        if (request.status !== 200) {
            alert('Ошибка!')
        } else {
            let html = '<tr>' +
                '<th></th>' +
                '<th>ID</th>' +
                '<th>name</th>' +
                '</tr>';
            let response = JSON.parse(request.response);


            for (let i = 0; i < response.length; i++) {
                html += '<tr>'
                html += '<td><input type="radio" value="' + response[i]['name'] + '" id="name" name="name"></td>'
                html += '<td>' + response[i]['id'] + '</td>';
                html += '<td>' + response[i]['name'] + '</td>';
                html += '</tr>';
            }

            document.getElementById('ill_table').innerHTML = html;
        }
    }
</script>
<div class="basic">
<label style="color: #ec9b02">Search
    by name
    <input class="input" name="name" id="name" placeholder="name" onkeyup="search(document.getElementById('name').value)">
</label>

<form method="post" id="fileUploadForm">
    <table id="ill_table" class="table"></table>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <input type="submit" value="Submit" id="submitButton">
</form>
<br>

<span id="result" style="color: #ec9b02"></span>
<br>
<br>
<form action="/add_ill/finish" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <input type="submit" value="Finish appointment">
</form>
</div>
<#if patient_illness?size==0>
    <div class="basic">
        <p>Patient has no Illness</p>
    </div>
<#else>
    <div class="basic">
        <table class="table">
            <thead>
            <tr>
                <th></th>
                <th>Ill</th>
            </tr>
            </thead>
            <tbody>
            <#list patient_illness as ill>
                <tr>
                    <td>
                        <form method="post" id="delete_ill">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                            <input type="hidden" value="${ill.getName()}" name="deleted_ill">
                            <input type="submit" value="Delete ill" id="button">
                        </form>
                    </td>
                    <td>${ill.getName()}</td>
                </tr>
            </#list>
            </tbody>
        </table>
        <br>
        <span id="result1" style="color: #ec9b02"></span>

    </div>
</#if>
</div>
</body>
</html>