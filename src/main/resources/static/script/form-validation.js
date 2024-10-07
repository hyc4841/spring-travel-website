// Example starter JavaScript for disabling form submissions if there are invalid fields


(function () {
  'use strict'

  // Fetch all the forms we want to apply custom Bootstrap validation styles to
  var forms = document.querySelectorAll('.needs-validation')

  // Loop over them and prevent submission
  Array.prototype.slice.call(forms)
    .forEach(function (form) {
      form.addEventListener('submit', function (event) {
        if (!form.checkValidity()) {
          event.preventDefault()
          event.stopPropagation()
        }

        form.classList.add('was-validated')
      }, false)
    })
})()
    
/*
var passwordCheck = document.getElementById('passwordcheckBtn');

passwordCheck.addEventListener('click', function() {

  let password1 = document.getElementById('password').value;
  let password2 = document.getElementById('passwordcheck').value;


  if (password1 == password2) {
    password2.classList.remove('is-invalid');
    password2.classList.add('is-valid');
  }
  else {
    password2.classList.remove('is-valid');
    password2.classList.add('is-invalid');
  }
});
*/

