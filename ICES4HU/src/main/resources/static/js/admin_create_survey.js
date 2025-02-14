'use strict'
const session_id=localStorage.getItem("user_id")

const getCourses = () =>{
    fetch('api/course/list/instructor/'+session_id,{
        method: 'GET',
    })
        .then((response)=>response.json())
        .then((data)=>{
                console.log("course list:",data);
            [...data].forEach((e)=>{

            document.querySelector("#course_content").innerHTML +=
                `
                    <tr class="courses" >
                        <td >${e.name}</td>
                        <td>${e.nameCode}</td>
                        <td>${e.quota}</td>
                        
                        <td>${e.instructor}</td>
                        <td>${e.location}</td>
                        <td>${e.start_time}+-+${e.end_time}></td>
                      
                        <td>
                          <button id=${e.id} type="button" class="btn btn-success"  data-bs-toggle="modal" data-bs-target="#exampleModal${e.id}"><i class="fa-solid fa-plus"></i></button>
                                       
                     </tr>
                      <div class="modal modal-dialog-scrollable fade" id="exampleModal${e.id}" tabindex="-1" aria-labelledby="exampleModalLabel${e.id}" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel${e.id}">Create Survey XXX</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="form-floating">
                        <textarea class="form-control" placeholder="Type question here..." id="floatingTextarea1${e.id}"></textarea>
                        <label for="floatingTextarea1">Question 1</label>
                    </div>
                    <div class="form-floating">
                        <textarea class="form-control" placeholder="Type question here..." id="floatingTextarea2${e.id}"></textarea>
                        <label for="floatingTextarea2">Question 2</label>
                    </div>
                    <div class="form-floating">
                        <textarea class="form-control" placeholder="Type question here..." id="floatingTextarea3${e.id}"></textarea>
                        <label for="floatingTextarea3">Question 3</label>
                    </div>
                    <div class="form-floating">
                        <textarea class="form-control" placeholder="Type question here..." id="floatingTextarea4${e.id}"></textarea>
                        <label for="floatingTextarea4">Question 4</label>
                    </div>
                    <div class="form-floating">
                        <textarea class="form-control" placeholder="Type question here..." id="floatingTextarea5${e.id}"></textarea>
                        <label for="floatingTextarea5">Question 5</label>
                    </div>
                    <div class="form-floating">
                        <textarea class="form-control" placeholder="Type question here..." id="floatingTextarea6${e.id}"></textarea>
                        <label for="floatingTextarea6">Question 6</label>
                    </div>
                    <div class="form-floating">
                        <textarea class="form-control" placeholder="Type question here..." id="floatingTextarea7${e.id}"></textarea>
                        <label for="floatingTextarea7">Question 7</label>
                    </div>
                    <div class="form-floating">
                        <textarea class="form-control" placeholder="Type question here..." id="floatingTextarea8${e.id}"></textarea>
                        <label for="floatingTextarea8">Question 8</label>
                    </div>
                    <div class="form-floating">
                        <textarea class="form-control" placeholder="Type question here..." id="floatingTextarea9${e.id}"></textarea>
                        <label for="floatingTextarea9">Question 9</label>
                    </div>
                    <div class="form-floating">
                        <textarea class="form-control" placeholder="Type question here..." id="floatingTextarea10${e.id}"></textarea>
                        <label for="floatingTextarea10">Question 10</label>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button id=${e.id} type="submit" class="btn btn-primary">Save changes</button>
                </div>
            </div>
        </div>
    </div>
</div>`
                    if(e.hasSurvey == 1){
                        document.getElementById(`${e.id}`).classList.remove('btn-success')
                        document.getElementById(`${e.id}`).classList.add('btn-danger')
                        document.getElementById(`${e.id}`).disabled= true;
                    }

            }
        )
            save()

}).catch((error) => {
        console.error('Error:', error);
    })
}
getCourses()

const save=()=>{
    const submitBtn = document.querySelectorAll('button[type="submit"]');
    submitBtn.forEach(d=>{
        d.addEventListener('click',(e)=>{
            const course_= d.id;

            const questions = [document.getElementById(`floatingTextarea1`+course_).value,document.getElementById(`floatingTextarea2`+course_).value,document.getElementById(`floatingTextarea3`+course_).value,document.getElementById(`floatingTextarea4`+course_).value,document.getElementById(`floatingTextarea5`+course_).value,document.getElementById(`floatingTextarea6`+course_).value,document.getElementById(`floatingTextarea7`+course_).value,document.getElementById(`floatingTextarea8`+course_).value,document.getElementById(`floatingTextarea9`+course_).value,document.getElementById(`floatingTextarea10`+course_).value]
            const data = {
                courseId:course_,
                userId:session_id,
                questions:questions
            }
            console.log("denemedatasi",data)
            fetch('/api/survey/create',{
                method: 'POST',
                body: JSON.stringify(data),
                headers: {
                    'Content-Type': 'application/json',
                }
            })
                .then((response)=>response.json())
                .then((data)=>{
                    //window.location.reload()
                    }
                )
        })
    })
}











