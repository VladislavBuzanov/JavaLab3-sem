<html>
<head>
    <title>User</title>
</head>
<body>
<form action="/produce" method="post">
    <label>Name:
        <input type="text" name="name">
    </label>
    <label> Surname:
        <input type="text" name="surname">
    </label>
    <label> Age:
        <input type="text" name="age">
    </label>

    <label> Passport date:
        <input type="text" name="passportDate">
    </label>
    <label>Passport number:
        <input type="text" name="passportNumber">
    </label>
    <select name="queue">
        <option value="expel">Expel</option>
        <option value="academical">Academical</option>
        <option value="fire">Fire</option>
        <option value="vacation">Vacation</option>
    </select>
    <input type="submit">
</form>
</body>
</html>
