let container = document.getElementById('container')

toggle = () => {
  container.classList.toggle('sign-in')
  container.classList.toggle('sign-up')
}

setTimeout(() => {
  container.classList.add('sign-in')
}, 200)

const loginBtn = document.getElementById('loginBtn');

loginBtn.onclick = async function() {
  const loginId = document.getElementById('loginId');
  const password = document.getElementById('loginPassword');

  const loginForm = {
    loginId: loginId.value,
    password: password.value
  };

  const res = await fetch('/login', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(loginForm)
  });

  if (res.ok) {
    const result = await res.json();
    console.log('Login data submitted successfully: ', result);
  }
  else {
    console.error('Data submission failed');
  }
}
  
signupBtn.onclick = async function() {
  const signupId = document.getElementById('signupId');
  const name = document.getElementById('name');
  const email = document.getElementById('email');
  const password = document.getElementById('password');


  const member = {
    loginId: signupId.value,
    name: name.value,
    email: email.value,
    password: password.value
  };

  const res = await fetch('/signup/add', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(member)
  });

  if (res.ok) {
    const result = await res.json();
    console.log('Signup data submitted successfully: ', result);
  }
  else {
    console.error('Data submission failed');
  }
}