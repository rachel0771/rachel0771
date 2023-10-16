var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Express' });
});

module.exports = router;

router.post('/myaction', (req, res, next) => {
  if (!req.body.color || !req.body.mynumber) {
      res.status(422).send('Missing required fields.');
      return;
  }
  console.log(req.body.color);
  console.log(req.body.mynumber);
  res.send("Your personality says you like pizza.");
});

router.post('/myaction', (req, res, next) => {
  // ... [Any previous code]

  // Here's the logic to set the cookie.
  const computedValue = "some-computed-value"; // Replace this with your actual computation.
  res.cookie('bestcookie', computedValue, { maxAge: 900000, httpOnly: false });
  res.send("Post processed");
});

var cookieParser = require('cookie-parser');
//...
app.use(cookieParser());

