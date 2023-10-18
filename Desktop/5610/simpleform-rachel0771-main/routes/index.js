var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Express' });
});

router.post('/myaction', (req, res, next) => {
  if (!req.body.color || !req.body.mynumber) {
      res.status(422).send('ERROR: Missing required fields.');
      return;
  }
  
  console.log(req.body.color);
  console.log(req.body.mynumber);
  
  // The logic to set the cookie.
  const computedValue = "some-computed-value"; // Replace this with your actual computation.
  res.cookie('bestcookie', computedValue, { maxAge: 900000, httpOnly: false });
  
  res.send("Your personality says you like pizza.");
});

// Added /submit POST route
router.post('/submit', (req, res) => {
  // Your logic for handling the submission can go here, e.g.:
  const { petname, age, specie, gender, remark } = req.body;

  // Process the data, save to database, etc...

  // For now, simply respond with success:
  res.status(200).send('Data received successfully');
});

module.exports = router;
