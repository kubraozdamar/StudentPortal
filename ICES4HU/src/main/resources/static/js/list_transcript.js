const session_id=localStorage.getItem("user_id")

const gettranscript = () =>{
    fetch('/api/profile/transcript/'+session_id,{
        method: 'GET',
    })
        .then((response)=>response.json())
        .then((data)=>{
            console.log(('courses list'), data);
            [...data].forEach((e)=>{
                document.querySelector("#course_content").innerHTML +=
                    `
                    <tr>
                        <td>${e.semester}</td>
                        <td>${e.courseCode}</td>
                        <td>${e.courseName}</td>
                        <td>${e.akts}</td>
                        <td>${e.grade}</td>
                    </tr> 
                    `
            })

        }).catch((error)=>{
            console.error('Error:', error)
    })
}
gettranscript()