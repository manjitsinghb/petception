$(document).ready(function(e) {
$.ajax({
url: "http://localhost:8080/getAllPets", // Url to which the request is send
type: "GET",             // Type of request to be send, called as method
data: "", // Data sent to server, a set of key/value pairs (i.e. form fields and values)
contentType: "application/json",       // The content type used when sending data to the server.
cache: false,             // To unable request pages to be cached
processData:false,        // To send DOMDocument or non processed data file it is set to false
success: function(data)   // A function to be called if request succeeds
{
 var pets = data["pet"];
 for(int i=0;i<pets.length;i++)
 {

 }

}
});
});