const submitBtn = document.getElementById("btn-login")

const email = document.querySelector('input[type="email"]');
const password = document.querySelector('input[type="password"]');

const validateForm = () => {
    if (email.value === "" || password.value === "") {
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
            password: password.value
        };
        console.log(data)
        login(data)
    } else {
        return;
    }
});
//qwe4
const login = (data) => {
    fetch('/api/auth/signin', {
        method: 'POST',
        body: JSON.stringify(data),
        headers: {
            'Content-Type': 'application/json',
        },
    })
        .then((response) => response.json())
        .then((data) => {
            console.log('Success:', data);
            if ((data.message === "User with this e-mail cannot be found.")||(data.message==="Password is not correct.")) {
                document.querySelector("#error").innerHTML = "Username or password is incorrect";
                submitBtn.disabled = false;
                //window.location.href='/'
            } else {
                localStorage.setItem("user_id",data.userId)
                if(data.roleId===1){
                    window.location.href='/main_page_admin'
                }else if(data.roleId===2){
                    window.location.href='/main_page'
                }
                else if(data.roleId===3){
                    window.location.href='/main_page_instructor'
                }else{
                    window.location.reload()
                }



            }
        })
        .catch((error) => {
            console.error('Error:', error);
        });
};