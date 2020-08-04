<!DOCTYPE html>
<html lang="en">
<head>
  <title>Auth Page</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<body>

<header style="height: 30px;background-color: maroon;">
</header>

<div class="container">
  <h2>Login Page!!!</h2>
   <img src="img/student.png" style="height: 120px;">
   <hr/>
   <span style="font-size: 18;color: blue;font-weight: bold;">${message}</span>
   <hr/>
   <div style="width: 60%">
  <form action="authUser" method="POST">
    <div class="form-group">
      <label>Username/Email:</label>
      <input type="text" class="form-control"  placeholder="Enter Username" name="username">
    </div>
    <div class="form-group">
      <label for="pwd">Password:</label>
      <input type="password" class="form-control"  placeholder="Enter password" name="pswd">
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
  </form>
  </div>
</div>

</body>
</html>
