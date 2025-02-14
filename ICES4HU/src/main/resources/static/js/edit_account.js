'use strict';
const session_id=localStorage.getItem("user_id")
console.log("session",session_id)
const submitBtn = document.querySelector('button[type="submit"]');
const fullName = document.querySelector('input[name="name-holder"]');
const password = document.querySelector('input[name="password-holder"]');
const confirmPassword = document.querySelector('input[name="confirm-password"]');
const email =  document.querySelector('input[name="email-holder"]');


const object = localStorage.getItem("facultyList");
const ret1 = JSON.parse(object);
console.log("deneme123",ret1);
[...ret1].forEach((e)=>{

    $('#faculty').append($('<option>', {
        value: e.name,
        text: e.name
    }));
})

const deneme1 = document.getElementById('faculty');
const validateForm = () => {
    if (fullName.value === "") {
        document.querySelector('#name_empty').innerHTML = "Name can't be blank";
        return false;

    }else if (email.value === "") {
        document.querySelector('#password_empty').innerHTML = "Email can't be blank";
        return false;
    } else if (password.value === "") {
        document.querySelector('#password_empty').innerHTML = "Password can't be blank";
        return false;
    } else if (confirmPassword.value === "") {
        document.querySelector('#confirm_empty').innerHTML = "Confirm password can't be blank";
        return false;
    } else if (password.value !== confirmPassword.value) {
        document.querySelector('#match_empty').innerHTML = "Passwords do not match";
        return false;
    } else {
        return true;
    }
};

submitBtn.addEventListener('click', (e) => {
    e.preventDefault();
    if (validateForm()) {
        submitBtn.disabled = true;
        const data = {
            name: fullName.value,
            password: password.value,
            email: email.value,
            faculty:deneme1.value
        };

        console.log(data)
        update(data)
    } else {
        return;
    }
});

const update = (data) => {
    fetch('/api/profile/edit/'+session_id, {
        method: 'POST',
        body: JSON.stringify(data),
        headers: {
            'Content-Type': 'application/json',
        },
    })
        .then((response) => response.json())
        .then((data) => {
            console.log('Success:', data);
            if (data.message === "Student with this e-mail address already exists." ) {
                submitBtn.disabled = false;
                document.querySelector('#error').innerHTML = "This email already taken.Please choose another email";
            } else {
                window.location.href = '/main_page'
            }
        })
        .catch((error) => {
            console.error('Error:', error);
        })
};

