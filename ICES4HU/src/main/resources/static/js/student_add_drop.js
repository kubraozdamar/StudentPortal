'use strict'
const session_id=localStorage.getItem("user_id")
const getCourses = () =>{
    fetch('api/course/list/student/ad/'+session_id,{
        method: 'GET',
    })
        .then((response)=>response.json())
        .then((data)=>{
            console.log(('courses list'), data);
            [...data].forEach((e)=>{


                document.querySelector("#course_content").innerHTML +=
                    `
                    <tr class="courses" >
                        <td >${e.name}</td>
                        <td>${e.nameCode}</td>
                        <td>${e.quota}</td>
                        <td>${e.akts}</td>
                        <td>${e.instructor}</td>
                        <td>${e.location}</td>
                        <td>${e.start_time}+-+${e.end_time}></td>
                        <td>
                           <button id=${e.id} filter="add_course" type="button" class="btn btn-success"><i class="fa-solid fa-plus" ></i></button>
                       </td>
                    </tr> 
                    `
            })
            handleupdate()
        }).catch((error)=>{
        console.error('Error:', error)
    })
}

getCourses()

const getCoursesPend = () =>{
    fetch('api/course/list/student/adself/'+session_id,{
        method: 'GET',
    })
        .then((response)=>response.json())
        .then((data)=>{
            console.log(('courses list'), data);
            [...data].forEach((e)=>{


                document.querySelector("#course_content_cart").innerHTML +=
                    `
                    <tr class="courses" >
                        <td >${e.name}</td>
                        <td>${e.nameCode}</td>
                        <td>${e.quota}</td>
                        <td>${e.akts}</td>
                        <td>${e.instructor}</td>
                        <td>${e.location}</td>
                        <td>${e.start_time}+-+${e.end_time}></td>
                        <td>
                           <button id=${e.id} filter="drop_course" type="button" class="btn btn-success"><i class="fa-solid fa-minus" ></i></button>
                       </td>
                    </tr> 
                    `
            })
            handleDelete()
        }).catch((error)=>{
        console.error('Error:', error)
    })
}


getCoursesPend()


const handleupdate=()=>{
    const action = document.querySelectorAll('[filter="add_course"]')
    const data="deneme"
    action.forEach(d=>{
        d.addEventListener('click',(e)=>{
            const course_ = d.id;
            fetch('/api/course/adddrop/add/'+session_id+"/"+course_,{
                method:"POST",
                body: JSON.stringify(data),
                headers: {
                    'Content-Type': 'application/json',
                },
            })

                .then((response) => response.json())
                .then((data) => {
                    console.log('Success:', data);

                })
                .catch((error) => {
                    console.error('Error:', error);
                });
            setTimeout(function(){
                window.location.reload();
            }, 1500);
        })
    })

}

const handleDelete=()=>{
    const action = document.querySelectorAll('[filter="drop_course"]')
    const data="deneme"
    action.forEach(d=>{
        d.addEventListener('click',(e)=>{
            const course_ = d.id;
            fetch('/api/course/adddrop/drop/'+session_id+"/"+course_,{
                method:"POST",
                body: JSON.stringify(data),
                headers: {
                    'Content-Type': 'application/json',
                },
            })

                .then((response) => response.json())
                .then((data) => {
                    console.log('Success:', data);

                })
                .catch((error) => {
                    console.error('Error:', error);
                });
            setTimeout(function(){
                window.location.reload();
            }, 1500);
        })
    })

}

const submitBtn = document.getElementById("btn-submit-all")
submitBtn.addEventListener('click', (e) => {
    e.preventDefault();
        submitBtn.disabled = true;
        fetch('/api/course/adddrop/accept/'+session_id,{
            method:"POST",
            headers: {
                'Content-Type': 'application/json',
            },
        })

            .then((response) => response.json())
            .then((data) => {
                console.log('Success:', data);

            })
            .catch((error) => {
                console.error('Error:', error);
            });
        setTimeout(function(){
            window.location.reload();
        }, 1500);

});




$(document).ready(function () {
    $('#dtDynamicVerticalScrollExample').DataTable({
        "scrollY": "20vh",
        "scrollCollapse": true,
        "searching" : false,
        "autoWidth": true,
        "ordering": false,
        "paging": false,
        "bInfo" : false
    });
    $('.dataTables_length').addClass('bs-select');
});

