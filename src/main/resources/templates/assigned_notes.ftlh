<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Assigned to me notes</title>
</head>
<body>
<div>
    <#if notes?size==0>
        <div class="basic">
            <p>No Notes Available</p>
        </div>
    <#else>
        <div class="basic">
            <table class="table">
                <thead>
                <tr>
                    <th></th>
                    <th>Note number</th>
                    <th>Patient id</th>
                    <th>Date</th>
                </tr>
                </thead>
                <tbody>
                <#list notes as note>
                    <tr>
                        <td><a href="/assigned_notes/start/${note.getId()}">Start appointment </a> </td>
                        <td>${note.getId()}</td>
                        <td>${note.getPatient().getId()}</td>
                        <td>${note.getNoteDate().time?date} ${note.getNoteDate().time?time}</td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </#if>
</div>
</body>
</html>