// Example starter JavaScript for disabling form submissions if there are invalid fields

(function () {
  'use strict'

  // Fetch all the forms we want to apply custom Bootstrap validation styles to
  var forms = document.querySelectorAll('.needs-validation')

  // Loop over them and prevent submission
  Array.prototype.slice.call(forms)
    .forEach(function (form) {
      form.addEventListener('submit', function (event) {

        var password = document.getElementById('password').value;
        var passwordCheck = document.getElementById('passwordCheck').value;

        if (!form.checkValidity()) {
          event.preventDefault()
          event.stopPropagation()
        }

        if (password != passwordCheck) {
          event.preventDefault()
          event.stopPropagation()
          alert("비밀번호를 확인하세요.");
        }

        if (password.length < 7) {
          event.preventDefault()
          event.stopPropagation()
          alert("비밀번호는 8자 이상으로 해주세요.");
        }

        form.classList.add('was-validated')
      }, false)
    })
})()
