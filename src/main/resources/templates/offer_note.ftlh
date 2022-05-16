<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<div>
    <form action="/offer_note" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <label>Choose address for note:
            <select name="address">
            <#list offices as office>
                <option value="${office}" name="address">${office}</option>
            </#list>
            </select>
        </label>
        <br>
        <label>Choose date and time for note:
            <input type="datetime-local" name="noteDate" required>
        </label>
        <input type="submit" value="Offer">
    </form>
</div>
</body>
</html>