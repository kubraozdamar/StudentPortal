
const session_id=localStorage.getItem("user_id")
const getCourses = () =>{
    fetch('api/course/list/'+session_id,{
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
                        <td>${e.instructor}</td>
                        <td>${e.location}</td>
                        <td>${e.start_time}+-+${e.end_time}></td>
                        <td>
                            <button type="button" class="btn btn-success"  data-bs-toggle="modal" data-bs-target="#exampleModal"><i class="fa-solid fa-paper-plane"></i></button>
                       </td>
                    </tr> 
                    

                    <div class="modal modal-dialog-scrollable fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-scrollable modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">Create Survey XXX</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item">
                            <h3>Question 1</h3>
                            <p class="fs-5" id="question1">•    trial</p>
                            <input type="radio" class="btn-check" name="options1" id="option11" autocomplete="off">
                            <label class="btn btn-outline-secondary" for="option11">1</label>
                            <input type="radio" class="btn-check" name="options1" id="option12" autocomplete="off">
                            <label class="btn btn-outline-secondary" for="option12">2</label>
                            <input type="radio" class="btn-check" name="options1" id="option13" autocomplete="off">
                            <label class="btn btn-outline-secondary" for="option13">3</label>
                            <input type="radio" class="btn-check" name="options1" id="option14" autocomplete="off">
                            <label class="btn btn-outline-secondary" for="option14">4</label>
                            <input type="radio" class="btn-check" name="options1" id="option15" autocomplete="off">
                            <label class="btn btn-outline-secondary" for="option15">5</label>
                        </li>
                        <li class="list-group-item">
                            <h3>Question 2</h3>
                            <p class="fs-5" id="question2">•    trial</p>
                            <input type="radio" class="btn-check" name="options2" id="option21" autocomplete="off">
                            <label class="btn btn-outline-secondary" for="option21">1</label>
                            <input type="radio" class="btn-check" name="options2" id="option22" autocomplete="off">
                            <label class="btn btn-outline-secondary" for="option22">2</label>
                            <input type="radio" class="btn-check" name="options2" id="option23" autocomplete="off">
                            <label class="btn btn-outline-secondary" for="option23">3</label>
                            <input type="radio" class="btn-check" name="options2" id="option24" autocomplete="off">
                            <label class="btn btn-outline-secondary" for="option24">4</label>
                            <input type="radio" class="btn-check" name="options2" id="option25" autocomplete="off">
                            <label class="btn btn-outline-secondary" for="option25">5</label>
                        </li>
                        <li class="list-group-item">
                            <h3>Question 3</h3>
                            <p class="fs-5" id="question3">•    trial</p>
                            <input type="radio" class="btn-check" name="options3" id="option31" autocomplete="off">
                            <label class="btn btn-outline-secondary" for="option31">1</label>
                            <input type="radio" class="btn-check" name="options3" id="option32" autocomplete="off">
                            <label class="btn btn-outline-secondary" for="option32">2</label>
                            <input type="radio" class="btn-check" name="options3" id="option33" autocomplete="off">
                            <label class="btn btn-outline-secondary" for="option33">3</label>
                            <input type="radio" class="btn-check" name="options3" id="option34" autocomplete="off">
                            <label class="btn btn-outline-secondary" for="option34">4</label>
                            <input type="radio" class="btn-check" name="options3" id="option35" autocomplete="off">
                            <label class="btn btn-outline-secondary" for="option35">5</label>
                        </li>
                        <li class="list-group-item">
                            <h3>Question 4</h3>
                            <p class="fs-5" id="question4">•    trial</p>
                            <input type="radio" class="btn-check" name="options" id="option41" autocomplete="off">
                            <label class="btn btn-outline-secondary" for="option41">1</label>
                            <input type="radio" class="btn-check" name="options" id="option42" autocomplete="off">
                            <label class="btn btn-outline-secondary" for="option42">2</label>
                            <input type="radio" class="btn-check" name="options" id="option43" autocomplete="off">
                            <label class="btn btn-outline-secondary" for="option43">3</label>
                            <input type="radio" class="btn-check" name="options" id="option44" autocomplete="off">
                            <label class="btn btn-outline-secondary" for="option44">4</label>
                            <input type="radio" class="btn-check" name="options" id="option45" autocomplete="off">
                            <label class="btn btn-outline-secondary" for="option45">5</label>
                        </li>
                        <li class="list-group-item">
                            <h3>Question 5</h3>
                            <p class="fs-5" id="question5">•    trial</p>
                            <input type="radio" class="btn-check" name="options" id="option51" autocomplete="off">
                            <label class="btn btn-outline-secondary" for="option51">1</label>
                            <input type="radio" class="btn-check" name="options" id="option52" autocomplete="off">
                            <label class="btn btn-outline-secondary" for="option52">2</label>
                            <input type="radio" class="btn-check" name="options" id="option53" autocomplete="off">
                            <label class="btn btn-outline-secondary" for="option53">3</label>
                            <input type="radio" class="btn-check" name="options" id="option54" autocomplete="off">
                            <label class="btn btn-outline-secondary" for="option54">4</label>
                            <input type="radio" class="btn-check" name="options" id="option55" autocomplete="off">
                            <label class="btn btn-outline-secondary" for="option55">5</label>
                        </li>
                        <li class="list-group-item">
                            <h3>Question 6</h3>
                            <p class="fs-5" id="question6">•    trial</p>
                            <input type="radio" class="btn-check" name="options" id="option61" autocomplete="off">
                            <label class="btn btn-outline-secondary" for="option61">1</label>
                            <input type="radio" class="btn-check" name="options" id="option62" autocomplete="off">
                            <label class="btn btn-outline-secondary" for="option62">2</label>
                            <input type="radio" class="btn-check" name="options" id="option63" autocomplete="off">
                            <label class="btn btn-outline-secondary" for="option63">3</label>
                            <input type="radio" class="btn-check" name="options" id="option64" autocomplete="off">
                            <label class="btn btn-outline-secondary" for="option64">4</label>
                            <input type="radio" class="btn-check" name="options" id="option65" autocomplete="off">
                            <label class="btn btn-outline-secondary" for="option65">5</label>
                        </li>
                        <li class="list-group-item">
                            <h3>Question 7</h3>
                            <p class="fs-5" id="question7">•    trial</p>
                            <input type="radio" class="btn-check" name="options" id="option71" autocomplete="off">
                            <label class="btn btn-outline-secondary" for="option71">1</label>
                            <input type="radio" class="btn-check" name="options" id="option72" autocomplete="off">
                            <label class="btn btn-outline-secondary" for="option72">2</label>
                            <input type="radio" class="btn-check" name="options" id="option73" autocomplete="off">
                            <label class="btn btn-outline-secondary" for="option73">3</label>
                            <input type="radio" class="btn-check" name="options" id="option74" autocomplete="off">
                            <label class="btn btn-outline-secondary" for="option74">4</label>
                            <input type="radio" class="btn-check" name="options" id="option75" autocomplete="off">
                            <label class="btn btn-outline-secondary" for="option75">5</label>
                        </li>
                        <li class="list-group-item">
                            <h3>Question 8</h3>
                            <p class="fs-5" id="question8">•    trial</p>
                            <input type="radio" class="btn-check" name="options" id="option81" autocomplete="off">
                            <label class="btn btn-outline-secondary" for="option81">1</label>
                            <input type="radio" class="btn-check" name="options" id="option82" autocomplete="off">
                            <label class="btn btn-outline-secondary" for="option82">2</label>
                            <input type="radio" class="btn-check" name="options" id="option83" autocomplete="off">
                            <label class="btn btn-outline-secondary" for="option83">3</label>
                            <input type="radio" class="btn-check" name="options" id="option84" autocomplete="off">
                            <label class="btn btn-outline-secondary" for="option84">4</label>
                            <input type="radio" class="btn-check" name="options" id="option85" autocomplete="off">
                            <label class="btn btn-outline-secondary" for="option85">5</label>
                        </li>
                        <li class="list-group-item">
                            <h3>Question 9</h3>
                            <p class="fs-5" id="question9">•    trial</p>
                            <input type="radio" class="btn-check" name="options" id="option91" autocomplete="off">
                            <label class="btn btn-outline-secondary" for="option91">1</label>
                            <input type="radio" class="btn-check" name="options" id="option92" autocomplete="off">
                            <label class="btn btn-outline-secondary" for="option92">2</label>
                            <input type="radio" class="btn-check" name="options" id="option93" autocomplete="off">
                            <label class="btn btn-outline-secondary" for="option93">3</label>
                            <input type="radio" class="btn-check" name="options" id="option94" autocomplete="off">
                            <label class="btn btn-outline-secondary" for="option94">4</label>
                            <input type="radio" class="btn-check" name="options" id="option95" autocomplete="off">
                            <label class="btn btn-outline-secondary" for="option95">5</label>
                        </li>
                        <li class="list-group-item">
                            <h3>Question 10</h3>
                            <p class="fs-5" id="question10">•    trial</p>
                            <input type="radio" class="btn-check" name="options" id="option101" autocomplete="off">
                            <label class="btn btn-outline-secondary" for="option101">1</label>
                            <input type="radio" class="btn-check" name="options" id="option102" autocomplete="off">
                            <label class="btn btn-outline-secondary" for="option102">2</label>
                            <input type="radio" class="btn-check" name="options" id="option103" autocomplete="off">
                            <label class="btn btn-outline-secondary" for="option103">3</label>
                            <input type="radio" class="btn-check" name="options" id="option104" autocomplete="off">
                            <label class="btn btn-outline-secondary" for="option104">4</label>
                            <input type="radio" class="btn-check" name="options" id="option105" autocomplete="off">
                            <label class="btn btn-outline-secondary" for="option105">5</label>
                        </li>
                    </ul>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary">Save changes</button>
                </div>
            </div>
        </div>
    </div>
</div>
                    `
            })
            //handleReject()
        }).catch((error)=>{
        console.error('Error:', error)
    })
}
getCourses()


const showSurvey = () =>{
    fetch('/api/survey/show/16',{
        method: 'GET',
    })
        .then((response)=>response.json())
        .then((data)=>{
                console.log("survey list:",data);
                //const instructorListString = JSON.stringify(data);
                //localStorage.setItem("instructorList",instructorListString )
            }
        )


}
showSurvey()