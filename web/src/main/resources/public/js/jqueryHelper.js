$(document).ready(function (e) {
$("#fileToUpload").change((function(e) {
var formData = new FormData();
formData.append('file', $('input[type=file]')[0].files[0]);
$.ajax({
url: "http://localhost:8080/uploadPic", // Url to which the request is send
type: "POST",             // Type of request to be send, called as method
data: formData, // Data sent to server, a set of key/value pairs (i.e. form fields and values)
contentType: false,       // The content type used when sending data to the server.
cache: false,             // To unable request pages to be cached
processData:false,        // To send DOMDocument or non processed data file it is set to false
success: function(data)   // A function to be called if request succeeds
{
alert("success");
}
});
}));});
