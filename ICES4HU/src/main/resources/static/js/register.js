
const submitBtn = document.querySelector('button[type="submit"]');
const email = document.querySelector('input[name="email-holder"]');
const fullName = document.querySelector('input[name="name-holder"]');
const studentId = document.querySelector('input[name="student-id-holder"]');
const password = document.querySelector('input[name="password-holder"]');
const confirmPassword = document.querySelector('input[name="confirm-password"]');


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
    } else if (email.value === "") {
        document.querySelector('#email_empty').innerHTML = "Email can't be blank";
        return false;
    } else if (studentId.value === "") {
        document.querySelector('#studentId_empty').innerHTML = "Student Id can't be blank";
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
            email: email.value,
            name: fullName.value,
            password: password.value,
            studentNumber: parseInt(studentId.value),
            faculty:deneme1.value
        };

        console.log(data)
        register(data)
    } else {
        return;
    }
});
/*const getFaculty = () =>{
    fetch('/api/course/faculty/list',{
        method: 'GET',
    })
        .then((response)=>response.json())
        .then((data)=>{
            console.log("faculty list:",data);
        })
}*/

const register = (data) => {
    fetch('/api/auth/register', {
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
                window.location.href = '/'
            }
        })
        .catch((error) => {
            console.error('Error:', error);
        });
};

