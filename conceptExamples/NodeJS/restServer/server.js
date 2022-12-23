/**        Creating REST API Server
 * 1) import the express module
 * 2) create an app by calling the express function
 * 3) set the app to listen on a particular port
 * 4) set up how the app should parse incoming data
 * 5) create endpoints - handle requests and reponses
 */
const express = require('express');
const app = express();
//hosts a static file
app.use(express.static('public'));

//parses incoming data as json
app.use(express.json());

//have the app start listening for requests from the client
app.listen(3000, ()=>{
    console.log("server started");
});


/*ENDPOINTS */
app.post('/',(req,res)=>{
    console.log(req.body);
    const data = req.body.data1;
    var ret = {};

    if(data === 0)
    {
        ret = {divData: '<div style = "color:red"> I am red!</div>'}
        res.json(ret); //this line converts data to json format, then adds to res
    }
    else if(data === 1){
        ret = {divData: '<div style = "color:blue"> I am blue!</div>'}
        res.json(ret);
    }
    else{
        ret = {divData: '<div style = "color:green"> I am green!</div>'}
        res.json(ret);
    }

    res.end(); //this line sends the data over the client and closes request
}); 
