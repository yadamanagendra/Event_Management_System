function validateForm() {
    var username = document.getElementById("username").value;
    var email = document.getElementById("email").value;
    var mobile = document.getElementById("mobile").value;
    
    if (username.trim() === "" || email.trim() === "" || mobile.trim() === "") {
        alert("All fields are required!");
        return false;
    }
    if (mobile.length !== 10 || isNaN(mobile)) {
        alert("Enter a valid 10-digit mobile number.");
        return false;
    }
    return true;
}