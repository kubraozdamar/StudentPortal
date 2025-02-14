'use strict';

const submitBtn = document.querySelector('button[type="submit"]');
const name = document.querySelector('input[name="name"]');
const quota = document.querySelector('input[name="quota"]');
const akts = document.querySelector('input[name="akts"]');
const code = document.querySelector('input[name="code"]');

const location_info = document.querySelector('input[name="location_info"]');
const start_time = document.querySelector('input[name="start_time"]');
const end_time = document.querySelector('input[name="end_time"]');


const object = localStorage.getItem("instructorList");
const ret1 = JSON.parse(object);
console.log("deneme123",ret1);
[...ret1].forEach((e)=>{

    $('#instructor').append($('<option>', {
        value: e.id,
        text: e.name
    }));
})
const deneme1 = document.getElementById('instructor');

const validateForm = () => {
    if (name.value === ""||quota.value===""||akts.value===""||code.value===""||instructor.value===""||location_info.value==="") {
        document.querySelector('#error').innerHTML = "Do not leave empty field";
        return false
    } else {
        return true;
    }
};

submitBtn.addEventListener('click', (e) => {
    e.preventDefault();
    if (validateForm()) {
        submitBtn.disabled = true;
        const data = {
            name: name.value,
            quota: parseInt(quota.value),
            akts: parseInt(akts.value),
            nameCode: code.value,
            location: location_info.value,
            instructorId:parseInt(deneme1.value),

        };

        console.log(data)
        addCourse(data)
    } else {
        return;
    }
});

const addCourse = (data) => {
    fetch('/api/course/create', {
        method: 'POST',
        body: JSON.stringify(data),
        headers: {
            'Content-Type': 'application/json',
        },
    })
        .then((response) => response.json())
        .then((data) => {
          window.location.href='/courses'
        })
        .catch((error) => {
            console.error('Error:', error);
        });
};

