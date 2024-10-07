const checkOut = document.getElementById('checkOutBtn');

document.getElementById('passwordCheck').addEventListener('input', function() {
  let password = document.getElementById("password").value;
  let checkPassword = this.value;

  if (password == checkPassword) { // 두 비번이 같으면 is valid, 다르면 is invalid
    this.classList.remove("is-invalid");
    this.classList.add("is-valid")
  }
  else {
    this.classList.remove("is-valid");
    this.classList.add("is-invalid");
  }
});
