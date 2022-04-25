<!doctype html>
<html lang="en">
<head>
    <title>Welcome</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/css/home.css">
</head>

<body>
<div class="parent">
    <div class="basic">
        <p style="background-color: darkgoldenrod">Sign in</p>
        <form method="post" action="/sign_in">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <input type="email" id="email" name="email" placeholder="Почта" class="input">
            <br>
            <input id="password" name="password" placeholder="Пароль" class="input">
            <br>
            <label>
                <input type="checkbox" name="rememberMe">Запомни меня
            </label>
            <br>
            <input type="submit" value="Sign in" class="submit">
            <br>
            <br>
            <span style="color: orangered">${sign_in_error_message!""}</span>
        </form>
    </div>
    <div class="basic">
        <p style="background-color: darkgoldenrod">Sign up</p>
        <form method="post" action="/sign_up">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <input id="name" name="name" placeholder="Имя" class="input">
            <br>
            <input type="email" id="email" name="email" placeholder="Почта" class="input">
            <br>
            <input id="password" name="password" placeholder="Пароль" class="input">
            <br>
            <br>
            <input type="submit" value="Sign Up" class="submit">
            <br>
            <br>
            <span style="color: orangered">${error_message!""}</span>
        </form>
    </div>
</div>
</body>
</html>