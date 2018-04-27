var mysql = require('mysql');

var con = mysql.createConnection({
  host: "localhost",
  user: "root",
  password: "",
  database: "phonebook"
});

con.connect(function(err) {
  if (err) throw err;
  con.query("SELECT * FROM contacts;", function (err, result, fields) {
    if (err) throw err;
   		
   		console.log(result);

  });
});


function display(result){

	var div = document.getElementById('requests');

	var span = document.createElement('span');

	var e = document.createTextNode(result);

	span.appendChild(e);

	div.appendChild(span);

}


