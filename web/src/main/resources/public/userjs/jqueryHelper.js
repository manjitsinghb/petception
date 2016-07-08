$(document).ready(function (e) {
$("#fileToUpload").change((function(e) {
$("#addPet").attr("disabled", "disabled");
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
    $("#url").attr('value',data);
    $("#fileUploadSuccess").css({ 'display': "block" });
    $("#fileUploadError").css({ 'display': "none" });;
    $("#addPet").removeAttr("disabled");
},error: function(data)
{
    $("#fileUploadSuccess").css({ 'display': "none" });
    $("#fileUploadError").css({ 'display': "block" });;
    $("#addPet").removeAttr("disabled");
}
});
}));
$("#addPet").click((function(e) {
var petData = {};
petData["name"]=$("#nameOfPet").val()
petData["breed"]=$("#breed").val()
petData["weight"]=$("#weight").val()
petData["age"]=$("#ageOfPet").val()
petData["color"]=$("#color").val()
petData["url"]=$("#url").val()
petData["email"]=$("#email").val();
petData["type"]=$("#typeOfPet").val();
petData["vaccine"]=$("#vaccine").val();
petData["comment"]=$("#comment").val();
var pet ={};
pet["pet"]=petData;
$.ajax({
url: "http://localhost:8080/addPet", // Url to which the request is send
type: "POST",             // Type of request to be send, called as method
data: JSON.stringify(pet), // Data sent to server, a set of key/value pairs (i.e. form fields and values)
contentType: "application/json",       // The content type used when sending data to the server.
cache: false,             // To unable request pages to be cached
    dataType: 'json',
processData:false,        // To send DOMDocument or non processed data file it is set to false
success: function(data)   // A function to be called if request succeeds
{
    if(data.status=="FAILED")
    {
    $("#error-msg").text(data.errorMessage);
    $("#error-msg").css('display','block');
    $("#success-msg").css('display','none');
    }
    else
    {
    $("#error-msg").css('display','none');
    $("#success-msg").css('display','block');
    }
}
});
}));

selectBreed=function(breed)
{
var breedData = {};
breedData["breed"]=breed;
$.ajax({
url: "http://localhost:8080/getBreed", // Url to which the request is send
type: "POST",             // Type of request to be send, called as method
data: JSON.stringify(breedData), // Data sent to server, a set of key/value pairs (i.e. form fields and values)
contentType: "application/json",       // The content type used when sending data to the server.
cache: false,             // To unable request pages to be cached
dataType: 'json',
processData:false,        // To send DOMDocument or non processed data file it is set to false
success: function(response)   // A function to be called if request succeeds
{
$("#breed").empty();
    var splits = response.breed.split(",");
    $.each(splits, function(key) {
         $('#breed')
             .append($("<option></option>")
                        .attr("value",splits[key])
                        .text(splits[key]));
    });
}
});
};

selectBreed($("#typeOfPet option:selected").text());

$("#typeOfPet").change(function(){
$("#breed").empty();
selectBreed($("#typeOfPet option:selected").text());
});

});
