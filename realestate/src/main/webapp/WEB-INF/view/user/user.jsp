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
    <h1>hello user</h1>
    <li>
        <form method="post" action="/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <button class="dropdown-item">Logout</button>
        </form>
    </li>
</body>
</html>