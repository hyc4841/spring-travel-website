let passwordValid = false;

document.getElementById('passwordCheck').addEventListener('input', function() {
  let password1 = document.getElementById("password").value;
  let inputpassword = this.value;

  passwordValid = password1 == inputpassword;

  if (passwordValid) {
    this.classList.remove("is-invalid");
    this.classList.add("is-valid")
  }
  else {
    this.classList.remove("is-valid");
    this.classList.add("is-invalid");
  }
});
