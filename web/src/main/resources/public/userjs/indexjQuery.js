$(function() {$(document).ready(function(e) {
$('#petDetails img').each(function(){
                      var imgData='data:image/jpg+xml;base64,';
                      var idToSearch=this.id;
                      $.ajax({
                      url: "http://localhost:8080/getPetPhoto", // Url to which the request is send
                      type: "POST",             // Type of request to be send, called as method
                      data: this.id, // Data sent to server, a set of key/value pairs (i.e. form fields and values)
                      contentType: "text/plain",       // The content type used when sending data to the server.
                      cache: false,             // To unable request pages to be cached
                      processData:false,        // To send DOMDocument or non processed data file it is set to false
                      success: function(response)   // A function to be called if request succeeds
                      {
                       imgData=imgData+response;
                       $('#'+idToSearch).attr('src',imgData);
                      }
                      });
                      });
}
);
});