const session_id=localStorage.getItem("user_id")
const addCourseButton = document.querySelector('button[type="button_add"]');

const object = localStorage.getItem("instructorList");
const ret1 = JSON.parse(object);
console.log("deneme123",ret1);

const deneme1 = document.getElementById('instructor');

//'api/course/list/student/ad/'+session_id
const getCourses = () =>{
    fetch('api/course/list',{
        method: 'GET',
    })
        .then((response)=>response.json())
        .then((data)=>{
            console.log(('courses list'), data);
            [...data].forEach((e)=>{
                [...ret1].forEach((e)=>{
                    $('#instructor').append($('<option>', {
                        value: e.name,
                        text: e.name
                    }));
                })
                document.querySelector("#course_content").innerHTML +=
                    `
                    <tr class="courses" >
                        <td >${e.name}</td>
                        <td>${e.nameCode}</td>
                        <td>${e.quota}</td>
                        <td>${e.akts}</td>
                        <td>${e.instructorId}</td>
                        <td>${e.location}</td>
                        
                        <td>
                          <button type="button" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#exampleModal${e.id}"><i class="fa-solid fa-pen-to-square" ></i></button>
                           <button  filter="approve_reject"  id=${e.id} type="button"   class="btn btn-danger"><i class="fa-solid fa-trash"></i></button>
                       </td>
                    </tr> 

                    <div class="modal modal-dialog-scrollable fade" id="exampleModal${e.id}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel${e.id}">Edit Course</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="form-floating mb-3">
                        <input name="name_"type="name" class="form-control" id="floatingName${e.id}" value="${e.name}"placeholder="Enter new name...">
                        <label for="floatingName">Enter new name...</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="nameCode" class="form-control" id="floatingNameCode${e.id}" value="${e.nameCode}" placeholder="Enter new name code...">
                        <label for="floatingNameCode">Enter new name code...</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="quota" class="form-control" id="floatingQuota${e.id}" value="${e.quota}"placeholder="Enter new quota...">
                        <label for="floatingQuota">Enter new quota...</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="AKTS" class="form-control" id="floatingAKTS${e.id}" value="${e.akts}" placeholder="Enter new AKTS...">
                        <label for="floatingAKTS">Enter new name...</label>
                    </div>
                    <div class="form-floating mb-3">
                        <select class="js-example-basic-single  form-control col-9" id="instructor">
                            
                        </select>
                        <label for="floatingInstructor">Enter new instructor...</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="location" class="form-control" id="floatingLocation${e.id}" value="${e.location}" placeholder="Enter new location...">
                        <label for="floatingLocation">Enter new location...</label>
                    </div>
                  
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button id=${e.id} type="submit" class="btn btn-primary">Save changes</button>
                </div>
            </div>
        </div>
    </div>
                    `
            })
            handleReject()
            handleEdit()
        }).catch((error)=>{
        console.error('Error:', error)
    })
}
getCourses()

addCourseButton.addEventListener('click', (e) => {
    e.preventDefault();
    window.location.href='/add_course'
});

const handleEdit=()=>{
    const submitBtn = document.querySelectorAll('button[type="submit"]');
    submitBtn.forEach(d=>{
        d.addEventListener('click',(e)=>{
            const course_= d.id;

            const data = {
                name:document.getElementById(`floatingName`+course_).value,
                nameCode:document.getElementById(`floatingNameCode`+course_).value,
                quota:document.getElementById(`floatingQuota`+course_).value,
                akts:document.getElementById(`floatingAKTS`+course_).value,
                location:document.getElementById(`floatingLocation`+course_).value

            }
            console.log("denemedatasi",data)
            fetch('/api/course/edit/'+course_,{
                method: 'POST',
                body: JSON.stringify(data),
                headers: {
                    'Content-Type': 'application/json',
                }
            })
                .then((response)=>response.json())
                .then((data)=>{
                        window.location.reload()
                    }
                )
            window.location.reload()
        })
    })
}


const handleReject=()=>{
    const action = document.querySelectorAll('[filter="approve_reject"]')
    action.forEach(d=>{
        d.addEventListener('click',(e)=>{
            const course_ = e.target;
            fetch('/api/course/delete/'+course_.getAttribute('id'),{
                method:"DELETE",

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
            }, 2500);
        })
    })

}

function search_course() {
    let input = document.getElementById('searchbar').value
    input=input.toLowerCase();
    let x = document.getElementsByClassName('courses');

    for (let i = 0; i < x.length; i++) {
        if (!x[i].innerHTML.toLowerCase().includes(input)) {
            x[i].style.display="none";
        }
        else {
            x[i].style.display="";
        }
    }
}



