<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script type="text/javascript" src="../js/home.js"></script>
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
                html += '<td><input type="radio"></td>'
                html += '<td>' + response[i]['id'] + '</td>';
                html += '<td>' + response[i]['name'] + '</td>';
                html += '</tr>';
            }

            document.getElementById('ill_table').innerHTML = html;
        }
    }
</script>
<script>

</script>
<label>
    <input name="name" id="name" placeholder="name" onkeyup="search(document.getElementById('name').value)">Search
    by name
</label>
<table id="ill_table"></table>
<br>
<div>
    <#if illness?size==0>
        <div class="basic">
            <p>No Illness</p>
        </div>
    <#else>
        <div class="basic">
            <form method="post" id="fileUploadForm">
                <#list illness as ill>
                    <input type="radio" value="${ill.getName()}" id="name" name="name">${ill.getName()}</input>
                    <br>
                </#list>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <input type="submit" value="Submit" id="submitButton">
            </form>
        </div>
    </#if>
</div>
<span id="result"></span>
</body>
</html>